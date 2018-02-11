package square.app.converters;

import square.api.domain.model.SquareDto;
import square.api.domain.model.SquareNumberDto;
import square.app.domain.jpa.SquareNumber;

public final class SquareConverter {

  private SquareConverter() {
  }

  /**
   * Convert number from database object to Dto and squares itself.
   * @param squareNumber squareNumber
   * @return squareDto
   */
  public static SquareDto toSquareDto(SquareNumber squareNumber) {
    return SquareDto.newBuilder()
        .withNumber((int) Math.pow(squareNumber.getNumber(), 2))
        .build();
  }

  /**
   * Convert number from database object to Dto and squares itself.
   * @param squareNumber squareNumber
   * @return squareDto
   */
  public static SquareNumberDto toSquareNumberDto(SquareNumber squareNumber) {
    return SquareNumberDto.newBuilder()
        .withId(squareNumber.getId().intValue())
        .withNumber(squareNumber.getNumber())
        .build();
  }
}
