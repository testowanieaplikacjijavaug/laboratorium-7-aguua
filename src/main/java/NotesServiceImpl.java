import java.util.Collection;
import com.google.common.base.Preconditions;

public class NotesServiceImpl implements NotesService {

    public static NotesServiceImpl createWith(final NotesStorage storageService) {
        return new NotesServiceImpl(storageService);
    }

    @Override
    public void add(Note note) {
        storageService.add(note);
    }

    @Override
    public float averageOf(String name) {
        Preconditions.checkArgument(name != null, "Imię ucznia nie może być null");
        Preconditions.checkArgument(!name.trim().isEmpty(), "Imię ucznia nie może być puste");
        try {
            float sum = 0.0f;
            final Collection<Note> notes = storageService.getAllNotesOf(name);
            for (final Note note : notes) {
                sum += note.getNote();
            }

        return sum / notes.size();
        }catch (NullPointerException e) {
            throw new IllegalArgumentException("Nie ma ocen ucznia: "+name);
        }

    }

    @Override
    public void clear() {
        storageService.clear();
    }

    private NotesServiceImpl(final NotesStorage storageService) {
        this.storageService = storageService;
    }

    private final NotesStorage storageService;

}