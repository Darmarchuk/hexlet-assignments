package exercise;

import java.util.List;

public class MaxThread extends Thread{


    public int maxValue;
    private int[] args;

    public MaxThread(int[] args){
        this.args=args;
    }

    public Integer  getMaxValue(int[] values){
        Integer max=null;
        for (int i:
                values) {
            if( max==null || i>max) max=i;
        }
       return max;


    };

    @Override
    public void run() {
    maxValue= getMaxValue(args);

    }
}