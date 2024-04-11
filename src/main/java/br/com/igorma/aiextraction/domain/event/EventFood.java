package br.com.igorma.aiextraction.domain.event;

import java.util.UUID;

// https://github.com/alves-dev/life/blob/main/events/README.md#alimenta%C3%A7%C3%A3o-routing_key---food
public class EventFood extends EventBase {

    private final String food;
    private final int weight;

    public String getFood() {
        return food;
    }

    public int getWeight() {
        return weight;
    }

    public EventFood(UUID personId, String food, int weight) {
        super(EventType.FOOD, personId);
        this.food = food;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return super.toString() + " | " +
                "EventFood{" +
                "food='" + food + '\'' +
                ", weight=" + weight +
                '}';
    }
}
