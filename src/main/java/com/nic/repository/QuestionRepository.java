package com.nic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nic.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
