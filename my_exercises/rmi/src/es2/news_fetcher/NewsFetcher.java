package es2.news_fetcher;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface NewsFetcher extends Remote{
    public void newsNotify() throws RemoteException;
}
