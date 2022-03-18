package com.networkroutingproblem;

public enum StorageUnit {
    Gigabytes(0),
    Megabytes(1),
    Kilobytes(2),
    Bytes(3);

    public final int value;

    private StorageUnit(int value) {
        this.value = value;
    }
}
