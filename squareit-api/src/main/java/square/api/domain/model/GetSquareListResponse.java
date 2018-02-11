package square.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = GetSquareListResponse.Builder.class)
public class GetSquareListResponse {

  private final Iterable<SquareNumberDto> squareNumberDtoList;

  private GetSquareListResponse(Builder builder) {
    squareNumberDtoList = builder.squareNumberDtoList;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * Builder.
   * @param copy copy
   * @return squareNumberDto
   */
  public static Builder newBuilder(GetSquareListResponse copy) {
    Builder builder = new Builder();
    builder.squareNumberDtoList = copy.getSquareNumberDtoList();
    return builder;
  }

  public Iterable<SquareNumberDto> getSquareNumberDtoList() {
    return squareNumberDtoList;
  }


  public static final class Builder {
    private Iterable<SquareNumberDto> squareNumberDtoList;

    private Builder() {
    }

    public Builder withSquareNumberDtoList(Iterable<SquareNumberDto> val) {
      squareNumberDtoList = val;
      return this;
    }

    public GetSquareListResponse build() {
      return new GetSquareListResponse(this);
    }
  }
}
