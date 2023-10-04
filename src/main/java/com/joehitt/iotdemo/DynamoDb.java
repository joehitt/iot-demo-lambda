package com.joehitt.iotdemo;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class DynamoDb {


    public static DynamoDbEnhancedClient get() {

        // This method can be used if not running in AWS and accessing dynamo directly:
        //        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        //        Region region = Region.US_EAST_1;
        //        DynamoDbClient ddb = DynamoDbClient.builder()
        //                .credentialsProvider(credentialsProvider)
        //                .region(region)
        //                .build();

        return DynamoDbEnhancedClient.builder().dynamoDbClient(DynamoDbClient.create()).build();

    }
}