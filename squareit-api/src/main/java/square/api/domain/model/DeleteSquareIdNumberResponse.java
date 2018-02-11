package square.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = GetCountAllSquareResponse.Builder.class)
public class DeleteSquareIdNumberResponse {

  private String statusText;

  private DeleteSquareIdNumberResponse(Builder builder) {
    statusText = builder.statusText;
  }

  public String getStatusText() {
    return statusText;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * Builder.
   * @param copy copy
   * @return countTot
   */
  public static Builder newBuilder(DeleteSquareIdNumberResponse copy) {
    Builder builder = new Builder();
    builder.statusText = copy.getStatusText();
    return builder;
  }

  public static final class Builder {
    private String statusText;

    private Builder() {
    }

    public Builder withStatusText(String val) {
      statusText = val;
      return this;
    }

    public DeleteSquareIdNumberResponse build() {
      return new DeleteSquareIdNumberResponse(this);
    }
  }
}
