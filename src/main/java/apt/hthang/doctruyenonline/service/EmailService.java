package apt.hthang.doctruyenonline.service;

import apt.hthang.doctruyenonline.entity.Mail;

public interface EmailService {
    
    boolean sendSimpleMessage(Mail mail, String template);
    
}
