package square.app.restcontroller;

import static org.junit.Assert.assertNotEquals;
import static square.api.domain.errors.ErrorCode.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import square.api.domain.errors.ErrorCode;
import square.api.domain.errors.ErrorInfo;
import square.api.domain.model.*;
import square.app.BaseSpringBootTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class SquareItRestControllerTest extends BaseSpringBootTest {

  private static final String NUMBER_URL = "/rest/square/number";

  @Before
  public void setUp() {
    tearDown();
  }

  @After
  public void tearDown() {
    numberRepository.deleteAll();
  }

  @Test
  public void saveNumber_ShouldWork_SaveANumber() {
    assertThat(numberRepository.findAll()).hasSize(0);

    int number = 1;

    CreateSquareRequest.Builder request = createCorrectNumberRequest(number);
    CreateSquareResponse response = callCreateNumberPostOK(OK, request);

    Long respNumberId = response.getId();

    assertThat(numberRepository.findAll()).hasSize(1);
    assertThat(numberRepository.findOne(respNumberId).getNumber()).isEqualTo(number);
  }

  @Test
  public void saveNumber_ShouldWork_SaveManyNumbers() {
    assertThat(numberRepository.findAll()).hasSize(0);

    int number1 = 1;
    int number2 = 2;

    CreateSquareRequest.Builder request1 = createCorrectNumberRequest(number1);
    CreateSquareRequest.Builder request2 = createCorrectNumberRequest(number2);

    CreateSquareResponse response1 = callCreateNumberPostOK(OK, request1);
    CreateSquareResponse response2 = callCreateNumberPostOK(OK, request2);

    Long respNumberId1 = response1.getId();
    Long respNumberId2 = response2.getId();

    assertThat(numberRepository.findAll()).hasSize(2);
    assertThat(numberRepository.findOne(respNumberId1).getNumber()).isEqualTo(number1);
    assertThat(numberRepository.findOne(respNumberId2).getNumber()).isEqualTo(number2);
  }

  @Test
  public void getNumber_ShouldWork_GetANumber() {
    assertThat(numberRepository.findAll()).hasSize(0);

    int number = 9;

    CreateSquareRequest.Builder request = createCorrectNumberRequest(number);
    CreateSquareResponse response = callCreateNumberPostOK(OK, request);

    Long respNumberId = response.getId();

    assertThat(numberRepository.findAll()).hasSize(1);
    assertThat(numberRepository.findOne(respNumberId).getNumber()).isEqualTo(number);

    GetSquareResponse responseGet = callGetNumberGetOK(OK, respNumberId);

    assertThat(responseGet).isNotNull();
    assertThat(responseGet.getSquareDto().getNumber()).isEqualTo((int) Math.pow(number, 2));
  }

  @Test
  public void getNumber_ShouldFail_IdNumberDoesNotExist() {
    Long id = 15L;

    ErrorInfo responseGetError = callGetNumberGetError(NOT_FOUND, id);

    checkErrorResponse(responseGetError, NUMBER_NOT_FOUND,
        "SquareNumber with id = '" + id + "' does not exist");
  }

  @Test
  public void countNumbers_ShouldWork_CountNumbersToDatabase() {
    assertThat(numberRepository.findAll()).hasSize(0);

    int number1 = 1;
    int number2 = 2;

    CreateSquareRequest.Builder request1 = createCorrectNumberRequest(number1);
    CreateSquareRequest.Builder request2 = createCorrectNumberRequest(number2);

    CreateSquareResponse response1 = callCreateNumberPostOK(OK, request1);
    CreateSquareResponse response2 = callCreateNumberPostOK(OK, request2);

    Long respNumberId1 = response1.getId();
    Long respNumberId2 = response2.getId();

    assertThat(numberRepository.findAll()).hasSize(2);
    assertThat(numberRepository.findOne(respNumberId1).getNumber()).isEqualTo(number1);
    assertThat(numberRepository.findOne(respNumberId2).getNumber()).isEqualTo(number2);

    GetCountAllSquareResponse getCountAllSquareResponse = callGetCountAllGetOK(OK);

    assertThat(numberRepository.count()).isEqualTo(getCountAllSquareResponse.getCountTot());
  }

  @Test
  public void countNumbers_ShouldFail_CountNumbersToDatabase() {
    assertThat(numberRepository.findAll()).hasSize(0);
    assertNotEquals(numberRepository.count(), 2L);
  }

  @Test
  public void deleteNumber_ShouldWork_DeleteANumber() {
    assertThat(numberRepository.findAll()).hasSize(0);

    int number1 = 1;

    CreateSquareRequest.Builder requestCreateNumber = createCorrectNumberRequest(number1);

    CreateSquareResponse responseCreateNumber = callCreateNumberPostOK(OK, requestCreateNumber);

    Long respNumberId1 = responseCreateNumber.getId();

    assertThat(numberRepository.findAll()).hasSize(1);
    assertThat(numberRepository.findOne(respNumberId1).getNumber()).isEqualTo(number1);

    DeleteSquareIdNumberRequest.Builder correctDeleteNumberRequest = createCorrectDeleteNumberRequest(respNumberId1);

    callCreateDeleteNumberPostOK(OK, correctDeleteNumberRequest);
    assertThat(numberRepository.findAll()).hasSize(0);
  }

  @Test
  public void deleteNumber_ShouldFail_NumberIdDoesNotExist() {
    assertThat(numberRepository.findAll()).hasSize(0);

    Long non_existing_number_id = 999L;

    DeleteSquareIdNumberRequest.Builder correctDeleteNumberRequest
        = createCorrectDeleteNumberRequest(non_existing_number_id);

    ErrorInfo errorResponse = callDeleteNumberPOSTError(NOT_FOUND, correctDeleteNumberRequest);

    assertThat(numberRepository.findAll()).hasSize(0);

    checkErrorResponse(errorResponse, NUMBER_NOT_FOUND,
        "SquareNumber with id = '" + non_existing_number_id + "' does not exist");
  }

  @Test
  public void getListItems_ShouldWork_GetListOfAllItems() {
    assertThat(numberRepository.findAll()).hasSize(0);

    int number1 = 1;
    int number2 = 2;
    int number3 = 3;

    CreateSquareRequest.Builder request1 = createCorrectNumberRequest(number1);
    CreateSquareRequest.Builder request2 = createCorrectNumberRequest(number2);
    CreateSquareRequest.Builder request3 = createCorrectNumberRequest(number3);

    CreateSquareResponse response1 = callCreateNumberPostOK(OK, request1);
    CreateSquareResponse response2 = callCreateNumberPostOK(OK, request2);
    CreateSquareResponse response3 = callCreateNumberPostOK(OK, request3);

    Long respNumberId1 = response1.getId();
    Long respNumberId2 = response2.getId();
    Long respNumberId3 = response3.getId();

    assertThat(numberRepository.findAll()).hasSize(3);
    assertThat(numberRepository.findOne(respNumberId1).getNumber()).isEqualTo(number1);
    assertThat(numberRepository.findOne(respNumberId2).getNumber()).isEqualTo(number2);
    assertThat(numberRepository.findOne(respNumberId3).getNumber()).isEqualTo(number3);

    GetSquareListResponse getSquareListResponse = callCreateGetListItemsGetOK(OK);

    assertThat(getSquareListResponse.getSquareNumberDtoList()).size().isEqualTo(3);
  }

  @Test
  public void getListItems_ShouldWork_GetListOfAllItemsWithEmptyList() {
    assertThat(numberRepository.findAll()).hasSize(0);

    GetSquareListResponse getSquareListResponse = callCreateGetListItemsGetOK(OK);

    assertThat(getSquareListResponse.getSquareNumberDtoList()).size().isEqualTo(0);
  }

  private CreateSquareResponse callCreateNumberPostOK(HttpStatus expectedStatus, CreateSquareRequest.Builder request) {
    return httpPost(NUMBER_URL + "/v1/setNumber", expectedStatus, request.build(), CreateSquareResponse.class);
  }

  private DeleteSquareIdNumberRequest callCreateDeleteNumberPostOK(HttpStatus expectedStatus,
      DeleteSquareIdNumberRequest.Builder request) {
    return httpPost(NUMBER_URL + "/v1/deleteId", expectedStatus, request.build(),
        DeleteSquareIdNumberRequest.class);
  }

  private GetSquareResponse callGetNumberGetOK(HttpStatus expectedStatus, Long id) {
    return httpGet(NUMBER_URL + "/v1/getNumber/" + id, expectedStatus, GetSquareResponse.class);
  }

  private GetCountAllSquareResponse callGetCountAllGetOK(HttpStatus expectedStatus) {
    return httpGet(NUMBER_URL + "/v1/countNumbers", expectedStatus, GetCountAllSquareResponse.class);
  }

  private GetSquareListResponse callCreateGetListItemsGetOK(HttpStatus expectedStatus) {
    return httpGet(NUMBER_URL + "/v1/getListOfItems", expectedStatus, GetSquareListResponse.class);
  }

  private ErrorInfo callDeleteNumberPOSTError(HttpStatus expectedStatus, DeleteSquareIdNumberRequest.Builder request) {
    return httpPostError(NUMBER_URL + "/v1/deleteId/", expectedStatus, request.build(), ErrorInfo.class);
  }

  private ErrorInfo callGetNumberGetError(HttpStatus expectedStatus, Long id) {
    return httpGetError(NUMBER_URL + "/v1/getNumber/" + id, expectedStatus, ErrorInfo.class);
  }

  private void checkErrorResponse(ErrorInfo response, ErrorCode code, String expectedDescription) {
    assertThat(response).isNotNull();
    assertThat(response.getErrorCode()).isEqualTo(code);
    assertThat(response.getErrorDescription()).isEqualTo(expectedDescription);
  }

  private CreateSquareRequest.Builder createCorrectNumberRequest(int number) {
    return CreateSquareRequest.newBuilder().withNumber(number);
  }

  private DeleteSquareIdNumberRequest.Builder createCorrectDeleteNumberRequest(Long number) {
    return DeleteSquareIdNumberRequest.newBuilder().withNumberId(number);
  }
}
