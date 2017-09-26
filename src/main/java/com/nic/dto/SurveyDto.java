package com.nic.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;


public class SurveyDto implements IDto {

  private Long id;

  private String surveyName;
  
  @JsonManagedReference
  private Set<CategoryDto> categories = new HashSet<>();

  public SurveyDto() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSurveyName() {
    return surveyName;
  }

  public void setSurveyName(String surveyName) {
    this.surveyName = surveyName;
  }

  public Set<CategoryDto> getCategories() {
    return categories;
  }

  public void setCategories(Set<CategoryDto> categories) {
    this.categories = categories;
  }

}
