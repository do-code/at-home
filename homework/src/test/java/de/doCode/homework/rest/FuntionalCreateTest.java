package de.doCode.homework.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.ws.rs.client.WebTarget;

import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.junit.Test;

import de.doCode.homework.model.Person;

public class FuntionalCreateTest extends FunctionalBaseTest {

	@Test
	public void createReadUpdateDeleteTest(@ArquillianResteasyResource(PERSON_SERVICE) WebTarget webTarget) {
		logger.debug("start with functional test createReadUpdateDeleteTest");
		final String aFixtureName = createValidFixtureName();

		Person newPerson = createService(webTarget, aFixtureName);
		assertNotNull(newPerson);

		final int id = newPerson.getId();
		
		Person personRead = readService(webTarget, id);
		assertNotNull(personRead);

		assertEquals(newPerson, personRead);

		String newName = createValidFixtureName();
		Person updatedPerson = updateService(webTarget, id, newName);
		assertNotNull(updatedPerson);

		assertEquals(personRead, updatedPerson);
		assertNotEquals(personRead.getName(), updatedPerson.getName());

		assertPersonName(updatedPerson, newName);
		assertEquals(id, updatedPerson.getId());

		Person deletedPerson = deleteService(webTarget,id);
		assertNotNull(deletedPerson);

		assertNull(readService(webTarget, id));

	}

}
