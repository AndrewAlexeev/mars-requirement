package ru.mokb.mars.requirements.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mokb.mars.requirements.database.model.Mode;

@Repository
public interface ModeJpaRepository extends JpaRepository<Mode, Integer> {
}
