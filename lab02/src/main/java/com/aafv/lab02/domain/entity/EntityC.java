package com.aafv.lab02.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "Entity")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class EntityC {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "entityName")
    private String entityName;

    @Column(name = "Patronus")
    private String entityPatronus;

    @Column(name = "House")
    private String entityHouse;

    @Column(name = "IsDeatheater")
    private Boolean isDeatheater;

    }