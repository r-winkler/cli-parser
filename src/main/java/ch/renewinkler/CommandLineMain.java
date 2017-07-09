package ch.renewinkler;

import com.github.jankroken.commandline.CommandLineParser;
import com.github.jankroken.commandline.OptionStyle;
import com.github.jankroken.commandline.annotations.*;

import java.lang.reflect.InvocationTargetException;

public class CommandLineMain {

    /**
     * Is verbosity enabled?
     */
    private static boolean verbose;
    /**
     * Name/path of applicable file.
     */
    private static String fileName;

    @Option
    @ShortSwitch("v")
    @LongSwitch("verbose")
    @Toggle(true)
    public void setVerbose(final boolean newVerbose) {
        verbose = newVerbose;
    }

    @Option
    @ShortSwitch("f")
    @LongSwitch("file")
    @SingleArgument
    @Required
    public void setFileName(final String newFileName) {
        fileName = newFileName;
    }

    public static void main(String[] args) {
        parse(args);
        System.out.println(verbose);
        System.out.println(fileName);

    }

    public static void parse(String[] args) {
        try {
            // "Parsing" stage.
            final CommandLineMain main = CommandLineParser.parse(CommandLineMain.class, args, OptionStyle.LONG_OR_COMPACT);
            // "Interrogation" stage.
            System.out.println("You provided file name of '" + main.fileName + "' and verbose is set to '" + main.verbose + "'.");
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException exception) {
            System.out.println("ERROR: Unable to parse command-line arguments: " + exception);
        }
    }
}
