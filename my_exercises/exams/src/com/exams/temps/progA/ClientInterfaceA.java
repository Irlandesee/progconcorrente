package com.exams.temps.progA;

import java.io.IOException;

public interface ClientInterfaceA {

    public static final int PORT = 9999;

    public void comunicaTemp() throws IOException;
    public void accendiRiscaldamento() throws IOException;
    public void spegniRiscaldamento() throws IOException;
    public void accendiCondizionamento() throws IOException;
    public void spegniCondizionamento() throws IOException;


}
