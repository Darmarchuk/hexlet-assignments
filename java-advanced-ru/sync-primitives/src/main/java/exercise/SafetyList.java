package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

class SafetyList {
    private List<Integer> safeList;
    public  SafetyList()
    {
        this.safeList=new ArrayList<>();
    }

    public  synchronized void add(Integer itm){
        safeList.add(itm);
    }

    public synchronized Integer get(Integer indx){
        return safeList.get(indx)  ;
    }

    public synchronized int getSize(){
        return safeList.size()  ;
    }
}
