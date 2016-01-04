package com.jgraham.server.ServerConnectionTests;


import com.jgraham.server.ServerConnection.ServerConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ServerConfigTest {

    public ServerConfig config;

    @Before
    public void setUp() {
        config = new ServerConfig();
    }

    @Test
    public void ServerConfigExists() {
        Assert.assertNotNull(config);
    }

    @Test
    public void returnsDefaultPortIfNoneGiven() {
        String[] args = new String[0];
        config.parseArgs(args);
        Assert.assertEquals(5000, config.getPort());
    }

    @Test
    public void returnsDefaultDirectoryIfNoneGiven() {
        String[] args = new String[0];
        config.parseArgs(args);
        Assert.assertEquals("/src/main/resources", config.getDirectory());
    }

    @Test
    public void returnsPortIfSpecified() {
        String[] args = new String[2];
        args[0] = "-p";
        args[1] = "8000";
        config.parseArgs(args);
        Assert.assertEquals(8000, config.getPort());
    }

    @Test
    public void returnsDefaultPortIfNonIntegerSpecified() {
        String[] args = new String[2];
        args[0] = "-p";
        args[1] = "eight";
        config.parseArgs(args);
        Assert.assertEquals(5000, config.getPort());
    }

    @Test
    public void returnsSpecifiedDirectory() {
        String[] args = new String[2];
        args[0] = "-d";
        args[1] = "/pub/dir";
        config.parseArgs(args);
        Assert.assertEquals("/pub/dir", config.getDirectory());
    }


}