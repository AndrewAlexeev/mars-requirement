package ru.mokb.mars.requirements.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mokb.mars.requirements.database.model.LAObject;

@Repository
public interface ObjectJpaRepository extends JpaRepository<LAObject, String> {
}
