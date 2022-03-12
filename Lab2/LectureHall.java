package com.roomassignemnt;

public class LectureHall extends Room {

    private boolean hasVideoProjector;

    /**
     *
     * @param name
     * @param capacity
     * @param hasVideoProjector
     */
    public LectureHall(String name, int capacity, boolean hasVideoProjector) {
        super(name, capacity);
        this.hasVideoProjector = hasVideoProjector;
    }

    @Override
    public void printToScreen()
    {
        System.out.println("<---- --- --- ---->");
        System.out.println("Room name : ".concat(this.name));
        System.out.println("Room type : Lecture Hall");
        System.out.println("Room capacity : ".concat(String.valueOf(this.capacity)));
        System.out.println("<---- --- --- ---->");
    }
}
