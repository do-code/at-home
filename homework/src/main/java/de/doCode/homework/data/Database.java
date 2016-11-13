package de.doCode.homework.data;

import de.doCode.homework.model.Person;

public interface Database {

	public Person saveNew(Person person);

	public Person read(int id);

	public Person update(Person person);

	public Person delete(Person person);

}