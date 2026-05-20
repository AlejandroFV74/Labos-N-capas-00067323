package com.aafv.lab03.repositories;

import com.aafv.lab03.domain.entity.Specimen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpecimenRepository extends JpaRepository<Specimen, UUID> {
}
