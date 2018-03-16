
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonLabelGroup
{
    private JButton button;
    private JLabel label;

    public ButtonLabelGroup(String buttonText)
    {
        this(buttonText, "");
    }

    public ButtonLabelGroup(String buttonText, String labelText)
    {
        super();

        button = new JButton(buttonText);
        label = new JLabel(labelText);
        label.setMinimumSize(new Dimension(200, 10));
    }

    public void setLabelText(String text)
    {
        label.setText(text);
    }

    public void addButtonListener(ActionListener listener)
    {
        button.addActionListener(listener);
    }

    public void removeButtonListener(ActionListener listener)
    {
        button.removeActionListener(listener);
    }

    public GroupLayout.Group getHorizontalGroup(GroupLayout layout)
    {
        GroupLayout.Group group = layout.createParallelGroup();
        group.addComponent(button);
        group.addComponent(label);
        return group;
    }

    public GroupLayout.Group getVerticalGroup(GroupLayout layout)
    {
        GroupLayout.Group group = layout.createSequentialGroup();
        group.addComponent(button);
        group.addComponent(label);
        return group;
    }
}

