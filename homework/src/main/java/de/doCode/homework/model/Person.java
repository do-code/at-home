package de.doCode.homework.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public interface Person extends Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId();

	public void setId(int id);

	public String getName();

	public void setName(@NotNull @Size(min = 3, max = 20) String name);

}