package com.insurance.models.dto.mappers;

import com.insurance.data.entities.InsuranceEntity;
import com.insurance.models.dto.InsuranceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InsuranceMapper {

    InsuranceEntity toEntity(InsuranceDTO source);

    InsuranceDTO toDto(InsuranceEntity source);

    void updateInsuranceDto(InsuranceDTO source, @MappingTarget InsuranceDTO target);

    void updateInsuranceEntity(InsuranceDTO source, @MappingTarget InsuranceEntity target);
}
