package com.example.Spring.repositories;

import com.example.Spring.database.City;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    @Override
    @NonNull
    Optional<City> findById(@NonNull Long aLong);
}
