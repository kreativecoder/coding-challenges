package com.mhp.coding.challenges.dependency.inquiry.event;

import com.mhp.coding.challenges.dependency.inquiry.Inquiry;
import org.springframework.context.ApplicationEvent;

public class InquiryCreatedEvent extends ApplicationEvent {

    private final Inquiry inquiry;

    public InquiryCreatedEvent(Object source, Inquiry inquiry) {
        super(source);
        this.inquiry = inquiry;
    }

    public Inquiry getInquiry() {
        return this.inquiry;
    }
}
