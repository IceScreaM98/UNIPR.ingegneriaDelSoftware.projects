package it.unipr.informatica.exercise2;

import java.util.Collection;
import java.util.Iterator;

import it.unipr.informatica.util.SortedLinkedList;

public class Main {
	public static void main(String[] args) {
		Collection<Integer> list = new SortedLinkedList<Integer>();
		list.add(1);
		list.add(5);
		list.add(4);
		list.remove(4);
		list.add(10);
		list.add(8);
		list.add(7);
		list.remove(1);
		list.remove(10);
		System.out.println(list);
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it);
			it.next();			
		}
		if (list.contains(7)) System.out.println("OK");
		else System.out.println("Error");
		Collection<Integer> list2 = new SortedLinkedList<Integer>();
		System.out.println(list2);		
	}

}
