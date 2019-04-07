package me.helloworld.playground;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {

	
	public static void main(String args[]) {
		
		
		
		System.out.println("asdfasdf");
		
		List<Integer> lst = new ArrayList();
		Producer p = new Producer(lst);
		
		Consumer c = new Consumer(lst);
		
		
		Thread t1 = new Thread(new Producer(lst));
		Thread t2 = new Thread(new Consumer(lst));
		Thread t3 = new Thread(new Consumer(lst));
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	
	static class Producer implements Runnable {
		List<Integer> lst;
		int counter = 0;

		Producer(List<Integer> lst) {
			this.lst = lst;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			while(true) {
				
				synchronized(lst) {
					while (lst.size() == 5) {
						try {
							lst.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
					lst.add(counter++);
					System.out.println("produce:" + counter);
					
					lst.notifyAll();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		
		
		
		
	}
	
	
	static class Consumer implements Runnable {

		List<Integer> lst;
		
		Consumer(List<Integer> lst) {
			this.lst = lst;
		}
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			while(true) {
				
				synchronized(lst) {
					while (lst.isEmpty()) {
						try {
							lst.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
					int item = lst.remove(0);
					System.out.println(Thread.currentThread().getId() +  "consume:" + item);
					
					lst.notifyAll();
				}
			}
			
		}
		
		
		
		
		
	}
}
