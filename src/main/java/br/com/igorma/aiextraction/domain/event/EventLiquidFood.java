package br.com.igorma.aiextraction.domain.event;

import java.util.UUID;

// https://github.com/alves-dev/life/blob/main/events/README.md#alimenta%C3%A7%C3%A3o-routing_key---food-1
public class EventLiquidFood extends EventBase {

    private final String liquid;
    private final int amount;

    public String getLiquid() {
        return liquid;
    }

    public int getAmount() {
        return amount;
    }

    private EventLiquidFood(UUID personId, String liquid, int amount) {
        super(EventType.LIQUID_FOOD, personId);
        this.liquid = liquid;
        this.amount = amount;
    }

    public static EventLiquidFood ofWater(UUID personId, int amount) {
        return new EventLiquidFood(personId, "water", amount);
    }

    @Override
    public String toString() {
        return super.toString() + " | " +
                "EventLiquidFood{" +
                "liquid='" + liquid + '\'' +
                ", amount=" + amount +
                '}';
    }
}
