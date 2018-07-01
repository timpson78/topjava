package ru.javawebinar.topjava.Exeptions;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String id) {
        super("Элемент " + id + " существует", id);
    }

}
