package com.aafv.lab03.services.impl;

import com.aafv.lab03.common.mappers.SpecimenMapper;
import com.aafv.lab03.domain.dto.request.CreateSpecimenRequest;
import com.aafv.lab03.domain.dto.response.SpecimenResponse;
import com.aafv.lab03.domain.entity.Specimen;
import com.aafv.lab03.repositories.SpecimenRepository;
import com.aafv.lab03.services.SpecimenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecimenServiceImpl implements SpecimenService {
    private final SpecimenRepository specimenRepository;
    private final SpecimenMapper specimenMapper;

    @Override
    @Transactional
    public SpecimenResponse createSpecimen(CreateSpecimenRequest request) {
        return specimenMapper.toDto(
                specimenRepository.save(specimenMapper.toEntity(request))
        );
    }

    @Override
    public List<SpecimenResponse> getAllSpecimens() {
        List<Specimen> specimens = specimenRepository.findAll();
        if (specimens.isEmpty())
            throw new ResourceNotFoundException("No specimens are registered in Hyrule");

        return specimens.stream()
                .map(specimenMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SpecimenResponse getSpecimenById(UUID id) {
        return specimenMapper.toDto(specimenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specimen not found in Hyrule Records"))
        );
    }

    @Override
    @Transactional
    public SpecimenResponse updateSpecimen(UUID id, UpdateSpecimenRequest request) {
        this.getSpecimenById(id);
        return specimenMapper.toDto(specimenRepository.save(specimenMapper.toEntityUpdate(request, id)));
    }

    @Transactional
    public SpecimenResponse deleteSpecimen(UUID id) {
        SpecimenResponse existSpecimen = this.getSpecimenById(id);
        specimenRepository.deleteById(id);
        return existSpecimen;
    }
}
