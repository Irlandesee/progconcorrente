package es2.news_fetcher;

import es2.client.CliBuffer;
import es2.server.NewsServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NewsFetcherImpl extends UnicastRemoteObject implements NewsFetcher {

    private CliBuffer theBuf;
    private NewsServiceImpl serv;
    private String lastNews;

    public NewsFetcherImpl(CliBuffer cliBuffer, NewsServiceImpl newsService) throws RemoteException {
        super();
        this.theBuf = cliBuffer;
        this.serv = newsService;
        this.lastNews = "";
    }

    public void newsNotify(){
        if(!(theBuf.getNews().equals(lastNews))) {
            theBuf.newsIsFresh();
            lastNews = theBuf.getNews();
            theBuf.addNews(serv.readNews());
        }
    }
}
