package ru.mokb.mars.requirements.database.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "`Связь отделов и подсистем`")
public class DepartmentSubsystem {
	@Id
	@Column(name = "Подсистема")
	private String subsystem;

	@Column(name = "Отдел")
	private String department;

}
