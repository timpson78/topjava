package ru.javawebinar.topjava.Exeptions;

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String id) {
        super("Элемент " + id + " не существует", id);
    }
}
