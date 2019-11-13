package it.unipr.informatica.exercise4;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import it.unipr.informatica.exercise4.model.Student;
//import it.unipr.informatica.exercise4.model.impl.StudentImpl;
 

public class Main {
	public static void printProperties(Object obj) {
		Class<?> clazz = obj.getClass();
		
		System.out.println("Class name: " + clazz.getName());
		
		/*
		try {  //per ora preferiamo il 1° metodo per ottenere la classe
			Class<?> c = Class.forName("it.unipr.informatica.exercise4.model.impl.StudentImpl");
			if (c == clazz) {
				System.out.println("OK");
			}
		}
		catch(Throwable throwable) {
			
		}
		*/
		
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			System.out.println("Method name : " + method.getName());
		}
		for (Method method : methods) {
			String methodName = method.getName();
			Class<?> propertyType = method.getReturnType();
			Class<?>[] parameterTypes = method.getParameterTypes();
			if (methodName.startsWith("get") && propertyType != void.class && parameterTypes.length == 0) { //!"void".equals(propertyType.getName())
				String propertyName = methodName.substring(3);
				if (parameterTypes.length == 0) {
					System.out.println("Property " + propertyName);
					System.out.println(propertyType.getName());
					try {
						Method setter = clazz.getMethod("set" + propertyName, new Class<?>[] { propertyType });
						if(setter.getReturnType() == void.class){
							System.out.println("read-write");
						}
					}
					catch(NoSuchMethodException exception) {
						System.out.println("read-only");
					}
				}
				try {
					Object result = method.invoke(obj, new Object[0]);
					System.out.println(" = " + result);
				}
				catch(Throwable throwable) {
					throwable.printStackTrace();
				}
				System.out.println("---------------------------------------");
			}
		}
	}
	
	public static void main(String[] args) {
		String className = "it.unipr.informatica.exercise4.model.impl.StudentImpl";
		Object[] arguments = new Object[]  {1, "Rossi", "Mario"};
		try {
			Class<?> clazz = Class.forName(className);
			Constructor<?> constructor = clazz.getConstructor(int.class, String.class, String.class);
			Object object = constructor.newInstance(arguments);
			Main.printProperties(object);
			Student student = (Student) Proxy.newProxyInstance(clazz.getClassLoader(),  //mi procuro un classloader qualsiasi
					new Class<?>[] {Student.class}, //potevo usare "it.unipr.informatica.exercise4.model.Student"
					new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) {
							return method.getName();
						}
					});   
			System.out.println("Prima chaiamata: " + student.getName());
			System.out.println("Seconda chaiamata: " + student.getFamilyName());
		}
		catch(Throwable throwable) {
			throwable.printStackTrace();
		}
		//Student student = new StudentImpl(1, "Rossi", "Mario");
		//Main.printProperties(student);
		
	}
}
