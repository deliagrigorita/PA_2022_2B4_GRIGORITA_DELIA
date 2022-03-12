package com.roomassignemnt;

import java.util.Arrays;
import java.util.Comparator;

class RoomComparator implements Comparator<Room> {
    /**
     *
     * @param leftRoom
     * @param rightRoom
     * @return
     */
    @Override
    public int compare(Room leftRoom, Room rightRoom)
    {
        if (leftRoom.capacity > rightRoom.capacity)
        {
            return 1;
        }
        else if (leftRoom.capacity < rightRoom.capacity)
        {
            return -1;
        }
        else {
            return 0;
        }
    }
}

public abstract class ProblemSolver {
    protected Room[] rooms;
    protected Event[] events;

    private boolean roomsIsInitialised, eventsIsInitialised;
    private int roomsCurrentSize, eventsCurrentSize;

    protected int[][] adjacencyList;

    protected ProblemSolution problemSolution;

    public ProblemSolver() {
        this.roomsIsInitialised = this.eventsIsInitialised = false;
        this.roomsCurrentSize = this.eventsCurrentSize = 0;
    }

    /**
     *
     * @param roomsSize
     * @param eventsSize
     */
    public ProblemSolver(int roomsSize, int eventsSize) {
        this.rooms = new Room[roomsSize];
        this.events = new Event[eventsSize];

        this.roomsIsInitialised = this.eventsIsInitialised = true;
        this.roomsCurrentSize = this.eventsCurrentSize = 0;

        this.adjacencyList = new int[eventsSize][];
    }

    /**
     *
     * @param searchedRoom
     * @return
     */
    public boolean hasRoom(Room searchedRoom)
    {
        if (this.roomsIsInitialised == false)
        {
            return false;
        }

        for (int index = 0; index < this.roomsCurrentSize; index++)
        {
            if (this.rooms[index].equals(searchedRoom))
            {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param searchedEvent
     * @return
     */
    public boolean hasEvent(Event searchedEvent)
    {
        if (this.eventsIsInitialised == false)
        {
            return false;
        }

        for (int index = 0; index < this.eventsCurrentSize; index++)
        {
            if (this.events[index].equals(searchedEvent))
            {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param room
     * @return
     */
    public boolean addRoom(Room room) {
        if (hasRoom(room))
        {
            System.out.println("Room already exists");
            return false;
        }

        if (this.roomsIsInitialised == false)
        {
            this.rooms = new Room[1];
            this.roomsIsInitialised = true;
        }

        if (this.roomsCurrentSize >= this.rooms.length)
        {
            this.rooms = Arrays.copyOf(this.rooms, this.rooms.length + 1);
        }

        this.rooms[this.roomsCurrentSize] = room;
        this.roomsCurrentSize++;

        return true;
    }

    /**
     *
     * @param event
     * @return
     */
    public boolean addEvent(Event event) {
        if (hasEvent(event))
        {
            System.out.println("Event already exists");
            return false;
        }

        if (this.eventsIsInitialised == false)
        {
            this.events = new Event[1];
            this.eventsIsInitialised = true;
        }

        if (this.eventsCurrentSize >= this.events.length)
        {
            this.events = Arrays.copyOf(this.events, this.events.length + 1);
        }

        this.events[this.eventsCurrentSize] = event;
        this.eventsCurrentSize++;

        return true;
    }

    public boolean canSolve()
    {
        if (this.roomsCurrentSize != this.rooms.length)
        {
            System.out.println("Not all rooms were added");
            return false;
        }
        if (this.eventsCurrentSize != this.events.length)
        {
            System.out.println("Not all events were added");
            return false;
        }

        return true;
    }

    public void sortRooms()
    {
        Arrays.sort(this.rooms, new RoomComparator());
    }

    public void graphRepresentation()
    {
        for (int eventsIt1 = 0; eventsIt1 < this.events.length; eventsIt1++)
        {
            this.adjacencyList[eventsIt1] = new int[0];

            for (int eventsIt2 = 0; eventsIt2 < this.events.length; eventsIt2++)
            {
                if (this.inSameTimeWindow(this.events[eventsIt1], this.events[eventsIt2]))
                {
                    this.adjacencyList[eventsIt1] = Arrays.copyOf(this.adjacencyList[eventsIt1],
                            this.adjacencyList[eventsIt1].length + 1);

                    this.adjacencyList[eventsIt1][this.adjacencyList[eventsIt1].length - 1] = eventsIt2;
                }
            }
        }
    }

    /**
     *
     * @param leftEvent
     * @param rightEvent
     * @return
     */
    public boolean inSameTimeWindow(Event leftEvent, Event rightEvent)
    {
        return Math.max(leftEvent.getStartTime().toSecondOfDay(), rightEvent.getStartTime().toSecondOfDay()) <
                Math.min(leftEvent.getEndTime().toSecondOfDay(), rightEvent.getEndTime().toSecondOfDay());
    }

    public abstract void solve();

    public void printSolution() {

        this.problemSolution.printSolution(this.events, this.rooms);
    }

    public void printStats()
    {

    }
}
