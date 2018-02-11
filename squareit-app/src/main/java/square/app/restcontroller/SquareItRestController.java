package square.app.restcontroller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import square.api.domain.model.*;
import square.app.converters.SquareConverter;
import square.app.domain.jpa.SquareNumber;
import square.app.exceptions.ObjectNotFoundException;
import square.app.restcontroller.baserestcontroller.BaseRestController;
import square.app.service.NumberService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/square/number")
public class SquareItRestController extends BaseRestController {

  private static final Logger log = LoggerFactory.getLogger(SquareItRestController.class);

  private final NumberService numberService;

  /**
   * SquareItRestController rest service.
   * @param numberService numberService
   */
  @Autowired
  public SquareItRestController(final NumberService numberService) {
    this.numberService = numberService;
  }

  /**
   * Save a number rest-service.
   * @param request Request object from the service request.
   */
  @ApiOperation(value = "saveNumber", notes = "Save a number in the database.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Number saved"),
      @ApiResponse(code = 400, message = "Malformed request"),
      @ApiResponse(code = 404, message = "Number not found")})
  @RequestMapping(method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE,
      value = "/v1/setNumber")
  @ResponseBody
  public CreateSquareResponse saveNumber(@RequestBody @Valid final CreateSquareRequest request) {
    try {
      final Long numberId = numberService.saveNumber(request.getNumber());

      return new CreateSquareResponse().withId(numberId);
    } catch (IllegalArgumentException | ObjectNotFoundException e) {
      log.info("Cannot save number: {}. Exception {}.", request.getNumber(), e.toString());
      throw e;
    } catch (RuntimeException e) {
      log.error("Error when saving number: " + request.getNumber() + ".", e);
      throw e;
    }
  }

  /**
   * Get a number rest-service.
   */
  @ApiOperation(value = "getNumber", notes = "Get a number from the database.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Number found"),
      @ApiResponse(code = 404, message = "Number not found")})
  @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, value = "/v1/getNumber/{number_id}")
  @ResponseBody
  public GetSquareResponse getNumber(@PathVariable("number_id") @Valid final Long id) {
    try {
      SquareNumber squareNumb = numberService.getNumberById(id);
      SquareDto squareDto = SquareConverter.toSquareDto(squareNumb);

      return GetSquareResponse.newBuilder().withSquareDto(squareDto).build();
    } catch (IllegalArgumentException | ObjectNotFoundException e) {
      log.info("Cannot fetch number with id: {}. Exception {}", id, e.toString());
      throw e;
    } catch (RuntimeException e) {
      log.error("Error when fetching id: " + id, e);
      throw e;
    }
  }

  /**
   * Delete a number rest-service.
   * @param request Request object from the service request.
   */
  @ApiOperation(value = "deleteNumber", notes = "Deletes a number in the database by id.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Number ID deleted"),
      @ApiResponse(code = 400, message = "Malformed request"),
      @ApiResponse(code = 404, message = "Number ID not found")})
  @RequestMapping(method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE,
      value = "/v1/deleteId")
  @ResponseBody
  public DeleteSquareIdNumberResponse deleteNumber(@RequestBody @Valid final DeleteSquareIdNumberRequest request) {
    try {
      numberService.deleteIdNumber(request.getNumberId());

      return DeleteSquareIdNumberResponse.newBuilder().withStatusText("OK").build();
    } catch (IllegalArgumentException | ObjectNotFoundException e) {
      log.info("Cannot delete ID-number: {}. Exception {}.", request.getNumberId(), e.toString());
      throw e;
    } catch (RuntimeException e) {
      log.error("Error when deleting ID-number: " + request.getNumberId() + ".", e);
      throw e;
    }
  }

  /**
   * Count all rows in the database rest-service.
   */
  @ApiOperation(value = "getCount", notes = "Count all numbers in the database.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Count done"),
      @ApiResponse(code = 400, message = "Malformed request"),
      @ApiResponse(code = 404, message = "Could not count")})
  @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, value = "/v1/countNumbers")
  @ResponseBody
  public GetCountAllSquareResponse getCount() {
    try {
      Long countTot = numberService.countAll();

      return GetCountAllSquareResponse.newBuilder().withCountTot(countTot).build();
    } catch (IllegalArgumentException | ObjectNotFoundException e) {
      log.info("Cannot count items. Exception {}", e.toString());
      throw e;
    } catch (RuntimeException e) {
      log.error("Error when counting all rows.", e);
      throw e;
    }
  }

  /**
   * Get list of all items of the database rest-service.
   */
  @ApiOperation(value = "getListItems", notes = "Get a list of all items in database.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "List returned"),
      @ApiResponse(code = 400, message = "Malformed request"),
      @ApiResponse(code = 404, message = "List could not be created")})
  @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, value = "/v1/getListOfItems")
  @ResponseBody
  public GetSquareListResponse getListItems() {
    try {
      Iterable<SquareNumber> squareNumberList = numberService.getAllItems();

      List<SquareNumberDto> squareDtoList = StreamSupport.stream(squareNumberList.spliterator(), false)
        .map(SquareConverter::toSquareNumberDto)
        .collect(Collectors.toList());

      return GetSquareListResponse.newBuilder().withSquareNumberDtoList(squareDtoList).build();
    } catch (IllegalArgumentException | ObjectNotFoundException e) {
      log.info("Cannot create list. Exception {}", e.toString());
      throw e;
    } catch (RuntimeException e) {
      log.error("Error when creating list: ", e);
      throw e;
    }
  }
}
