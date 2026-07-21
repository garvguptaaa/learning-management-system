package com.elearning.certificate.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elearning.certificate.dto.response.GenerateCertificateResponse;
import com.elearning.certificate.entity.Certificate;
import com.elearning.certificate.repository.CertificateRepository;
import com.elearning.certificate.service.CertificateService;
import com.elearning.certificate.service.PdfCertificateService;
import com.elearning.common.exception.BadRequestException;
import com.elearning.common.exception.ResourceNotFoundException;
import com.elearning.course.entity.Course;
import com.elearning.course.repository.CourseRepository;
import com.elearning.enrollment.repository.EnrollmentRepository;
import com.elearning.lesson.repository.LessonRepository;
import com.elearning.progress.repository.LessonProgressRepository;
import com.elearning.user.entity.User;
import com.elearning.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
	
	private final CertificateRepository certificateRepository;
	private final UserRepository userRepository;
	private final CourseRepository courseRepository;
	private final EnrollmentRepository enrollmentRepository;
	private final LessonRepository lessonRepository;
	private final LessonProgressRepository lessonProgressRepository;
	private final PdfCertificateService pdfCertificateService;

	@Override
	@Transactional
	public GenerateCertificateResponse generateCertificate(
	        Long courseId,
	        String studentEmail) {

	    User student = userRepository.findByEmail(studentEmail)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Student not found"));

	    Course course = courseRepository.findById(courseId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Course not found"));

	    enrollmentRepository.findByStudentAndCourse(student, course)
	            .orElseThrow(() ->
	                    new BadRequestException("Student is not enrolled in this course"));

	    certificateRepository.findByStudentAndCourse(student, course)
	            .ifPresent(c ->
	            {
	                throw new BadRequestException(
	                        "Certificate already generated");
	            });

	    long totalLessons =
	            lessonRepository.countByModuleCourse(course);

	    long completedLessons =
	            lessonProgressRepository
	                    .countByStudentAndLessonModuleCourseAndCompletedTrue(
	                            student,
	                            course);

	    if (completedLessons != totalLessons) {
	        throw new BadRequestException(
	                "Complete the course before generating certificate");
	    }

	    Certificate certificate = Certificate.builder()
	            .student(student)
	            .course(course)
	            .certificateNumber(generateCertificateNumber())
	            .issuedAt(LocalDateTime.now())
	            .build();

	    certificateRepository.save(certificate);

	    return GenerateCertificateResponse.builder()
	            .certificateId(certificate.getId())
	            .certificateNumber(certificate.getCertificateNumber())
	            .studentName(student.getFirstName() + " " + student.getLastName())
	            .courseTitle(course.getTitle())
	            .message("Certificate generated successfully")
	            .build();
	}
	
	
	private String generateCertificateNumber() {

	    return "LMS-"
	            + LocalDate.now().getYear()
	            + "-"
	            + UUID.randomUUID()
	                    .toString()
	                    .substring(0, 8)
	                    .toUpperCase();
	}
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<byte[]> downloadCertificate(
	        Long certificateId,
	        String studentEmail) {

	    User student = userRepository.findByEmail(studentEmail)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Student not found"));

	    Certificate certificate =
	            certificateRepository.findById(certificateId)
	                    .orElseThrow(() ->
	                            new ResourceNotFoundException(
	                                    "Certificate not found"));

	    if (!certificate.getStudent().getId()
	            .equals(student.getId())) {

	        throw new BadRequestException(
	                "Access denied");
	    }

	    byte[] pdf =
	            pdfCertificateService
	                    .generateCertificatePdf(certificate);

	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION,
	                    "attachment; filename=certificate.pdf")
	            .contentType(MediaType.APPLICATION_PDF)
	            .body(pdf);
	}
}