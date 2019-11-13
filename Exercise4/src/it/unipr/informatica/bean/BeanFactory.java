package it.unipr.informatica.bean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import it.unipr.informatica.bean.Bean;

public class BeanFactory {
	public static <T> T create(Class<?> i) {
		Bean bean = new Bean();
		@SuppressWarnings("unchecked")
		T proxy = (T) Proxy.newProxyInstance(
				i.getClassLoader(),
				new Class<?>[] {i},  
				new InvocationHandler() {		
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						String methodName = method.getName();
						Parameter[] parameters = method.getParameters();
						Class<?> returnType = method.getReturnType();
						if (methodName.startsWith("get")) {
							if (returnType == void.class)
									throw new IllegalArgumentException();
							if (parameters != null && parameters.length != 0)
								throw new IllegalArgumentException();
							String propertyName = methodName.substring(3);
							if (propertyName.length() == 0)
								throw new IllegalArgumentException();
							return bean.get(propertyName);
						}
						if (methodName.startsWith("set")) {
							if (returnType != void.class)
									throw new IllegalArgumentException();
							if (parameters == null || parameters.length != 1)
								throw new IllegalArgumentException();
							String propertyName = methodName.substring(3);
							if (propertyName.length() == 0)
								throw new IllegalArgumentException();
							bean.set(propertyName, args[0]);
							return null;
						}
						return null;
					}
				});
		return proxy;
	}
}
