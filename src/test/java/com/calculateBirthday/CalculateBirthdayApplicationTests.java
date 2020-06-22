package com.calculateBirthday;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import com.calculateBirthday.domain.BirthdayInfoResponse;
import com.calculateBirthday.implement.CalculateBirthdayImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("local")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
@SpringBootTest
class CalculateBirthdayApplicationTests {

	@Test
	void contextLoads() {
	}

	private static final String TEST_MESSAGE = "Test Message Logic";

	public static final String POEM_RESPONSE = "src/test/java/resources/poemistResponse.json";

	public static final String URL = "https://www.poemist.com/api/v1/randompoems";
	
	@InjectMocks
	CalculateBirthdayImpl calcbirthdayImpl;

	@Mock
	RestTemplate restTemplate;

	@Test
	public void testCorrectInformationDateNow() throws ParseException {
		BirthdayInfoResponse[] response = loadJson(POEM_RESPONSE, BirthdayInfoResponse[].class);
		log.info("response: " + response);
		when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(BirthdayInfoResponse[].class))).thenReturn(response);
		calcbirthdayImpl.calculateBirthday("Pedro", "Picapiedra", LocalDate.now().toString());
		assertNotNull(TEST_MESSAGE, response);
	}

	public void testDateLessThanCurrent() throws ParseException {
		BirthdayInfoResponse[] response = loadJson(POEM_RESPONSE, BirthdayInfoResponse[].class);
		log.info("response: " + response);
		when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(BirthdayInfoResponse[].class))).thenReturn(response);
		calcbirthdayImpl.calculateBirthday("Pedro", "Picapiedra", "12/02/1992");
		assertNotNull(TEST_MESSAGE, response);
	}
	
	private <T> T loadJson(final String path, Class<T> clazz) {
		File jFile = new File(path);

		ObjectMapper jObject = new ObjectMapper();
		try {
			return jObject.readValue(jFile, clazz);
		} catch (IOException e) {
			return null;
		}
	}
	
}
