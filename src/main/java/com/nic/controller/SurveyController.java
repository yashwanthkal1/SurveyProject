package com.nic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.nic.dto.SurveyDto;
import com.nic.model.Survey;
import com.nic.service.IService;
import com.nic.service.SurveyService;

@RestController
@RequestMapping("/surveys")
public class SurveyController extends AbstractController<SurveyDto, Survey> {

	@Autowired
	SurveyService surveyService;

	@Autowired
	public SurveyController() {
		super(SurveyDto.class, Survey.class);
	}

	@Override
	protected IService<Survey> getService() {
		return surveyService;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<SurveyDto> findAll(final HttpServletRequest request) {
		return findAllInternal(request);
	}

	// find - one

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SurveyDto findOne(@PathVariable("id") final Long id) {
		return findOneInternal(id);
	}

	// create

	@RequestMapping(method = RequestMethod.POST)
	public SurveyDto create(@RequestBody @Valid final SurveyDto resource, final UriComponentsBuilder uriBuilder,
			final HttpServletResponse response) {
		return createInternal(resource, uriBuilder, response);
	}

	// update

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public SurveyDto update(@PathVariable("id") final Long id, @RequestBody @Valid final SurveyDto resource) {
		return updateInternal(id, resource);
	}

	// delete

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") final Long id) {
		deleteByIdInternal(id);
	}

}
