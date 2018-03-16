import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

import java.io.File;

public class TextFileChooser extends JFileChooser
{
    private static final FileFilter textFilter = new FileFilter(){
        @Override
        public boolean accept(File file)
        {
            // get file's extension
            String filename = file.getName();
            int extensionIdx = filename.lastIndexOf('.');

            // add 1 to exclude the '.'
            String extension = filename.substring(extensionIdx + 1);

            return extension.equalsIgnoreCase("txt");
        }

        @Override
        public String getDescription()
        {
            return "Text files (*.txt)";
        }
    };

    public TextFileChooser(int dialogType)
    {
        super(FileSystemView.getFileSystemView().getHomeDirectory());

        setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        setFileFilter(textFilter);
        setDialogType(dialogType);

        switch(dialogType)
        {
            case JFileChooser.OPEN_DIALOG:
                setDialogTitle("Open a file to format");
                break;

            case JFileChooser.SAVE_DIALOG:
                setDialogTitle("Create the file to output");
                break;

            default:
                throw new IllegalArgumentException("Invalid dialogType");
        }
    }
}
