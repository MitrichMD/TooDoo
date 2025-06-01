package com.keshakot.toodoo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    //    private LinearLayout linearLayoutNotes;
    private FloatingActionButton buttonAddNote;
    private NotesAdapter notesAdapter;

//    private Database database = Database.getInstance();
//    private  NoteDatabase noteDatabase;
//    private Handler handler = new Handler(Looper.getMainLooper());
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        viewModel = new MainViewModel(getApplication());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                Toast.makeText(
                        MainActivity.this,
                        String.valueOf(count),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
//        noteDatabase = NoteDatabase.getInstance(getApplication());
        initViews();

        notesAdapter = new NotesAdapter();
        notesAdapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
//                database.remove(note.getId());
//                showNotes();
                viewModel.showCount();
//                int count = viewModel.getCount();

            }
        });
        recyclerViewNotes.setAdapter(notesAdapter);
//        recyclerViewNotes.setLayoutManager(LinearLayoutManager(this));

//        noteDatabase.notesDao().getNotes().observe(this, new Observer<List<Note>>() {
        viewModel.getNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                    notesAdapter.setNotes(notes);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(
                        0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                ) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target
                    ) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Note note = notesAdapter.getNotes().get(position);
////                        database.remove(note.getId());
//                        Thread thread = new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                noteDatabase.notesDao().remove(note.getId());
////                                handler.post(new Runnable() {
////                                    @Override
////                                    public void run() {
////                                        showNotes();
////                                    }
////                                });
//                            }
//                        });
//                        thread.start();
                        viewModel.remove(note);
                    }
                });
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes);

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddNoteActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        showNotes();
//    }

    private void initViews() {
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
//        linearLayoutNotes = findViewById(R.id.linearLayoutNotes);
        buttonAddNote = findViewById(R.id.floatingActionButtonAddNote);
    }

//    private void showNotes() {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<Note> notes = noteDatabase.notesDao().getNotes();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        notesAdapter.setNotes(notes);
//                    }
//                });
//            }
//        });
//        thread.start();
////        notesAdapter.setNotes(database.getNotes());
////        linearLayoutNotes.removeAllViews();
////        for (Note note : database.getNotes()) {
////            View view = getLayoutInflater().inflate(
////                    R.layout.note_item,
////                    linearLayoutNotes,
////                    false
////            );
////            view.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    database.remove(note.getId());
////                    showNotes();
////                }
////            });
////            TextView textViewNote = view.findViewById(R.id.textViewNote);
////            textViewNote.setText(note.getText());
////
////            int colorResId;
////            switch (note.getPriority()) {
////                case 0:
////                    colorResId = android.R.color.holo_green_light;
////                    break;
////                case 1:
////                    colorResId = android.R.color.holo_orange_light;
////                    break;
////                default:
////                    colorResId = android.R.color.holo_red_light;
////            }
////            int color = ContextCompat.getColor(this, colorResId);
////            textViewNote.setBackgroundColor(color);
////            linearLayoutNotes.addView(view);
////        }
//    }
}