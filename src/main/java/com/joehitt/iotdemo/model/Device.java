package com.joehitt.iotdemo.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@DynamoDbBean
public class Device {

    @DynamoDbPartitionKey
    @SuppressWarnings("unused")
    public String getClientId() {
        return clientId;
    }
    @Setter
    private String clientId;

    @DynamoDbSortKey
    @SuppressWarnings("unused")
    public Type getType() {
        return type;
    }
    @Setter
    private Type type;

    @Getter
    @Setter
    private String state;

    @Getter
    @Setter
    private String details;

    public enum Type {

        CPU, AJAR, BELL, LIGHT, LOCK, VIDEO

    }

    public static Device fromJson(ObjectMapper mapper, String json) throws JsonProcessingException {
        return mapper.readValue(json, Device.class);
    }

}
