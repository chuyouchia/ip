package main.java.com.jacob.duke.command;

import main.java.com.jacob.duke.DukeException;
import main.java.com.jacob.duke.Storage;
import main.java.com.jacob.duke.DukeList;
import main.java.com.jacob.duke.Ui;


public interface Command {
    public String execute(Ui ui, DukeList dukeList, Storage storage) throws DukeException;
    public boolean isBye();
}
