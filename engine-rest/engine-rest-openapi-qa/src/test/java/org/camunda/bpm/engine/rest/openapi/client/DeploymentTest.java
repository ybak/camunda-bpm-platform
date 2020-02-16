package org.camunda.bpm.engine.rest.openapi.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import org.junit.Rule;
import org.junit.Test;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.DeploymentApi;
import org.openapitools.client.model.DeploymentDto;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

public class DeploymentTest {

  private static final String ENGINE_REST_DEPLOYMENT = "/engine-rest/deployment";

  final DeploymentApi api = new DeploymentApi();

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(8080);

  @Test
  public void shouldCreateDeployment() throws ApiException {
    // given
    wireMockRule.stubFor(
        post(urlEqualTo(ENGINE_REST_DEPLOYMENT + "/create"))
        .willReturn(aResponse()
            .withStatus(200)
            .withBody("{\n" + 
                "    \"links\": [\n" + 
                "        {\n" + 
                "            \"method\": \"GET\",\n" + 
                "            \"href\": \"http://localhost:38080/rest-test/deployment/aDeploymentId\",\n" + 
                "            \"rel\": \"self\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"id\": \"aDeploymentId\",\n" + 
                "    \"name\": \"deployment-test\",\n" + 
                "    \"source\": \"test-source\",\n" + 
                "    \"deploymentTime\": \"2013-01-23T13:59:43+02:00\",\n" + // TODO Dates CAM-11407
                "    \"tenantId\": null,\n" + 
                "    \"deployedProcessDefinitions\": {\n" + 
                "        \"aProcDefId\": {\n" + 
                "            \"id\": \"aProcDefId\",\n" + 
                "            \"key\": \"aKey\",\n" + 
                "            \"category\": \"aCategory\",\n" + 
                "            \"description\": \"aDescription\",\n" + 
                "            \"name\": \"aName\",\n" + 
                "            \"version\": 42,\n" + 
                "            \"resource\": \"aResourceName\",\n" + 
                "            \"deploymentId\": \"aDeploymentId\",\n" + 
                "            \"diagram\": \"aResourceName.png\",\n" + 
                "            \"suspended\": true,\n" + 
                "            \"tenantId\": null,\n" + 
                "            \"versionTag\": null\n" + 
                "        }\n" + 
                "    },\n" + 
                "    \"deployedCaseDefinitions\": null,\n" + 
                "    \"deployedDecisionDefinitions\": null,\n" + 
                "    \"deployedDecisionRequirementsDefinitions\": null\n" + 
                "}")));

    // when
    String deploymentSource = "test-source";
    String deploymentName = "deployment-test";
    DeploymentDto deployment = api.createDeployment(null, deploymentSource, false, false, deploymentName, new File("src/test/resources/one.bpmn"));

    // then
    assertThat(deployment.getId()).isEqualTo("aDeploymentId");
    assertThat(deployment.getName()).isEqualTo(deploymentName);
    assertThat(deployment.getSource()).isEqualTo(deploymentSource);
    assertThat(deployment.getDeployedProcessDefinitions()).containsKey("aProcDefId");
    assertThat(deployment.getDeployedProcessDefinitions().get("aProcDefId").getId()).isEqualTo("aProcDefId");
    assertThat(deployment.getDeployedProcessDefinitions().get("aProcDefId").getKey()).isEqualTo("aKey");
    assertThat(deployment.getDeployedProcessDefinitions().get("aProcDefId").getName()).isEqualTo("aName");
  }

}
