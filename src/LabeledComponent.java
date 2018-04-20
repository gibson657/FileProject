import javax.swing.*;

public class LabeledComponent<T extends JComponent>
{
    protected JLabel label;
    protected T      comp;

    protected LabeledComponent(T comp, String labelText)
    {
        super();

        this.comp = comp;
        label = new JLabel(labelText);
    }

    public void setLabelText(String text)
    {
        label.setText(text);
    }

    public GroupLayout.Group getHorizontalGroup(GroupLayout layout)
    {
        GroupLayout.Group group = layout.createSequentialGroup();
        group.addComponent(label);
        group.addComponent(comp);
        return group;
    }

    public GroupLayout.Group getVerticalGroup(GroupLayout layout)
    {
        GroupLayout.Group group = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
        group.addComponent(label);
        group.addComponent(comp);
        return group;
    }
}
