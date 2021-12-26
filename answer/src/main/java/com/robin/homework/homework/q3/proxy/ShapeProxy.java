package com.robin.homework.homework.q3.proxy;

public class ShapeProxy implements BaseShape{

  private final BaseShape baseShape;

  public ShapeProxy(BaseShape baseShape) {
    this.baseShape = baseShape;
  }

  @Override
  public void draw() {
    this.preProcess();
    baseShape.draw();
    this.postProcess();
  }

  private void preProcess(){
    System.out.println("pre process");
  }

  private void postProcess() {
    System.out.println("post process");
  }
}
