package com.aafv.lab03.controllers;


import com.aafv.lab03.domain.dto.request.CreateSpecimenRequest;
import com.aafv.lab03.domain.dto.request.UpdateSpecimenRequest;
import com.aafv.lab03.domain.dto.response.GeneralResponse;
import com.aafv.lab03.domain.dto.response.SpecimenResponse;
import com.aafv.lab03.services.SpecimenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/specimens")
@RequiredArgsConstructor
public class SpecimenController {

    private final SpecimenService specimenService;

    public ResponseEntity<GeneralResponse> buildResponse(String message, HttpStatus status, Object data) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return ResponseEntity
                .status(status)
                .body(GeneralResponse.builder()
                        .uri(uri)
                        .message(message)
                        .status(status.value())
                        .time(LocalDateTime.now())
                        .data(data)
                        .build()
                );
    }

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createSpecimen(
            @Valid @RequestBody CreateSpecimenRequest request
    ) {
        SpecimenResponse response = specimenService.createSpecimen(request);
        return buildResponse(
                "Specimen succesfully created",
                HttpStatus.CREATED,
                response
        );

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<GeneralResponse> getSpecimenById(@PathVariable UUID id) {
        SpecimenResponse response = specimenService.getSpecimenById(id);
        return buildResponse(
                "Specimen founded",
                HttpStatus.OK,
                response
        );
    }

    @GetMapping("/get")
    public ResponseEntity<GeneralResponse> getAllSpecimens() {
        return buildResponse(
                "Specimens found",
                HttpStatus.OK,
                specimenService.getAllSpecimens()
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GeneralResponse> updateSpecimen(
            @PathVariable UUID id,
            @RequestBody UpdateSpecimenRequest request
    ) {
        SpecimenResponse response = specimenService.updateSpecimen(id, request);

        return buildResponse(
                "Specimen successfully updated",
                HttpStatus.OK,
                response
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GeneralResponse> deleteSpecimenById(@PathVariable UUID id) {
        SpecimenResponse response = specimenService.deleteSpecimenById(id);

        return buildResponse(
                "Specimen successfully deleted",
                HttpStatus.OK,
                response
        );
    }
}
