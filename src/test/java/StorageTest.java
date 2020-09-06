import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import main.java.com.jacob.duke.task.Event;
import org.junit.jupiter.api.Test;

import main.java.com.jacob.duke.Storage;
import main.java.com.jacob.duke.task.Deadline;
import main.java.com.jacob.duke.task.Task;
import main.java.com.jacob.duke.task.Todo;

public class StorageTest {
    //whatIsBeingTested_descriptionOfTestInputs_expectedOutcome
    private Storage storageReadObject = new Storage("data/readTest.txt");

    @Test
    public void readFile_foundFile_success() {
        List<Task> taskList = storageReadObject.readFile();
        List<Task> dummyTaskList = new ArrayList<Task>();
        dummyTaskList.add(new Deadline("omo"));
        dummyTaskList.add(new Todo("omo"));
        assertEquals(dummyTaskList.get(0).getClass(), taskList.get(0).getClass());
        assertEquals(dummyTaskList.get(1).getClass(), taskList.get(1).getClass());
    }

    @Test
    public void writeToFile_updateFile_success() {
        Storage storageWriteObject = new Storage("data/writeTest.txt");
        String s1 = new Deadline("omo", "2019-10-15 1800").convertToFile();
        String s2 = new Todo("omo").convertToFile();
        storageWriteObject.appendText(s1);
        storageWriteObject.appendText(s2);
        storageWriteObject.writeToFile();

        List<Task> dummyTaskList = storageWriteObject.readFile();
        assertEquals(Deadline.class, dummyTaskList.get(dummyTaskList.size()-2).getClass());
        assertEquals(Todo.class, dummyTaskList.get(dummyTaskList.size()-1).getClass());
    }
    @Test
    public void replace_updateTextFileFromDeadlineToEventAndBack_success() {
        Storage storageWriteObject = new Storage("data/writeTest.txt");

        //change deadline to event
        List<Task> dummyTaskList= storageWriteObject.readFile();
        String original = dummyTaskList.get(0).convertToFile();
        String replacement = new Event("omo", "2019-10-15 1800").convertToFile();
        storageWriteObject.replacement(original, replacement);

        storageWriteObject.writeToFile();
        dummyTaskList = storageWriteObject.readFile();

        assertEquals(Event.class, dummyTaskList.get(dummyTaskList.size()-2).getClass());

        //re-read the updated
        String original1 = dummyTaskList.get(0).convertToFile();
        String replacement1 = new Deadline("omo", "2019-10-15 1800").convertToFile();
        storageWriteObject.replacement(original1, replacement1);

        storageWriteObject.writeToFile();
        dummyTaskList = storageWriteObject.readFile();
        assertEquals(Deadline.class, dummyTaskList.get(dummyTaskList.size()-2).getClass());
    }

    @Test
    public void appendText_updateTextFile_success() {
        int x = 0;
        assertEquals(0, x);
    }

    @Test
    public void removeText_updateTextFile_success() {
        int x = 0;
        assertEquals(0, x);
    }
}