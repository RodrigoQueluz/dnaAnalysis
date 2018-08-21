package com.queluz.dna.analyisis.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.queluz.dna.analyisis.dao.MutantRepository;
import com.queluz.dna.analyisis.model.DNA;

/**
 * Service for processing Persons
 * 
 */
@Service
@Transactional
public class MutantService {

	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	private MutantRepository repository;

	public List<DNA> getAll() {
		return (List<DNA>) repository.findAll();
	}

	public DNA add(DNA dna) {
		return repository.save(dna);
	}
	
	public DNA findByID(Long id){
		return repository.findOne(id);
	}

	public boolean processDna(String[] dna) {
		Integer horizontal = 0;
		Integer vertical = 0;
		
		DNA dnaModel = new DNA();
		dnaModel.setIsMutant(false);	
		while(vertical < dna.length) {
			dnaModel.getGenes().add(dna[vertical]);
			char[] aux = dna[vertical].toCharArray();
			horizontal = 0;
			while (horizontal < aux.length) {
				char character = aux[horizontal];
				if (lookupHorizontal(horizontal, aux, character, dna) || lookupVertical(vertical, character, dna) ||
						lookupDiagonal(horizontal, vertical, aux, character, dna)) {
					dnaModel.setIsMutant(true);
				}
				horizontal++;
			}
			vertical++;
		}

		repository.save(dnaModel);
		return dnaModel.getIsMutant();
	}

	private static boolean lookupVertical(int vertical, char character, String[] dna) {
		if(dna.length - vertical < 4 )
			return false;
		else {
			int i = vertical + 1;
			while(i<dna.length && character == dna[i].charAt(vertical) && i - vertical < 4) {
				i++;
			}
			return i - vertical == 4;
		}
	}

	private static boolean lookupDiagonal(int horizontal, Integer vertical, char[] aux, char character, String[] dna) {
		if(aux.length - horizontal < 4  || dna.length - vertical < 4 )
			return false;
		else {
			int i = horizontal + 1;
			int j = vertical +1;
			while(i<aux.length && j < dna.length && character == dna[j].charAt(i) && i - horizontal < 4 && j - vertical < 4) {
				i++;
				j++;
			}
			return i - horizontal == 4 && j - vertical == 4;
		}
	}

	private static boolean lookupHorizontal(Integer horizontal, char[] aux, char character, String[] dna) {
		if(aux.length - horizontal < 4 )
			return false;
		else {
			int i = horizontal + 1;
			while(i<aux.length && character == aux[i] && i - horizontal < 4) {
				i++;
			}
			return i - horizontal == 4;
		}
	}
}