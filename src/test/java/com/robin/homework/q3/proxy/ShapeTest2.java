package com.robin.homework.q3.proxy;

import org.junit.Test;

public class ShapeTest2 {

  @Test
  public void testDraw(){
    ShapeProxy proxy = new ShapeProxy(new Circle());
    proxy.draw();
  }
}