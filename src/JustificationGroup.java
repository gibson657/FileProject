
import javax.swing.*;

public class JustificationGroup extends RadioButtonGroup
{
    public interface Listener
    {
        void event(FileFormatter.Justification justification);
    }

    private static final int LEFT  = 0;
    private static final int RIGHT = 1;
    private static final int FULL  = 2;

    public JustificationGroup(Listener listener)
    {
        super("Justification:", new JRadioButton("left"), new JRadioButton("right"), new JRadioButton("full"));
        buttons.get(LEFT)
               .addActionListener(event -> listener.event(FileFormatter.Justification.LEFT));
        buttons.get(RIGHT)
               .addActionListener(event -> listener.event(FileFormatter.Justification.RIGHT));
        buttons.get(FULL)
               .addActionListener(event -> listener.event(FileFormatter.Justification.FULL));
    }
}
