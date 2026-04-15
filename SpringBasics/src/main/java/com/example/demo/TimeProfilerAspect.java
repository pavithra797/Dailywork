package com.example.demo;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeProfilerAspect {
	TimeProfilerAspect(){
		System.out.println("+++++++");
	}
	
//	@Around("execution(* com.example.demo.service.NoteService.getOrder())")
//	public Iterable calculateTime(ProceedingJoinPoint pjp) throws Throwable{
//		long start = System.currentTimeMillis();
//		Iterable result = (Iterable) pjp proceed();
//		long end = System.currentTimeMillis();
//		System.out.println("total time"+(end-start));
//		return result;
//	}
	
//	@Before("execution(* com.example.demo.services.NotesService.*(..))")
	@Before("execution(* com.example.demo.service.NoteService.*(..))")
	public void logger() {
		System.out.println("adviced===========");
	}

}
