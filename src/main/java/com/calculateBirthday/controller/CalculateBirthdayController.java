package com.calculateBirthday.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calculateBirthday.domain.BirthdayInfoResponse;
import com.calculateBirthday.service.CalculateBirthdayService;

@RestController
@RequestMapping("calculateBirthday")

public class CalculateBirthdayController {
	
	@Autowired
	CalculateBirthdayService calculateService;
	
	@GetMapping("/birthdayInfo") 
	public ResponseEntity<BirthdayInfoResponse> getBirthdayInfo(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName, @RequestParam(value = "birthday") String birthday) throws ParseException{
		BirthdayInfoResponse response = calculateService.calculateBirthday(firstName, lastName, birthday);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
