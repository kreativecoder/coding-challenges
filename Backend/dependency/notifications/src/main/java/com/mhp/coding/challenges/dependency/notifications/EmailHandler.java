package com.mhp.coding.challenges.dependency.notifications;

import com.mhp.coding.challenges.dependency.inquiry.Inquiry;
import com.mhp.coding.challenges.dependency.inquiry.event.InquiryCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EmailHandler implements ApplicationListener<InquiryCreatedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(EmailHandler.class);

    public void sendEmail(final Inquiry inquiry) {
        LOG.info("Sending email for: {}", inquiry);
    }

    @Override
    public void onApplicationEvent(InquiryCreatedEvent event) {
        this.sendEmail(event.getInquiry());
    }
}
