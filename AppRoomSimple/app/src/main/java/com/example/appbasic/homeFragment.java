package com.example.appbasic;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class homeFragment extends Fragment {


   // List<User> userList = new ArrayList<User>();
    RecyclerView recyclerView;

    DbHandle dbHandle;
    MyAdapter myAdapter;
    List<User> userList;

    ExecutorService service;
    Handler handler;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbHandle = DbHandle.getInstance(requireContext());

        //reference vue
        recyclerView = view.findViewById(R.id.recycleView1);

        SearchView userSearch = view.findViewById(R.id.userSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myAdapter = new MyAdapter(new ArrayList<>());

        recyclerView.setAdapter(myAdapter);

        service = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

        //database
//        Executors.newSingleThreadExecutor().execute(() -> {
////            dbHandle.getUserDao().insertSingleUser(new User("Derisma","Hericka","herickaderisma.com",R.drawable.ednerimage));
////            dbHandle.getUserDao().insertSingleUser(new User("Derisma","Hericka","herickaderisma.com",R.drawable.ednerimage));
//
//            userList = dbHandle.getUserDao().getAll();
//
//            new Handler(Looper.getMainLooper()).post(() -> {
//                myAdapter.setListeUser(userList);
//            });
//
//        });

        loadUsers();

        userSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                myAdapter.filter(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                myAdapter.filter(s);
                return true;
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int position1 = viewHolder.getAdapterPosition();
                int position2 = target.getAdapterPosition();
                Collections.swap(userList, position1, position2);
                myAdapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                User user = userList.get(position);

//                Executors.newSingleThreadExecutor().execute(() -> {
//                    dbHandle.getUserDao().deleteUser(user);
//                });
//                myAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(),  user.getPrenom()+" "+user.getNom() + " deleted", Toast.LENGTH_SHORT).show();
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);



    }

    private void loadUsers() {
        service.execute(() -> {
           List<User> userList1 = dbHandle.getUserDao().getAll();

           handler.post(() -> {
                myAdapter.setListeUser(userList1);
           });
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //reference vue
        ImageButton addButton = view.findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), AddOrEditActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        service.shutdown();
    }
}