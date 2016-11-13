package de.doCode.homework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;

import de.doCode.homework.model.Person;
import de.doCode.homework.model.PersonImpl;
import de.doCode.homework.rest.Service;

public abstract class RestBase extends RestBaseConfig {

	private static final Random RANDOM = new Random();

	protected Person getResponseForGrowingNameOrNull(String aGrowingName, Response response) {
		if (aGrowingName.length() < 3 || aGrowingName.length() > 20) {
			assertResponseIs(response, Status.BAD_REQUEST);
			return null;
		} else {
			assertValidResponseHasEntity(response);
			Person actual = castResponseToPerson(response);
			assertPersonName(actual, aGrowingName);
			return actual;
		}
	}

	protected void assertValidResponseHasEntity(Response response) {
		assertResponseIsHttp200(response);
		assertTrue(response.hasEntity());
	}

	protected void assertResponseIsHttp200(Response response) {
		assertResponseIs(response, Response.Status.OK);
	}

	protected Person castResponseToPerson(Response response) {
		assertNotNull(response);
		return (Person) response.readEntity(PersonImpl.class);
	}

	protected void assertPersonName(Person person, String expected) {
		assertNotNull(person);
		assertEquals(expected, person.getName());
	}

	protected Response callPostService(WebTarget webTarget, final String name) {
		return buildRequest(webTarget, Service.PERSON).post(createFormMapForName(name));
	}

	protected Response callPutService(WebTarget webTarget, int id, String name) {
		Response response = buildRequest(webTarget, Service.PERSON).put(createFormMap(id, name));
		return response;
	}

	protected Response callDeleteService(WebTarget webTarget, int id) {
		return callDelete(webTarget, Service.PERSON + "/" + id);
	}

	private Response callDelete(WebTarget webTarget, String path) {
		return buildRequest(webTarget, path).delete();
	}

	protected Response callGetService(WebTarget webTarget, int id) {
		return callGet(webTarget, Service.PERSON + "/" + id);
	}

	private Response callGet(WebTarget webTarget, String path) {
		return buildRequest(webTarget, path).get();
	}

	private Builder buildRequest(WebTarget webTarget, String path) {
		return webTarget.path(path).request();
	}

	protected Entity<Form> createFormMapForName(String name) {
		return Entity.form(ensurePut("name", name));
	}

	protected Entity<Form> createFormMap(int id, String name) {
		return Entity.form(savePut("id", String.valueOf(id), ensurePut("name", name)));
	}

	private MultivaluedMap<String, String> ensurePut(String key, String value) {
		return savePut(key, value, null);
	}

	private MultivaluedMap<String, String> savePut(String key, String value, MultivaluedMap<String, String> map) {
		if (map == null) {
			map = new MultivaluedHashMap<>();
		}
		map.putSingle(key, value);
		return map;
	}

	protected String createValidFixtureName() {
		return createValidFixtureName("aName-");
	}

	protected String createValidFixtureName(String prefix) {
		String max20 = StringUtils.truncate((prefix == null ? "" : prefix) + aRandomHexString() + System.nanoTime()
				+ aRandomHexString() + System.currentTimeMillis() + aRandomHexString(), 20);
		return max20.length() < 3 ? "123" : max20;
	}

	protected String aRandomHexString() {
		return Long.toHexString(RANDOM.nextLong());
	}

	protected void assertResponseIs(Response response, Response.Status status) {
		assertNotNull(response);
		assertEquals(status.getStatusCode(), response.getStatus());
	}

}
