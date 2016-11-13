package de.doCode.homework.rest;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.junit.Test;

import de.doCode.homework.RestBase;

public class SimpleGetTest extends RestBase {

	@Test
	public void getWithAnyId(@ArquillianResteasyResource(PERSON_SERVICE) WebTarget webTarget) {
		Response response = callGetService(webTarget, 0);
		assertResponseIs(response, Status.NO_CONTENT);
	}

}
