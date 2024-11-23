package _04_Thread_Pool;

public class QueueTester {
public static void main(String[] args) {
	WorkQueue wq = new WorkQueue();
	System.out.println("Total threads: "+wq.getThreadCount());
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	for(int i = 0; i<100;i++) {
		int x=i;
		wq.addJob(()->{
			System.out.println("Thread "+Thread.currentThread().getId()+" straight up jobbin it. And by 'it' lets just say. haha, well. "+x);
		});
	}
	wq.shut();
}
}
