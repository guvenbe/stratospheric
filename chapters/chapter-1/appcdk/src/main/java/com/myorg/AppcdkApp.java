package com.myorg;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public class AppcdkApp {
    public static void main(final String[] args) {
        App app = new App();

      String accoountId = (String) app.getNode().tryGetContext("accountId");
      requireNonEmpty(accoountId, "context variable 'accountId' must not be null");
      String region = (String) app.getNode().tryGetContext("region");
      requireNonEmpty(region, "context variable 'region' must not be null");

      Environment environment = makeEnv(accoountId, region);

        new AppcdkStack(app, "AppcdkStack", StackProps.builder()
          .env(environment)
                .build());

        app.synth();
    }

  static Environment makeEnv(String account, String region) {
    return Environment.builder()
      .account(account)
      .region(region)
      .build();
  }

  public static void requireNonEmpty(String value, String message) {
    if (value == null || value.isEmpty()) {
      throw new IllegalArgumentException(message);
    }
  }
}

