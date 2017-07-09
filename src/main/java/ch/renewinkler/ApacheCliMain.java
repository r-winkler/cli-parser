package ch.renewinkler;

import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.util.Arrays;

public class ApacheCliMain {

    private static final String VERBOSE_OPTION = "verbose";
    private static final String FILE_OPTION = "file";

    public static void main(String[] args) {
        Options options = generateOptions();
        CommandLine commandLine = generateCommandLine(options, args);
        final boolean verbose = commandLine.hasOption(VERBOSE_OPTION);
        final String fileName = commandLine.getOptionValue(FILE_OPTION);
        System.out.println("The file '" + fileName + "' was provided and verbosity is set to '" + verbose + "'.");
        printUsage(options);
        printHelp(options);
    }

    private static Options generateOptions() {
        final Option verboseOption = Option.builder("v")
                .required(false)
                .hasArg(false)
                .longOpt(VERBOSE_OPTION)
                .desc("Print status with verbosity.")
                .build();
        final Option fileOption = Option.builder("f")
                .required()
                .longOpt(FILE_OPTION)
                .hasArg()
                .desc("File to be processed.")
                .build();
        final Options options = new Options();
        options.addOption(verboseOption);
        options.addOption(fileOption);
        return options;
    }

    private static CommandLine generateCommandLine(final Options options, final String[] commandLineArguments) {
        final CommandLineParser cmdLineParser = new DefaultParser();
        CommandLine commandLine = null;
        try {
            commandLine = cmdLineParser.parse(options, commandLineArguments);
        } catch (ParseException parseException) {
            System.out.println("ERROR: Unable to parse command-line arguments "
                    + Arrays.toString(commandLineArguments) + " due to: "
                    + parseException);
        }
        return commandLine;
    }

    private static void printUsage(final Options options) {
        final HelpFormatter formatter = new HelpFormatter();
        final String syntax = "Main";
        System.out.println("\n=====");
        System.out.println("USAGE");
        System.out.println("=====");
        final PrintWriter pw = new PrintWriter(System.out);
        formatter.printUsage(pw, 80, syntax, options);
        pw.flush();
    }

    private static void printHelp(final Options options) {
        final HelpFormatter formatter = new HelpFormatter();
        final String syntax = "Main";
        final String usageHeader = "Example of Using Apache Commons CLI";
        final String usageFooter = "See http://marxsoftware.blogspot.com/ for further details.";
        System.out.println("\n====");
        System.out.println("HELP");
        System.out.println("====");
        formatter.printHelp(syntax, usageHeader, options, usageFooter);
    }
}
