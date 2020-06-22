package com.calculateBirthday.domain;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class BirthdayInfoResponse implements Serializable{

	private static final long serialVersionUID = 9009181010389348548L;
	Integer statusCode;
	String firsName;
	String lastName;
	LocalDate birthDate; 
	Long age;
	Long dateOfBirthday;
	String poem;
}
