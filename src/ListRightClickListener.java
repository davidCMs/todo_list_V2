import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListRightClickListener extends MouseAdapter {
    private JList<Task> list;

    ListRightClickListener(JList list) {
        this.list = list;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            int index = list.locationToIndex(e.getPoint());
            list.setSelectedIndex(index);

            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem deleteTask = new JMenuItem("Delete");
            deleteTask.addActionListener(actionEvent -> {
                DefaultListModel<Task> model = (DefaultListModel<Task>) list.getModel();
                model.removeElementAt(list.getSelectedIndex());
                dumpToMain();
            });
            JMenuItem renameTask = new JMenuItem("Rename");
            renameTask.addActionListener(actionEvent -> {
                DefaultListModel<Task> model = (DefaultListModel<Task>) list.getModel();
                Task taskToRename = model.get(list.getSelectedIndex());
                taskToRename.setName(JOptionPane.showInputDialog("Enter new task name"));
                dumpToMain();
            });
            JMenuItem reDescribeTask = new JMenuItem("Redescribe");
            reDescribeTask.addActionListener(actionEvent -> {
                DefaultListModel<Task> model = (DefaultListModel<Task>) list.getModel();
                Task taskToRedescribe = model.get(list.getSelectedIndex());
                taskToRedescribe.setDescription(JOptionPane.showInputDialog("Enter new task describe"));
                dumpToMain();
            });
            JMenuItem toggleCompleate = new JMenuItem("Toggle complete");
            toggleCompleate.addActionListener(actionEvent -> {
                DefaultListModel<Task> model = (DefaultListModel<Task>) list.getModel();
                Task taskToToggleCompleate = model.get(list.getSelectedIndex());

                taskToToggleCompleate.setComplete(!taskToToggleCompleate.isComplete());

                dumpToMain();
            });

            popupMenu.add(toggleCompleate);
            popupMenu.add(renameTask);
            popupMenu.add(reDescribeTask);
            popupMenu.add(deleteTask);
            popupMenu.show(list, e.getX(), e.getY());

        }
    }
    private void dumpToMain() {
        Main.tasks.clear();
        for (int i = 0; i < GuiWindow.taskListModel.getSize(); i++)
            Main.tasks.add(GuiWindow.taskListModel.get(i));
        GuiWindow.refreshWindow();
    }
}