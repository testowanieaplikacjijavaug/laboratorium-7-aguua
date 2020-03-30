import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.Map.Entry;
import java.util.TreeMap;


public class MockNotesStorage implements NotesStorage {

    private Map<String, List<Note>> studentNotes = new TreeMap<>();

    private boolean emptyList = true;

    public void add(Note note) {
        if(studentNotes.containsKey(note.getName()))
            studentNotes.get(note.getName()).add(note);
        else{
            List<Note> notes = new ArrayList<>();
            notes.add(note);
            studentNotes.put(note.getName(), notes);
        }

        emptyList = false;
    }


    public boolean isClear(){
        return emptyList;
    }

    public int studentNotesCount(String name){
        return studentNotes.get(name).size();
    }

    public List<Note> getAllNotesOf(String name) {
        return studentNotes.get(name);
    }

    public void clear() {
        studentNotes.clear();
        emptyList = true;
    }
}
