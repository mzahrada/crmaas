package cz.cvut.fit.zahrama8.crmaas.dao;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Transient;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

public class ObjectifyGenericDao<T> extends DAOBase implements GenericDAO<T> {
	
	static final int BAD_MODIFIERS = Modifier.FINAL | Modifier.STATIC | Modifier.TRANSIENT;
	
	protected Class<T> clazz;
	
	/**
	 * Writing a Constructor for each DAO that extends ObjectifyGenericDao not needed.
	 */
	@SuppressWarnings("unchecked")
	public ObjectifyGenericDao() {
		this.clazz = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	protected ObjectifyGenericDao(Class<T> clazz){
		this.clazz = clazz;
	}

	
	@Override
	public Key<T> put(T entity) {
		return ofy().put(entity);
	}
	@Override
	public Map<Key<T>, T> putAll(Iterable<T> entities) {
		return ofy().put(entities);
	}

	
	@Override
	public T get(Long id) throws EntityNotFoundException {
		return ofy().get(this.clazz, id);
	}
	@Override
	public T get(Key<T> key) throws EntityNotFoundException {
		return ofy().get(key);
	}
	@Override
	public Key<T> getKey(Long id) {
		return new Key<T>(clazz, id);
	}
	

	@Override
	public List<T> listAll() {
		return ofy().query(clazz).list();
//		Query<T> q = ofy().query(clazz);
//		return asList(q.fetch());
	}
	

	@Override
	public T getByProperty(String propName, Object propValue) {
		Query<T> q = ofy().query(clazz);
		q.filter(propName, propValue);
		return q.get();
	}
	@Override
	public List<T> listByProperty(String propName, Object propValue) {
		Query<T> q = ofy().query(clazz);
		q.filter(propName, propValue);
		return asList(q.fetch());
	}
	@Override
	public List<Key<T>> listKeysByProperty(String propName, Object propValue) {
		Query<T> q = ofy().query(clazz);
		q.filter(propName, propValue);
		return asKeyList(q.fetchKeys());
	}

	
	@Override
	public T getByExample(T exampleObj) {
		Query<T> queryByExample = buildQueryByExample(exampleObj);
		Iterable<T> iterableResults = queryByExample.fetch();
		Iterator<T> i = iterableResults.iterator();
		T obj = i.next();
		if (i.hasNext())
			throw new RuntimeException("Too many results");
		return obj;
	}
	@Override
	public List<T> listByExample(T exampleObj) {
		Query<T> queryByExample = buildQueryByExample(exampleObj);
		return asList(queryByExample.fetch());
	}

	
	@Override
	public void delete(T entity) {
		ofy().delete(entity);
	}
	@Override
	public void deleteAll(Iterable<T> entities) {
		ofy().delete(entities);
	}
	@Override
	public void deleteKey(Key<T> entityKey) {
		ofy().delete(entityKey);
	}
	@Override
	public void deleteKeys(Iterable<T> keys) {
		ofy().delete(keys);
	}
	
	
	private List<T> asList(Iterable<T> iterable){
		ArrayList<T> list = new ArrayList<T>();
		for(T t : iterable){
			list.add(t);
		}
		return list;
	}
	private List<Key<T>> asKeyList(Iterable<Key<T>> iterableKey){
		ArrayList<Key<T>> keys = new ArrayList<Key<T>>();
		for(Key<T> key : iterableKey){
			keys.add(key);
		}
		return keys;
	}
	private Query<T> buildQueryByExample(T exampleObj){
		Query<T> q = ofy().query(clazz);
		// Add all non-null properties to query filter
		for (Field field : clazz.getDeclaredFields()) {
			// Ignore transient, embedded, array, and collection properties
			if (field.isAnnotationPresent(Transient.class)
				|| (field.isAnnotationPresent(Embedded.class))
				|| (field.getType().isArray())
				|| (Collection.class.isAssignableFrom(field.getType()))
				|| ((field.getModifiers() & BAD_MODIFIERS) != 0))
				continue;
			field.setAccessible(true);
			Object value;
			try {
				value = field.get(exampleObj);
			}
			catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			}
			catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			if (value != null) {
				q.filter(field.getName(), value);
			}
		}
		return q;
	}

}
