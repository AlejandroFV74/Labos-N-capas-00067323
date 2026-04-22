package com.aafv.lab01.services;


import com.aafv.lab01.models.EntityClass;
import com.aafv.lab01.repositories.EntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntityService {

    private final EntityRepository entityRepository;

    public List<EntityClass> getEntities() {
        return entityRepository.getEntityClasses();
    }
}
