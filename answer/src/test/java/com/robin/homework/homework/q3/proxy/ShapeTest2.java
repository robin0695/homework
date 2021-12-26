package com.robin.homework.homework.q3.proxy;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShapeTest2 {

  @Test
  public void testDraw(){
    ShapeProxy proxy = new ShapeProxy(new Circle());
    proxy.draw();
  }
}