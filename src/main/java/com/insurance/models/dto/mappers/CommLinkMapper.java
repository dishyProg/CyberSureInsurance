package com.insurance.models.dto.mappers;

import com.insurance.data.entities.CommLinkEntity;
import com.insurance.models.dto.CommLinkDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommLinkMapper {

    CommLinkEntity toEntity(CommLinkDTO source);
}
