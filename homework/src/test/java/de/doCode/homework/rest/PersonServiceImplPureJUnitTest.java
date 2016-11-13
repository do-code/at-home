package de.doCode.homework.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.doCode.homework.RestBase;
import de.doCode.homework.UnitTestHashMapDatabase;
import de.doCode.homework.control.Controller;
import de.doCode.homework.control.ServiceController;
import de.doCode.homework.model.Person;
import javax.inject.Named;

public class PersonServiceImplPureJUnitTest extends RestBase {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private Service service;
	@Inject @Named
	private Controller controller;

	@Before
	public void mockServiceCauseInectionSeemsNotFunction() {
		service = new PersonServiceImpl();
		controller = new ServiceController();
		((ServiceController) controller).setDatabase(new UnitTestHashMapDatabase());
		service.setController(controller);
	}

	@Test
	public void testRead() throws Exception {
		logger.debug("run testRead");

		final String fixture = "123";
		final int id = service.create(fixture).getId();
		assertNotNull(service.read(id));

		assertEquals(service.read(id).getId(), id);
		assertEquals(service.read(id).getName(), fixture);

	}

	@Test
	public void testCreate() throws Exception {
		logger.debug("run testCreate");
		assertNull(service.create(null));
		assertNull(service.create(""));
		assertNull(service.create("  "));
		assertNull(service.create("ab"));
		assertNull(service.create("123456789012345678901"));

		final String fixture = "123";
		final Person created = service.create(fixture);
		assertNotNull(created);
		assertNotNull(created.getId());
		assertNotNull(created.getName());
		assertEquals(fixture, created.getName());

	}

	@Test
	public void testUpdate() throws Exception {
		logger.debug("run testUpdate");

		final String fixtureName = "123";
		final Person created = service.create(fixtureName);

		final String newName = "new";
		Person updated = service.update(created.getId(), newName);
		assertNotNull(updated);
		assertNotNull(updated.getId());
		assertNotNull(updated.getName());
		assertEquals(created, updated);
		assertTrue(created == updated);
		// noSenseAnnymore ;-) at this point
		assertEquals(created.getName(), updated.getName());
		assertEquals(newName, updated.getName());

	}

	@Test
	public void testDelete() throws Exception {
		logger.debug("run testDelete");

		final String fixture = "123";
		final Person created = service.create(fixture);
		assertNotNull(service.read(created.getId()));

		Person deleted = service.delete(created.getId());
		assertNotNull(deleted);
		assertEquals(created, deleted);

		assertNull(service.read(created.getId()));
	}
}
