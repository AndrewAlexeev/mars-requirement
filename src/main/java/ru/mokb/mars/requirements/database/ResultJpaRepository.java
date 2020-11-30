package ru.mokb.mars.requirements.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mokb.mars.requirements.database.model.Result;

import java.util.Optional;

public interface ResultJpaRepository extends JpaRepository<Result, Integer> {
	Optional<Result> findByModePointId(Integer id);

	@Query(value = "SELECT id FROM MODE_OF_POINT WHERE MODE_ID = ? AND POINT_ID = ?", nativeQuery = true)
	Integer findModePointIdByModeAndPoint(Double modeId, Integer pointId);
}
