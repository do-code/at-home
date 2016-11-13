package de.doCode.homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.enterprise.inject.Default;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.doCode.homework.data.Database;
import de.doCode.homework.model.Person;

@Singleton
@Default
public class UnitTestHashMapDatabase implements Database {

	private final Random rnd = new Random();

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Map<Integer, Person> db = new HashMap<>();

	public UnitTestHashMapDatabase() {
		logger.info("db started!");
	}

	@Override
	public Person saveNew(Person person) {
		logger.trace("save:'{}'", person);
		person.setId(randomId());
		if (read(person.getId()) != null) {
			logger.warn("duplicate found to id:'{}' - saveNew fails", person.getId());
			return null;
		}
		db.put(person.getId(), person);
		logger.debug("saved new Person:{}", person);
		return person;
	}

	private int randomId() {
		return rnd.nextInt();
	}

	@Override
	public Person update(Person person) {
		logger.trace("update:{}", person);
		Person oldPerson = read(person.getId());
		if (oldPerson == null) {
			logger.warn("not found id:'{}' - update fails", person.getId());
			return null;
		}
		db.put(person.getId(), person);
		logger.debug("update:{} old:{}", person, oldPerson);
		return person;
	}

	@Override
	public Person read(int id) {
		logger.trace("read:{}", id);
		Person result = db.get(id);
		logger.debug("read:{} found:{}", id, result);
		return result;
	}

	@Override
	public Person delete(Person person) {
		logger.trace("delete:{}", person);

		Person oldPerson = db.remove(person.getId());
		if (oldPerson == null) {
			logger.warn("not found id:'{}' - delete fails", person.getId());
			return null;
		}
		logger.debug("delete:{}", oldPerson);
		return oldPerson;
	}

}
