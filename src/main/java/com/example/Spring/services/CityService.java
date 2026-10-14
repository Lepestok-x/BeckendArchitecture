package com.example.Spring.services;
import com.example.Spring.database.City;
import com.example.Spring.dto.CityDTO;
import com.example.Spring.repositories.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    @Transactional(rollbackFor = Exception.class)
    public void createCity(CityDTO city_dto) {
     // Ручная валидация, в дальнейшем применю библиотеку.
     if(city_dto.city() == null) {
         throw new IllegalArgumentException("City is null");
     }
     City city = toEntity(city_dto.city(), city_dto.isReal());
     cityRepository.save(city);
    }

    private City toEntity(String city, boolean isReal) {
        return new City(city, isReal);
    }
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public CityDTO getCity(Long id) {
        if(id <= 0) throw new ArithmeticException("Error id not found: " + id);
        Optional<City> city = Optional.of(cityRepository.findById(id)
            .orElseThrow(() -> new ClassCastException("Object not found")));

        String cit = city.get().getCity();
        boolean isReal = city.get().isReal();
        return new CityDTO(cit, isReal);
    }
}
