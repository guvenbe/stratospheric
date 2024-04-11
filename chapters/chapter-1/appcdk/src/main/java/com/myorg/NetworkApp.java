package com.myorg;

import dev.stratospheric.cdk.DockerRepository;
import dev.stratospheric.cdk.Network;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

public class NetworkApp {

  public static void main(final String[] args) {
    App app = new App();

    String accountId = (String) app.getNode().tryGetContext("accountId");
    Validations.requireNonEmpty(accountId, "context variable 'accountId' must not be null");

    String region = (String) app.getNode().tryGetContext("region");
    Validations.requireNonEmpty(region, "context variable 'region' must not be null");

    String applicationName = (String) app.getNode().tryGetContext("applicationName");
    Validations.requireNonEmpty(applicationName, "context variable 'applicationName' must not be null");


    String environmentName = (String) app.getNode().tryGetContext("environmentName");
    Validations.requireNonEmpty(environmentName, "context variable 'environmentName' must not be null");
    Environment env = makeEnv(accountId, region);

    Stack networkStack = new Stack(app, "NetworkStack", StackProps.builder()
      .stackName(environmentName + "-Network")
      .env(env)
      .build());
    Network.NetworkInputParameters inputParameters = new Network.NetworkInputParameters();

    Network network = new Network(
      networkStack,
      "Network",
      env,
      environmentName,
      inputParameters);

    app.synth();
  }

  static Environment makeEnv(String account, String region) {
    return Environment.builder()
      .account(account)
      .region(region)
      .build();
  }

}
