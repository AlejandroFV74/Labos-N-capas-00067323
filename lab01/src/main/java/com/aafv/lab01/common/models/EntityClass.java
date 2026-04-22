package com.aafv.lab01.common.models;

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
    private ArrayList<String> baseVirus;
    private Integer dangerLvl;
    private String weaknessPoint;
    private ArrayList<String> actualState;
    private ArrayList<String> lastReportedLocation;
    private String possibleOrigin;
}
