package ru.mokb.mars.requirements.database.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "TECHNICAL_TASK_SYSTEM")
public class TechnicalTaskSystem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_TECHNICAL_TASK_SYSTEM")
	@SequenceGenerator(name="SEQ_TECHNICAL_TASK_SYSTEM", sequenceName="SEQ_TECHNICAL_TASK_SYSTEM_ID", allocationSize = 10, initialValue = 100)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="technical_task_id", nullable=false)
	private TechnicalTask technicalTask;

	@OneToOne
	@JoinColumn(name = "system_id")
	private Subsystem subsystem;
}
