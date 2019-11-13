package it.unipr.informatica.aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Logging {
	public static <T> T apply(final Object object) {   //N.B. final != const
		if (object == null) 
			throw new IllegalArgumentException();
		Class<?> clazz = object.getClass();
		Class<?>[] interfaces = clazz.getInterfaces();
		if (interfaces == null || interfaces.length == 0)
			throw new IllegalArgumentException();
		@SuppressWarnings("unchecked")
		T proxy = (T) Proxy.newProxyInstance(
				clazz.getClassLoader(),
				interfaces,
				new InvocationHandler() {
					@Override
					public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
						System.out.println("Enter " + method.getName());
						Object result = method.invoke(object, args);
						return result;
					}
				});
		return proxy;
	}
}
