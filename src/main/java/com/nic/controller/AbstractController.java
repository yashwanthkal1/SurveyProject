package com.nic.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.base.Preconditions;
import com.nic.dto.IDto;
import com.nic.model.IEntity;
import com.nic.service.IService;
import com.nic.web.RestPreconditions;
import com.nic.web.exception.MyResourceNotFoundException;

public abstract class AbstractController<D extends IDto, E extends IEntity> {

	protected Class<D> dtoClazz;
	protected Class<E> entityClazz;

	@Autowired
	protected ModelMapper modelMapper;

	@Autowired
	public AbstractController(final Class<D> dtoClazz, Class<E> entityClazz) {
		super();
		Preconditions.checkNotNull(dtoClazz);
		this.dtoClazz = dtoClazz;
		this.entityClazz = entityClazz;
	}

	// save/create/persist

	protected final D createInternal(final D resource, final UriComponentsBuilder uriBuilder,
			final HttpServletResponse response) {
		RestPreconditions.checkRequestElementNotNull(resource);
		E entity = modelMapper.map(resource, entityClazz);
		final E existingResource = getService().create(entity);
		return modelMapper.map(existingResource, dtoClazz);
	}

	// update

	/**
	 * - note: the operation is IDEMPOTENT <br/>
	 */
	protected final D updateInternal(final long id, final D resource) {
		RestPreconditions.checkRequestElementNotNull(resource);
		RestPreconditions.checkRequestElementNotNull(resource.getId());
		RestPreconditions.checkNotNull(getService().findOne(resource.getId()));

		E entity = modelMapper.map(resource, entityClazz);
		final E updatedResource = getService().update(entity);
		return modelMapper.map(updatedResource, dtoClazz);
	}

	// delete/remove

	protected final void deleteByIdInternal(final long id) {
		// InvalidDataAccessApiUsageException - ResourceNotFoundException
		// IllegalStateException - ResourceNotFoundException
		// DataAccessException - ignored
		getService().delete(id);
	}

	// find - one
	protected final D findOneInternal(final Long id) {
		final E resource = getService().findOne(id);
		RestPreconditions.checkNotNull(resource);
		return modelMapper.map(resource, dtoClazz);
	}

	// find - all

	protected final List<D> findAllInternal(final HttpServletRequest request) {
		if (request.getParameterNames().hasMoreElements()) {
			throw new MyResourceNotFoundException();
		}

		final Iterable<E> resources = getService().findAll();
		return newArrayList(resources);
	}

	// template method

	protected abstract IService<E> getService();

	public List<D> newArrayList(Iterable<E> elements) {
		Preconditions.checkNotNull(elements);
		List<D> dtos = new ArrayList<>();
		for (E model : elements) {
			dtos.add(modelMapper.map(model, dtoClazz));
		}
		return dtos;
	}
}
