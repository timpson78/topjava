package storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Storage {

    public void clear();

    public void update( Meal m);

    public void save(Meal m);

    public boolean isExist(Integer index);

    public Meal get( String id);

    public void delete(String id);

    public List<Meal> getAllSorted();

    public int size();
}
