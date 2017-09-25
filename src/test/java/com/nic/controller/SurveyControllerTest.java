package com.nic.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.nic.model.Survey;
import com.nic.service.SurveyService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SurveyControllerTest {
  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private SurveyService surveyService;

  private Survey survey1;

  // Default test data
  @Before
  public void beforeTest() {
    survey1 = new Survey();
    survey1.setSurveyName("demo survey 1");
    survey1 = surveyService.create(survey1);
  }

  @After
  public void afterTest() {
    surveyService.deleteAll();
  }

  @Test
  public void testGetAllSurveys() throws IOException {
    // prepare the data for test case.

    List<Survey> surveys = restTemplate.getForObject("/surveys", List.class);
    assertNotNull(surveys);
    assertEquals(1, surveys.size());
  }

  @Test
  public void testGetSurvey() {
    Survey survey = restTemplate.getForObject("/surveys/" + survey1.getId(), Survey.class);
    assertNotNull(survey);
    assertEquals("demo survey 1", survey.getSurveyName());
  }

  @Test
  public void testUpdateSurvey() {
    survey1.setSurveyName("updated survey 1");
    RequestEntity<Survey> reqEntity = new RequestEntity<Survey>(survey1, HttpMethod.PUT,
        URI.create("/surveys/" + survey1.getId()));

    ResponseEntity<Survey> respSurveyDto = restTemplate.exchange(reqEntity, Survey.class);

    // restTemplate.
    assertNotNull(respSurveyDto);
    assertEquals("updated survey 1", respSurveyDto.getBody().getSurveyName());
  }

  @Test
  public void testdeleteSurvey() {
    Survey survey2 = new Survey();
    survey2.setSurveyName("survey 2");
    RequestEntity<Survey> reqEntity =
        new RequestEntity<Survey>(survey2, HttpMethod.POST, URI.create("/surveys"));

    ResponseEntity<Survey> respSurveyDto = restTemplate.exchange(reqEntity, Survey.class);

    // restTemplate.
    assertNotNull(respSurveyDto);
    assertEquals("survey 2", respSurveyDto.getBody().getSurveyName());
  }

  @Test
  public void testDeleteSurvey() throws IOException {

    RequestEntity<Survey> reqEntity =
        new RequestEntity<Survey>(HttpMethod.DELETE, URI.create("/questions"));
    ResponseEntity<Survey> respSurveyDto = restTemplate.exchange(reqEntity, Survey.class);
    assertNotNull(restTemplate.getForObject("/surveys/" + survey1.getId(), Survey.class));
  }
}
