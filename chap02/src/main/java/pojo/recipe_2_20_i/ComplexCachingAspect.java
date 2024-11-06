package pojo.recipe_2_20_i;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class ComplexCachingAspect {

    private final Map<String, Complex> cache = new ConcurrentHashMap<>();

    @Around("call(Complex.new(int, int)) && args(a,b)")
    public Object cacheAround(ProceedingJoinPoint joinPoint, int a, int b) throws Throwable {

        String key =  a + "," + b;
        Complex complex = cache.get(key);

        if(complex == null) {
            System.out.println("Cache MISS for (" + key + ")");
            complex = (Complex) joinPoint.proceed();
            cache.put(key, complex);
        }
        else {
            System.out.println("Cache HIT for key:(" + key + "), value:" + cache.get(key));
        }

        return complex;
    }
}
