import javax.swing.*;

public class SpacingGroup extends RadioButtonGroup
{
    public interface Listener
    {
        void event(FileFormatter.Spacing spacing);
    }

    private static final int SINGLE = 0;
    private static final int DOUBLE = 1;

    public SpacingGroup(Listener listener)
    {
        super("Spacing:", new JRadioButton("single"), new JRadioButton("double"));
        buttons.get(SINGLE)
               .addActionListener(event -> listener.event(FileFormatter.Spacing.SINGLE));
        buttons.get(DOUBLE)
               .addActionListener(event -> listener.event(FileFormatter.Spacing.DOUBLE));
    }
}
