package com.nic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nic.model.QuestionChoice;

public interface QuestionChoiceRepository extends JpaRepository<QuestionChoice, Long> {

}
