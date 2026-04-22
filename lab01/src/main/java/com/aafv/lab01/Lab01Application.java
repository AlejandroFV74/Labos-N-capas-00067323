package com.aafv.lab01;

import com.aafv.lab01.services.EntityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab01Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab01Application.class, args);
    }

    @Bean
    public CommandLineRunner run(EntityService entityService) {

        return args -> {

            System.out.println("=== INICIANDO PROGRAMA ===");
            entityService.getEntities().forEach(p ->
                    System.out.println("[S.T.A.R.S-REPORT]" + "Nombre: " + p.getName() + " | " + "Nivel de peligro: " + p.getDangerLvl() + " | " + "Punto debil: " + p.getWeaknessPoint())
            );
        };
    }

}
