package basecamp.repository;

import basecamp.domain.NumberEntity;
import org.springframework.data.repository.CrudRepository;

public interface NumberEntityRepository extends CrudRepository<NumberEntity, Integer> {
}
