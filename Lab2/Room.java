package com.roomassignemnt;

public abstract class Room {

    protected String name;
    protected int capacity;

    /**
     *
     * @param name
     * @param capacity
     */
    public Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        return ( this.toString().compareTo(obj.toString()) ) == 0;
    }

    public abstract void printToScreen();
}
