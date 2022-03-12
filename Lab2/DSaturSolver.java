package com.roomassignemnt;

import java.util.Arrays;

public class DSaturSolver extends ProblemSolver {

    private int eventNeighboringNrOfColors[];
    private boolean coloredEvents[];

    /**
     *
     */
    public DSaturSolver() {}

    /**
     *
     * @param roomsSize
     * @param eventsSize
     */
    public DSaturSolver(int roomsSize, int eventsSize) {

        super(roomsSize, eventsSize);
        this.eventNeighboringNrOfColors = new int[eventsSize];
        this.coloredEvents = new boolean[eventsSize];

        Arrays.fill(this.eventNeighboringNrOfColors, 0);
        Arrays.fill(this.coloredEvents, false);
    }

    /**
     *
     */
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

        int eventIndex = getNextEventIndex();

        for  (int stepIndex = 0; stepIndex < this.events.length && eventIndex != -1; stepIndex++)
        {
            for (int roomIndex = 0; roomIndex < this.rooms.length; roomIndex++)
            {
                if (this.events[eventIndex].getEventSize() <= this.rooms[roomIndex].capacity &&
                        this.checkPossibleSolution(roomIndex, this.adjacencyList[eventIndex]))
                {
                    EventRoomAssociation eventRoomAssociation = new EventRoomAssociation(eventIndex, roomIndex);
                    this.problemSolution.appendEventRoomAssociation(eventRoomAssociation);

                    updateNeighbours(eventIndex);

                    break;
                }
            }

            eventIndex = getNextEventIndex();
        }
    }

    /**
     *
     * @return
     */
    public int getNextEventIndex()
    {
        int maxColors = -1;
        int maxIndex = -1;

        for (int eventIndex = 0; eventIndex < this.eventNeighboringNrOfColors.length; eventIndex++)
        {
            if (this.eventNeighboringNrOfColors[eventIndex] > maxColors && this.coloredEvents[eventIndex] == false)
            {
                maxColors = this.eventNeighboringNrOfColors[eventIndex];
                maxIndex = eventIndex;
            }
        }

        return maxIndex;
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

    /**
     *
     * @param eventIndex
     */
    public void updateNeighbours(int eventIndex)
    {
        this.coloredEvents[eventIndex] = true;

        for (int neighborIndex : this.adjacencyList[eventIndex])
        {
            this.eventNeighboringNrOfColors[neighborIndex] += 1;
        }
    }
}
