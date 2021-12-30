package com.robin.homework.q2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * - Concurrency:
 *
 * Use java concurrency packages to write a queue that supports multi-writer and multi-reader,
 * i.e. the writer pushes the stuff into the queue and the reader pops the stuff out the queue.
 * All the writer's stuff can't be lost and must be into the queue, and each reader can't pops out the same stuff.
 * Think of how you would simulate the situation and perform the testing. Using the java blocking queue is not allowed.
 *
 * @param <T>
 */

public class MyBlockingQueue<T> {
  private final Queue<T> queue = new LinkedList<T>();
  private final int capacity;
  private final Lock lock = new ReentrantLock();
  private final Condition notFull = lock.newCondition();
  private final Condition notEmpty = lock.newCondition();

  /**
   * if the capacity is invalid, throw exception.
   * @param capacity max number of the element.
   */
  public MyBlockingQueue(int capacity) {
    if(capacity <= 0) {
      throw new RuntimeException("capacity should be greater equal 1.");
    }
    this.capacity = capacity;
  }

  /**
   * Put the element into the queue.
   * @param element element in the queue.
   * @throws InterruptedException
   */
  public void put(T element) throws InterruptedException {
    String threadName = Thread.currentThread().getName();
    lock.lock();
    try {
      // check if the size of the queue reach to the capacity.
      while (queue.size() == capacity) {
        System.out.println("The queue is full.");
        notFull.await();
      }
      queue.add(element);
      System.out.printf("%s put 1 object into the queue.%n", threadName);

      // notify that the consumer thread could start to get the element.
      notEmpty.signal();
      System.out.printf("%s notify 1 thread that the queue is not empty.%n", threadName);
    } finally {
      lock.unlock();
    }
  }

  /**
   * get the element from the queue.
   * @return the first element from the queue.
   * @throws InterruptedException
   */
  public T take() throws InterruptedException {
    String threadName = Thread.currentThread().getName();
    lock.lock();
    try {

      // if the queue is empty, consumer should be blocked and wait.
      while (queue.isEmpty()) {
        System.out.println("The queue is empty.");
        notEmpty.await();
      }
      T item = queue.remove();
      System.out.printf("%s take 1 object out of the queue.%n", threadName);

      // notify the producer to produce the element.
      notFull.signal();
      System.out.printf("%s notify 1 thread that the queue is not full.%n", threadName);
      return item;
    } finally {
      lock.unlock();
    }
  }
}