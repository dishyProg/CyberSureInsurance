package com.insurance.models.services;

import com.insurance.data.entities.CommLinkEntity;
import com.insurance.data.repositories.CommLinkRepository;
import com.insurance.models.dto.CommLinkDTO;
import com.insurance.models.dto.mappers.CommLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommLinkServiceImpl implements CommLinkService {

    @Autowired
    private CommLinkRepository commLinkRepository;

    @Autowired
    private CommLinkMapper commLinkMapper;

    @Override
    public void create(CommLinkDTO inquiry) {
        CommLinkEntity newInquiry = commLinkMapper.toEntity(inquiry);

        commLinkRepository.save(newInquiry);
    }
}
