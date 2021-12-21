package es2.client;

import es2.news_fetcher.NewsFetcherImpl;

public class CliBuffer {

    private String theNews;
    private NewsFetcherImpl newsFetcher;
    private NewsClientImpl newsClient;
    private boolean newsIsFresh;

    public CliBuffer(){
        this.newsIsFresh = false;
    }

    public String getNews(){return this.theNews;}
    public void addNews(String news){this.theNews = news;}
    public void newsIsFresh(){
        this.newsIsFresh = true;
    }

    public boolean getFreshNews(){return this.newsIsFresh;}

}
