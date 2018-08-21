package com.queluz.dna.analyisis.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.queluz.dna.analyisis.domain.DNADomain;
import com.queluz.dna.analyisis.model.DNA;
import com.queluz.dna.analyisis.service.MutantService;

/**
 * Handles and retrieves person request
 */
@Controller
@RequestMapping("/mutant")
public class MutantController {

	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	private MutantService mutantService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<String> add(@RequestBody DNADomain dnaDomain) throws IOException {
		boolean isMutant = false;
		
		isMutant = mutantService.processDna(dnaDomain.getDna());
		
		return isMutant ? ResponseEntity.status(HttpStatus.OK).body(null) : ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<DNA> getAll() throws IOException {
		
		List<DNA> dnaList = mutantService.getAll();
		
		return dnaList;
	}
	
}