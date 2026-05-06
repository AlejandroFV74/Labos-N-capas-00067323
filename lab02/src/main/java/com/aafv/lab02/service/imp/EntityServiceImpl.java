package com.aafv.lab02.service.imp;

import com.aafv.lab02.domain.entity.EntityC;
import com.aafv.lab02.repository.EntityRepository;
import com.aafv.lab02.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntityServiceImpl implements EntityService {

    private final EntityRepository entityRepository;

    @Override
    public void createEntity(EntityC entity) {
        entityRepository.save(entity);
    }

}
