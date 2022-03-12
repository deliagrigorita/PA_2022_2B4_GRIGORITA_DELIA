package com.roomassignemnt;

public class GreedySolver extends ProblemSolver {
    public GreedySolver() {}

    /**
     *
     * @param roomsSize
     * @param eventsSize
     */
    public GreedySolver(int roomsSize, int eventsSize) {
        super(roomsSize, eventsSize);
    }

    @Override
    public void solve()
    {
        if (this.canSolve() == false)
        {
            return;
        }

        this.sortRooms();
        this.graphRepresentation();

        this.problemSolution = new ProblemSolution(this.events.length);

        for (int eventIndex = 0; eventIndex < this.events.length; eventIndex++)
        {
            for (int roomIndex = 0; roomIndex < this.rooms.length; roomIndex++)
            {
                if (this.events[eventIndex].getEventSize() <= this.rooms[roomIndex].capacity &&
                        this.checkPossibleSolution(roomIndex, this.adjacencyList[eventIndex]))
                {
                    EventRoomAssociation eventRoomAssociation = new EventRoomAssociation(eventIndex, roomIndex);
                    this.problemSolution.appendEventRoomAssociation(eventRoomAssociation);
                    break;
                }
            }
        }
    }

    /**
     *
     * @param roomIndex
     * @param neighbourEventIndexes
     * @return
     */
    public boolean checkPossibleSolution(int roomIndex, int[] neighbourEventIndexes)
    {
        for (int eventIndex : neighbourEventIndexes)
        {
            if (this.problemSolution.getRoomIndexForEventIndex(eventIndex) == roomIndex)
            {
                return false;
            }
        }

        return true;
    }
}
