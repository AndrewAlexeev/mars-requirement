package ru.mokb.mars.requirements.database.model;

import lombok.Data;
import ru.mokb.mars.requirements.components.converters.PositionConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "TECHNICAL_TASK_POINTS")
public class TechnicalTaskPoint {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TECHNICAL_TASK_POINTS")
	@SequenceGenerator(name = "SEQ_TECHNICAL_TASK_POINTS", sequenceName = "SEQ_TECHNICAL_TASK_POINTS_ID", allocationSize = 10, initialValue = 100)
	private Integer id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "technical_task_system_id", nullable = false)
	private TechnicalTaskSystem technicalTaskSystem;

	@Lob
	@Convert(converter = PositionConverter.class)
	@Column(name = "POSITION", nullable = false)
	private Position position;

	private String description;


}
