import javax.swing.*;
import java.awt.*;

public class InfoGroup
{
    private JLabel titleLabel;
    private JLabel infoLabel;

    public InfoGroup(String infoTitle)
    {
        super();

        titleLabel = new JLabel(infoTitle);
        infoLabel = new JLabel();
        infoLabel.setMinimumSize(new Dimension(100, 10));
    }

    public void setInfo(String info)
    {
        infoLabel.setText(info);
    }

    public GroupLayout.Group getHorizontalGroup(GroupLayout layout)
    {
        GroupLayout.SequentialGroup group = layout.createSequentialGroup();
        group.addComponent(titleLabel);
        group.addComponent(infoLabel);
        return group;
    }

    public GroupLayout.Group getVerticalGroup(GroupLayout layout)
    {
        GroupLayout.ParallelGroup group = layout.createParallelGroup();
        group.addComponent(titleLabel);
        group.addComponent(infoLabel);
        return group;
    }
}