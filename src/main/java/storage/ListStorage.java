package storage;

import ru.javawebinar.topjava.Exeptions.ExistStorageException;
import ru.javawebinar.topjava.Exeptions.NotExistStorageException;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStorage implements Storage {

    protected List<MealWithExceed> storageLst = new ArrayList<>();

    public ListStorage(List<MealWithExceed> storageLst) {
        this.storageLst = storageLst;
    }

    @Override
    public void clear() {
        storageLst.clear();
    }

    @Override
    public void update(MealWithExceed m) {
        int pos=getSearchKey(m.getId());
        if (isExist(pos)) {
            storageLst.set((Integer) pos, m);
        } else {
            throw new NotExistStorageException(m.getId());
        }
    }

    @Override
    public void save(MealWithExceed m) {

        if (isExist(  getSearchKey(m.getId()))) {
            throw new ExistStorageException(m.getId());
        } else {
            storageLst.add(m);
        }
    }

    @Override
    public boolean isExist(Integer index) {
        return index != null;
    }

    @Override
    public MealWithExceed get(String id) {
        return storageLst.get(getSearchKey(id));
    }

    @Override
    public void delete(String id) {
        int pos=getSearchKey(id);
        if (isExist(pos)) {
            storageLst.remove(pos);
        } else {
            throw new NotExistStorageException(id);
        }
    }

    @Override
    public List<MealWithExceed> getAllSorted() {
        return storageLst;
    }

    @Override
    public int size() {
        return storageLst.size();
    }

    protected Integer getSearchKey(String id) {

        for (Integer i = 0; i < storageLst.size(); i++) {
            if (id.equals(storageLst.get(i).getId())) {
                return i;
            }
        }
        return null;
    }
}
