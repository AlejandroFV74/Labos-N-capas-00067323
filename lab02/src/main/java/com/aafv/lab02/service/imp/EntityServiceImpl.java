package com.aafv.lab02.service.imp;

import com.aafv.lab02.domain.entity.EntityC;
import com.aafv.lab02.repository.EntityRepository;
import com.aafv.lab02.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EntityServiceImpl implements EntityService {

    private final EntityRepository entityRepository;

    @Override
    public void createEntity(EntityC entity) {
        entityRepository.save(entity);
    }

    @Override
    public List<EntityC> getAllEntities(){
       return entityRepository.getAllEntities();
    }

    @Override
    public List<EntityC> getEntityByPatronus(String pratonus){
        return entityRepository.getByPatronus(pratonus);
    }

    @Override
    public List<EntityC> getOnlyDeatheaters(){
        return entityRepository.findByIsDeatheaterTrue();
    }

    @Override
    public void updateEntity(EntityC entity, UUID id){
        EntityC existEntity = entityRepository.getById(id);
        existEntity.setEntityName(entity.getEntityName());
        existEntity.setEntityPatronus(entity.getEntityPatronus());
        existEntity.setEntityHouse(entity.getEntityHouse());
        existEntity.setIsDeatheater(entity.getIsDeatheater());
        entityRepository.save(existEntity);
    }

    @Override
    public EntityC deleteEntity(UUID id){
        EntityC existEntity = entityRepository.getById(id);
        entityRepository.delete(existEntity);
        return existEntity;
    }
}
