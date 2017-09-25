package com.nic.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.nic.model.Category;
import com.nic.model.Question;
import com.nic.service.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CategoryService categoryService;

	private Category category1;

	// Default test data
	@Before
	public void beforeTest() {
		category1 = new Category();
		Question question1 = new Question();
		question1.setQuestion("category1 Question 1");
		// questionServiceImpl.addQuestion(question1);
		Question question2 = new Question();
		question2.setQuestion("category1 Question 2");
		// questionServiceImpl.addQuestion(question2);
		Set<Question> questions = new HashSet<>();
		questions.add(question1);
		questions.add(question2);
		category1.setCategoryName("demo catrgory1");
		category1.setQuestions(questions);
		category1 = categoryService.create(category1);
	}

	@After
	public void afterTest() {
		categoryService.deleteAll();
	}

	@Test
	public void testGetAllCategories() throws IOException {

		Category[] categories = restTemplate.getForObject("/categories", Category[].class);

		assertNotNull(categories);
		assertEquals(1, categories.length);

	}

	@Test
	public void testGetCategory() {
		Category category = restTemplate.getForObject("/categories/" + category1.getId(), Category.class);
		assertNotNull(category);
		assertEquals("demo catrgory1", category.getCategoryName());
	}

	// Not sure
	@Test
	public void testUpdateCategory() {
		category1.setCategoryName("updated category 1");
		RequestEntity<Category> reqEntity = new RequestEntity<Category>(category1, HttpMethod.PUT,
				URI.create("/categories/" + category1.getId()));

		ResponseEntity<Category> respCategoryDto = restTemplate.exchange(reqEntity, Category.class);

		// restTemplate.
		assertNotNull(respCategoryDto);
		assertEquals("updated category 1", respCategoryDto.getBody().getCategoryName());
	}

	@Test
	public void testPostCategory() {
		Category category2 = new Category();
		category2.setCategoryName("Category from post");
		RequestEntity<Category> reqEntity = new RequestEntity<Category>(category2, HttpMethod.POST,
				URI.create("/categories"));

		ResponseEntity<Category> respCategoryDto = restTemplate.exchange(reqEntity, Category.class);

		// restTemplate.
		assertNotNull(respCategoryDto);
		assertEquals("Category from post", respCategoryDto.getBody().getCategoryName());
	}

	@Test
	public void testDeleteCategory() throws IOException {

		RequestEntity<Category> reqEntity = new RequestEntity<Category>(HttpMethod.DELETE,
				URI.create("/categories/" + category1.getId()));
		ResponseEntity<Object> respCategoryDto = restTemplate.exchange(reqEntity, Object.class);
		assertNotNull(restTemplate.getForObject("/categories/" + category1.getId(), Category.class));
	}

}
