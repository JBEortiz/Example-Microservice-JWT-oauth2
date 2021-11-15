package com.example.demo.service;

import java.util.List;

public interface AbstractService<E,K> {
	
	List<E> getAll();
	
	E getById(K id);
	
	boolean save(E entity);

	boolean delete(K id);
	
	
	

}
