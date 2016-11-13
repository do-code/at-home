package de.doCode.homework.control;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.doCode.homework.model.Person;

public interface Controller {

	@Valid
	public Person read(@NotNull int id);

	@Valid
	public Person create(@NotNull @Size(min = 3, max = 20) String name);

	@Valid
	public Person update(@NotNull int id, @Size(min = 3, max = 20) String name);

	@Valid
	public Person delete(@NotNull int id);

}
