package es2.server;
import es2.news_fetcher.NewsFetcher;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NewsService extends Remote{
    public String readNews() throws RemoteException;
    public void addObserver(NewsFetcher c) throws RemoteException;
}
