import javax.swing.*;
import java.awt.*;

public class TaskCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Task task = (Task) value;
        JLabel label = (JLabel) super.getListCellRendererComponent(list, "", index, isSelected, cellHasFocus);

        label.setText("<html>" + (index + 1) + ". " + task.getName() + " status: " +
                (task.isComplete() ? "<font color='green'>done</font>" : "<font color='red'>not done</font>") +
                "</html>");

        return label;
    }
}
