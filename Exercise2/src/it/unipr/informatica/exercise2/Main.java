package it.unipr.informatica.exercise2;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

import it.unipr.informatica.util.SortedLinkedList;

public class Main {
	
	private static void printString(String s) {
		System.out.print(s + " ");
	}
	
	private static void printInt(int i) {
		System.out.print(i + " ");
	}
	
	
	private static int toInt(String s) {
		try {
			int i = Integer.parseInt(s);
			return i;
		}
		catch(Throwable e) {
			throw new ArithmeticException();
		}
		
	}
	
	public static void main(String[] args) {
		Collection<Integer> list = new SortedLinkedList<Integer>();
		list.add(1);
		list.add(5);
		list.add(6);
		list.add(4);
		list.remove(4);
		list.add(10);
		list.add(8);
		list.add(234324);
		list.remove(6);
		list.add(7);
		list.remove(1);
		list.remove(10);
		list.add(11);
		list.remove(8888);
		list.remove(11);
		System.out.println("STAMPA LISTA");
		System.out.println(list);
		System.out.println("STAMPA LISTA CON ITERATOR");
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it);
			it.next();			
		}
		System.out.print("CHECK 7 APPARTIENE ALLA LISTA: ");
		if (list.contains(7)) System.out.println("OK");
		else System.out.println("Error");
		Collection<Integer> list2 = new SortedLinkedList<Integer>();
		list2.add(7);
		list2.add(5);
		System.out.println(list2);	
		System.out.println(list2.size());
		list.retainAll(list2);
		System.out.println("STAMPA LISTA");
		System.out.println(list);
		list2.remove(5);
		list2.remove(7);
		System.out.println("STAMPA LISTA VUOTA");
		System.out.println(list2);	
		SortedLinkedList<String> list3 = new SortedLinkedList<String>();
		list3.add("1");
		list3.add("5");		
		System.out.println("STAMPA STRINGHE METODO 1");
		list3.accept(new Consumer<String>() {  //metodo anonymous class
			@Override
			public void accept(String t) {
				System.out.print(t + " ");
			}
		});		
		System.out.println();	
		System.out.println("STAMPA STRINGHE METODO 2");
		list3.accept((t) -> {  //metodo lambda function
			System.out.print(t + " ");
		});
		System.out.println();
		System.out.println("STAMPA STRINGHE METODO 3");
		list3.accept(Main::printString);  //metodo puntatori a funzioni
		System.out.println();
		SortedLinkedList<Integer> list4 = new SortedLinkedList<Integer>();
		list4 = list3.apply(Main::toInt);
		System.out.println("STAMPA INTERI");
		list4.accept(Main::printInt);
		System.out.println();
		System.out.println("STAMPA LISTA CREATA DAL METODO toArray");
		String[] arrayString = list3.toArray(new String[10]);
		for (int i = 0; i < arrayString.length; i++) {
			System.out.print(arrayString[i] + " ");
		}
		System.out.println();
		System.out.println("STAMPA ECCEZIONE METODO toArray");
		Float[] arrayFloat = list.toArray(new Float[10]);
		for (int i = 0; i < arrayFloat.length; i++) {
			System.out.print(arrayFloat[i] + " ");	
		}
		System.out.println("Fine");
	}

}
