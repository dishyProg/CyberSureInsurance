package com.insurance.models.services;

import com.insurance.data.entities.InsuranceEntity;
import com.insurance.models.dto.InsuranceDTO;

import java.util.List;

public interface InsuranceService {

    InsuranceDTO getById(Long id);

    void create(String email, InsuranceEntity insurance);

    List<InsuranceDTO> getInsurances(String email);

    void remove(Long id);

    void edit(InsuranceDTO insurance);
}
