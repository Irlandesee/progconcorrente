package es2.news_service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NewsService extends Remote{
    public String readNews() throws RemoteException;
}
