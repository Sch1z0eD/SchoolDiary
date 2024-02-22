package ru.sch1z0ed.diary.service;

import javax.mail.MessagingException;

public interface MailService {
    void sendMail(String to, String subject, String text, boolean isHtmlContent) throws MessagingException;

    void createRegistrationMail(final String email);
}
