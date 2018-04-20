import javax.swing.*;
import java.awt.event.ActionListener;

public class FileSelectButton extends LabeledComponent<JButton>
{
    public FileSelectButton(String buttonText, String labelText)
    {
        super(new JButton(buttonText), labelText);
    }

    public void setListener(ActionListener listener)
    {
        comp.addActionListener(listener);
    }

    @Override
    public GroupLayout.Group getHorizontalGroup(GroupLayout layout)
    {
        GroupLayout.Group group = layout.createSequentialGroup();
        group.addComponent(comp);
        group.addComponent(label);
        return group;
    }
}

