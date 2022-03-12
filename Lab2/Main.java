package com.roomassignemnt;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
	    Main program = new Main();
        program.mandatoryWork();
        program.homework();
        program.bonus();
        program.randomInstances(700);
    }

    public void mandatoryWork()
    {
        Event event1 = new Event("Curs1", EventType.Curs, 100, getTimeObj("10:00 AM"), getTimeObj("12:00 PM"));
        Event event2 = new Event("Lab1", EventType.Laborator, 30, getTimeObj("12:00 PM"), getTimeObj("2:00 PM"));
        Room c401 = new LectureHall("C401", 200, true);

        event1.printToScreen();
        event2.printToScreen();
        c401.printToScreen();
    }

    public void homework()
    {
        Event C1 = new Event("C1", EventType.Curs, 100, getTimeObj("8:00 AM"), getTimeObj("10:00 AM"));
        Event C2 = new Event("C2", EventType.Curs, 100, getTimeObj("10:00 AM"), getTimeObj("12:00 PM"));
        Event L1 = new Event("L1", EventType.Laborator, 30, getTimeObj("8:00 AM"), getTimeObj("10:00 AM"));
        Event L2 = new Event("L2", EventType.Laborator, 30, getTimeObj("8:00 AM"), getTimeObj("10:00 AM"));
        Event L3 = new Event("L3", EventType.Laborator, 30, getTimeObj("10:00 AM"), getTimeObj("12:00 PM"));

        Room c401 = new Lab("C401", 30, "Win10");
        Room c403 = new Lab("C403", 30, "Win10");
        Room c405 = new Lab("C405", 30, "Win10");
        Room c309 = new LectureHall("C309", 100, true);

        ProblemSolver solver = new GreedySolver(4, 5);

        solver.addEvent(C1);
        solver.addEvent(C2);
        solver.addEvent(L1);
        solver.addEvent(L2);
        solver.addEvent(L3);

        solver.addRoom(c401);
        solver.addRoom(c403);
        solver.addRoom(c405);
        solver.addRoom(c309);

        solver.solve();
        solver.printSolution();
    }

    public void bonus()
    {
        Event C1 = new Event("C1", EventType.Curs, 100, getTimeObj("8:00 AM"), getTimeObj("10:00 AM"));
        Event C2 = new Event("C2", EventType.Curs, 100, getTimeObj("10:00 AM"), getTimeObj("12:00 PM"));
        Event L1 = new Event("L1", EventType.Laborator, 30, getTimeObj("8:00 AM"), getTimeObj("10:00 AM"));
        Event L2 = new Event("L2", EventType.Laborator, 30, getTimeObj("8:00 AM"), getTimeObj("10:00 AM"));
        Event L3 = new Event("L3", EventType.Laborator, 30, getTimeObj("10:00 AM"), getTimeObj("12:00 PM"));

        Room c401 = new Lab("C401", 30, "Win10");
        Room c403 = new Lab("C403", 30, "Win10");
        Room c405 = new Lab("C405", 30, "Win10");
        Room c309 = new LectureHall("C309", 100, true);

        DSaturSolver solver = new DSaturSolver(4, 5);

        solver.addEvent(C1);
        solver.addEvent(C2);
        solver.addEvent(L1);
        solver.addEvent(L2);
        solver.addEvent(L3);

        solver.addRoom(c401);
        solver.addRoom(c403);
        solver.addRoom(c405);
        solver.addRoom(c309);

        solver.solve();
        solver.printSolution();
    }

    /**
     *
     * @param number
     */
    public void randomInstances(int number)
    {
        RandomInstance instance = new RandomInstance(number);

        ProblemSolver greedySolver = new GreedySolver(instance.getRoomSize(), instance.getEventSize());
        DSaturSolver dSaturSolver = new DSaturSolver(instance.getRoomSize(), instance.getEventSize());

        System.out.println("Starting Greedy Solver");
        instance.runSolver(greedySolver);
        System.out.println("Done Greedy Solver");
        System.out.println("Starting DSatur Solver");
        instance.runSolver(dSaturSolver);
        System.out.println("Done DSatur Solver");
    }

    public LocalTime getTimeObj(String time)
    {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("h:m a"));
    }
}
