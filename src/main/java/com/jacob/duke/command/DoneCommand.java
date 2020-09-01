package main.java.com.jacob.duke.command;

import java.util.List;

import main.java.com.jacob.duke.Storage;
import main.java.com.jacob.duke.TaskList;
import main.java.com.jacob.duke.Ui;

import main.java.com.jacob.duke.task.Task;



public class DoneCommand implements Command {
    private String fullCommand;

    /**
     * Constructor for Done Command
     * @param fullCommand with from console input
     */
    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Execution command for pre-determined Done Command
     * @param ui UI object to deal with program output
     * @param tasks Task List Representation
     * @param storage Storage object to deal with interfacing with file system
     */
    @Override
    public String execute(Ui ui, TaskList tasks, Storage storage) {
        List<Task> taskList = tasks.getTaskList();
        //get the integer from the string command and convert to integer
        Task theTask = taskList.get(Integer.parseInt(fullCommand.substring(5)) - 1);
        String lineToEdit = theTask.convertToFile();

        //set task as done
        theTask.setDone();
        String replacementText = theTask.convertToFile();

        //replace line
        try {
            storage.replacement(lineToEdit, replacementText);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Line to replace does not match any string in text: " + e.getMessage());
        }
        return ui.showDone(theTask.getCurrentStatus());
    }

    /**
     * Check if it is the bye Command
     * @return false since it is not
     */
    @Override
    public boolean isBye() {
        return false;
    }
}
