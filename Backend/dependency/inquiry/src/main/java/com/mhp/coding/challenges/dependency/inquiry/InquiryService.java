package com.mhp.coding.challenges.dependency.inquiry;

import com.mhp.coding.challenges.dependency.inquiry.event.InquiryCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class InquiryService {

    private static final Logger LOG = LoggerFactory.getLogger(InquiryService.class);

    private final ApplicationEventPublisher eventPublisher;

    public InquiryService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void create(final Inquiry inquiry) {
        LOG.info("User sent inquiry: {}", inquiry);
        eventPublisher.publishEvent(new InquiryCreatedEvent(this, inquiry));
    }

}
