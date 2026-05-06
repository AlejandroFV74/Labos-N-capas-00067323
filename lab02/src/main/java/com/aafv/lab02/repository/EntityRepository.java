package com.aafv.lab02.repository;

import com.aafv.lab02.domain.entity.EntityC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EntityRepository extends JpaRepository<EntityC, UUID> {
    List<EntityC> getAllEntities();
    List<EntityC> getByPatronus(String pratonus);
    List<EntityC> findByIsDeatheaterTrue();
}
