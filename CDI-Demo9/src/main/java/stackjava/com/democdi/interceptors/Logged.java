package stackjava.com.democdi.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Logger
public class Logged {

	  @AroundInvoke
	    public Object log(InvocationContext invocationCtx) throws Exception{
	        long startTime = System.currentTimeMillis();
	        System.out.println("Start method: " + invocationCtx.getMethod().getName());
	        //execute the intercepted method and store the return value
	        Object returnValue = invocationCtx.proceed();
	        System.out.println("End method: " + invocationCtx.getMethod().getName());
	        long endTime = System.currentTimeMillis();
	        System.out.println(invocationCtx.getMethod().getName() +": " + (endTime-startTime)+"ms");
	        return returnValue;
	        
	    }
}
