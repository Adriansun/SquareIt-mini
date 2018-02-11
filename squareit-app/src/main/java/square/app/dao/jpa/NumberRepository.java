package square.app.dao.jpa;

import square.app.domain.jpa.SquareNumber;

import org.springframework.data.repository.CrudRepository;

public interface NumberRepository extends CrudRepository<SquareNumber, Long> {
}
