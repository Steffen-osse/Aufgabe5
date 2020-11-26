package de.hfu;
import org.junit.Test;

public class QueueTest {
	@Test
	public void testQueueEintrag() {
		Queue s = new Queue(5);
		for(int i = 1; i<=7; i++) {
			s.enqueue(i);
		} // expected: 1,2,3,4,7
		for(int i = 0; i<7; i++) {
			System.out.println(s.queue[i]);
		}
		System.out.println("Test 1 zu ende");
	}
	
	@Test
	public void testQueueLeeren() {
		Queue x = new Queue(3);
		x.enqueue(10);
		x.enqueue(-5);
		x.enqueue(0);
		x.dequeue();
		System.out.println("head: "+x.head+" tail: "+x.tail);
		for(int i=0; i<2; i++) {
			System.out.println(x.queue[i]);
		} // expected: -5, 0

		for(int i=0; i<5; i++) {
			x.dequeue();
		}
		
	}
	
}
