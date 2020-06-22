package com.calculateBirthday.service;

import java.text.ParseException;

import com.calculateBirthday.domain.BirthdayInfoResponse;

public interface CalculateBirthdayService {

	public BirthdayInfoResponse calculateBirthday(String firstName, String lastName, String birthday) throws ParseException;
	
}
