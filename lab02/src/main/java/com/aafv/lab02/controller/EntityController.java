package com.aafv.lab02.controller;

import com.aafv.lab02.domain.entity.EntityC;
import com.aafv.lab02.service.EntityService;
import com.aafv.lab02.service.imp.EntityServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/wizard")
@AllArgsConstructor
public class EntityController {
    private final EntityServiceImpl entityService;

    @PostMapping("/create")
    public ResponseEntity<EntityC> createEntity(@RequestBody EntityC entity) {
        entityService.createEntity(entity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entity);

    }

    @GetMapping("/")
    public ResponseEntity<List<EntityC>> getAllEntity() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityService.getAllEntities());
    }

    @GetMapping("/deatheaters")
    public ResponseEntity<List<EntityC>> getAllDeatheaters() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityService.getOnlyDeatheater());
    }

    @GetMapping("/patronus/{patronus}")
    public ResponseEntity<List<EntityC>> getAllPatronus(@PathVariable String patronus) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityService.getEntityByPatronus(patronus));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityC> updateEntity(@PathVariable UUID id, @RequestBody EntityC entity) {
        entityService.updateEntity(entity, id);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(entity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EntityC> deleteEntity(@PathVariable UUID id) {
        entityService.deleteEntity(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
