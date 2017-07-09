package ch.renewinkler;


import java.io.IOException;

public class Args4jMain {
    public static void main(final String[] args) {
        Args4jArguments arguments = new Args4jArguments();

        try {
            arguments.parse(args);
        } catch (IOException ioEx) {
            System.out.println("ERROR: I/O Exception encountered: " + ioEx);
        }

        System.out.println(arguments.isVerbose());
        System.out.println(arguments.getFileName());
    }
}
