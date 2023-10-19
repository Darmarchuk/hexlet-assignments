package exercise;



import exercise.calculator.Calculator;
import exercise.calculator.CalculatorImpl;
import org.slf4j.event.Level;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor{
    private Map<Object,String> logLevels=new HashMap<>();
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if( bean.getClass().isAnnotationPresent(Inspect.class)){
         logLevels.put(bean, bean.getClass().getAnnotation(Inspect.class).level().toUpperCase(Locale.ROOT));
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if( bean.getClass().isAnnotationPresent(Inspect.class)  ){
        Logger logger=LoggerFactory.getLogger(Inspect.class);

            Object finalBean = bean;
            Calculator calculatorPoxy=(Calculator) Proxy.newProxyInstance(
                CalculatorImpl.class.getClassLoader(),
                CalculatorImpl.class.getInterfaces(),
                (proxy,method,args)->{
                    method.setAccessible(true);
//                    System.out.println(Level.valueOf(logLevels.get(finalBean)));
                    logger.atLevel(Level.valueOf(logLevels.get(finalBean)));//Level.valueOf(logLevels.get(finalBean))
                    logger.info(String.format("Was called method: %s() with arguments: %s",method.getName(),Arrays.toString(args) ));
                    return  method.invoke(bean,args);
                });
            return calculatorPoxy;
        }

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}