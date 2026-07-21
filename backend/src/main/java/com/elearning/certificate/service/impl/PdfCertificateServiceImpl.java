package com.elearning.certificate.service.impl;


import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

import com.elearning.certificate.entity.Certificate;
import com.elearning.certificate.service.PdfCertificateService;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfCertificateServiceImpl
        implements PdfCertificateService {

	@Override
	public byte[] generateCertificatePdf(Certificate certificate) {

	    try {

	        ByteArrayOutputStream out = new ByteArrayOutputStream();

	        Document document = new Document(PageSize.A4);

	        PdfWriter.getInstance(document, out);

	        document.open();

	        Font titleFont =
	                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 26);

	        Font normalFont =
	                FontFactory.getFont(FontFactory.HELVETICA, 16);

	        Font boldFont =
	                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);

	        Paragraph title =
	                new Paragraph("CERTIFICATE OF COMPLETION", titleFont);

	        title.setAlignment(Element.ALIGN_CENTER);

	        document.add(title);

	        document.add(new Paragraph(" "));

	        Paragraph text =
	                new Paragraph(
	                        "This certifies that",
	                        normalFont);

	        text.setAlignment(Element.ALIGN_CENTER);

	        document.add(text);

	        document.add(new Paragraph(" "));

	        Paragraph student =
	                new Paragraph(
	                        certificate.getStudent().getFirstName()
	                        + " "
	                        + certificate.getStudent().getLastName(),
	                        boldFont);

	        student.setAlignment(Element.ALIGN_CENTER);

	        document.add(student);

	        document.add(new Paragraph(" "));

	        Paragraph completed =
	                new Paragraph(
	                        "has successfully completed",
	                        normalFont);

	        completed.setAlignment(Element.ALIGN_CENTER);

	        document.add(completed);

	        document.add(new Paragraph(" "));

	        Paragraph course =
	                new Paragraph(
	                        certificate.getCourse().getTitle(),
	                        boldFont);

	        course.setAlignment(Element.ALIGN_CENTER);

	        document.add(course);

	        document.add(new Paragraph(" "));

	        document.add(
	                new Paragraph(
	                        "Certificate Number : "
	                                + certificate.getCertificateNumber()));

	        document.add(
	                new Paragraph(
	                        "Issued On : "
	                                + certificate.getIssuedAt()));

	        document.close();

	        return out.toByteArray();

	    } catch (Exception ex) {

	        throw new RuntimeException(ex);

	    }

	}
    
   
}
