package baguette;

import baguette.datatypes.Task;
import baguette.datatypes.Todo;
import baguette.datatypes.Deadline;
import baguette.datatypes.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Handles file storage operations for saving and loading tasks locally.
 */
public class Storage {
    private static final String folderPath = "data";
    private static final String filePath = "./data/tasks.txt";

    /**
     * Parses a task from a line of text retrieved from the stored file.
     * Determines the task type (Todo, Deadline, or Event) and reconstructs the task.
     *
     * @param line A line of text from the stored file representing a task.
     * @return A Task object parsed from the given line.
     */
    public static Task parseTask(String line) {
        Task task;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm", Locale.ENGLISH);
        switch (line.substring(0, 1)) {
        case "T":
            task = new Todo(line.substring(Constants.STORAGE_PREFIX_WIDTH).trim());
            break;
        case "D":
            int ddlIndex = line.indexOf(Constants.PREFIX_D);
            task = new Deadline(line.substring(Constants.STORAGE_PREFIX_WIDTH, ddlIndex).trim(),
                    LocalDateTime.parse(line.substring(ddlIndex + Constants.STORAGE_TIME_WIDTH).trim(), formatter));
            break;
        case "E":
            int fromIndex = line.indexOf(Constants.PREFIX_E_F);
            int toIndex = line.indexOf(Constants.PREFIX_E_T);
            System.out.println(line.substring(fromIndex + Constants.STORAGE_TIME_WIDTH, toIndex));
            task = new Event(line.substring(Constants.STORAGE_PREFIX_WIDTH, fromIndex).trim(),
                    LocalDateTime.parse(line.substring(fromIndex + Constants.STORAGE_TIME_WIDTH, toIndex).trim(), formatter),
                    LocalDateTime.parse(line.substring(toIndex + Constants.STORAGE_TIME_WIDTH).trim(), formatter));
            break;
        default:
            task = new Todo(line);
            break;
        }
        if (line.substring(2,3).equals("Y")) {
            task.mark();
        }
        return task;
    }

    /**
     * Loads tasks by reading from the storage file into memory.
     * Ensures target directory and file exist before reading.
     * Parses non-empty lines into Tasks.
     *
     * @return An {@code ArrayList<Task>} containing the tasks retrieved from the stored file.
     * @throws RuntimeException If an I/O error occurs while reading.
     */
    public static ArrayList<Task> generateList() {
        ensureStorage();

        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);

        try (Scanner scanner = new Scanner(file)) {
            populateTaskList(scanner, list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    /**
     * Reads lines from the scanner and adds valid tasks to the list.
     *
     * @param scanner The Scanner to read from.
     * @param list The list to populate with parsed tasks.
     */
    public static void populateTaskList(Scanner scanner, ArrayList<Task> list) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                list.add(parseTask(line));
            }
        }
    }

    /**
     * Ensures that the target storage folder and task files exist.
     * Creates directory and file if missing.
     * Throws runtime exception if an I/O error occurs.
     */
    public static void ensureStorage() {
        File folder = new File(folderPath);
        File file = new File(filePath);

        try {
            if (!folder.exists()) {
                folder.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the stored file with the current list of tasks.
     * Overwrites the existing file with the updated task list.
     *
     * @param tasks The list of tasks to be saved in the storage file.
     * @throws IOException If an error occurs while writing to the file.
     */
    public static void updateFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(convertTaskToFile(task) + Constants.NEW_LINE);
        }
        fw.close();
    }

    /**
     * Converts a Task object into a string format suitable for storage in a file.
     * Formats Todo, Deadline, and Event tasks accordingly.
     *
     * @param task The Task object to be converted.
     * @return A formatted string representation of the task for file storage.
     */
    public static String convertTaskToFile(Task task) {
        switch (task.getType()) {
        case "[T]":
            return "T " + ((task.getIsDone()) ? "Y " : "N ") + task.getDesc();
        case "[D]":
            Deadline deadline = (Deadline) task;
            return "D " + ((task.getIsDone()) ? "Y " : "N ") + task.getDesc() + " " +
                    Constants.PREFIX_D + deadline.getDdl();
        case "[E]":
            Event event = (Event) task;
            return "E " + ((task.getIsDone()) ? "Y " : "N ") + task.getDesc() + " " +
                    Constants.PREFIX_E_F + event.getFrom() + " " +
                    Constants.PREFIX_E_T+ event.getTo();
        }
        return "";
    }
}
