import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class RadioButtonGroup
{
    private   JLabel             label;
    private   ButtonGroup        buttonGroup;
    protected List<JRadioButton> buttons;

    public RadioButtonGroup(String labelText, JRadioButton... buttons)
    {
        label = new JLabel(labelText);

        this.buttons = new ArrayList<>(buttons.length);
        Collections.addAll(this.buttons, buttons);

        buttonGroup = new ButtonGroup();
        this.buttons.forEach(buttonGroup::add);
        buttonGroup.clearSelection();
    }

    public GroupLayout.Group getHorizontalGroup(GroupLayout layout)
    {
        GroupLayout.Group group = layout.createSequentialGroup();
        group.addComponent(label);
        this.buttons.forEach(group::addComponent);
        return group;
    }

    public GroupLayout.Group getVerticalGroup(GroupLayout layout)
    {
        GroupLayout.Group group = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
        group.addComponent(label);
        this.buttons.forEach(group::addComponent);
        return group;
    }
}
