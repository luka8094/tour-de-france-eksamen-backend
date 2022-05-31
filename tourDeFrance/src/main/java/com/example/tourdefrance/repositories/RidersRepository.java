package com.example.tourdefrance.repositories;

import com.example.tourdefrance.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RidersRepository extends JpaRepository<Rider, Long> {

    Rider getById(Long id);

    Rider getByCountry(String countryName);

}
