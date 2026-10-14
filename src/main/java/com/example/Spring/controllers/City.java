package com.example.Spring.controllers;

import com.example.Spring.dto.CityDTO;
import com.example.Spring.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class City {
    private final CityService service;

    @GetMapping("/city/{id}")
    public ResponseEntity<CityDTO> city(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getCity(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/city/create")
    public ResponseEntity<?> create(@RequestBody CityDTO cityBody) {
        try {
            CityDTO cityDTO = cityDTO(cityBody);
            service.createCity(cityDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(cityBody);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }
    private CityDTO cityDTO(CityDTO cityDTO) {
        return cityDTO;
    }
}
