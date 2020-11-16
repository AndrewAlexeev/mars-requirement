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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "PIMS")
public class PIM {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_PIMS")
	@SequenceGenerator(name="SEQ_PIMS", sequenceName="SEQ_PIMS_ID", allocationSize = 10, initialValue = 100)
	private Integer id;

	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "MODE_OF_PIM",
			joinColumns = @JoinColumn(name = "PIM_ID"),
			inverseJoinColumns = @JoinColumn(name = "MODE_ID"))
	private List<Mode> modes = new ArrayList<>();
}
