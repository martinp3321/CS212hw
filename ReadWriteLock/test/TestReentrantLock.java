import org.junit.Assert;
import org.junit.Test;


public class TestReentrantLock {

	@Test
	public void testLockSimple() {		
		String testName = "testLockSimple";
		ReentrantLock lock = new ReentrantLock();
		lock.lockWrite();
		System.out.println("after lock write: hasWrite = " + lock.hasWrite());
		lock.lockRead();
		System.out.println("after lock read: hasRead = " + lock.hasRead());
		Assert.assertTrue(String.format("%n" + "Test Case: %s%n" +
				" Read lock not held. %n", testName), lock.hasRead());		
		lock.unlockRead();
		Assert.assertFalse(String.format("%n" + "Test Case: %s%n" +
				" Read lock not released. %n", testName), lock.hasRead());		
		Assert.assertTrue(String.format("%n" + "Test Case: %s%n" +
				" Write lock not held. %n", testName), lock.hasWrite());		
		lock.unlockWrite();
		Assert.assertFalse(String.format("%n" + "Test Case: %s%n" +
				" Write lock not released. %n", testName), lock.hasWrite());
		
	}

	@Test
	public void testLockMultipleWrites() {		
		String testName = "testLockMultipleWrites";
		ReentrantLock lock = new ReentrantLock();
		lock.lockWrite();
		lock.lockWrite();
		
		lock.unlockWrite();
		Assert.assertTrue(String.format("%n" + "Test Case: %s%n" +
				" Write lock not held. %n", testName), lock.hasWrite());
		lock.unlockWrite();
	}

	@Test
	public void testWriteLockMultiThread() {	
		String testName = "testWriteLockMultiThread";

		ReentrantLock lock = new ReentrantLock();
		boolean result = lock.tryLockWrite();
		if(!result) {
			Assert.fail(String.format("%n" + "Test Case: %s%n" +
					" Unable to acquire write lock. %n", testName));
		}
		Thread t1 = new Thread() {
			public void run() {
				Assert.assertFalse(String.format("%n" + "Test Case: %s%n" +
						" Read lock acquired. %n", testName), lock.tryLockRead());
			}
		};

		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			Assert.fail();
		}
		lock.unlockWrite();
	}

	@Test
	public void testReadLockMultiThread() {	
		String testName = "testReadLockMultiThread";

		ReentrantLock lock = new ReentrantLock();
		lock.lockRead();

		Thread t1 = new Thread() {
			public void run() {
				Assert.assertTrue(String.format("%n" + "Test Case: %s%n" +
						" Read lock not acquired. %n", testName), lock.tryLockRead());
			}
		};

		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			Assert.fail();
		}
		lock.unlockRead();
	}

	@Test
	public void testLockUpgrade() {
		String testName = "testLockUpgrade";
		ReentrantLock lock = new ReentrantLock();
		lock.lockRead();
				
		boolean result = lock.tryLockWrite();
		if(result) {
			Assert.fail(String.format("%n" + "Test Case: %s%n" +
					" Lock upgrade read to write should be disallowed. %n", testName));
		}
		lock.unlockRead();

	}
}