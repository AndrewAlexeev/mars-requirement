package ru.mokb.mars.requirements.database.model;


import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ПиМы")
public class PIM {

	@Id
	@Column(name = "`Идентификатор ПиМа`")
	private Double id;

	@Column(name = "ПиМ")
	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "`ПиМы Режимов`",
			joinColumns = @JoinColumn(name = "`Ид ПиМа`"),
			inverseJoinColumns = @JoinColumn(name = "Режим"))
	private List<Mode> modes = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name="Объект", nullable=false)
	private LAObject LAObject;
}
