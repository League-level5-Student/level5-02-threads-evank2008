package _04_Thread_Pool;

import java.lang.Thread.State;
import java.util.ArrayDeque;

public class WorkQueue implements Runnable{
private Thread[] threads;
int totalThreads=Runtime.getRuntime().availableProcessors();
private volatile boolean isRunning=true;
ArrayDeque<Job> jobQueue = new ArrayDeque<Job>();
public WorkQueue() {
	threads=new Thread[totalThreads-1];	
		for(Thread t:threads) {
			t=new Thread(this);
			t.start();
		}
}
public interface Job {
	void perform();
}
public void addJob(Job j) {
	
	synchronized(jobQueue) {
		jobQueue.add(j);
		jobQueue.notify();
	}
	
}
public void completeAllJobs() {
	while(!jobQueue.isEmpty()) {
		performJob();
	}
	System.out.println("isEmpty");
	boolean reset = false;
	while(reset) {
reset=false;
	for(int i = 0; i<threads.length;i++) {
		try {
			System.out.println("should i reset i?");
		if(threads[i].getState()!=State.WAITING) {
			System.out.println("thread "+i+" still working, resetting");
			reset=true;
		}
		} catch(NullPointerException e) {
			System.out.println("uh oh oopsie poopsie... i="+i);
			e.printStackTrace();
		}
	}}
}
boolean performJob() {
	Job j=null;
	synchronized(jobQueue) {
	if(!jobQueue.isEmpty()) {
		j=jobQueue.remove();
		
	}
	if(j!=null) {
		j.perform();
		return true;
	}
	}
	return false;
}
	public void run() {
		while(isRunning) {
			System.out.println("Current thread output: "+Thread.currentThread().getId());
			if(!performJob()) {
			synchronized(jobQueue) {
				try {
					jobQueue.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		}
	}
	public void shut() {
		completeAllJobs();
		isRunning=false;
		synchronized(jobQueue) {
			jobQueue.notifyAll();
		}
	}
public int getThreadCount() {
	return threads.length;
}
}
