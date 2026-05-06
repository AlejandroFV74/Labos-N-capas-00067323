package com.aafv.lab02.repository;

import com.aafv.lab02.domain.entity.EntityC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntityRepository extends JpaRepository<EntityC, UUID> {

}
