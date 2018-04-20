import javax.swing.*;
import java.awt.*;

public class Info extends LabeledComponent<JLabel>
{
    public Info(String infoTitle)
    {
        super(new JLabel(), infoTitle);

        comp.setMinimumSize(new Dimension(100, 10));
    }

    public void setTitle(String info)
    {
        comp.setText(info);
    }
}
