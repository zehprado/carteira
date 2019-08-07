package br.com.credsystem.test.carteira.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Aspect
@Component
public class LogAspect {

	final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger("jsonServiceLogger");
	
	@Around("@within(org.springframework.web.bind.annotation.RestController)")
	public Object logArount(ProceedingJoinPoint joinpoint) throws Throwable {
		
		Gson gson = new Gson();
		
		logger.info(joinpoint.getSignature().getName());
		logger.info(gson.toJson(joinpoint.proceed()));
		
		return joinpoint.proceed();
	}
	
}
