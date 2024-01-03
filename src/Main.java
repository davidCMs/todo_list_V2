import java.util.*;

public class Main {

    static List<Task> tasks = new ArrayList<>();
    static TaskManager taskManager = new TaskManager();
    static Scanner consoleIn = new Scanner(System.in);
    static SaveLoad saverLoader = new SaveLoad();


    public static void main(String[] args) {
        tasks.clear();
        tasks = saverLoader.loadFileWhitExtension("default");
        if (args.length > 0 && Objects.equals(args[0], "console")) {
            Main.OutIn();
        } else {
            GuiWindow mainWindow = new GuiWindow();
            mainWindow.makeWindow();
        }


    }

    static void OutIn() {
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("1: List tasks");
            System.out.println("2: Add a task");
            System.out.println("3: Complete a task");
            System.out.println("4: Edit a task");
            System.out.println("5: Delete a task");
            System.out.println("6: Save to file");
            System.out.println("7: load to file");
            System.out.println("8: Exit");

            String input = consoleIn.nextLine();

            if (input.isEmpty()) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            try {
                int inputNumber = Integer.parseInt(input);
                if (inputNumber == 1) {
                    taskManager.ListTasks();
                } else if (inputNumber == 2) {
                    System.out.println("Enter a name for the new task.");
                    String name = consoleIn.nextLine();
                    System.out.println("Enter a description for the new task.");
                    String description = consoleIn.nextLine();
                    taskManager.addTask(name, description);
                    taskManager.ListTasks();
                } else if (inputNumber == 3) {
                    taskManager.completeTask();
                } else if (inputNumber == 4) {
                    taskManager.editTask();
                } else if (inputNumber == 5) {
                    taskManager.deleteTask();
                } else if (inputNumber == 6) {
                    System.out.println("Name for the save file.");
                    String fileName = Main.consoleIn.nextLine();
                    saverLoader.saveFileWithExtension(tasks, fileName);
                } else if (inputNumber == 7) {
                    tasks.clear();
                    System.out.println("Load from file with the name of.");
                    String fileName = Main.consoleIn.nextLine();
                    tasks = saverLoader.loadFileWhitExtension(fileName);
                } else if (inputNumber == 8) {
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                } else {
                    System.out.println("You have not put in a valid option. Please look below at the options.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}