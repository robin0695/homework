package com.robin.homework.homework.q2;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

public class MyBlockingQueueTest {

  /**
   * normal case for the queue.
   * @throws InterruptedException
   */
  @Test
  public void testQueue1() throws InterruptedException {
    MyBlockingQueue<String> queue = new MyBlockingQueue<>(2);
    queue.put("hello");
    Assert.assertEquals("hello", queue.take());
  }

  /**
   * Test wrong capacity parameter for the queue.
   * @throws InterruptedException
   */
  @Test(expected = RuntimeException.class)
  public void testQueue2() throws InterruptedException {
    new MyBlockingQueue<>(0);
  }

  /**
   *
   * Test 30 threads work as producer, and 30 threads work as consumer.
   *
   * @throws InterruptedException
   * @throws ExecutionException
   */
  @Test
  public void testQueue3() throws InterruptedException, ExecutionException {
    MyBlockingQueue<String> queue = new MyBlockingQueue<>(5);
    List<Future<String>> resultList = new ArrayList<>();

    // create new thread pool the generate the element in the blocking queue.
    // the element in the queue is an unique string.
    ExecutorService producerPool = Executors.newFixedThreadPool(10);

    for (int i = 0; i < 30; i++) {
      producerPool.submit(() -> {
        try {
          queue.put(UUID.randomUUID().toString());
          Thread.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }

    // create a new thread pool to get the element from the queue.
    // and use future to get the result, and put the result a list.
    ExecutorService consumerPool = Executors.newFixedThreadPool(20);
    for (int j = 0; j < 30; j++) {
      Future<String> result = consumerPool.submit(() -> {
        Thread.sleep(new Random().nextInt(10));
        return queue.take();
      });
      resultList.add(result);
    }

    // get all the element back and put them in a hashset.
    Set<String> resultSet = new HashSet<>();
    resultList.forEach(e -> {
      try {
        resultSet.add(e.get());
      } catch (InterruptedException | ExecutionException interruptedException) {
        interruptedException.printStackTrace();
      }
    });

    // check the size of the hashset, if the size is not 30 means some of the
    // the threads get a wrong element. other-wise the queue works as expected.
    Assert.assertEquals(30, resultSet.size());
  }
}