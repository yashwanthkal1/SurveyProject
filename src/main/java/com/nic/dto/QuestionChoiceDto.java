package com.nic.dto;

public class QuestionChoiceDto implements IDto {

  private static final long serialVersionUID = -7397043528713658635L;

  private Long id;

  private QuestionDto questionId;

  private String choice;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public QuestionDto getQuestionId() {
    return questionId;
  }

  public void setQuestionId(QuestionDto questionId) {
    this.questionId = questionId;
  }

  public String getChoice() {
    return choice;
  }

  public void setChoice(String choice) {
    this.choice = choice;
  }



}
