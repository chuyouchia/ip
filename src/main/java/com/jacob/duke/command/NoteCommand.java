package main.java.com.jacob.duke.command;

import java.util.ArrayList;
import java.util.List;

import main.java.com.jacob.duke.DukeException;
import main.java.com.jacob.duke.DukeList;
import main.java.com.jacob.duke.Storage;
import main.java.com.jacob.duke.Ui;
import main.java.com.jacob.duke.note.Note;



public class NoteCommand implements Command {
    private String fullCommand;
    public NoteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    @Override
    public String execute(Ui ui, DukeList dukeList, Storage storage) throws DukeException {
        int breakpoint = fullCommand.indexOf("?");
        if (fullCommand.length() <= "note".length()) {
            throw new DukeException("A note cannot be empty!");
        } else if (breakpoint == -1) {
            throw new DukeException("Hey, a note must have a corresponding question!");
        }
        //before break is question
        String question = fullCommand.substring("note".length() + 1, breakpoint + 1);
        //after break is answer till the end
        String answer = fullCommand.substring(breakpoint + 1).trim();
        Note theNote = new Note(question, answer);
        List<Note> noteList = dukeList.getNoteList();
        noteList.add(theNote);
        storage.appendTextToNotes(theNote.convertToFileFormat());
        return ui.showNewNoteAdded(theNote.getCurrentStatus(), noteList);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
