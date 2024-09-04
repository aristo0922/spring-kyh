package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect // aop 로 사용하도록 하는 어노테이션
public class TimeTraceAop {

  @Around("execution(* hello.hello_spring..*(..))")
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
    long start = System.currentTimeMillis();
    System.out.println("START : "+joinPoint.toString());

    try{
      return joinPoint.proceed();
    }finally {
      long finish = System.currentTimeMillis();
      long timeMs = finish - start;
      System.out.println("END: "+joinPoint.toString()+" "+timeMs +"ms");
    }
  }

}
