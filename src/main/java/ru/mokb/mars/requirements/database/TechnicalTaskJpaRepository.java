package ru.mokb.mars.requirements.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mokb.mars.requirements.database.model.LAObject;
import ru.mokb.mars.requirements.database.model.TechnicalTask;

import java.util.List;

@Repository
public interface TechnicalTaskJpaRepository extends JpaRepository<TechnicalTask, Integer> {
	List<TechnicalTask> findAllByLAObject(LAObject laObject);
}
