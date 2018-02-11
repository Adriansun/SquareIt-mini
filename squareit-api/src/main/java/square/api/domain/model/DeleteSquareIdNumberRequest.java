package square.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = DeleteSquareIdNumberRequest.Builder.class)
public class DeleteSquareIdNumberRequest {

  @NotNull(message = "ID of number must not be empty")
  @Valid
  private final Long numberId;

  private DeleteSquareIdNumberRequest(Builder builder) {
    numberId = builder.numberId;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * Builder.
   * @param copy copy
   * @return numberId
   */
  public static Builder newBuilder(DeleteSquareIdNumberRequest copy) {
    Builder builder = new Builder();
    builder.numberId = copy.getNumberId();
    return builder;
  }

  public Long getNumberId() {
    return numberId;
  }


  public static final class Builder {
    private Long numberId;

    private Builder() {
    }

    public Builder withNumberId(Long val) {
      numberId = val;
      return this;
    }

    public DeleteSquareIdNumberRequest build() {
      return new DeleteSquareIdNumberRequest(this);
    }
  }
}
