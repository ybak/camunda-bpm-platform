package org.camunda.bpm.engine.rest.openapi.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import org.junit.Rule;
import org.junit.Test;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.ProcessInstanceApi;
import org.openapitools.client.model.CountResultDto;
import org.openapitools.client.model.InlineObject;
import org.openapitools.client.model.ProcessInstanceQueryDto;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import static org.assertj.core.api.Assertions.assertThat;

public class ProcessInstanceTest {

  private static final String ENGINE_REST_PROCESS_INSTANCE = "/engine-rest/process-instance";

  final ProcessInstanceApi api = new ProcessInstanceApi();

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(8080);

  @Test
  public void shouldQueryProcessInstancesCount() throws ApiException {
    // given
    wireMockRule.stubFor(
        post(urlEqualTo(ENGINE_REST_PROCESS_INSTANCE + "/count"))
        .willReturn(aResponse()
            .withStatus(200)
            .withBody("{ \"count\": 3 }")));

    // when
    ProcessInstanceQueryDto processInstanceQueryDto = new ProcessInstanceQueryDto();
    processInstanceQueryDto.setActive(true);
    CountResultDto count = api.queryProcessInstancesCount(processInstanceQueryDto);

    // then
    assertThat(count.getCount()).isEqualTo(3);
  }

  @Test
  public void shouldUpdateSuspensionState() throws ApiException {
    // given
    String id = "anProcessInstanceId";
    wireMockRule.stubFor(
        put(urlEqualTo(ENGINE_REST_PROCESS_INSTANCE + "/" + id + "/suspended"))
        .willReturn(aResponse().withStatus(204)));

    // when
    InlineObject dto = new InlineObject();
    dto.setSuspended(true);
    api.updateSuspensionStateById(id, dto);

    // then no error
  }

}
