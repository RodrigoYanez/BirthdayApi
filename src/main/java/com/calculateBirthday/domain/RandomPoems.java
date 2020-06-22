package com.calculateBirthday.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class RandomPoems implements Serializable{

	private static final long serialVersionUID = 6530032521979800866L;
	String title;
	String content;
	String url;
	Poet poet;
	
	
}
