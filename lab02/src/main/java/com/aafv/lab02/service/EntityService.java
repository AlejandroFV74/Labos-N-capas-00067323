package com.aafv.lab02.service;

import com.aafv.lab02.domain.entity.EntityC;
import com.aafv.lab02.repository.EntityRepository;

import java.util.List;
import java.util.UUID;

public interface EntityService {
   //Post
    void createEntity(EntityC entity);
    //Get
    List<EntityC> getAllEntities();
    List<EntityC> getEntityByPatronus(String patronus);
    List<EntityC> getOnlyDeatheaters();
    //Update
    void updateEntity(EntityC entity, UUID uuid);
    //Delete
    EntityC deleteEntity(UUID uuid);
}
