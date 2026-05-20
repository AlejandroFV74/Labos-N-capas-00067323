package com.aafv.lab03.services.impl;

import com.aafv.lab03.common.mappers.SpecimenMapper;
import com.aafv.lab03.domain.dto.request.CreateSpecimenRequest;
import com.aafv.lab03.domain.dto.request.UpdateSpecimenRequest;
import com.aafv.lab03.domain.dto.response.PageableResponse;
import com.aafv.lab03.domain.dto.response.SpecimenResponse;
import com.aafv.lab03.domain.entity.Specimen;
import com.aafv.lab03.exceptions.ResourceNotFoundException;
import com.aafv.lab03.repositories.SpecimenRepository;
import com.aafv.lab03.services.SpecimenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecimenServiceImpl implements SpecimenService {
    private final SpecimenRepository specimenRepository;
    private final SpecimenMapper specimenMapper;

    @Override
    @Transactional
    public SpecimenResponse createSpecimen(CreateSpecimenRequest request) {
        return specimenMapper.toDto(
                specimenRepository.save(specimenMapper.toEntityCreate(request))
        );
    }

//    @Override
//    public List<SpecimenResponse> getAllSpecimens() {
//        List<Specimen> specimens = specimenRepository.findAll();
//        if (specimens.isEmpty())
//            throw new ResourceNotFoundException("No specimens are registered in Hyrule");
//
//        return specimens.stream()
//                .map(specimenMapper::toDto)
//                .collect(Collectors.toList());
//    }

    @Override
    public PageableResponse<SpecimenResponse> getAllSpecimens(
            int page, int size, String sortBy, String order
    ){
        Sort.Direction direction = order.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<Specimen> specimens = specimenRepository.findAll(pageable);

        if (specimens.isEmpty()) {
            throw new ResourceNotFoundException("No specimens are registered in Hyrule");
        }

        return PageableResponse.<SpecimenResponse>builder()
                .content(specimenMapper.toDto(specimens))
                .page(specimens.getNumber())
                .size(specimens.getSize())
                .totalElements(specimens.getTotalElements())
                .totalPages(specimens.getTotalPages())
                .first(specimens.isFirst())
                .last(specimens.isLast())
                .sortBy(sortBy)
                .sortOrder(direction.name().toLowerCase()).build();
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
    public SpecimenResponse deleteSpecimenById(UUID id) {
        SpecimenResponse existSpecimen = this.getSpecimenById(id);
        specimenRepository.deleteById(id);
        return existSpecimen;
    }
}
