package client;

import java.util.concurrent.ThreadLocalRandom;

import beerawaiter.BeerAwaiter;


public class Musician extends Thread{

	private int playTime;
	private int minWaitTime = 100;
	private int maxWaitTime = 500;
	private int maxPlayTime = 50;

	private BeerAwaiter ba;
	private ServedBeers sb;

	public Musician(String name, BeerAwaiter _ba, ServedBeers _sb){
		super(name);
		this.playTime = ThreadLocalRandom.nextInt(maxPlayTime);

		ba = _ba;
		sb = _sb;

		System.out.printf("Musician: %s started with playtime left: %d\n",name, playTime);
	}

	public void playMusic(){
		System.out.printf("Musician: %s is playing music...\n play time left: %d\n", super.getName(), getPlayTime());
		try{
			Thread.sleep(ThreadLocalRandom.nextInt(minWaitTime, maxWaitTime));
		}catch(InterruptedException ie){}
		System.out.printf("Musician %s: stops playing music\n", super.getName());
	}

	private int getPlayTime(){
		return this.playTime;
	}

	public void addPlayTime(){
		this.playTime++;
	}

	public void run(){
		boolean freeBeerServed = false;
		while(playTime > 0){
			playMusic();
			system.out.printf("Musician %s needs a beer\n", super.getName());
			thirstyList.add(super.getName());
			freeBeerServed = sb.fetch(super.getName());
			if(freeBeerServed)
				System.out.printf("Musician: %s drinks a beer\n", super.getName());
			else
				System.out.printf("Musician: %s no luck!\n", super.getName());

			playTime--;
		}
		System.out.printf("Musician: %s, Play time is over! heading home!\n", super.getName());
	}

}