package com.aafv.lab03.services;

import com.aafv.lab03.domain.dto.request.CreateSpecimenRequest;
import com.aafv.lab03.domain.dto.request.UpdateSpecimenRequest;
import com.aafv.lab03.domain.dto.response.PageableResponse;
import com.aafv.lab03.domain.dto.response.SpecimenResponse;
import com.aafv.lab03.domain.entity.Specimen;

import java.util.List;
import java.util.UUID;

public interface SpecimenService {
    SpecimenResponse createSpecimen(CreateSpecimenRequest request);
    //List<SpecimenResponse> getAllSpecimens();
    SpecimenResponse getSpecimenById(UUID id);
    SpecimenResponse updateSpecimen(UUID id, UpdateSpecimenRequest request);
    SpecimenResponse deleteSpecimenById(UUID id);
    PageableResponse<SpecimenResponse> getAllSpecimens(
            int page,
            int size,
            String sortBy,
            String sortOrder
    );
}
