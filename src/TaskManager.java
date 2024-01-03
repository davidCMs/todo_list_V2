
public class TaskManager{
    static void addTask(String name, String description) {
        Task addedTask = new Task(name, description, false);
        Main.tasks.add(addedTask);
    }
    static void ListTasks() {
        if (!Main.tasks.isEmpty()) {
            System.out.println("\n");
            System.out.println("--------------------------------------------------------------------------------\n");
            for (int i = 0; i < Main.tasks.size(); i++) {
                String N = Main.tasks.get(i).getName();
                boolean C = Main.tasks.get(i).isComplete();
                String D = Main.tasks.get(i).getDescription();

                System.out.println(("task id: " + (i + 1)));
                System.out.println("name: " + N);
                System.out.println("description: " + D);
                String green = "\u001B[32m", red = "\u001B[31m", reset = "\u001B[0m";
                System.out.println("completed: " + (C ? green + "true" : red + "false") + reset + "\n");
                System.out.println("--------------------------------------------------------------------------------\n");
            }
        } else System.out.println("No tasks to display\n");
    }
    static void completeTask(){
        System.out.println("Complete which task?");
        String taskToComplete = Main.consoleIn.nextLine();
        try {
            int taskId = Integer.parseInt(taskToComplete);
            int index = taskId - 1;
            if (index >= 0 && index < Main.tasks.size()) {
                Task toComplete = Main.tasks.get(index);
                toComplete.setComplete(true);
                Main.tasks.set(index, toComplete);
                System.out.println("Task " + toComplete.getName() + " set to completed\n" );
            } else {
                System.out.println("You entered a task that doesn't exist. Please look at the task list and enter the right task ID");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid task ID.");
        }
    }
    static void editTask() {
        System.out.println("Edit which task?");
        String taskToEdit = Main.consoleIn.nextLine();

        try {
            int taskId = Integer.parseInt(taskToEdit);
            int index = taskId - 1;
            if (index >= 0 && index < Main.tasks.size()) {
                Task toEdit = Main.tasks.get(index);
                System.out.println("How do you want to edit " + toEdit.getName());
                System.out.println("1: Name");
                System.out.println("2: Description");
                System.out.println("3: Completed or not");

                int inputNumber = Integer.parseInt(Main.consoleIn.nextLine());

                if (inputNumber == 1) {
                    System.out.println("Enter new name");
                    String newName = Main.consoleIn.nextLine();
                    toEdit.setName(newName);
                    Main.tasks.set(index, toEdit);
                    System.out.println("Set the name of the task whit the id of " + taskId +" to " + newName);
                } else if (inputNumber == 2) {
                    System.out.println("Enter new description");
                    String newDescription = Main.consoleIn.nextLine();
                    toEdit.setDescription(newDescription);
                    Main.tasks.set(index, toEdit);
                    System.out.println("Set the description of the task whit the id of " + taskId +" to " + newDescription);
                } else if (inputNumber == 3) {
                    System.out.println("1: True");
                    System.out.println("2: False");
                    int inputNumber2 = Integer.parseInt(Main.consoleIn.nextLine());
                    if (inputNumber2 == 1) {
                        toEdit.setComplete(true);
                        Main.tasks.set(index, toEdit);
                        System.out.println("Task " + toEdit.getName() + " set to completed\n" );
                    } else if (inputNumber2 == 2) {
                        toEdit.setComplete(false);
                        Main.tasks.set(index, toEdit);
                        System.out.println("Task " + toEdit.getName() + " set to not completed\n" );
                    }
                }
            } else {
                System.out.println("You entered a task that doesn't exist. Please look at the task list and enter the right task ID");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid task ID/option.");
        }
    }
    static void deleteTask() {
        System.out.println("Delete which task? WARNING this cannot be undone!");
        String taskToDelete = Main.consoleIn.nextLine();
        try {
            int taskId = Integer.parseInt(taskToDelete);
            int index = taskId - 1;
            if (index >= 0 && index < Main.tasks.size()) {
                Task toDelete = Main.tasks.get(index);
                String nameOfDeleteTask = toDelete.getName();

                Main.tasks.remove(index);
                System.out.println("Deleted task " + nameOfDeleteTask);
            }
        } catch (NumberFormatException e) {
            System.out.println("You entered a task that doesn't exist. Please look at the task list and enter the right task ID");
        }
    }
}
