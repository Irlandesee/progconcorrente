package es2.server;

import es2.news_fetcher.NewsFetcher;
import es2.news_fetcher.NewsFetcherImpl;
import es2.news_service.NewsService;
import es2.news_updater.NewsUpdater;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class NewsServiceImpl extends UnicastRemoteObject implements NewsService {

    public NewsServiceImpl() throws RemoteException {super();}
    private MyBuffer buf;
    private NewsUpdater updater;

    public synchronized String readNews(){
        return buf.getNews();
    }

    public static void main(String[] args){
        try{
            NewsServiceImpl obj = new NewsServiceImpl();
            obj.buf = new MyBuffer();
            obj.updater = new NewsUpdater(obj);
            obj.updater.start();
            Registry reg = LocateRegistry.createRegistry(1100);
            reg.rebind("NEWS", obj);
            System.err.println("Server ready");
        }catch(Exception e){
            System.err.println("Server exception: "+e.toString());
            e.printStackTrace();
        }
    }

    public void addObserver(NewsFetcher newsFetcher){
        updater.addFetcher((NewsFetcherImpl) newsFetcher);
    }

    public void addNews(String news){
        buf.addNews(news);
    }

}
