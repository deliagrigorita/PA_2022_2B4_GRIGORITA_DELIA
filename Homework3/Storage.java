package com.networkroutingproblem;

public interface Storage {
    int getStorageSize();
    default long getStorageSizeInUnits(StorageUnit unit)
    {
        return ((long)Math.pow(1024.0, unit.value)) * this.getStorageSize();
    }
}
