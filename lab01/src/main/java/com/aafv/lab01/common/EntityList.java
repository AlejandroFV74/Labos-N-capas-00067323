package com.aafv.lab01.common;

import org.springframework.stereotype.Component;
import com.aafv.lab01.models.EntityClass;
import java.util.ArrayList;
import java.util.List;

@Component
public class EntityList {

    private final ArrayList<EntityClass> entityList;

    public EntityList() {
        this.entityList = new ArrayList<>();

        this.entityList.add(EntityClass.builder()
                .id(1)
                .name("Alex")
                .baseVirus(new ArrayList<>(List.of("T-virus", "G-virus", "Cadou")))
                .dangerLvl(2)
                .weaknessPoint("head")
                .actualState("Contained")
                .lastReportedLocation("Hospital")
                .possibleOrigin("Aulas D")
                .build());

        this.entityList.add(EntityClass.builder()
                .id(2)
                .name("Isaac")
                .baseVirus(new ArrayList<>(List.of("T-virus", "Cadou")))
                .dangerLvl(4)
                .weaknessPoint("heart")
                .actualState("Contained")
                .lastReportedLocation("Hospital")
                .possibleOrigin("Aulas A")
                .build());

        this.entityList.add(EntityClass.builder()
                .id(3)
                .name("Diego")
                .baseVirus(new ArrayList<>(List.of("T-virus", "G-virus", "Cadou")))
                .dangerLvl(1)
                .weaknessPoint("feat")
                .actualState("On Search")
                .lastReportedLocation("Street ab-2")
                .possibleOrigin("Aulas B")
                .build());
        }

        public List<EntityClass> getEntityList() {
        return entityList;
        }
}
