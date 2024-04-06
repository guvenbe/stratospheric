package com.myorg;

import dev.stratospheric.cdk.DockerRepository;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.constructs.Construct;
// import software.amazon.awscdk.Duration;
// import software.amazon.awscdk.services.sqs.Queue;

public class AppcdkStack2 extends Stack {
    public AppcdkStack2(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public AppcdkStack2(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

//        Repository repository = Repository.Builder.create(this, "repository")
//                .repositoryName("hello-world-repo")
//          .imageScanOnPush(true)
//          .lifecycleRules(Arrays.asList(LifecycleRule.builder()
//                  .maxImageCount(10)
//                  .build()))
//          .imageTagMutability(TagMutability.IMMUTABLE)
//                .build();

//      String accountId = "383626418458";
//      Environment environment = Environment.builder()
//        .account(accountId)
//        .region("us-east-2")
//        .build();

      Environment environment = props.getEnv();

      DockerRepository dockerRepository = new DockerRepository(
        this,
        "repo",
        environment,
        new DockerRepository.DockerRepositoryInputParameters(
          "hello-world-repo",
          environment.getAccount(),
          10));

    }
}
