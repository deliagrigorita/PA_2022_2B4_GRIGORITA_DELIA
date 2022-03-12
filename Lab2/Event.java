package com.roomassignemnt;

import java.time.LocalTime;

public class Event {
    private String eventName;
    private EventType eventType;
    private int eventSize;
    private LocalTime startTime, endTime;

    /**
     *
     */
    public Event() {}

    /**
     *
     * @param eventType
     * @param eventSize
     * @param startTime
     * @param endTime
     */
    public Event(String eventName, EventType eventType, int eventSize, LocalTime startTime, LocalTime endTime) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventSize = eventSize;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     *
     * @return
     */
    public String getEventName() { return eventName; }

    /**
     *
     * @param eventName
     */
    public void setEventName(String eventName) { this.eventName = eventName; }

    /**
     *
     * @return
     */
    public int getEventSize() { return eventSize; }

    /**
     *
     * @param eventSize
     */
    public void setEventSize(int eventSize) { this.eventSize = eventSize; }

    /**
     *
     * @return
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     *
     * @param startTime
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     *
     * @return
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     *
     * @param endTime
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     *
     * @return
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     *
     * @param eventType
     */
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Event{" +
                "eventName=" + eventName +
                ", eventType=" + eventType +
                ", startTime=" + startTime.toString() +
                ", endTime=" + endTime.toString() +
                '}';
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        return ( this.toString().compareTo(obj.toString()) ) == 0;
    }

    /**
     *
     */
    public void printToScreen()
    {
        System.out.println("<---- --- --- ---->");
        System.out.println("Event Name : ".concat(String.valueOf(this.eventName)));
        System.out.println("Event Type : ".concat(String.valueOf(this.eventType)));
        System.out.println("Event Size : ".concat(String.valueOf(this.eventSize)));
        System.out.println("Time between ".concat(this.startTime.toString()).concat(" - ").concat(this.endTime.toString()));
        System.out.println("<---- --- --- ---->");
    }
}
