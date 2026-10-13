package de.tum.in.ase.eist;

import java.util.List;

public class Main {

    public static void main(String[] args)  {
       detectDeadlock(new SwimmingPool());
    }

    public static void detectDeadlock(SwimmingPool swimmingPool) {
       Thread t1 = new Thread(() -> swimmingPool.handleEntryRequest(new Swimmer(),SwimmingPoolActionOrder.CHANGING_ROOM_BEFORE_LOCKER));
       Thread t2 = new Thread(() -> swimmingPool.handleEntryRequest(new Swimmer(),SwimmingPoolActionOrder.LOCKER_BEFORE_CHANGING_ROOM));
       t1.start();
       t2.start();
       try {
           t1.join();
           t2.join();
       }
       catch (InterruptedException ignored){Thread.currentThread().interrupt();}
       if (t1.isAlive() && t2.isAlive()){
           System.out.println("Deadlock");
       }
       else{
           System.out.println("No deadlock");
       }

    }
}
