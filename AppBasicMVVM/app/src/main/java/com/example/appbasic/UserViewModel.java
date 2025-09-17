package com.example.appbasic;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private final UserRepository userRepository;
    private final LiveData<List<User>> allUsers;

    private LiveData<User> singleUser;


    public LiveData<User> getSingleUser(int userId) {
        singleUser = userRepository.getSingleUser(userId);
        return singleUser;
    }

//    public void setSingleUser(int userId) {
//        if(singleUser == null){
//            singleUser = userRepository.getSingleUser(userId);
//        }
//    }
    public UserViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }


    public void insertUsers(User user){
        userRepository.insertUsers(user);
    }

    public void deleteUser(User user){
        userRepository.deleteUser(user);
    }

    public void updateUser(User user){
        userRepository.updateUser(user);
    }
}
