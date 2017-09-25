package com.nic.dto;

import java.util.HashSet;
import java.util.Set;

public class CategoryDto implements IDto {

  private static final long serialVersionUID = 2244958901493208701L;

  private Long id;

  private SurveyDto survey;

  private String categoryName;

  private Set<QuestionDto> questions = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SurveyDto getSurvey() {
    return survey;
  }

  public void setSurvey(SurveyDto survey) {
    this.survey = survey;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public Set<QuestionDto> getQuestions() {
    return questions;
  }

  public void setQuestions(Set<QuestionDto> questions) {
    this.questions = questions;
  }

}
