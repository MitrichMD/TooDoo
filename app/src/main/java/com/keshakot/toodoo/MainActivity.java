package com.keshakot.toodoo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
//    private LinearLayout linearLayoutNotes;
    private FloatingActionButton buttonAddNote;
    private NotesAdapter notesAdapter;

    private Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        notesAdapter = new NotesAdapter();
        notesAdapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
                database.remove(note.getId());
                showNotes();
            }
        });
        recyclerViewNotes.setAdapter(notesAdapter);
//        recyclerViewNotes.setLayoutManager(LinearLayoutManager(this));

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddNoteActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showNotes();
    }

    private void initViews() {
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
//        linearLayoutNotes = findViewById(R.id.linearLayoutNotes);
        buttonAddNote = findViewById(R.id.floatingActionButtonAddNote);
    }

    private void showNotes() {
        notesAdapter.setNotes(database.getNotes());
//        linearLayoutNotes.removeAllViews();
//        for (Note note : database.getNotes()) {
//            View view = getLayoutInflater().inflate(
//                    R.layout.note_item,
//                    linearLayoutNotes,
//                    false
//            );
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    database.remove(note.getId());
//                    showNotes();
//                }
//            });
//            TextView textViewNote = view.findViewById(R.id.textViewNote);
//            textViewNote.setText(note.getText());
//
//            int colorResId;
//            switch (note.getPriority()) {
//                case 0:
//                    colorResId = android.R.color.holo_green_light;
//                    break;
//                case 1:
//                    colorResId = android.R.color.holo_orange_light;
//                    break;
//                default:
//                    colorResId = android.R.color.holo_red_light;
//            }
//            int color = ContextCompat.getColor(this, colorResId);
//            textViewNote.setBackgroundColor(color);
//            linearLayoutNotes.addView(view);
//        }
    }
}