package com.roomassignemnt;

public class EventRoomAssociation {
    private int eventIndex;
    private int roomIndex;

    /**
     *
     * @param eventIndex
     * @param roomIndex
     */
    public EventRoomAssociation(int eventIndex, int roomIndex) {
        this.eventIndex = eventIndex;
        this.roomIndex = roomIndex;
    }

    /**
     *
     * @return
     */
    public int getEventIndex() {
        return eventIndex;
    }

    /**
     *
     * @return
     */
    public int getRoomIndex() {
        return roomIndex;
    }

    /**
     *
     * @param events
     * @param rooms
     */
    public void printAssociation(Event[] events, Room[] rooms)
    {
        System.out.println(events[this.eventIndex].toString() + " -> " + rooms[this.roomIndex].name);
    }
}
