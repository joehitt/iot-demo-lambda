package com.joehitt.iotdemo;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.joehitt.iotdemo.model.ClientEvent;
import com.joehitt.iotdemo.model.Event;
import com.joehitt.iotdemo.model.EventResponse;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.time.Instant;


public class IotDemo implements RequestHandler<APIGatewayV2HTTPEvent, EventResponse> {

    public IotDemo() {
        DynamoDbEnhancedClient dynamo = DynamoDb.get();
        this.table = dynamo.table("Event", TableSchema.fromBean(Event.class));
    }

    private final DynamoDbTable<Event> table;

    @Override
    public EventResponse handleRequest(APIGatewayV2HTTPEvent raw, Context context) {
        try {
            ClientEvent event = ClientEvent.fromJson(objectMapper, raw.getBody());
            table.putItem(event.toEvent(raw.getRawPath().substring(1), Instant.now()));
            return new EventResponse();
        } catch (JsonProcessingException je) {
            // RequestHandler interface does not allow checked exceptions
            throw new RuntimeException("Could not parse ClientEvent JSON from request body", je);
        }
    }

    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());


}
