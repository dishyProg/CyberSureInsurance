package com.insurance.data.repositories;

import com.insurance.data.entities.InsuranceEntity;
import com.insurance.data.entities.UserEntity;
import com.insurance.models.dto.InsuranceDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InsuranceRepository extends CrudRepository<InsuranceEntity, Long> {
    @Query("SELECT new com.insurance.models.dto.InsuranceDTO(i.insuranceId, i.insuranceName, i.insuranceType, " +
            "i.insuranceStartDate, i.insuranceEndDate, i.price) " +
            "FROM InsuranceEntity i WHERE i.user.email = :email")
    List<InsuranceDTO> findInsuranceDTOsByUserEmail(@Param("email") String email);
}
