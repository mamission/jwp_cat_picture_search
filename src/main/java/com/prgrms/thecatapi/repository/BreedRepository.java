package com.prgrms.thecatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgrms.thecatapi.cat.Breed;

public interface BreedRepository extends JpaRepository<Breed, String> {
}
