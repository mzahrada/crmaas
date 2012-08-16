package cz.cvut.fit.zahrama8.crmaas.dao;

import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;

public interface GenericDAO<T> {

	public Key<T> put(T entity);
	public Map<Key<T>, T> putAll(Iterable<T> entities);
	
	public T get(Long id) throws EntityNotFoundException;
	public T get(Key<T> key) throws EntityNotFoundException;
	
	/**
	 * Generates typed Key<T> for given id.
	 */
	public Key<T> getKey(Long id);
	
	/**
	 * Gets all entities of type <T> from datastore. Inefficient?!
	 */
	public List<T> listAll();
	
	/**
	 * Gets all object matching a single property.
	 */
	public T getByProperty(String propName, Object propValue);
	public List<T> listByProperty(String propName, Object propValue);
	public List<Key<T>> listKeysByProperty(String propName, Object propValue);
	
	public T getByExample(T exampleObj);
	public List<T> listByExample(T exampleObj);
	
	public void delete(T entity);
	public void deleteAll(Iterable<T> entities);
	public void deleteKey(Key<T> entityKey);
	public void deleteKeys(Iterable<T> keys);
	
}