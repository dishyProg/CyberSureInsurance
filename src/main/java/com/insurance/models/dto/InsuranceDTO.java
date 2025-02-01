package com.insurance.models.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class InsuranceDTO {

    private Long insuranceId;

    @NotBlank
    private String insuranceName;

    @NotBlank(message = "Choose your insurance plan")
    private String insuranceType;

    @NotNull(message = "Enter the start date")
    @FutureOrPresent(message = "Start date must be today or in the future")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate insuranceStartDate;

    @NotNull(message = "Enter the end date")
    @FutureOrPresent(message = "End date must be today or in the future")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate insuranceEndDate;


    private int price;

    public InsuranceDTO() {};

    public InsuranceDTO(Long insuranceId, String insuranceName, String insuranceType, LocalDate insuranceStartDate, LocalDate insuranceEndDate, int price) {
        this.insuranceId = insuranceId;
        this.insuranceName = insuranceName;
        this.insuranceType = insuranceType;
        this.insuranceStartDate = insuranceStartDate;
        this.insuranceEndDate = insuranceEndDate;
        this.price = price;
    }

    // Region: Getters & Setters

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public LocalDate getInsuranceStartDate() {
        return insuranceStartDate;
    }

    public void setInsuranceStartDate(LocalDate insuranceStartDate) {
        this.insuranceStartDate = insuranceStartDate;
    }

    public LocalDate getInsuranceEndDate() {
        return insuranceEndDate;
    }

    public void setInsuranceEndDate(LocalDate insuranceEndDate) {
        this.insuranceEndDate = insuranceEndDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
