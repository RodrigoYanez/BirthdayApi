package com.calculateBirthday.implement;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.calculateBirthday.domain.BirthdayInfoResponse;
import com.calculateBirthday.domain.RandomPoems;
import com.calculateBirthday.service.CalculateBirthdayService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CalculateBirthdayImpl implements CalculateBirthdayService{

	@Override
	public BirthdayInfoResponse calculateBirthday(String firstName, String lastName, String birthday) throws ParseException {
		
		RestTemplate restTemplate = new RestTemplate();
		BirthdayInfoResponse response = new BirthdayInfoResponse();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		response.setFirsName(firstName);
		response.setLastName(lastName);
		response.setBirthDate(LocalDate.parse(birthday, format));
		log.info("Fecha Cumpleaños : "+response.getBirthDate());
		
		Integer birthDay = response.getBirthDate().getDayOfMonth();
		Integer birthMonth = response.getBirthDate().getMonthValue();	
		String birthMonthStr = birthMonth < 10 ?"0"+birthMonth.toString() : birthMonth.toString();
		Integer year = LocalDate.now().getYear();
		
//		log.info(" DIA CUMPLEAÑOS 1 : "+birthDay+" MES CUMPLEAÑOS : "+birthMonth+" AÑO : "+year);
		if(year==LocalDate.now().getYear() 
				&& birthMonth<=LocalDate.now().getMonthValue() 
				&& birthDay < LocalDate.now().getDayOfMonth()){
			year = year+1;
		}
		
//		log.info(" DIA CUMPLEAÑOS 2 : "+birthDay+" MES CUMPLEAÑOS : "+birthMonth+" AÑO : "+year);
		
		String birthDateStr = birthDay.toString()+"/"+birthMonthStr+"/"+year.toString();
		
		LocalDate birthDate = LocalDate.parse(birthDateStr, format);
		
		log.info("fecha cumpleanos actual : "+birthDate);
		
		
		response.setDateOfBirthday(ChronoUnit.DAYS.between(LocalDate.now(), birthDate));
		response.setAge(ChronoUnit.YEARS.between(response.getBirthDate(), LocalDate.now()));
		log.info("Dias para el cumpleaños : "+response.getDateOfBirthday());
		
		if (response.getDateOfBirthday() == 0) {
			List<RandomPoems> poemList = Arrays.asList(restTemplate.getForObject("https://www.poemist.com/api/v1/randompoems", RandomPoems[].class));
			Random random = new Random();
			RandomPoems poem = poemList.get(random.nextInt(poemList.size()));
			response.setPoem(poem.getContent());
		}
			
		log.info("RESULTADO : "+response);
		
		return response;
	}

}
