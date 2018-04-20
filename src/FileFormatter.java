
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
    public class InputFileException extends RuntimeException{}

    public class OutputFileException extends RuntimeException{}
    
    public class LineLengthExecption extends RuntimeException{}

    public class JustificationException extends RuntimeException{}

    public class SpacingException extends RuntimeException{}

    public enum Justification
    {
        NOT_SET,
        LEFT,
        RIGHT,
        FULL,
    }

    public enum Spacing
    {
        NOT_SET,
        SINGLE,
        DOUBLE,
    }

    public class Results
    {
        public int   wordsProcessed;
        public int   numberOfLines;
        public int   blankLinesRemoved;
        public int   spacesAdded;
        public float averageWordsPerLine;
        public float averageLineLength;
    }

    private File          inputFile;
    private File          outputFile;
    private int           maxLineLength;
    private Justification justification;
    private Spacing       spacing;

    public FileFormatter()
    {
    	maxLineLength = Main.INITIAL_LINE_LENGTH;
        justification = Justification.NOT_SET;
        spacing = Spacing.NOT_SET;
    }

    public void setInputFile(File file)
    {
        inputFile = file;
    }

    public void setOutputFile(File file)
    {
        outputFile = file;
    }

    public void setMaxLineLength(int maxLineLength)
    {
        this.maxLineLength = maxLineLength;
    }

    public void setJustification(Justification justification)
    {
        this.justification = justification;
    }

    public void setSpacing(Spacing spacing)
    {
        this.spacing = spacing;
    }

    public Results format()
        throws InputFileException, OutputFileException, LineLengthExecption,JustificationException, SpacingException, IOException
    {
        if (inputFile == null)
            throw new InputFileException();

        if (outputFile == null)
            throw new OutputFileException();
        if (maxLineLength< Main.MIN_LINE_LENGTH || maxLineLength > Main.MAX_LINE_LENGTH)
        	throw new LineLengthExecption();
        if (justification == Justification.NOT_SET)
            throw new JustificationException();

        if (spacing == Spacing.NOT_SET)
            throw new SpacingException();

        Results results = new Results();

        Scanner    scan = new Scanner(inputFile);
        FileWriter out  = new FileWriter(outputFile);

        ArrayList<String> lines = new ArrayList<>();

        // grab all lines in the input file
        while (scan.hasNextLine())
        {
            lines.add(scan.nextLine());
        }

        scan.close();

        // get some stats before doing the formatting
        results.numberOfLines = lines.size();
        results.blankLinesRemoved = (int) lines.stream()
                                               .filter(String::isEmpty)
                                               .count();

        ArrayList<String> nonEmptyLines = lines.stream()
                                               .filter(str -> !str.isEmpty())
                                               .collect(Collectors.toCollection(ArrayList::new));
        int[] lineLengths = nonEmptyLines.stream()
                                         .mapToInt(String::length)
                                         .toArray();
        ArrayList<String> words = nonEmptyLines.stream()
                                               .flatMap(str -> Stream.of(str.split(" \\s")))
                                               .filter(str -> !str.isEmpty())
                                               .collect(Collectors.toCollection(ArrayList::new));

        results.wordsProcessed = words.size();

        // now time for actual formatting

        StringBuilder nextLine = new StringBuilder(maxLineLength);
        nextLine.append(words.get(0));

        for (String word : words.subList(1, words.size()))
        {
            // + 1 for a space
            if (nextLine.length() + 1 + word.length() < maxLineLength)
            {
                nextLine.append(' ');
                nextLine.append(word);
                ++results.spacesAdded;
            }
            else
            {
            	int spacesToAdd = maxLineLength - nextLine.length();
            	
            	switch (justification)
            	{
            	case LEFT:
            		spacesToAdd = 0;
            		break;
            	case RIGHT:
            		String spaceSequence = String.join("", Collections.nCopies(spacesToAdd, " "));
            		nextLine.insert(0, spaceSequence);
            		break;
            	case FULL:
            	int pos= nextLine.indexOf("", 0);
            		for (int i = 0; i < spacesToAdd; ++i)
            		{
            			nextLine.insert(pos , " ");
            			pos = nextLine.indexOf(" " , pos +2);
            			if (pos == -1)
            				pos = nextLine.indexOf(" ",0);
            			
            			
            		}
            		break;
                }
            	results.spacesAdded += spacesToAdd;
            	
            	nextLine.append("\n");
            	if (spacing == Spacing.DOUBLE)
            		nextLine.append("\n");
                
                
                out.write(nextLine.toString() + "\n");
                nextLine.setLength(0);
                nextLine.append(word);
            }
        }

        out.close();

        results.averageLineLength = (float) Arrays.stream(lineLengths)
                                                  .sum() / (float) results.numberOfLines;
        results.averageWordsPerLine = (float) results.wordsProcessed / (float) results.numberOfLines;

        return results;
    }
}
