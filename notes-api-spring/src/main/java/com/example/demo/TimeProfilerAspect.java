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
	
	@Before("execution(* com.example.demo.services.NotesService.*(..))")
	public void logger() {
		System.out.println("adviced===========");
	}

}
