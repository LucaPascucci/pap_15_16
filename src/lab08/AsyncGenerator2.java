package lab08;

import rx.Subscriber;

public class AsyncGenerator2 extends Thread {

	private Subscriber<? super Integer> subscriber;
	private int start;
	private long howLong;
	
	public AsyncGenerator2(Subscriber<? super Integer> subscriber, int start, int howLong){
		this.subscriber = subscriber;
		this.start = start;
		this.howLong = howLong;
	}
	
	@Override
	public void run() {
		int currentValue = start;
		for (int i = 0; i < 3; i++){
			System.out.println("GEN " + currentValue);
			subscriber.onNext(currentValue);
			currentValue++;
			try {
				Thread.sleep(200);
			} catch (Exception ex){
				subscriber.onError(ex);
			}
		}
		subscriber.onCompleted();
		
	}
}
