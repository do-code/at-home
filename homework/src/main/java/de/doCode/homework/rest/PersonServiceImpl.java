package de.doCode.homework.rest;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.doCode.homework.control.Controller;
import de.doCode.homework.model.Person;

@Default
@Path("/")
public class PersonServiceImpl implements Service {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private Controller controller;

	@Override
	public Person read(int id) {
		Person result = controller.read(id);
		logger.info("read('{}') -> '{}'", id, result);
		return result;
	}

	@Override
	public Person update(int id, String name) {
		Person result = controller.update(id, name);
		logger.info("update(id:'{}' with name:'{}') -> '{}'", id, name, result);
		return result;
	}

	@Override
	public Person create(String name) {
		Person result = controller.create(name);
		logger.info("create('{}') -> '{}'", name, result);
		return result;
	}

	@Override
	public Person delete(int id) {
		Person result = controller.delete(id);
		logger.info("delete('{}') -> '{}'", id, result);
		return result;
	}

	@Inject
	@Override
	public void setController(@Named("serviceController") Controller controller) {
		this.controller = controller;

	}

}
