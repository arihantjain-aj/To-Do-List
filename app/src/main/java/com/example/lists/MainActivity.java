package com.example.lists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.lists.Adapter.ToDoAdapter;
import com.example.lists.Models.ToDoModel;
import com.example.lists.Utils.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener {

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private List<ToDoModel> taskList;
    private DatabaseHandler db;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();

        getSupportActionBar().hide();

        db= new DatabaseHandler(this);
        db.openDatabase();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(db,this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        fab= findViewById(R.id.fab);

        ItemTouchHelper itemTouchHelper= new ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        taskList = db.getAllTasks ();
        Collections.reverse(taskList);
        tasksAdapter.setTasks (taskList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new task
                AddNewTask addNewTaskBottomSheet = AddNewTask.newInstance();
                addNewTaskBottomSheet.show(getSupportFragmentManager(), AddNewTask.TAG);

            }
        });
    }

    @Override
    public void handleDialogClose (DialogInterface dialog) {
        taskList = db.getAllTasks();
        Collections. reverse(taskList);
        tasksAdapter.setTasks (taskList);
        tasksAdapter.notifyDataSetChanged ( ) ;
    }
}