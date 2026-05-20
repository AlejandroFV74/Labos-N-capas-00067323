package com.aafv.lab03.services;

import com.aafv.lab03.domain.entity.Specimen;

import java.util.List;
import java.util.UUID;

public interface SpecimenService {
    void createProduct(Specimen specimen);
    List<Specimen> getAllSpecimens();
    Specimen getSpecimenById(UUID id);
    void updateSpecimen(UUID id, Specimen specimen);
    Specimen deleteSpecimen(UUID id);
    void createSpecimen(Specimen specimen);
}
