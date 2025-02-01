package com.insurance.models.services;

import com.insurance.data.entities.InsuranceEntity;
import com.insurance.data.entities.UserEntity;
import com.insurance.data.repositories.InsuranceRepository;
import com.insurance.data.repositories.UserRepository;
import com.insurance.models.dto.InsuranceDTO;
import com.insurance.models.dto.mappers.InsuranceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class InsuranceServiceImpl implements InsuranceService{

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InsuranceMapper insuranceMapper;

    @Override
    public void create(String email, InsuranceEntity insurance) {

        UserEntity user = userRepository
                .findByEmail(email)
                .orElseThrow();

        insurance.setUser(user);
        insuranceRepository.save(insurance);
    }

    @Override
    public List<InsuranceDTO> getInsurances(String email) {
        return insuranceRepository.findInsuranceDTOsByUserEmail(email);
    }

    @Override
    public void remove(Long id) {
        InsuranceEntity fetchedEntity = fetchEntityById(id);
        insuranceRepository.delete(fetchedEntity);
    }

    @Override
    public InsuranceDTO getById(Long id) {
        InsuranceEntity fetchedEntity = fetchEntityById(id);
        return insuranceMapper.toDto(fetchedEntity);
    }

    @Override
    public void edit(InsuranceDTO insurance) {
        InsuranceEntity fetchedEntity = fetchEntityById(insurance.getInsuranceId());

        insuranceMapper.updateInsuranceEntity(insurance, fetchedEntity);
        insuranceRepository.save(fetchedEntity);
    }

    private InsuranceEntity fetchEntityById(Long id) {
        return insuranceRepository.findById(id).orElseThrow();
    }
}
