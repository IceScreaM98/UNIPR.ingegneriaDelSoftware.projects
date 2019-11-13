package it.unipr.informatica.bean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import it.unipr.informatica.bean.Bean;

public class BeanFactory {
	public static Object create(Class<?> i) {
		Bean bean = new Bean();
		return Proxy.newProxyInstance(
				i.getClassLoader(),
				new Class<?>[] {i},  
				new InvocationHandler() {		
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						String methodName = method.getName();
						if (methodName.startsWith("get")) {
							Parameter[] parameters = method.getParameters();
							Class<?> returnType = method.getReturnType();
							if ((Void.class).equals(returnType))
									throw new IllegalArgumentException();
							if (parameters != null && parameters.length == 0)
								throw new IllegalArgumentException();
							String propertyName = methodName.substring(3);
							if (propertyName.length() == 0)
								throw new IllegalArgumentException();
							return bean.get(propertyName);
						}
						if (methodName.startsWith("set")) {
							Parameter[] parameters = method.getParameters();
							Class<?> returnType = method.getReturnType();
							if (!(Void.class).equals(returnType))
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
	}
}
