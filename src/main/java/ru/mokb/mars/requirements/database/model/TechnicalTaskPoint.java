package ru.mokb.mars.requirements.database.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "TECHNICAL_TASK_POINTS")
public class TechnicalTaskPoint {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_TECHNICAL_TASK_POINTS")
	@SequenceGenerator(name="SEQ_TECHNICAL_TASK_POINTS", sequenceName="SEQ_TECHNICAL_TASK_POINTS_ID", allocationSize = 10, initialValue = 100)
	private Integer id;

	private String name;

	@ManyToOne
	@JoinColumn(name="technical_task_id", nullable=false)
	private TechnicalTask technicalTask;
}
