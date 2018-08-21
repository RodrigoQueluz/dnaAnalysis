package com.queluz.dna.analyisis.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.queluz.dna.analyisis.domain.StatsDomain;
import com.queluz.dna.analyisis.service.StatsService;

/**
 * Handles and retrieves person request
 */
@Controller
@RequestMapping("/stats")
public class StatsController {

	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	private StatsService statsService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody StatsDomain getAll() throws IOException {
		
		StatsDomain stats = new StatsDomain();
		
		stats = statsService.processStats();
		
		return stats;
	}
	
}