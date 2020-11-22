package ru.mokb.mars.requirements.database.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Отделы")
public class Department {
	@Id
	@Column(name = "Отдел")
	private String id;

	@Column(name = "Начальник")
	private String chief;

}
