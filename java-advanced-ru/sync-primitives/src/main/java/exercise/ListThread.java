package exercise;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class ListThread extends Thread{
   private SafetyList safeList;
    public ListThread(SafetyList argList){
        this.safeList=argList;
    }

    @Override
    public void run() {
        Random rnd=new Random();
        List<Integer> nums= Stream.generate(()-> rnd.nextInt(1000) ).limit(1000).toList();
        for (Integer i: nums
             ) {
            safeList.add(i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}