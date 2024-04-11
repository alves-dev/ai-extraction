package br.com.igorma.aiextraction.domain.event;

public enum EventType {
    FOOD(EventFood.class),
    LIQUID_FOOD(EventLiquidFood.class);

    private final Class<? extends EventBase> eventClass;

    EventType(Class<? extends EventBase> eventClass) {
        this.eventClass = eventClass;
    }

    public Class<? extends EventBase> getEventClass() {
        return eventClass;
    }
}
