package com.tiy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.*;

/**
 * Created by Yehia830 on 9/23/16.
 */
public class MyClientTest {
    public static final String SERVER_TRANSACTION_OK = "TX::OK";
    @Before
    public void setUp() throws Exception {
//        Server server = new Server();
//        server.startServer();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void sendUserMessage() throws Exception {
        Socket clientSocket = new Socket("localhost", 8080);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out.println();
        String serverResponse  = in.readLine();
        assertEquals("Message received!",serverResponse);


    }



}