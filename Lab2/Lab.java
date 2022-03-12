package com.roomassignemnt;

public class Lab extends Room {

    private String osName;

    /**
     *
     * @param name
     * @param capacity
     * @param osName
     */
    public Lab(String name, int capacity, String osName) {
        super(name, capacity);
        this.osName = osName;
    }

    @Override
    public void printToScreen()
    {
        System.out.println("<---- --- --- ---->");
        System.out.println("Room name : ".concat(this.name));
        System.out.println("Room type : Lab");
        System.out.println("Room capacity : ".concat(String.valueOf(this.capacity)));
        System.out.println("<---- --- --- ---->");
    }
}
