package de.doCode.homework.control;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.doCode.homework.data.Database;
import de.doCode.homework.model.Person;
import de.doCode.homework.model.PersonImpl;

@Named
@Default
public class ServiceController implements Controller {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private Database db;

	@Override
	public Person create(String name) {
		if (isNameInvalid(name)) {
			return null;
		}
		Person toSave = new PersonImpl();
		toSave.setName(name);
		Person saved = db.saveNew(toSave);
		if (saved != null) {
			logger.debug("person saved:{}", saved);
			return saved;
		}
		logger.warn("no Person saved for name:'{}'", name);
		return null;
	}

	@Override
	public Person update(int id, String name) {
		if (isNameValid(name)) {
			Person person = db.read(id);
			if (person != null) {
				final String oldName = person.getName();
				person.setName(name);
				Person updated = db.update(person);
				if (updated != null) {
					logger.debug("person updated:{} oldName:'{}'", updated, oldName);
					return updated;
				}
			}
		}
		logger.warn("no Person updated for id:'{}' name:'{}'", id, name);
		return null;
	}

	boolean isNameValid(String name) {
		return !isNameInvalid(name);
	}

	boolean isNameInvalid(String name) {
		name = StringUtils.trimToEmpty(name);
		if (StringUtils.isBlank(name)) {
			return true;
		}
		if (name.length() < 3 || name.length() > 20) {
			return true;
		}
		return false;
	}

	@Override
	public Person read(int id) {
		Person found = db.read(id);
		if (found == null) {
			logger.warn("no Person found for invalid id:'{}'", id);
		}
		return found;
	}

	@Override
	public Person delete(int id) {
		Person found = db.read(id);
		if (found != null) {
			Person deleted = db.delete(found);
			if (deleted != null) {
				logger.debug("person deleted:{}", deleted);
				return deleted;
			}
		}

		logger.warn("no Person deleted for id:'{}'", id);
		return null;
	}

	@Inject
	public void setDatabase(@Named("persistence") Database database) {
		db = database;
	}
}
