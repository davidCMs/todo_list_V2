import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Objects;


public class GuiWindow {
    static JFrame window = new JFrame("ToDo list");
    static DefaultListModel<Task> taskListModel = new DefaultListModel<>();
    static boolean descriptionLoading;
    static int selectedTask;
    static String loadedFile;
    public static void makeWindow() {

        window.setSize(800, 400);
        window.setLayout(new GridBagLayout());
        JPanel gridBagPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        refreshWindow();
        

        JList<Task> taskList = new JList<>(taskListModel);
        taskList.setCellRenderer(new TaskCellRenderer());
        JScrollPane taskListScroll = new JScrollPane(taskList);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.addMouseListener(new ListRightClickListener(taskList));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 2;
        gbc.weighty = 30.0;
        gbc.fill = GridBagConstraints.BOTH;
        gridBagPanel.add(taskListScroll, gbc);
        gbc.gridwidth = 1;

        JTextArea description = new JTextArea("The description of the task you select should appear here.");
        description.setLineWrap(true);
        description.setWrapStyleWord(true);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = .01;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gridBagPanel.add(description, gbc);
        gbc.gridheight = 1;

        MouseListener taskSelectListner = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedTask = taskList.getSelectedIndex();
                desctripsionLoader(description);
                System.out.println(selectedTask);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        DocumentListener descriptionChangeListner = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                descriptionUnloader(taskList,description);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                descriptionUnloader(taskList,description);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        };
        taskList.addMouseListener(taskSelectListner);
        description.getDocument().addDocumentListener(descriptionChangeListner);


        window.addWindowListener( new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);


                if (Objects.equals(description.getText(), "")) {
                    descriptionUnloader(taskList,description);
                }




                Main.saverLoader.saveFileWithExtension(Main.tasks, "default");



                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });



        JButton newTask = new JButton("Add Task");
        newTask.addActionListener(e -> {
            String Name = JOptionPane.showInputDialog("Enter task name.");
            String Description = JOptionPane.showInputDialog("Enter task description.");
            Main.taskManager.addTask(Name,Description);
            refreshWindow();
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        gridBagPanel.add(newTask, gbc);

        JButton save = new JButton("Save");
        save.addActionListener(e -> {


            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select a Directory");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {

                String fileName = JOptionPane.showInputDialog("Name for the file");

                if (!(fileName ==null)) {
                    descriptionUnloader(taskList,description);
                    if (!Objects.equals(fileChooser.getSelectedFile().getAbsolutePath(), "null")) {
                        Main.saverLoader.saveFileWithExtension(Main.tasks, fileChooser.getSelectedFile().getAbsolutePath() + "\\" + fileName);
                        System.out.println(fileChooser.getSelectedFile().getAbsolutePath() + "\\" + fileName);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"No name was given!", "No Name", JOptionPane.ERROR_MESSAGE);
                }

            } else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(null,"No file was selected!", "No File", JOptionPane.ERROR_MESSAGE);
            }


        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        gridBagPanel.add(save, gbc);

        JButton load = new JButton("Load");
        load.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                Main.tasks = Main.saverLoader.loadFile(selectedFile.getAbsolutePath());
                refreshWindow();

            } else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(null,"No file was selected!", "No File", JOptionPane.ERROR_MESSAGE);
            }



        });
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 0.01;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        gridBagPanel.add(load, gbc);


        GridBagConstraints mainPanelConstraints = new GridBagConstraints();
        mainPanelConstraints.gridx = 0;
        mainPanelConstraints.gridy = 0;
        mainPanelConstraints.weightx = 1.0;
        mainPanelConstraints.weighty = 1.0;
        mainPanelConstraints.fill = GridBagConstraints.BOTH;
        window.add(gridBagPanel, mainPanelConstraints);





        window.setVisible(true);
    }
    static void refreshWindow() {
        loadedFile = Main.saverLoader.getLoadedFile();
        System.out.println(loadedFile);
        window.setTitle("ToDo list, currently loaded File: " + loadedFile );
        taskListModel.clear();
        for (Task task : Main.tasks) {
            taskListModel.addElement(task);
        }
    }
    static void desctripsionLoader(JTextArea textArea) {
        descriptionLoading = true;
        textArea.setText(taskListModel.get(selectedTask).getDescription());
        descriptionLoading = false;
    }
    static void descriptionUnloader(JList taskList, JTextArea textArea) {

        if ((!descriptionLoading) && (Objects.equals(textArea.getText(), "The description of the task you select should appear here.")) && (selectedTask == -1)) {

            System.out.println(selectedTask);
            Task toEdit = new Task(taskListModel.get(selectedTask).getName(), textArea.getText(), taskListModel.get(selectedTask).isComplete());
            Main.tasks.set(selectedTask, toEdit);
            refreshWindow();
        }
    }
}
