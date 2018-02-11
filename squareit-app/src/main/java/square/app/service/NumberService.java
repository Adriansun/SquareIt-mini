package square.app.service;

import square.app.domain.jpa.SquareNumber;

public interface NumberService {

  Long saveNumber(int number);

  SquareNumber getNumberById(Long id);

  void deleteIdNumber(Long id);

  Long countAll();

  Iterable<SquareNumber> getAllItems();

}
