package com.backend.service.Impl;

import com.backend.entity.Application;
import com.backend.entity.Post;
import com.backend.entity.User;
import com.backend.repository.ApplicationRepository;
import com.backend.repository.PostRepository;
import com.backend.service.ApplicationService;
import com.backend.service.MailService;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Service;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final MailService mailService;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, PostRepository postRepository, MailService mailService) {
        this.applicationRepository = applicationRepository;
        this.mailService = mailService;
    }

    @Override
    public List<Application> findByPostId(int id) {
        return applicationRepository.findByPostId(id) ;
    }

    @Override
    public void save(Application application) {
//        if(application.getResume() != null) {
//            String resumePath = createStyledResume(application.getFullName(), application.getEmail(), application.getPhoneNumber(), application.getJobTitle(), application.getLinkedInUrl(),  application.getAdditionalInformation());
//            application.setResume(resumePath.getBytes());
//        }
        applicationRepository.save(application);

        String toCompany = null;
        try {
            toCompany = application.getPost().getCompany().getUser().getEmail();
        } catch (Exception e) {
        }
        String subjectToCompany = "New Application Received";
        String textToCompany = "A new application has been received from " + application.getUser().getEmail();
        mailService.sendSimpleMessage(toCompany, subjectToCompany, textToCompany);
        String toUser = application.getUser().getEmail() ;
        String subjectToUser = "Application Sent";
        String textToUser = "You have successfully applied to " + application.getPost().getTitle();
        mailService.sendSimpleMessage(toUser, subjectToUser, textToUser);

    }

    @Override
    public Application findById(int id) {
        return applicationRepository.findById(id);
    }

    @Override
    public List<Application> findAllByUser(User user) {
        return applicationRepository.findAllByUser(user);
    }

    @Override
    public List<Application> findAllByPostCompany(int companyId) {
        return applicationRepository.findAllByPostCompany(companyId);
    }
    @Override
    public String createStyledResume(String fullName, String emailAddress, String phoneNumber, String jobTitle, String additionalInformation) {
        String filePath = "resume.pdf";
        try {
            // Create a new PDF document
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(filePath));
            Document doc = new Document(pdfDoc);

            // Add a title
            Paragraph title = new Paragraph("Resume")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(20);
            doc.add(title);

            // Add the user's information
            doc.add(new Paragraph("Full Name: " + fullName));
            doc.add(new Paragraph("Email Address: " + emailAddress));
            doc.add(new Paragraph("Phone Number: " + phoneNumber));
            doc.add(new Paragraph("Job Title: " + jobTitle));
            doc.add(new Paragraph("Additional Information: " + additionalInformation));

            doc.close();
        } catch (Exception e) {
        }
        return filePath;
    }
}
