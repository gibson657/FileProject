import javax.swing.*;

public class IntegerInput extends LabeledComponent<JSpinner>
{
    public interface Listener
    {
        void event(int maxLineLength);
    }

    public IntegerInput(String labelText, int initialValue, int minValue, int maxValue, Listener listener)
    {
        super(new JSpinner(new SpinnerNumberModel(initialValue, minValue, maxValue, 1)), labelText);
        ((JSpinner.DefaultEditor) comp.getEditor()).getTextField()
                                                   .setEditable(true);
        comp.addChangeListener(event -> listener.event((int) comp.getValue()));
    }
}
