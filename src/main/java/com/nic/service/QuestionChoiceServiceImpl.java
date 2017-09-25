package com.nic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nic.model.QuestionChoice;
import com.nic.repository.QuestionChoiceRepository;

@Service
public class QuestionChoiceServiceImpl extends AbstractService<QuestionChoice>
    implements QuestionChoiceService {

  @Autowired
  private QuestionChoiceRepository questionChoiceRepo;

  public QuestionChoiceServiceImpl() {
    super(QuestionChoice.class);
  }

  @Override
  protected QuestionChoiceRepository getDao() {
    return questionChoiceRepo;
  }

}
