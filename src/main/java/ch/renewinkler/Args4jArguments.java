package ch.renewinkler;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class Args4jArguments {

    @Option(name = "-v", aliases = "--verbose", usage = "Print verbose status.")
    private boolean verbose;
    @Option(name = "-f", aliases = "--file", usage = "Fully qualified path and name of file.", required = true)
    private String fileName;

    public boolean isVerbose() {
        return verbose;
    }

    public String getFileName() {
        return fileName;
    }

    public void parse(final String[] args) throws IOException {
        final CmdLineParser parser = new CmdLineParser(this);
        if (args.length < 1) {
            parser.printUsage(System.out);
            System.exit(-1);
        }
        try {
            parser.parseArgument(args);
        } catch (CmdLineException clEx) {
            System.out.println("ERROR: Unable to parse command-line options: " + clEx);
        }
        System.out.println("The file '" + fileName + "' was provided and verbosity is set to '" + verbose + "'.");
    }
}
