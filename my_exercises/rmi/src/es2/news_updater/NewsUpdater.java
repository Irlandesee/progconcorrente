package es2.news_updater;

import es2.news_fetcher.NewsFetcherImpl;
import es2.server.MyBuffer;
import es2.server.NewsServiceImpl;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class NewsUpdater extends Thread{

    private int nCount = 0;
    private boolean freshNews = false;

    private NewsServiceImpl myServ;
    private LinkedList<NewsFetcherImpl> fetchers;
    private MyBuffer myBuf;

    public NewsUpdater(NewsServiceImpl myServ){
        myBuf = new MyBuffer();
        this.myServ = myServ;
        fetchers = new LinkedList<NewsFetcherImpl>();
    }

    public void run(){
        //update the news
        while(true){
            freshNews = false;
            try{
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3000));
            }catch(InterruptedException ie){ie.printStackTrace();}
            myServ.addNews(updateNews());
            //notify fetchers
            for(NewsFetcherImpl f : fetchers){f.newsNotify();}
        }
    }

    public void addFetcher(NewsFetcherImpl fetcher){
        this.fetchers.add(fetcher);
    }

    private String updateNews(){
        freshNews = true;
        return "abc"+(nCount++);
    }


}
