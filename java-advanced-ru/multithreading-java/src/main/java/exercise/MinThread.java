package exercise;

import java.util.List;

public class MinThread extends Thread{


    public Integer minValue;
    private int[] args;

    public MinThread(int[] args){
        this.args=args;
    }

    public Integer  getMinValue(int[] values){
        Integer min=null;
        for (int i:
                values) {
            if( min==null || i<min) min=i;
        }
        return min;


    };

    @Override
    public void run() {
        minValue= getMinValue(args);

    }
}