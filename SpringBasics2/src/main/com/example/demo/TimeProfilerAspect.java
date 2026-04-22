package com.example.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeProfilerAspect {

	TimeProfilerAspect() {
		System.out.println("+++++++");
	}

	@Around("execution(* com.example.demo.service.NoteService.getOrder(..))")
	public Object calculateTime(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = pjp.proceed();
		long end = System.currentTimeMillis();
		System.out.println("total time: " + (end - start));
		return result;
	}

	@Before("execution(* com.example.demo.service.*.*(..))")
	public void logger() {
		System.out.println("before advice===========");
	}

	@After("execution(* com.example.demo.service.*.*(..))")
	public void afterLogger() {
		System.out.println("after advice===========");
	}
}