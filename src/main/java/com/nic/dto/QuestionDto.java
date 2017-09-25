package com.nic.dto;

import java.util.HashSet;
import java.util.Set;

import com.nic.model.Question;

public class QuestionDto implements IDto {

  private static final long serialVersionUID = -6435125739500356524L;

  private Long id;

  private CategoryDto category;

  private String question;

  private QuestionTypeDto questionType;

  private Set<QuestionChoiceDto> questionChoice = new HashSet<>();
  
  private Question parentQuestion;
  
  public Question getParentQuestion() {
	return parentQuestion;
}

public void setParentQuestion(Question parentQuestion) {
	this.parentQuestion = parentQuestion;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public CategoryDto getCategory() {
    return category;
  }

  public void setCategory(CategoryDto category) {
    this.category = category;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public QuestionTypeDto getQuestionType() {
    return questionType;
  }

  public void setQuestionType(QuestionTypeDto questionType) {
    this.questionType = questionType;
  }

  public Set<QuestionChoiceDto> getQuestionChoice() {
    return questionChoice;
  }

  public void setQuestionChoice(Set<QuestionChoiceDto> questionChoice) {
    this.questionChoice = questionChoice;
  }

}
