package com.joehitt.iotdemo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.joehitt.iotdemo.model.Client;
import com.joehitt.iotdemo.model.Device;
import com.joehitt.iotdemo.model.Event;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Arrays;
import java.util.Map;

/**
 * Convenience class for setup of DynamoDB tables using bean structure.
 */
public class TableSetup implements RequestHandler<Map<String, Integer>, Boolean> {

    public TableSetup() {
    }

    @Override
    public Boolean handleRequest(Map<String, Integer> map, Context context) {
        DynamoDbEnhancedClient dynamo = DynamoDb.get();
        DynamoDbTable<?>[] tables = {
                dynamo.table("Client", TableSchema.fromBean(Client.class)),
                dynamo.table("Device", TableSchema.fromBean(Device.class)),
                dynamo.table("Event", TableSchema.fromBean(Event.class)),
        };
        Arrays.stream(tables).parallel().forEach(DynamoDbTable::createTable);
        return true;
    }
}
