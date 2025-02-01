package com.insurance.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CommLinkDTO {


    private long inquiryId;

    @NotBlank(message = "Fill in your name!")
    private String customerName;

    @NotBlank(message = "Fill in your email!")
    @Email(message = "Enter valid email!")
    private String customerEmail;

    @NotBlank(message = "Fill in your message!")
    private String inquiry;

    // Region: Getters & Setters

    public long getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(long inquiryId) {
        this.inquiryId = inquiryId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getInquiry() {
        return inquiry;
    }

    public void setInquiry(String inquiry) {
        this.inquiry = inquiry;
    }
}
