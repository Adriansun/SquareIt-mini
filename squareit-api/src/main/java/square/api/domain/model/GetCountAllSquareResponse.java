package square.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = GetCountAllSquareResponse.Builder.class)
public class GetCountAllSquareResponse {

  private Long countTot;

  private GetCountAllSquareResponse(Builder builder) {
    countTot = builder.countTot;
  }

  public Long getCountTot() {
    return countTot;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * Builder.
   * @param copy copy
   * @return countTot
   */
  public static Builder newBuilder(GetCountAllSquareResponse copy) {
    Builder builder = new Builder();
    builder.countTot = copy.getCountTot();
    return builder;
  }

  public static final class Builder {
    private Long countTot;

    private Builder() {
    }

    public Builder withCountTot(Long val) {
      countTot = val;
      return this;
    }

    public GetCountAllSquareResponse build() {
      return new GetCountAllSquareResponse(this);
    }
  }
}
