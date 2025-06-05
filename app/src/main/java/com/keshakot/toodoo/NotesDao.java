package com.keshakot.toodoo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM notes")
    List<Note> getNotes();
//    Single<List<Note>> getNotes();
//    LiveData<List<Note>> getNotes();

    @Insert
//    Completable add(Note note);
    void add(Note note);

    @Query("DELETE FROM notes WHERE id = :id")
//    Completable remove(int id);
    void remove(int id);
}
