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

import com.nic.dto.CategoryDto;
import com.nic.model.Category;
import com.nic.service.CategoryService;
import com.nic.service.IService;

@RestController
@RequestMapping("/categories")
public class CategoryController extends AbstractController<CategoryDto, Category> {

  @Autowired
  CategoryService categoryService;

  @Autowired
  public CategoryController() {
    super(CategoryDto.class, Category.class);
  }

  @Override
  protected IService<Category> getService() {
    return categoryService;
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public List<CategoryDto> findAll(final HttpServletRequest request) {
    return findAllInternal(request);
  }

  // find - one

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public CategoryDto findOne(@PathVariable("id") final Long id) {
    return findOneInternal(id);
  }

  // create

  @RequestMapping(method = RequestMethod.POST)
  public CategoryDto create(@RequestBody @Valid final CategoryDto resource,
      final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
    return createInternal(resource, uriBuilder, response);
  }

  // update

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public CategoryDto update(@PathVariable("id") final Long id,
      @RequestBody @Valid final CategoryDto resource) {
    return updateInternal(id, resource);
  }

  // delete

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") final Long id) {
    deleteByIdInternal(id);
  }

}
