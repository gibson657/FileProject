
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileFormatter
{
    private static final int MAX_LINE_LENGTH = 80;

    public class InputFileException extends RuntimeException
    {}

    public class OutputFileException extends RuntimeException
    {}

    public class JustificationException extends RuntimeException
    {}

    public enum Justification
    {
        NOT_SET,
        LEFT,
        RIGHT,
    }

    public class Results
    {
        public int wordsProcessed;
        public int numberOfLines;
        public int blankLinesRemoved;
        public float averageWordsPerLine;
        public float averageLineLength;
    }

    private File inputFile;
    private File outputFile;
    private Justification justification;

    public FileFormatter()
    {
        justification = Justification.NOT_SET;
    }

    public void setInputFile(File file)
    {
        inputFile = file;
    }

    public void setOutputFile(File file)
    {
        outputFile = file;
    }

    public void setJustification(Justification justification)
    {
        this.justification = justification;
    }

    public Results format() throws IOException
    {
        if(inputFile == null)
            throw new InputFileException();

        if(outputFile == null)
            throw new OutputFileException();

        if(justification == Justification.NOT_SET)
            throw new JustificationException();

        Results results = new Results();

        Scanner scan = new Scanner(inputFile);
        FileWriter out = new FileWriter(outputFile);

        ArrayList<String> lines = new ArrayList<>();

        // grab all lines in the input file
        while(scan.hasNextLine())
        {
            lines.add(scan.nextLine());
        }

        scan.close();

        // get some stats before doing the formatting
        results.numberOfLines = lines.size();
        results.blankLinesRemoved = (int)lines.stream().filter(String::isEmpty).count();

        ArrayList<String> nonEmptyLines = lines.stream().filter(str -> !str.isEmpty()).collect(Collectors.toCollection(ArrayList::new));
        int[] lineLengths = nonEmptyLines.stream().mapToInt(String::length).toArray();
        ArrayList<String> words = nonEmptyLines.stream().flatMap(str -> Stream.of(str.split(" "))).collect(Collectors.toCollection(ArrayList::new));

        results.wordsProcessed = words.size();

        // now time for actual formatting

        StringBuilder nextLine = new StringBuilder(MAX_LINE_LENGTH);
        nextLine.append(words.get(0));

        for(String word : words.subList(1, words.size()))
        {
            // + 1 for a space
            if(nextLine.length() + 1 + word.length() < MAX_LINE_LENGTH)
            {
                nextLine.append(' ');
                nextLine.append(word);
            }
            else
            {
                if(justification == Justification.RIGHT)
                {
                    // insert enough spaces to fully right justify the line
                    String spaceSequence = String.join("", Collections.nCopies(MAX_LINE_LENGTH - nextLine.length(), " "));
                    nextLine.insert(0, spaceSequence);
                }
                out.write(nextLine.toString() + "\n");
                nextLine.setLength(0);
                nextLine.append(word);
            }
        }

        out.close();

        results.averageLineLength = (float) Arrays.stream(lineLengths).sum() / (float)results.numberOfLines;
        results.averageWordsPerLine = (float)results.wordsProcessed / (float)results.numberOfLines;

        return results;
    }
}
