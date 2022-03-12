package com.roomassignemnt;

public class ProblemSolution {
    private EventRoomAssociation[] eventRoomAssociations;
    private int currentEventRoomCount;

    public ProblemSolution(int eventRoomAssociationsSize) {
        this.eventRoomAssociations = new EventRoomAssociation[eventRoomAssociationsSize];
    }

    public void appendEventRoomAssociation(EventRoomAssociation eventRoomAssociation)
    {
        if (this.currentEventRoomCount < this.eventRoomAssociations.length)
        {
            this.eventRoomAssociations[this.currentEventRoomCount] = eventRoomAssociation;
            this.currentEventRoomCount++;
        }
    }

    public void printSolution(Event[] events, Room[] rooms)
    {
        for (int associationIndex = 0; associationIndex < this.currentEventRoomCount; associationIndex++)
        {
            this.eventRoomAssociations[associationIndex].printAssociation(events, rooms);
        }
    }

    public int getRoomIndexForEventIndex(int eventIndex)
    {
        for (int associationIndex = 0; associationIndex < this.currentEventRoomCount; associationIndex++)
        {
            if (this.eventRoomAssociations[associationIndex].getEventIndex() == eventIndex)
            {
                return this.eventRoomAssociations[associationIndex].getRoomIndex();
            }
        }
        return -1;
    }
}
