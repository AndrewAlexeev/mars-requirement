package ru.mokb.mars.requirements.database.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "`Объекты`")
public class LAObject implements Serializable {

	@Id
	@Column(name = "Объект")
	private String id;

	@Column(name = "`Официальное название`")
	private String officialName;

	@OneToMany(mappedBy = "LAObject")
	private List<TechnicalTask> technicalTaskList = new ArrayList<>();

	@OneToMany(mappedBy = "LAObject")
	private List<PIM> pims = new ArrayList<>();

	@OneToMany(mappedBy = "LAObject")
	private List<Subsystem> subsystems = new ArrayList<>();
}
