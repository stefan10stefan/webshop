package com.lilly021.quickok.events;

public class BaseEvent {

    private EventType type;

    public BaseEvent(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }
}
