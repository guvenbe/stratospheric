package com.myorg;

import software.amazon.awscdk.Environment;
import software.amazon.awscdk.services.ecr.LifecycleRule;
import software.amazon.awscdk.services.ecr.Repository;
import software.amazon.awscdk.services.ecr.TagMutability;
import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import dev.stratospheric.cdk.DockerRepository;

import java.util.Arrays;
// import software.amazon.awscdk.Duration;
// import software.amazon.awscdk.services.sqs.Queue;

public class AppcdkStack extends Stack {
    public AppcdkStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public AppcdkStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

//        Repository repository = Repository.Builder.create(this, "repository")
//                .repositoryName("hello-world-repo")
//          .imageScanOnPush(true)
//          .lifecycleRules(Arrays.asList(LifecycleRule.builder()
//                  .maxImageCount(10)
//                  .build()))
//          .imageTagMutability(TagMutability.IMMUTABLE)
//                .build();

      String accountId = "12345";
      Environment environment = Environment.builder()
        .account(accountId)
        .region("us-east-2")
        .build();

      DockerRepository dockerRepository = new DockerRepository(
        this,
        "repo",
        environment,
        new DockerRepository.DockerRepositoryInputParameters(
          "hello-world-repo",
          accountId,
          10));

    }
}
