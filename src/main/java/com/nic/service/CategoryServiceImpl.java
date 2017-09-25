package com.nic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nic.model.Category;
import com.nic.repository.CategoryRepository;

@Service
public class CategoryServiceImpl extends AbstractService<Category> implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	public CategoryServiceImpl() {
		super(Category.class);
	}

	@Override
	protected CategoryRepository getDao() {
		return categoryRepo;
	}

}
