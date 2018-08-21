package com.queluz.dna.analyisis.dao;

import org.springframework.data.repository.CrudRepository;

import com.queluz.dna.analyisis.model.DNA;

public interface MutantRepository extends CrudRepository<DNA, Long> {

}