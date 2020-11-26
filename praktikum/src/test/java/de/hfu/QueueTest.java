package de.hfu;
import org.junit.Test;
import static org.junit.Assert.*; 

public class QueueTest {
	@Test
	public void testQueueEintrag() {
		Queue s = new Queue(5);
		for(int i = 1; i<=7; i++) {
			s.enqueue(i);
		} // expected: 1,2,3,4,7
		for(int i = 1; i<=4; i++) {
			assertEquals(s.dequeue(), i);
		}
		assertEquals(s.dequeue(), 7);
	}
	
	
	@Test
	public void testQueueLeeren() {
		Queue x = new Queue(3);
		x.enqueue(10);
		x.enqueue(-5);
		x.enqueue(0);
		x.dequeue(); //in x -5 und 0
		assertEquals(x.dequeue(), -5);
		assertEquals(x.dequeue(), 0);
		try{
		for(int i=0; i<5; i++) {
			x.dequeue();
		} fail("Dequeue auf leere Queue sollte Exception werfen");
		}
		catch (ArrayIndexOutOfBoundsException e) {}
			
	}
	
}
