import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NoteTest {


    Note note;
    @Before
    public void setUP(){
        note =  Note.of("Adam",4);
    }
    @Test
    public void testGetName(){
        assertThat( note.getName()).isEqualTo("Adam");
    }
    @Test
    public void testGetNote(){
        assertThat(note.getNote()).isBetween(2.0f,6.0f).isEqualTo(4);
    }

    @Test
    public void testNullName(){
        assertThatThrownBy(() -> {
            Note newNone = Note.of (null, 3);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Imię ucznia nie może być null");
    }

    @Test
    public void testEmptyName(){
        assertThatThrownBy(() -> {
            Note newNone = Note.of ("", 3);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Imię ucznia nie może być puste");
    }

    @Test
    public void testNoteUnderExpected(){
        assertThatThrownBy(() -> {
            Note newNone = Note.of ("Adam", 1);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Niewłaściwa ocena");
    }
    @Test
    public void testNoteAboveExpected(){
        assertThatThrownBy(() -> {
            Note newNone = Note.of ("Adam", 8);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Niewłaściwa ocena");
    }
}
