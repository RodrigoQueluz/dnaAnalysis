package com.queluz.dna.analysis;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.queluz.dna.analyisis.controller.MutantController;
import com.queluz.dna.analyisis.domain.DNADomain;
import com.queluz.dna.analyisis.model.DNA;
import com.queluz.dna.analyisis.service.MutantService;

public class DnaAnalysisTest {

	MutantController controller;
	MutantService mutantService;
	
	@Before
    public void setUp() {
		controller  = new MutantController();
		mutantService = new MutantService();
	}
	
	@Test
	public void shouldBeMutant() {
		String[] dna = {"GTGCCA","CAGTGC","TTATGT","AGAAGG","CCACTA","TCACTG"};

		DNA dnaModel = mutantService.anylize(dna);
		
		assertTrue(dnaModel.getIsMutant());
	}
	
	@Test
	public void shouldBeHuman() {
		String[] dna = {"GTGCCA","CAGTGC","TTGTGT","AGAAGG","CCACTA","TCACTG"};


		DNA dnaModel = mutantService.anylize(dna);
		
		assertFalse(dnaModel.getIsMutant());
	}
	
}
