package ru.mokb.mars.requirements.database.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "OBJECTS")
public class Object {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_OBJECTS")
	@SequenceGenerator(name="SEQ_OBJECTS", sequenceName="SEQ_OBJECTS_ID", allocationSize = 10, initialValue = 100)
	private Integer id;

	private String name;

	@OneToMany(mappedBy = "object")
	private List<TechnicalTask> technicalTaskList = new ArrayList<>();
}
