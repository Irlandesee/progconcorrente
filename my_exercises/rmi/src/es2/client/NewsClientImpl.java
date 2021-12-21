package es2.client;

import es2.news_service.NewsService;
import es2.news_fetcher.NewsFetcher;
import es2.news_fetcher.NewsFetcherImpl;
import es2.server.NewsServiceImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.Remote;
import java.rmi.registry.Registry;

public class NewsClientImpl{

    private NewsService serv;
    private CliBuffer theBuf;

    public static void main(String[] args){
        NewsClientImpl obj = new NewsClientImpl();
        obj.theBuf = new CliBuffer();
        try{
            Registry reg = LocateRegistry.getRegistry(1100);
            obj.serv = (NewsService) reg.lookup("NEWS");
            NewsFetcherImpl fetcher = new NewsFetcherImpl(obj.theBuf,
                    (NewsServiceImpl) obj.serv);
            ((NewsServiceImpl) obj.serv).addObserver(fetcher);

            while(!(obj.theBuf.getFreshNews())){
                System.err.println("Fresh news!");
                System.out.println("> "+obj.theBuf.getNews());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
