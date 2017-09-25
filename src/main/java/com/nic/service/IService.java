package com.nic.service;

import com.nic.model.IEntity;

public interface IService<T extends IEntity> {

  public Iterable<T> findAll();

  T findOne(final long id);

  public T create(final T resourceDto);

  public T update(final T resourceDto);

  void delete(final long id);

  void deleteAll();

  long count();
}
