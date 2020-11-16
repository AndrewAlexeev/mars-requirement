package ru.mokb.mars.requirements.database.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "MODES")
public class Mode {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MODES")
	@SequenceGenerator(name = "SEQ_MODES", sequenceName = "SEQ_MODES_ID", allocationSize = 10, initialValue = 100)
	private Integer id;

	private String name;

	@ManyToMany(mappedBy = "modes",cascade = CascadeType.ALL)
	private List<PIM> pims = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "MODE_OF_POINT",
			joinColumns = @JoinColumn(name = "MODE_ID"),
			inverseJoinColumns = @JoinColumn(name = "POINT_ID"))
	private List<TechnicalTaskPoint> technicalTaskPoints = new ArrayList<>();

}