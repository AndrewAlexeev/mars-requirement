package ru.mokb.mars.requirements.database.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Режимы")
public class Mode {

	@Id
	@Column(name = "`Идентификатор режима`")
	private Double id;

	@Column(name = "`Моделируемый режим`")
	private String simulatedMode;

	@ManyToMany(mappedBy = "modes",cascade = CascadeType.ALL)
	private List<PIM> pims = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "MODE_OF_POINT",
			joinColumns = @JoinColumn(name = "MODE_ID"),
			inverseJoinColumns = @JoinColumn(name = "POINT_ID"))
	private List<TechnicalTaskPoint> technicalTaskPoints = new ArrayList<>();

}