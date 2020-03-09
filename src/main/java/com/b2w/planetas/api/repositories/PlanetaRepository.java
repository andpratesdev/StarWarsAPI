package com.b2w.planetas.api.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.b2w.planetas.api.models.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, String> {

	Optional<Planeta> findByNome(String nome);

}
