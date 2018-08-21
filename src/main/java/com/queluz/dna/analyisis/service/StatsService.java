package com.queluz.dna.analyisis.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.queluz.dna.analyisis.domain.StatsDomain;
import com.queluz.dna.analyisis.model.DNA;

/**
 * Service for processing Persons
 * 
 */
@Service
@Transactional
public class StatsService {

	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	private MutantService mutantService;
	
	public StatsDomain processStats() {
		StatsDomain statsDomain = new StatsDomain();
		
		List<DNA> dnas = mutantService.getAll();
		Long totalHumanDnas = 0L;
		Long totalMutantDnas = 0L;
		
		for(DNA dna : dnas) {
			if(dna.getIsMutant()) {
				totalMutantDnas++;
			} else {
				totalHumanDnas++;
			}
		}
		
		statsDomain.setCount_human_dna(totalHumanDnas);
		statsDomain.setCount_mutant_dna(totalMutantDnas);
		
		if(totalHumanDnas == 0L || totalMutantDnas == 0) {
			statsDomain.setRatio(0.0);
		}else {
			statsDomain.setRatio(BigDecimal.valueOf((double) totalMutantDnas / totalHumanDnas)
					.setScale(3, RoundingMode.HALF_UP)
					.doubleValue());
		}
		return statsDomain;
	}
}