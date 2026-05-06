package com.aafv.lab02.service;

import com.aafv.lab02.domain.entity.EntityC;

import java.util.UUID;

public interface EntityService {
   //Post
    void createEntity(EntityC entity);
    //Get
    EntityC getAllEntities();
    EntityC getEntityByPatronus(String patronus);
    EntityC getOnlyDeatheaters();
    //Update
    void updateEntity(EntityC entity, UUID uuid);
    //Delete
    EntityC deleteEntity(UUID uuid);
}
