package de.doCode.homework.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="person")
public class PersonImpl implements Serializable, Person {

	private static final long serialVersionUID = -5712292507461054154L;

	public static final Person EMPTY_PERSON = new PersonImpl();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Size(min = 3, max = 20)
	private String name;

	public PersonImpl() {
		id = 0;
		name = "";
	}

	public PersonImpl(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(@NotNull @Size(min = 3, max = 20) String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Person [id=%d, name=%s]", id, name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonImpl other = (PersonImpl) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
