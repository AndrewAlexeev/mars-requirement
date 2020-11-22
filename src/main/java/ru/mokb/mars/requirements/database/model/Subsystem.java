package ru.mokb.mars.requirements.database.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "subsystems")
public class Subsystem {
	@Id
	private Integer id;

	@Column(name = "Подсистема")
	private String name;

	@ManyToOne
	@JoinColumn(name="Объект", nullable=false)
	private LAObject LAObject;
}
