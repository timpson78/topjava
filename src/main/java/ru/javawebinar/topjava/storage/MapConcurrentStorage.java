package ru.javawebinar.topjava.storage;


import ru.javawebinar.topjava.model.Meal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MapConcurrentStorage implements Storage {

    private Map<String, Meal> storageMap;

    public MapConcurrentStorage(Map<String, Meal> storageMap) {
        this.storageMap = storageMap;
    }

    @Override
    public void delete(String searchkey) {
        storageMap.remove(searchkey);
    }


    @Override
    public void update(Meal m) {
        storageMap.put(m.getId(), m);
    }

    @Override
    public void save(Meal m) {
        storageMap.put(m.getId(), m);
    }

    @Override
    public Meal get(String searchKey) {
        return storageMap.get(searchKey);

    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<Meal>(storageMap.values());
    }


}
