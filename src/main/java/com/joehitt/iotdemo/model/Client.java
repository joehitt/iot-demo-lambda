package com.joehitt.iotdemo.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbIgnore;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@DynamoDbBean
public class Client {
    @DynamoDbPartitionKey
    @SuppressWarnings("unused")
    public String getId() {
        return id;
    }
    @Setter
    private String id;
    @Getter
    @Setter
    private State state;
    @DynamoDbIgnore
    public List<Device> getDevices() {
        return devices;
    }
    @Setter
    private List<Device> devices = new ArrayList<>();


    public enum State {

        ACTIVE,
        INACTIVE

    }

    public static Client fromJson(ObjectMapper mapper, String json) throws JsonProcessingException {
        return mapper.readValue(json, Client.class);
    }


}
