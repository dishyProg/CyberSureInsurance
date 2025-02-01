package com.insurance.data.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Table(name = "comm_link")
@Entity
public class CommLinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inquiryId;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false, columnDefinition = "TEXT")
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

    // Endregion
}
