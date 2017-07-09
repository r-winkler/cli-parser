package ch.renewinkler;

import net.jbock.CommandLineArguments;
import net.jbock.Description;
import net.jbock.LongName;
import net.jbock.ShortName;

import java.util.Optional;

public class JBockMain {
    private boolean verbose;
    private String file;

    @CommandLineArguments
    public JBockMain(
            @ShortName('v') @LongName("verbose") @Description("Verbosity enabled?") final boolean newVerbose,
            @ShortName('f') @LongName("file") @Description("File name and path") final Optional<String> newFileName) {
        verbose = newVerbose;
        file = newFileName.orElse("");
    }

    public String getFile() {
        return file;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public static void main(final String[] arguments) {
        if (arguments.length < 1) {
            // error can not resolve symbol, but it runs anyway
            JBockMain_Parser.printUsage(System.out, 3);
            System.exit(-1);
        }
        final JBockMain_Parser.Binder binder = JBockMain_Parser.parse(arguments);
        final JBockMain main = binder.bind();
        System.out.println("The file '" + main.getFile() + "' was provided and verbosity is set to '" + main.isVerbose() + "'.");
    }
}
