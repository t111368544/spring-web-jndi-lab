package bulletin;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// add 2 annotations
public class LabLogAround {

	static Logger logger = Logger.getLogger(LabLogAround.class);

	// add point-cut expression
	public void playShow() {
	}

	@Around("playShow()")
	public Object aroundShow(ProceedingJoinPoint jp) {
		String declaringTypeName = jp.getSignature().getDeclaringTypeName();
		String methodName = jp.getSignature().getName();
		Object o = null;
		try {
			o = jp.proceed();
			// output some information
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}
		return o;
	}

}
