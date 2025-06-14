package ru.nsu.peretyatko.service.auth;

import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.nsu.peretyatko.config.type.MailType;
import ru.nsu.peretyatko.model.auth.User;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {

    private final Configuration configuration;

    private final JavaMailSender mailSender;

    public void sendEmail(User user, MailType mailType) {
        switch (mailType) {
            case REGISTER -> sendRegistrationEmail(user);
            case RESET_PASSWORD -> sendResetPasswordEmail(user);
            default -> {}
        }
    }

    @SneakyThrows
    private void sendRegistrationEmail(User user) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        helper.setSubject("Thank you for your registration, " + user.getName());
        helper.setTo(user.getEmail());
        String emailContent = getRegistrationEmailContent(user);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    private String getRegistrationEmailContent(User user) {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getName());
        model.put("domain", "localhost:8080");
        model.put("token", user.getVerificationCode().toString());
        configuration.getTemplate("mail/register.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }

    @SneakyThrows
    private void sendResetPasswordEmail(User user) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        helper.setSubject("Request to Reset Your Password");
        helper.setTo(user.getEmail());
        String emailContent = getResetPasswordEmailContent(user);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    private String getResetPasswordEmailContent(User user) {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getName());
        model.put("domain", "localhost:8080");
        model.put("token", user.getVerificationCode().toString());
        configuration.getTemplate("mail/reset.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
