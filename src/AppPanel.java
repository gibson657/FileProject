
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class AppPanel extends JPanel
{
    private JFileChooser inputChooser;
    private JFileChooser outputChooser;

    private ButtonLabelGroup   inputSelect;
    private ButtonLabelGroup   outputSelect;
    private JustificationGroup justificationGroup;

    private InfoGroup wordsProcessedPanel;
    private InfoGroup numberOfLinesPanel;
    private InfoGroup blankLinesRemovedPanel;
    private InfoGroup averageWordsPerLinePanel;
    private InfoGroup averageLineLengthPanel;

    private JButton formatButton;

    public AppPanel()
    {
        super();

        inputChooser = new TextFileChooser(JFileChooser.OPEN_DIALOG);
        outputChooser = new TextFileChooser(JFileChooser.SAVE_DIALOG);

        inputSelect = new ButtonLabelGroup("Select Input File");
        inputSelect.addButtonListener(event -> {
            int state = inputChooser.showOpenDialog(this);

            if (state == JFileChooser.APPROVE_OPTION)
            {
                File file = inputChooser.getSelectedFile();
                Main.getFormatter().setInputFile(file);
                inputSelect.setLabelText(file.getName());
            }
            else
            {
                Main.getFormatter().setInputFile(null);
            }
        });

        outputSelect = new ButtonLabelGroup("Select Output File");
        outputSelect.addButtonListener(event -> {
            int state = outputChooser.showOpenDialog(this);

            if (state == JFileChooser.APPROVE_OPTION)
            {
                File file = outputChooser.getSelectedFile();
                Main.getFormatter().setOutputFile(file);
                outputSelect.setLabelText(file.getName());
            }
            else
            {
                Main.getFormatter().setOutputFile(null);
            }
        });

        justificationGroup = new JustificationGroup(justification -> {
            Main.getFormatter().setJustification(justification);
        });

        wordsProcessedPanel = new InfoGroup("Words Processed:");
        numberOfLinesPanel = new InfoGroup("Number of Lines:");
        blankLinesRemovedPanel = new InfoGroup("Blank Lines Removed:");
        averageWordsPerLinePanel = new InfoGroup("Average Words Per Line:");
        averageLineLengthPanel = new InfoGroup("Average Line Length:");

        formatButton = new JButton("Format");
        formatButton.addActionListener(event -> {
            try
            {
                FileFormatter.Results results = Main.getFormatter().format();
                wordsProcessedPanel.setInfo(results.wordsProcessed + "");
                numberOfLinesPanel.setInfo(results.numberOfLines + "");
                blankLinesRemovedPanel.setInfo(results.blankLinesRemoved + "");
                averageWordsPerLinePanel.setInfo(String.format("%.2f", results.averageWordsPerLine));
                averageLineLengthPanel.setInfo(String.format("%.2f", results.averageLineLength));
            }
            catch (FileFormatter.InputFileException exception)
            {
                JOptionPane.showMessageDialog(this, "Input File not set");
            }
            catch (FileFormatter.OutputFileException exception)
            {
                JOptionPane.showMessageDialog(this, "Output File not set");
            }
            catch (FileFormatter.JustificationException exception)
            {
                JOptionPane.showMessageDialog(this, "Justification not set");
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

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(
                    layout.createParallelGroup()
                        .addGroup(inputSelect.getHorizontalGroup(layout))
                        .addGroup(outputSelect.getHorizontalGroup(layout))
                        .addGroup(justificationGroup.getHorizontalGroup(layout))
                        .addComponent(formatButton)
                )
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(wordsProcessedPanel.getHorizontalGroup(layout))
                        .addGroup(numberOfLinesPanel.getHorizontalGroup(layout))
                        .addGroup(blankLinesRemovedPanel.getHorizontalGroup(layout))
                        .addGroup(averageWordsPerLinePanel.getHorizontalGroup(layout))
                        .addGroup(averageLineLengthPanel.getHorizontalGroup(layout))
                )
        );

        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(
                    layout.createSequentialGroup()
                        .addGroup(inputSelect.getVerticalGroup(layout))
                        .addGroup(outputSelect.getVerticalGroup(layout))
                        .addGroup(justificationGroup.getVerticalGroup(layout))
                        .addComponent(formatButton)
                )
                .addGroup(
                    layout.createSequentialGroup()
                        .addGroup(wordsProcessedPanel.getVerticalGroup(layout))
                        .addGroup(numberOfLinesPanel.getVerticalGroup(layout))
                        .addGroup(blankLinesRemovedPanel.getVerticalGroup(layout))
                        .addGroup(averageWordsPerLinePanel.getVerticalGroup(layout))
                        .addGroup(averageLineLengthPanel.getVerticalGroup(layout))
                )
        );
    }
}

