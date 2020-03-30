import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NotesServiceImplTest {
    NotesServiceImpl servicImp;
    MockNotesStorage mockStorage;

    @Before public void setUp(){
        mockStorage =  new MockNotesStorage();
        servicImp = NotesServiceImpl.createWith(mockStorage);
        mockStorage.add( Note.of("Adam", 4));
        mockStorage.add( Note.of("Adam", 5.5f));
        mockStorage.add( Note.of("Adam", 3));
        mockStorage.add( Note.of("Adam", 5));
    }

    @Test public void testAvarage(){
        assertThat(servicImp.averageOf("Adam")).isEqualTo(4.375f);
    }
    @Test public void testAvarageForNoExistingPerson(){
        assertThatThrownBy(() -> {
            servicImp.averageOf("Ala");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Nie ma ocen ucznia: Ala");
    }
    @Test public void testAvarageForNull(){
        assertThatThrownBy(() -> {
            servicImp.averageOf(null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Imię ucznia nie może być null");
    }
    @Test public void testAvarageForEmpty(){
        assertThatThrownBy(() -> {
            servicImp.averageOf("");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Imię ucznia nie może być puste");
    }

    @Test
    public  void testClear(){
        servicImp.clear();
        assertThat(mockStorage.isClear()).isTrue();
    }

    @Test
    public  void testAddNextNote(){
        servicImp.add(Note.of("Adam", 4));
        assertThat(mockStorage.studentNotesCount("Adam")).isEqualTo(5);
    }

    @Test
    public  void testAddFirstNote(){
        servicImp.add(Note.of("Ala", 4));
        assertThat(mockStorage.studentNotesCount("Ala")).isEqualTo(1);
    }

    @After
    public void tearDown() {
        mockStorage.clear();
    }

}
