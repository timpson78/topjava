package ru.javawebinar.topjava.Exeptions;

public class StorageException extends RuntimeException {
    private final String id;

    public StorageException(String message, String id) {
        super(message);
        this.id = id;
    }

    public StorageException(String message, String id, Exception e) {
        super(message,e);
        this.id = id;
    }

    public StorageException(Exception e) {
        this (e.getMessage(),null);
    }

    public String getId() {
        return id;
    }
}
