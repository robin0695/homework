package com.robin.homework.q1;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Merge2ListTest {

  @Test
  public void testMerge() {
    User user1 = new User("a1", 10);
    User user2 = new User("a2", 11);
    User user3 = new User("a3", 12);
    User user4 = new User("a4", 13);
    User user5 = new User("a5", 14);

    List<User> userList1 = new ArrayList<>();
    userList1.add(user1);
    userList1.add(user3);
    userList1.add(user5);

    List<User> userList2 = new ArrayList<>();
    userList2.add(user2);
    userList2.add(user4);

    List<User> result = Merge2List.merge(userList1, userList2, ((u1, u2) -> u1.getAge() < u2.getAge()));
    Assert.assertNotNull(result);
    Assert.assertEquals(10, result.get(0).getAge());
    Assert.assertEquals(11, result.get(1).getAge());
    Assert.assertEquals(12, result.get(2).getAge());
    Assert.assertEquals(13, result.get(3).getAge());
    Assert.assertEquals(14, result.get(4).getAge());
  }

  @Test
  public void testMerge1() {
    User user1 = new User("a1", 10);
    User user2 = new User("a2", 11);
    User user3 = new User("a3", 12);
    User user4 = new User("a4", 13);
    User user5 = new User("a5", 14);

    List<User> userList1 = new ArrayList<>();
    userList1.add(user1);
    userList1.add(user3);

    List<User> userList2 = new ArrayList<>();
    userList2.add(user2);
    userList2.add(user4);
    userList2.add(user5);

    List<User> result = Merge2List.merge(userList1, userList2, ((u1, u2) -> u1.getAge() < u2.getAge()));
    Assert.assertNotNull(result);

    Assert.assertEquals(10, result.get(0).getAge());
    Assert.assertEquals(11, result.get(1).getAge());
    Assert.assertEquals(12, result.get(2).getAge());
    Assert.assertEquals(13, result.get(3).getAge());
    Assert.assertEquals(14, result.get(4).getAge());
  }

  class User {
    private String name;
    private int age;

    public User(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }
  }
}