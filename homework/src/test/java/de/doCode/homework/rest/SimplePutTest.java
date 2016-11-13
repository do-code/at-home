package de.doCode.homework.rest;

import static org.junit.Assert.*;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.junit.Test;

import de.doCode.homework.RestBase;
import de.doCode.homework.model.Person;

public class SimplePutTest extends RestBase {

	@Test
	public void putSimpleWithSuccess(@ArquillianResteasyResource(PERSON_SERVICE) WebTarget webTarget) {
		final String aFixtureName = createValidFixtureName();
		Response response = callPostService(webTarget, aFixtureName);
		assertValidResponseHasEntity(response);
		final Person fixture = castResponseToPerson(response);

		String newName = "newName1";
		Person updatedPerson = castResponseToPerson(callPutService(webTarget, fixture.getId(), newName));
		assertEquals(newName, updatedPerson.getName());
		assertEquals(fixture, updatedPerson);
		assertNotEquals(fixture.getName(), updatedPerson.getName());

		newName = "newName2";
		updatedPerson = castResponseToPerson(callPutService(webTarget, fixture.getId(), newName));
		assertEquals(newName, updatedPerson.getName());
		assertEquals(fixture, updatedPerson);
		assertNotEquals(fixture.getName(), updatedPerson.getName());

		// one idempotent-test
		updatedPerson = castResponseToPerson(callPutService(webTarget, fixture.getId(), newName));
		assertEquals(newName, updatedPerson.getName());
		assertEquals(fixture, updatedPerson);
		assertNotEquals(fixture.getName(), updatedPerson.getName());
	}

	@Test
	public void putWithLengthValidation(@ArquillianResteasyResource(PERSON_SERVICE) WebTarget webTarget) {
		Person validNewPerson = castResponseToPerson(callPostService(webTarget, "1234"));
		final int id = validNewPerson.getId();
		String aGrowingName = "";

		for (int i = 0; i < 30; i++) {
			logger.debug("try to putWithLengthValidation:{}", aGrowingName);
			Response response = callPutService(webTarget, id, aGrowingName);

			Person perhapsUpdated = getResponseForGrowingNameOrNull(aGrowingName, response);
			if (perhapsUpdated != null) {
				assertEquals(validNewPerson, perhapsUpdated);
			}
			aGrowingName += "" + (i % 10);
		}
	}

	@Test
	public void putWithUnknownId(@ArquillianResteasyResource(PERSON_SERVICE) WebTarget webTarget) {
		Response response = callPutService(webTarget, 0, "anyName");
		assertResponseIs(response, Status.NO_CONTENT);
	}

}
