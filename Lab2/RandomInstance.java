package com.roomassignemnt;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RandomInstance {
    private int roomSize, eventSize;
    private int sizeCoefficient;
    private Room[] rooms;
    private Event[] events;

    /**
     *
     * @param sizeCoefficient
     */
    public RandomInstance(int sizeCoefficient) {
        this.sizeCoefficient = sizeCoefficient;

        this.roomSize = sizeCoefficient;
        this.eventSize = sizeCoefficient * 6;

        this.rooms = new Room[this.roomSize];
        this.events = new Event[this.eventSize];

        this.generateInstance();
    }

    public int getRoomSize() {
        return roomSize;
    }

    public int getEventSize() {
        return eventSize;
    }

    public void generateInstance() {

        for (int index = 0; index < this.roomSize; index++)
        {
            int roomCapacity = (int)(Math.random() * 1000);

            Room generatedRoom = new LectureHall("generatedRoom_" + index, roomCapacity, true);

            this.rooms[index] = generatedRoom;
        }

        for (int index = 0; index < this.eventSize; index++)
        {
            int eventSize = (int)(Math.random() * 1000);

            int generatedEventStart = (int)(Math.random() * 22);
            int generatedEventEnd = generatedEventStart + (int)(Math.random() * (23 - generatedEventStart));

            LocalTime eventStartTime = LocalTime.parse(generatedEventStart + ":00", DateTimeFormatter.ofPattern("H:m"));
            LocalTime eventEndTime = LocalTime.parse(generatedEventEnd + ":00", DateTimeFormatter.ofPattern("H:m"));

            Event generatedEvent = new Event("generatedEvent" + index, EventType.Curs, eventSize, eventStartTime, eventEndTime);

            this.events[index] = generatedEvent;
        }

    }

    public void runSolver(ProblemSolver solver)
    {
        for (int index = 0 ; index < this.roomSize; index++)
        {
            solver.addRoom(this.rooms[index]);
        }
        for (int index = 0; index < this.eventSize; index++)
        {
            solver.addEvent(this.events[index]);
        }

        solver.solve();
    }
}
