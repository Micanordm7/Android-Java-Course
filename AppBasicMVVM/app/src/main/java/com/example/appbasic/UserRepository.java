package com.example.appbasic;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UserRepository {
    private final UserDao userDao;
    private final LiveData<List<User>> allUsers;

    private LiveData<User> singleUser;
    Executor executor = Executors.newSingleThreadExecutor();


    public UserRepository(Application application) {
        DbHandle db = DbHandle.getInstance(application);
        userDao = db.getUserDao();
        allUsers = userDao.getAll();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<User> getSingleUser(int idUser){
        singleUser = userDao.getSingleUser(idUser);
        return singleUser;
    }

    public void insertUsers(User user){
        executor.execute(() -> userDao.insertAll(user));
    }

    public void deleteUser(User user){
        executor.execute(() -> userDao.deleteUser(user));
    }

    public void updateUser(User user){
        executor.execute(() -> userDao.updateUser(user));
    }
}
