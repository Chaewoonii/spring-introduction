package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 스프링 빈으로 등록해서 사용
// @Component를 써도 되는데 SpringConfig에서 등록하는 편이 좋음
// Controller, Service 등 정형화된 클래스가 아니기 때문에 config에서 설정하면
// 스프링빈으로 등록하여 사용됨을 직관적으로 알 수 있음.
@Aspect
@Component // 강의에서는 @Component를 사용: springConfig에서 등록하면 순환참조 발생.. 왜일까?
public class TimeTraceAop {

    //hello.hellospring.패키지명.클래스명(파라미터) 형식으로 원하는 조건을 넣을 수 있음
    //hello.hellospring..*(..)은 hellospring 하위의 모든 패키지, 모든 매소드에 적용한다는 의미.
    @Around("execution(* hello.hellospring.service..*(..))") //service 패키지 하위의 모든 메소드에 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString()); //어떤 메소드를 호출하는지 이름을 얻어올 수 있음
        try{
            return joinPoint.proceed();
        }finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");

        }

    }
}
