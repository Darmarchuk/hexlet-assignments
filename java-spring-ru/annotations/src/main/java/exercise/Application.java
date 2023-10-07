package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        for(Method method: address.getClass().getDeclaredMethods() ){
            if (method.isAnnotationPresent(Inspect.class)){
                String output=String.format("Method %s returns a value of type %s",method.getName(),method.getReturnType().getSimpleName());
                System.out.println(output);
            }

        }

    }
}
