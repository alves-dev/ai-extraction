package br.com.igorma.aiextraction.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.UUID;

// https://github.com/alves-dev/life/blob/main/events/README.md#todos-os-eventos-vao-ter-os-seguintes-campos
public class EventBase {

    private final EventType type;

    @JsonProperty("person_id")
    private final UUID personId;

    private final ZonedDateTime datetime;

    public EventType getType() {
        return type;
    }

    public UUID getPersonId() {
        return personId;
    }

    public ZonedDateTime getDatetime() {
        return datetime;
    }

    public EventBase(EventType type, UUID personId) {
        this.type = type;
        this.personId = personId;
        this.datetime = ZonedDateTime.now();
    }

    @Override
    public String toString() {
        return "EventBase{" +
                "type=" + type +
                ", personId=" + personId +
                ", datetime=" + datetime +
                '}';
    }
}
