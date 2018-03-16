import javax.swing.*;

public class Main
{
    private static FileFormatter formatter;

    public static void main(String[] args)
    {
        formatter = new FileFormatter();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Text File Formatter");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setContentPane(new AppPanel());
            frame.pack();
            frame.setVisible(true);
        });
    }

    public static FileFormatter getFormatter()
    {
        return formatter;
    }
}
