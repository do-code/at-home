package de.doCode.homework.rest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.junit.Test;

import de.doCode.homework.RestBase;
import de.doCode.homework.model.Person;

public class SimplePostTest extends RestBase {

	@Test
	public void postWithEmptyNameParameter(@ArquillianResteasyResource(PERSON_SERVICE) WebTarget webTarget) {
		Response response = callPostService(webTarget, "");
		assertResponseIs(response, Status.BAD_REQUEST);
	}

	@Test
	public void postWithoutParameters(@ArquillianResteasyResource(PERSON_SERVICE) WebTarget webTarget) {
		Response response = webTarget.path(Service.PERSON).request().post(Entity.form((Form) null));
		assertResponseIs(response, Status.BAD_REQUEST);
	}

	@Test
	public void simpleValidPost(@ArquillianResteasyResource(PERSON_SERVICE) WebTarget webTarget) {
		final String aFixtureName = createValidFixtureName();
		Response response = callPostService(webTarget, aFixtureName);
		assertValidResponseHasEntity(response);
		// logger.trace("PUT-Response:'{}'", response.readEntity(String.class));
		Person person = castResponseToPerson(response);

		// but ignore inserted value
		assertPersonName(person, aFixtureName);
	}

	@Test
	public void postWithLengthValidation(@ArquillianResteasyResource(PERSON_SERVICE) WebTarget webTarget) {
		String aGrowingName = "";

		for (int i = 0; i < 30; i++) {
			logger.debug("try to postWithLengthValidation:{}", aGrowingName);
			Response response = callPostService(webTarget, aGrowingName);
			getResponseForGrowingNameOrNull(aGrowingName, response);
			aGrowingName += "" + (i % 10);
		}
	}

}
