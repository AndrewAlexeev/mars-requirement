package ru.mokb.mars.requirements.database.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "results")
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESULT")
	@SequenceGenerator(name = "SEQ_RESULT", sequenceName = "SEQ_RESULT_ID", allocationSize = 10, initialValue = 100)
	private Integer id;
	@Column(name = "mode_point_id")
	private Integer modePointId;


	private String body;

}
