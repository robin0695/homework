package com.robin.homework.homework.q3.template;

public abstract class BaseShape {
  private void preProcess(){
    System.out.println("pre process");
  }

  abstract void myDraw() ;

  private void postProcess(){
    System.out.println("post process");
  }

  public void draw() {
    this.preProcess();
    this.myDraw();
    this.postProcess();
  }
}
