package de.doCode.homework.data;

import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.doCode.homework.model.Person;
import de.doCode.homework.model.PersonImpl;

@Default
@Named
public class Persistence implements Database {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	@Override
	@Transactional
	public Person saveNew(Person person) {
		try {
			entityManager.persist(person);
			entityManager.refresh(person);
			return person;
		} catch (PersistenceException e) {
			logger.warn("fail to savenNew", e);
		}

		return null;
	}

	@Override
	@Transactional
	public Person read(int id) {
		try {
			return entityManager.find(PersonImpl.class, id);
		} catch (PersistenceException e) {
			logger.warn("fail to read id:" + id, e);
		}
		return null;
	}

	@Override
	@Transactional
	public Person update(Person person) {
		try {
			return entityManager.merge(person);
		} catch (PersistenceException e) {
			logger.warn("fail to update person:" + person, e);
		}
		return null;
	}

	@Override
	@Transactional
	public Person delete(Person person) {
		try {
			entityManager.remove(entityManager.contains(person) ? person : entityManager.merge(person));
			return person;
		} catch (PersistenceException e) {
			logger.warn("fail to delete person:" + person, e);
		}
		return null;
	}

}
