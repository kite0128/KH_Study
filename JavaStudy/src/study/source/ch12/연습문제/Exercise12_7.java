package study.source.ch12.연습문제;
class Exercise12_7
{
	static boolean stopped = false;

	public static void main(String[] args)
	{
		Thread5 th1 = new Thread5();
		th1.start();

		try {
			Thread.sleep(6*1000);
		} catch(Exception e) {}

		stopped = true;	// 쓰레드를 정지시킨다.
		System.out.println("stopped");
	}
}

class Thread5 extends Thread {
	public void run() {
		// Exercise12_7.stopped의 값이 false인 동안 반복한다.
		for(int i=0; !Exercise12_7.stopped; i++) {
			System.out.println(i);

			try {
				Thread.sleep(3*1000);
			} catch(Exception e) {}
		}
	} // run()
}
