package com.joehitt.iotdemo.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.time.Instant;

/**
 * Event recorded in cloud database
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@DynamoDbBean
public class Event {


    private String clientId;
    private Instant serverTimestamp;
    private Instant clientTimestamp;
    private Device.Type type;
    private String state;
    private String details;

    @DynamoDbSortKey
    @SuppressWarnings("unused")
    public Instant getServerTimestamp() {
        return serverTimestamp;
    }

    @DynamoDbPartitionKey
    @SuppressWarnings("unused")
    public String getClientId() {
        return clientId;
    }


    @SuppressWarnings("unused")
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @SuppressWarnings("unused")
    public void setServerTimestamp(Instant serverTimestamp) {
        this.serverTimestamp = serverTimestamp;
    }

    @SuppressWarnings("unused")
    public Instant getClientTimestamp() {
        return clientTimestamp;
    }

    @SuppressWarnings("unused")
    public void setClientTimestamp(Instant clientTimestamp) {
        this.clientTimestamp = clientTimestamp;
    }

    @SuppressWarnings("unused")
    public Device.Type getType() {
        return type;
    }

    @SuppressWarnings("unused")
    public void setType(Device.Type type) {
        this.type = type;
    }

    @SuppressWarnings("unused")
    public String getState() {
        return state;
    }

    @SuppressWarnings("unused")
    public void setState(String state) {
        this.state = state;
    }

    @SuppressWarnings("unused")
    public String getDetails() {
        return details;
    }

    @SuppressWarnings("unused")
    public void setDetails(String details) {
        this.details = details;
    }


}
