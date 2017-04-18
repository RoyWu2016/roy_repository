package com.roy.demo.thread;

class NumberPrinter2 extends Thread {
	static int c = 0;
	volatile static int state = 0;
	private int id;
	private String name;

//	public synchronized void run() {
//		while (state < 15) {
////		synchronized(this) {
//			if (state % 3 == id) {
//					for (int j = 0; j < 5; j++) {
//						c++;
//						System.out.println(name + ": " +  c);
//					}
//					System.out.println();
//					state++;
//				}
////			}
//		}
////		try {
//////			Thread.sleep(100);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//	}
	
	 public synchronized void run() {  
	        while (state < 15) {  
	            if (state % 3 == id) {  
	                for (int j = 0; j < 5; j++) {  
	                    c++;  
	                    System.out.println(name + ": " +  c);
	                }  
	                System.out.println();
	                state++;  
	            }  
	        }  
	    }

	public NumberPrinter2(int id,String name) {
		this.id = id;
		this.name = name;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			int j = i+1;
			new NumberPrinter2(i,"线程" + j).start();
		}
	}
}
