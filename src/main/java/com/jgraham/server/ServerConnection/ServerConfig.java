package com.jgraham.server.ServerConnection;

public class ServerConfig {

    private int port = 5000;
    private String directory = "/src/main/resources";

    public int getPort() {
        return port;
    }

    public String getDirectory() {
        return directory;
    }

    public void parseArgs(String[] args) {
        parsePort(args);
        parseDirectory(args);
    }

    private void parsePort(String[] args) {
        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("-p")) {
                try {
                    port = Integer.parseInt(args[++i]);
                }
                catch (NumberFormatException nfe) {
                    System.out.println("Invalid port entry");
                }
            }
        }
    }

    private void parseDirectory(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                directory = args[++i];
            }
        }
    }
}
