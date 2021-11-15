package com.example.demo.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractServiceImpl<E, K extends Serializable> implements AbstractService<E, K> {

	@Autowired
	protected JpaRepository<E, K> repository;

	@Override
	@Cacheable("person")
	public List<E> getAll() {

		return repository
				.findAll();
	}

	@Override
	public E getById(K id) {

		return repository
				.findById(id)
				.orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public boolean save(E entity) {

		if (entity != null) {
			repository.save(entity);
			return true;
		}

		return false;
	}

	@Override
	@CacheEvict("person")
	public boolean delete(K id) {

		E entity = repository
				.findById(id)
				.orElseThrow(EntityNotFoundException::new);

		if (entity != null) {
			repository.delete(entity);
			return true;
		}

		return false;
	}

}
