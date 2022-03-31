package com.socketAdvanced.es1.BankInterface;

import com.socketAdvanced.es1.operations.OperationRequest;
import com.socketAdvanced.es1.operations.Result;

import java.io.IOException;

public interface BankInterface {

    int PORT = 8080;
    String QUIT = "QUIT";
    void executeOperation(String op, int amount);
    void quit() throws IOException;
}
