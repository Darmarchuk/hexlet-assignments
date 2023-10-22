package exercise;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");


    public static Map<String,Integer> getMinMax(int[] args) {
        MaxThread maxThread=new MaxThread(args);
        maxThread.start();
        MinThread minThread=new MinThread(args );
        minThread.start();

        try {
            minThread.join();
            maxThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        return Map.of("max",maxThread.maxValue,"min",minThread.minValue);


    }

    public static void main(String[] args) {
        int[] numbers = {10, -4, 67, 100, -100, 8};
        Map<String, Integer> actual = App.getMinMax(numbers);
    }

}
