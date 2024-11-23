package _03_Threaded_Greeting;

public class ThreadedGreeter implements Runnable {
int num;
	public ThreadedGreeter(int num) {
	this.num=num;	
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
System.out.println("greetin"+num);
if(num<50) {
new Thread(new ThreadedGreeter(num+1)).run();
}
	}

}
