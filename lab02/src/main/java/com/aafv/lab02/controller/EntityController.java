package com.aafv.lab02.controller;

import com.aafv.lab02.domain.entity.EntityC;
import com.aafv.lab02.service.EntityService;
import com.aafv.lab02.service.imp.EntityServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
