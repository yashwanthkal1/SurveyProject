package com.nic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.nic.model.IEntity;
import com.nic.web.ServicePreconditions;

@Transactional
public abstract class AbstractService<T extends IEntity> implements IService<T> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private Class<T> clazz;

	public AbstractService(final Class<T> clazzToSet) {
		super();
		this.clazz = clazzToSet;
	}

	// find - one
	@Override
	@Transactional(readOnly = true)
	public T findOne(final long id) {
		T entity = getDao().findOne(id);
		return entity;
	}

	// find - all
	@Override
	@Transactional(readOnly = true)
	public Iterable<T> findAll() {
		Iterable<T> resources = getDao().findAll();
		return resources;
	}

	@Override
	public T create(final T entity) {
		Preconditions.checkNotNull(entity);
		T savedEntity = getDao().save(entity);
		return savedEntity;
	}

	// update/merge

	@Override
	public T update(final T entity) {
		Preconditions.checkNotNull(entity);
		T updatedEntity = getDao().save(entity);
		return updatedEntity;
	}

	// delete

	@Override
	public void deleteAll() {
		getDao().deleteAll();
	}

	@Override
	public void delete(final long id) {
		final T entity = getDao().findOne(id);
		ServicePreconditions.checkEntityExists(entity);

		getDao().delete(entity);
	}

	// count
	@Override
	public long count() {
		return getDao().count();
	}

	// template method
	protected abstract PagingAndSortingRepository<T, Long> getDao();
}
