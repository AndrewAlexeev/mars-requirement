package ru.mokb.mars.requirements.database.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "TECHNICAL_TASKS")
public class TechnicalTask {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_TECHNICAL_TASK")
	@SequenceGenerator(name="SEQ_TECHNICAL_TASK", sequenceName="SEQ_TECHNICAL_TASK_ID", allocationSize = 10, initialValue = 100)
	private Integer id;

	private String name;
	private String path;

	@ManyToOne
	@JoinColumn(name="object_id", nullable=false)
	private LAObject LAObject;

	@OneToMany(mappedBy = "technicalTask")
	private List<TechnicalTaskSystem> technicalTaskSystems = new ArrayList<>();

}
