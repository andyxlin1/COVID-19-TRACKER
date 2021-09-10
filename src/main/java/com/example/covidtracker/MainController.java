package com.example.covidtracker;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	
	
	CoronaService coronaService;

	public MainController(CoronaService coronaService) {
		this.coronaService = coronaService;
	}

	@GetMapping("/test")
	public String testMethod (Model model) throws IOException{
		coronaService.populateDatabase2();
		coronaService.populateDatabase();
		return "mainTemplate";
		}

	@GetMapping("/")
	public String root (Model model) throws IOException{
		coronaService.populateDatabase();
		model.addAttribute("coronaData", coronaService.findByLastUpdate(LocalDate.now()));
		//model.addAttribute("coronaData", coronaService.findAll());
		return "mainTemplate";		
		}

		
}
