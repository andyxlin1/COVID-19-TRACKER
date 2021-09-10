package com.example.covidtracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.opencsv.CSVReader;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CoronaService {
    CoronaRepository coronaRepository;

    public CoronaService(CoronaRepository coronaRepository) {
        this.coronaRepository = coronaRepository;
    }

    public void save (Corona corona){
        coronaRepository.save(corona);
    }

    @Scheduled(cron = "0 4 * * *")
    public void populateDatabase() throws IOException {
        URL url = new URL("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/09-04-2021.csv");
		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		int responseCode = huc.getResponseCode();
		

		if (responseCode == 200){
			log.info("Succesfully connected to github");

			CSVReader reader = null;
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(huc.getInputStream()));
			reader = new CSVReader(input);
			String[] line;
			int i = 0;
			while((line = reader.readNext()) != null) {
				if (i == 0) {
					i++;
					continue;
				}
				
				Corona corona = new Corona();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

				corona.setLastUpdate(LocalDateTime.parse(line[4], formatter));
				corona.setConfirmed(Long.valueOf(line[7]));
				if (line[9] == "") {
					corona.setRecovered(Long.valueOf(0));
				}
				else {
					corona.setRecovered(Long.valueOf(line[9]));
				}
				
				if (line[10] == "") {
					corona.setActive(Long.valueOf(0));
				}
				else {
					corona.setActive(Long.valueOf(line[10]));	
				}
				corona.setCombinedKey(line[11]);
				corona.setDeaths(Long.valueOf(line[8]));
				
				List<Corona> coronaList = findByCombinedKey(corona.getCombinedKey()); 
				if (!coronaList.isEmpty()){
					corona.setConfirmedChanges(corona.getConfirmed() - coronaList.get(coronaList.size() - 1).getConfirmed());
					corona.setDeathChanges(corona.getDeaths() - coronaList.get(coronaList.size() - 1).getDeaths());
				}

				coronaRepository.save(corona);
				log.info(corona.toString());

			} 
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		
		
	    }
    }

	public void populateDatabase2() throws IOException {
        URL url = new URL("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/08-22-2021.csv");
		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		int responseCode = huc.getResponseCode();
		

		if (responseCode == 200){
			log.info("Succesfully connected to github");

			CSVReader reader = null;
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(huc.getInputStream()));
			reader = new CSVReader(input);
			String[] line;
			int i = 0;
			while((line = reader.readNext()) != null) {
				if (i == 0) {
					i++;
					continue;
				}
				
				Corona corona = new Corona();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

				corona.setLastUpdate(LocalDateTime.parse(line[4], formatter));
				corona.setConfirmed(Long.valueOf(line[7]));
				if (line[9] == "") {
					corona.setRecovered(Long.valueOf(0));
				}
				else {
					corona.setRecovered(Long.valueOf(line[9]));
				}
				
				if (line[10] == "") {
					corona.setActive(Long.valueOf(0));
				}
				else {
					corona.setActive(Long.valueOf(line[10]));	
				}
				corona.setCombinedKey(line[11]);
				corona.setDeaths(Long.valueOf(line[8]));
				
				List<Corona> coronaList = findByCombinedKey(corona.getCombinedKey()); 
				if (!coronaList.isEmpty()){
					corona.setConfirmedChanges(corona.getConfirmed() - coronaList.get(coronaList.size() - 1).getConfirmed());
					corona.setDeathChanges(corona.getDeaths() - coronaList.get(coronaList.size() - 1).getDeaths());
				}

				coronaRepository.save(corona);
				log.info(corona.toString());

			} 
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		
		
	    }
    }

    private List<Corona> findByCombinedKey(String combinedKey) {
		return coronaRepository.findByCombinedKey(combinedKey);
	}

	public List<Corona> findByLastUpdate(LocalDate localDate) {
        return coronaRepository.findByLastUpdateBetween(LocalDateTime.of(localDate, LocalTime.MIN), LocalDateTime.of(localDate, LocalTime.MAX));
    }

    public List<Corona> findAll() {
        return coronaRepository.findAll();
    }
}
