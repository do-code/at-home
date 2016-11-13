package de.doCode.homework.rest;

import javax.ws.rs.client.WebTarget;

import de.doCode.homework.RestBase;
import de.doCode.homework.model.Person;

public abstract class FunctionalBaseTest extends RestBase {

	protected Person readService(WebTarget webTarget, int id) {
		return castResponseToPerson(callGetService(webTarget, id));
	}

	protected Person createService(WebTarget webTarget, final String name) {
		return castResponseToPerson(callPostService(webTarget, name));
	}

	protected Person updateService(WebTarget webTarget, int id, String name) {
		return castResponseToPerson(callPutService(webTarget, id, name));
	}

	protected Person deleteService(WebTarget webTarget, int id) {
		return castResponseToPerson(callDeleteService(webTarget, id));
	}

}
