package com.example.demo.login.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LogAspect {

	//@Before("execution(*com.example.demo.login.controller.LoginController.getLogin(..))")
	@Before("execution(**..*.*Controller.*(..))")
	//@Around("execution(**..*.*Controller.*(..))")
	public void startLog(JoinPoint jp) {
		System.out.println("メソッド開始："+jp.getSignature());

	}

	//@After("execution(*com.example.demo.login.controller.LoginController.getLogin(..))")
	@After("execution(**..*.*Controller.*(..))")
	public void endLog(JoinPoint jp) {
		System.out.println("メソッド終了："+jp.getSignature());
	}
}