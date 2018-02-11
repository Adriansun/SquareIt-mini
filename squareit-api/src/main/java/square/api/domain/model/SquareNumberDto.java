package square.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = SquareNumberDto.Builder.class)
public class SquareNumberDto {

  private final int id;

  private final int number;

  private SquareNumberDto(Builder builder) {
    id = builder.id;
    number = builder.number;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * Builder.
   * @param copy copy
   * @return squareNumberDto
   */
  public static Builder newBuilder(SquareNumberDto copy) {
    Builder builder = new Builder();
    builder.id = copy.getId();
    builder.number = copy.getNumber();
    return builder;
  }

  public int getId() {
    return id;
  }

  public int getNumber() {
    return number;
  }


  public static final class Builder {
    private int id;
    private int number;

    private Builder() {
    }

    public Builder withId(int val) {
      id = val;
      return this;
    }

    public Builder withNumber(int val) {
      number = val;
      return this;
    }

    public SquareNumberDto build() {
      return new SquareNumberDto(this);
    }
  }
}
