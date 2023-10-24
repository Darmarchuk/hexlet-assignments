package exercise;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class App {

    public static void main(String[] args) {

        // Создаём лист
        SafetyList list = new SafetyList();


// Создаём поток, передав туда созданный лист
        Thread thread = new Thread(new ListThread(list));

// Запускаем поток
        thread.start();

// Работает примерно 1 секунду (1000 элементов * 1 мс)

// Дожидаемся его окончания
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

// Поток добавил в лист 1000 элементов
        list.getSize(); // 1000
    }
}

