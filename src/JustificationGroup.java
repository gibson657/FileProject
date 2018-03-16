
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JustificationGroup implements ActionListener
{
    public interface Listener
    {
        void event(FileFormatter.Justification justification);
    }

    private Listener listener;
    private JLabel label;
    private JRadioButton leftJustify;
    private JRadioButton rightJustify;
    private ButtonGroup group;

    public JustificationGroup(Listener listener)
    {
        super();

        this.listener = listener;

        label = new JLabel("Justification:");

        leftJustify = new JRadioButton("left");
        rightJustify = new JRadioButton("right");
        leftJustify.addActionListener(this);
        rightJustify.addActionListener(this);

        group = new ButtonGroup();
        group.add(leftJustify);
        group.add(rightJustify);
        group.clearSelection();
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == leftJustify)
        {
            listener.event(FileFormatter.Justification.LEFT);
        }
        else if(event.getSource() == rightJustify)
        {
            listener.event(FileFormatter.Justification.RIGHT);
        }
    }

    public GroupLayout.Group getHorizontalGroup(GroupLayout layout)
    {
        GroupLayout.SequentialGroup group = layout.createSequentialGroup();
        group.addComponent(label);
        group.addComponent(leftJustify);
        group.addComponent(rightJustify);
        return group;
    }

    public GroupLayout.Group getVerticalGroup(GroupLayout layout)
    {
        GroupLayout.ParallelGroup group = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
        group.addComponent(label);
        group.addComponent(leftJustify);
        group.addComponent(rightJustify);
        return group;
    }
}
