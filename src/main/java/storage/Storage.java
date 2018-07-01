package storage;

import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

public interface Storage {

    public void clear();

    public void update( MealWithExceed m);

    public void save(MealWithExceed m);

    public boolean isExist(Integer index);

    public MealWithExceed get( String id);

    public void delete(String id);

    public List<MealWithExceed> getAllSorted();

    public int size();
}
