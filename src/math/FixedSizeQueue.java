package math;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by liangzhang on 2018/11/8.
 */
public class FixedSizeQueue<E> extends LinkedBlockingQueue<E>{
  private int sizeLimit = 0;
  public FixedSizeQueue(int size) {
    sizeLimit = size;
  }

  public boolean add(E e) {
    if (this.size()<sizeLimit) {
      super.add(e);
      return true;
    } else {
      this.poll();
      super.add(e);
      return false;
    }
  }

  private void printThis(){
    for (Object obj: this){
      System.out.print(", "+obj);
    }
  }

}
