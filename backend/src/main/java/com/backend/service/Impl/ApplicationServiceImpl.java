package com.backend.service.Impl;

import com.backend.entity.Application;
import com.backend.entity.Level;
import com.backend.entity.Post;
import com.backend.entity.User;
import com.backend.repository.ApplicationRepository;
import com.backend.repository.LevelRepository;
import com.backend.repository.PostRepository;
import com.backend.service.ApplicationService;
import com.backend.service.MailService;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Service;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final MailService mailService;
    private final LevelRepository levelRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, PostRepository postRepository, MailService mailService, LevelRepository levelRepository, LevelRepository levelRepository1) {
        this.applicationRepository = applicationRepository;
        this.mailService = mailService;
        this.levelRepository = levelRepository1;
    }

    @Override
    public List<Application> findByPostId(int id) {
        return applicationRepository.findByPostId(id) ;
    }

    @Override
    public void save(Application application) {
        Application existingApplication = applicationRepository.findById(application.getId());

        if (existingApplication == null) {
            String toCompany = null;
            try {
                toCompany = application.getPost().getCompany().getUser().getEmail();
            } catch (Exception e) {
            }
            String subjectToCompany = "New Application Received";
            String textToCompany = "A new application has been received from " + application.getUser().getEmail();
            mailService.sendSimpleMessage(toCompany, subjectToCompany, textToCompany);
            String toUser = application.getUser().getEmail();
            String subjectToUser = "Application Sent";
            String textToUser = "You have successfully applied to " + application.getPost().getTitle();
            mailService.sendSimpleMessage(toUser, subjectToUser, textToUser);
        }

        applicationRepository.save(application);
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

    @Override
    public int countApplicationsByStatusAndCompanyAndTimePeriod(String status, int companyId, LocalDateTime startDate, LocalDateTime endDate) {
        return applicationRepository.countByStatusAndCompanyAndTimePeriod(status, companyId, startDate, endDate);
    }
    @Override
    public Map<String, Integer> getStatisticsByCompanyAndTimePeriod(int companyId, LocalDateTime startDate, LocalDateTime endDate) {
        Map<String, Integer> statistics = new HashMap<>();
        statistics.put("accepted", countApplicationsByStatusAndCompanyAndTimePeriod("Accepted", companyId, startDate, endDate));
        statistics.put("interview", countApplicationsByStatusAndCompanyAndTimePeriod("Interview", companyId, startDate, endDate));
        statistics.put("total",countApplicationsByCompanyAndTimePeriod (companyId, startDate, endDate));
        return statistics;
    }
    @Override
    public int countApplicationsByCompanyAndTimePeriod(int companyId, LocalDateTime startDate, LocalDateTime endDate) {
        return applicationRepository.countByCompanyAndTimePeriod(companyId, startDate, endDate);
    }
    @Override
    public Map<String, Integer> getApplicationCountsByLevelAndCompany(int companyId) {
        List<Level> levels = levelRepository.getAllLevel();
        Map<String, Integer> applicationCountsByLevel = new HashMap<>();
        for (Level level : levels) {
            int count = applicationRepository.countByCompanyAndPostLevel(level.getName(), companyId);
            applicationCountsByLevel.put(level.getName(), count);
        }
        return applicationCountsByLevel;
    }

    @Override
    public void updateStatus(int id, String status) {
        Application application = applicationRepository.findById(id);
        application.setStatus(status);

        applicationRepository.save(application);
        String imagePath = "/static/images/footer.png";

        String toUser = application.getUser().getEmail();
        String subject = "";
        String text = "";
        String footer = "<br/><br/>---<br/>" + "This is a system-generated email, please do not reply directly. " +
                "For any inquiries, please contact us at jobhuntly@gmail.com.";
        switch (status) {
            case "Accepted":
                subject = "<strong> Your Application for " + application.getPost().getTitle() + " – Congratulations! </strong>";
                text = "Dear <strong> " + application.getUser().getUserDisplayName() + " </strong> ,<br/><br/>" +
                        "We are pleased to inform you that your application for the position of " + application.getPost().getTitle() + " at " + application.getPost().getCompany().getName() + " has been accepted. Your skills and experience align well with our requirements, and we are excited about the prospect of you joining our team.<br/><br/>" +
                        "Please be advised that your official offer letter, including details regarding compensation, benefits, and start date, will be sent to you shortly. In the meantime, should you have any questions, feel free to reach out to us.<br/><br/>" +
                        "Thank you for your interest in  <strong>" + application.getPost().getCompany().getName() + "</strong>, and we look forward to welcoming you on board.<br/><br/>" +
                        "Sincerely,<br/>" +
                        "<strong> The " + application.getPost().getCompany().getName() + " Team </strong> ";
                text += footer;
                break;
            case "Unsuitable":
                subject = "<strong> Your Application for  " + application.getPost().getTitle() + " – We regret to inform you </strong>" ;
                text = "Dear " + application.getUser().getUserDisplayName() + ",<br/><br/>" +
                        " Thank you for your interest in the position of <strong>" + application.getPost().getTitle() + "</strong at <strong>" + application.getPost().getCompany().getName() + ".</strong> We appreciate you taking the time to apply and share your qualifications with us.<br/><br/>" +
                        "After careful consideration of your application and the numerous qualified candidates we received, we regret to inform you that we have decided to pursue other applicants whose skills and experience more closely align with the requirements of this position.<br/><br/>" +
                        "We wish you the best of luck in your job search and future endeavors.<br/><br/>" +
                        "Sincerely,<br/>" +
                        "<strong> The " + application.getPost().getCompany().getName() + " Team </strong>";
                text += footer;
                break;
            case "Interview":
                subject = "<strong> Invitation to Interview for " + application.getPost().getTitle() + " </strong>";
                text = "Dear <strong> " + application.getUser().getUserDisplayName() + "</strong> ,<br/><br/>" +
                        "Thank you for applying for the position of <strong>" + application.getPost().getTitle() + "</strong>  at <strong> " + application.getPost().getCompany().getName() + "</strong>. We have reviewed your application materials and are impressed with your qualifications.<br/><br/>" +
                        "We would like to invite you to an interview to further discuss your candidacy and learn more about your experience. The interview is scheduled to start at <strong> " + application.getInterviewStartTime() + ".</strong> Please contact us to confirm this time or to reschedule if necessary.<br/><br/>" +
                        "We look forward to the opportunity to speak with you soon.<br/><br/>" +
                        "Sincerely,<br/>" +
                        "<strong> The " + application.getPost().getCompany().getName() + " Team </strong>";
                text += footer;
                break;
        }
        mailService.sendSimpleMessage(toUser, subject, text, imagePath);
    }
}
