package com.example.Spring.repositories;

import com.example.Spring.database.Customers;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {
    @Override
    @NonNull
    Optional<Customers> findById(@NonNull Long aLong);
}
