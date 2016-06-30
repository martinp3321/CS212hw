Reentrant Read/Write Lock
=========================

For this homework, you will implement a *reentrant* read-write lock.

## Due Friday 3/4 - 11:59pm

You will implement a lock similar to Java's [ReentrantReadWriteLock](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantReadWriteLock.html), however you may not use this class in your final submission!

The required methods are documented in `ReentrantLock`.

Make sure to adhere to the following rules:

- Multiple threads may hold the read lock as long as no thread holds the write lock.
- Only one thread may hold the write lock at any time.
- A thread holding a write lock may also acquire the read lock. 
- A thread only releases one lock at a time, therefore a thread may acquire a write lock then acquire a read lock. If this thread releases the write lock it will still hold the read lock.
- A thread holding a read lock *may not* upgrade to the write lock. This is to prevent deadlock.
- The `try...Lock` methods will *not* block, but rather return true or false to indicate whether the lock was acquired.
- The `lock` methods will block until the lock is acquired. Hint, you should use the `wait` method.
