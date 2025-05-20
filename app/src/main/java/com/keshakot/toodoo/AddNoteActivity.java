package com.keshakot.toodoo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextEnterNote;
    private RadioButton radioButtonPriorityLow;
    private RadioButton radioButtonPriorityMedium;
//    private RadioButton radioButtonPriorityHigh;
    private Button buttonSaveNote;

    private Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();
        buttonSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void saveNote() {
        String text = editTextEnterNote.getText().toString().trim();
        if (text.isEmpty()) {
            Toast.makeText(AddNoteActivity.this,
                    R.string.toast_note_not_input,
                    Toast.LENGTH_LONG).show();
            return;
        }
        int priority = getPriority();
        int id = database.getNotes().size();
        Note note = new Note(id, text, priority);
        database.add(note);

        finish();
    }

    private int getPriority() {
        int priority;
        if (radioButtonPriorityLow.isChecked()) {
            priority = 0;
        } else if (radioButtonPriorityMedium.isChecked()) {
            priority = 1;
        } else priority = 2;
        return priority;
    }

    private void initViews() {
        editTextEnterNote = findViewById(R.id.editTextEnterNote);
        radioButtonPriorityLow = findViewById(R.id.radioButtonPriorityLow);
        radioButtonPriorityMedium = findViewById(R.id.radioButtonPriorityMedium);
//        radioButtonPriorityHigh = findViewById(R.id.radioButtonPriorityHigh);
        buttonSaveNote = findViewById(R.id.buttonSaveNote);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AddNoteActivity.class);
    }
}