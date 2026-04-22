package com.aafv.lab01.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityClass {

    private Integer id;
    private String name;
    private ArrayList<String> baseVirus;
    private Integer dangerLvl;
    private String weaknessPoint;
    private String actualState;
    private String lastReportedLocation;
    private String possibleOrigin;
}
