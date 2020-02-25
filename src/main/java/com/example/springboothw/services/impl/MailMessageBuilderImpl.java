package com.example.springboothw.services.impl;

import com.example.springboothw.entities.Order;
import com.example.springboothw.entities.User;
import com.example.springboothw.services.MailMessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailMessageBuilderImpl implements MailMessageBuilder {
    private TemplateEngine templateEngine;

    @Autowired
    public void setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildOrderEmail(Order order) {
        Context context = new Context();
        context.setVariable("order", order);
        return templateEngine.process("mail/order-mail", context);
    }

    public String buildRegConfirmationEmail(User user, String url) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("reg_url",url);
        return templateEngine.process("mail/reg_confirmation-mail", context);
    }
}
