
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class AppPanel extends JPanel
{
    private JFileChooser inputChooser;
    private JFileChooser outputChooser;

    private FileSelectButton   inputSelect;
    private FileSelectButton   outputSelect;
    private IntegerInput       lineLengthSelect;
    private JustificationGroup justificationGroup;
    private SpacingGroup       spacingGroup;

    private Info wordsProcessedPanel;
    private Info numberOfLinesPanel;
    private Info blankLinesRemovedPanel;
    private Info spacesAddedPanel;
    private Info averageWordsPerLinePanel;
    private Info averageLineLengthPanel;

    private JButton formatButton;

    public AppPanel()
    {
        super();

        inputChooser = new TextFileChooser(JFileChooser.OPEN_DIALOG);
        outputChooser = new TextFileChooser(JFileChooser.SAVE_DIALOG);

        inputSelect = new FileSelectButton("Select Input File", "");
        inputSelect.setListener(event -> {
            int state = inputChooser.showOpenDialog(this);

            if (state == JFileChooser.APPROVE_OPTION)
            {
                File file = inputChooser.getSelectedFile();
                Main.getFormatter()
                    .setInputFile(file);
                inputSelect.setLabelText(file.getName());
            }
            else
            {
                Main.getFormatter()
                    .setInputFile(null);
            }
        });

        outputSelect = new FileSelectButton("Select Output File", "");
        outputSelect.setListener(event -> {
            int state = outputChooser.showOpenDialog(this);

            if (state == JFileChooser.APPROVE_OPTION)
            {
                File file = outputChooser.getSelectedFile();
                Main.getFormatter()
                    .setOutputFile(file);
                outputSelect.setLabelText(file.getName());
            }
            else
            {
                Main.getFormatter()
                    .setOutputFile(null);
            }
        });

        lineLengthSelect =
            new IntegerInput("Line length:", Main.INITIAL_LINE_LENGTH, Main.MIN_LINE_LENGTH, Main.MAX_LINE_LENGTH,
                             Main.getFormatter()::setMaxLineLength);
        justificationGroup = new JustificationGroup(Main.getFormatter()::setJustification);
        spacingGroup = new SpacingGroup(Main.getFormatter()::setSpacing);

        wordsProcessedPanel = new Info("Words Processed:");
        numberOfLinesPanel = new Info("Number of Lines:");
        blankLinesRemovedPanel = new Info("Blank Lines Removed:");
        spacesAddedPanel = new Info("Total Spaces Added:");
        averageWordsPerLinePanel = new Info("Average Words Per Line:");
        averageLineLengthPanel = new Info("Average Line Length:");

        formatButton = new JButton("Format");
        formatButton.addActionListener(event -> {
            try
            {
                FileFormatter.Results results = Main.getFormatter()
                                                    .format();
                wordsProcessedPanel.setTitle(results.wordsProcessed + "");
                numberOfLinesPanel.setTitle(results.numberOfLines + "");
                blankLinesRemovedPanel.setTitle(results.blankLinesRemoved + "");
                spacesAddedPanel.setTitle(results.spacesAdded + "");
                averageWordsPerLinePanel.setTitle(String.format("%.2f", results.averageWordsPerLine));
                averageLineLengthPanel.setTitle(String.format("%.2f", results.averageLineLength));
            }
            catch (FileFormatter.InputFileException exception)
            {
                JOptionPane.showMessageDialog(this, "Input File not set");
            }
            catch (FileFormatter.OutputFileException exception)
            {
                JOptionPane.showMessageDialog(this, "Output File not set");
            }
            catch (FileFormatter.LineLengthException exception)
            {
                JOptionPane.showMessageDialog(this, "Line length is invalid");
            }
            catch (FileFormatter.JustificationException exception)
            {
                JOptionPane.showMessageDialog(this, "Justification not set");
            }
            catch (FileFormatter.SpacingException exception)
            {
                JOptionPane.showMessageDialog(this, "Spacing is not set");
            }
            catch (IOException exception)
            {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        });

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        setLayout(layout);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup()
                                                        .addGroup(inputSelect.getHorizontalGroup(layout))
                                                        .addGroup(outputSelect.getHorizontalGroup(layout))
                                                        .addGroup(lineLengthSelect.getHorizontalGroup(layout))
                                                        .addGroup(justificationGroup.getHorizontalGroup(layout))
                                                        .addGroup(spacingGroup.getHorizontalGroup(layout))
                                                        .addComponent(formatButton))
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(wordsProcessedPanel.getHorizontalGroup(layout))
                                                        .addGroup(numberOfLinesPanel.getHorizontalGroup(layout))
                                                        .addGroup(blankLinesRemovedPanel.getHorizontalGroup(layout))
                                                        .addGroup(spacesAddedPanel.getHorizontalGroup(layout))
                                                        .addGroup(averageWordsPerLinePanel.getHorizontalGroup(layout))
                                                        .addGroup(averageLineLengthPanel.getHorizontalGroup(layout))));

        layout.setVerticalGroup(layout.createParallelGroup()
                                      .addGroup(layout.createSequentialGroup()
                                                      .addGroup(inputSelect.getVerticalGroup(layout))
                                                      .addGroup(outputSelect.getVerticalGroup(layout))
                                                      .addGroup(lineLengthSelect.getVerticalGroup(layout))
                                                      .addGroup(justificationGroup.getVerticalGroup(layout))
                                                      .addGroup(spacingGroup.getVerticalGroup(layout))
                                                      .addComponent(formatButton))
                                      .addGroup(layout.createSequentialGroup()
                                                      .addGroup(wordsProcessedPanel.getVerticalGroup(layout))
                                                      .addGroup(numberOfLinesPanel.getVerticalGroup(layout))
                                                      .addGroup(blankLinesRemovedPanel.getVerticalGroup(layout))
                                                      .addGroup(spacesAddedPanel.getVerticalGroup(layout))
                                                      .addGroup(averageWordsPerLinePanel.getVerticalGroup(layout))
                                                      .addGroup(averageLineLengthPanel.getVerticalGroup(layout))));
    }
}

