package ru.mokb.mars.requirements.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mokb.mars.requirements.database.model.TechnicalTaskPoint;
import ru.mokb.mars.requirements.database.model.TechnicalTaskSystem;

import java.util.List;

@Repository
public interface TechnicalTaskPointJpaRepository extends JpaRepository<TechnicalTaskPoint, Integer> {
	List<TechnicalTaskPoint> findAllByTechnicalTaskSystem(TechnicalTaskSystem technicalTaskSystem);
}
