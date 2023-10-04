package com.joehitt.iotdemo.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Event serialized and sent by IoT device
 */
@Getter
@Data
public class ClientEvent {

    public ClientEvent() {
    }

    @Setter
    private Instant clientTimestamp;
    @Setter
    private Device.Type type;
    @Setter
    private String state;
    @Setter
    private String details;

    public Event toEvent(String clientId, Instant serverTimestamp) {
        return new Event(clientId, serverTimestamp, this.clientTimestamp, this.type, this.state, this.details);
    }

    public static ClientEvent fromJson(ObjectMapper mapper, String json) throws JsonProcessingException {
        return mapper.readValue(json, ClientEvent.class);
    }

}
