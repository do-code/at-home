package de.doCode.homework.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.doCode.homework.control.Controller;
import de.doCode.homework.model.Person;

public interface Service extends Controller {

	public static final String PERSON = "/person";

	@Inject
	public void setController(Controller controller);

	/**
	 * Reads a {@link Person} if found
	 * <p>
	 * 
	 * @param id
	 * @return {@link Person} or null on fail
	 */
	@GET
	@Path(Service.PERSON + "/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person read(@PathParam("id") int id);

	/**
	 * Creates a ne {@link Person}
	 * <p>
	 * PUT and POST are both unsafe methods. However, PUT is idempotent, while
	 * POST is not. So POST creates.
	 * 
	 * @param name
	 * @return a {@link Person} or null on fail
	 */
	@POST
	@Path(Service.PERSON)
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA })
	@Produces(MediaType.APPLICATION_JSON)
	public Person create(@FormParam("name") String name);

	/**
	 * Updates a {@link Person} if found
	 * <p>
	 * PUT and POST are both unsafe methods. However, PUT is idempotent, while
	 * POST is not. So PUT updates.
	 * 
	 * @param id
	 * @param name
	 * @return updated Person on success or null on fail
	 */
	@PUT
	@Path(Service.PERSON)
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Person update(@FormParam("id") int id, @FormParam("name") String name);

	/**
	 * Deletes a {@link Person} if found
	 * <p>
	 * 
	 * @param id
	 * @return deleted {@link Person} or null on fail
	 */
	@DELETE
	@Path(Service.PERSON + "/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person delete(@PathParam("id") int id);

}