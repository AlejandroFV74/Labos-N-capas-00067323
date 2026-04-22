package com.aafv.lab01.repositories;

import com.aafv.lab01.common.EntityList;
import com.aafv.lab01.models.EntityClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor

public class EntityRepository {

    private final EntityList entityList;

    public List<EntityClass> getEntityClasses() {
        return entityList.getEntityList();
    }
}
