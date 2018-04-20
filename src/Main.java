import javax.swing.*;

public class Main
{
	static final int INITIAL_LINE_LENGTH = 80 ;
	static final int MIN_LINE_LENGTH = 20;
	static final int MAX_LINE_LENGTH = 100;
	
  private static final FileFormatter formatter = new FileFormatter();

        public static void main(String[] args)
        {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Text File Formatter");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setContentPane(new AppPanel());
            frame.pack();
            frame.setVisible(true);
        });
    }

        static FileFormatter getFormatter() {
        	return formatter;
        }
}
