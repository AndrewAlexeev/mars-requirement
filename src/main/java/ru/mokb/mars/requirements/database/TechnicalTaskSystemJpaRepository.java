package ru.mokb.mars.requirements.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mokb.mars.requirements.database.model.TechnicalTask;
import ru.mokb.mars.requirements.database.model.TechnicalTaskSystem;

import java.util.List;

@Repository
public interface TechnicalTaskSystemJpaRepository extends JpaRepository<TechnicalTaskSystem, Integer> {
	List<TechnicalTaskSystem> findAllByTechnicalTask(TechnicalTask technicalTask);
}
