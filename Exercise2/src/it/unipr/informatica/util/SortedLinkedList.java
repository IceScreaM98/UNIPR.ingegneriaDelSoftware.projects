package it.unipr.informatica.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

public class SortedLinkedList<T extends Comparable<T>> implements SortedList<T>{
	private Node head = null;
	private int size = 0;
	
	@Override
	public boolean addAll(Collection<? extends T> c) {	
		if (c == null) throw new NullPointerException();
		boolean modified = false;
		for (T obj : c) {
			if (this.add(obj)) modified = true;
		}
		return modified;
		
		
		/*
		boolean modified = false;
		Iterator<? extends T> it = c.iterator();
		while (it.hasNext()) {
			T obj = it.next();
			if (this.add(obj)) modified = true;
		}
		return modified;
		*/
		
		
	}
	
	@Override
	public boolean removeAll(Collection<?> c) {
		if (c == null) throw new NullPointerException();
		boolean modified = false;
		Iterator<?> it = c.iterator();
		while (it.hasNext()) {
			Object obj = it.next();
			if (this.remove(obj)) modified = true;
		}
		return modified;
		
		
		
		/*  //meglio usare gli iteratori quando si elimina
		for (Object obj : c) {
			if (this.remove(obj)) modified = true;
		}
		return modified;
		*/
	}
	
	@Override
	public boolean containsAll(Collection<?> c) {
		if (c == null) throw new NullPointerException();
		for (Object obj : c) {
			if (!(this.contains(obj))) return false;
		}
		return true;
		
		/*
		Iterator<?> it = c.iterator();
		while (it.hasNext()) {
			Object obj = it.hasNext();
			if (!(this.contains(obj))) return false;
		}
		return true;
		*/
		
	}
	
	@Override
	public boolean retainAll(Collection<?> c) {
		if (c == null) throw new NullPointerException();
		boolean modified = false;
		Node current = this.head;
		while (current != null) {
			T obj = current.getValue();
			if (!(c.contains(obj))){
				if (this.remove(obj)) modified = true;
			}
			current = current.getNext();
		}
		return modified;
	}
	
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	@Override
	public int size() {
		return this.size;
	}
	
	@Override
	public boolean add(T element) {
		if (element == null) throw new NullPointerException();
		Node node = new Node(element,null);
		if (this.head == null) return this.addHead(node);
		else return this.addTailOrdered(node) ;
	}
	
	
	@Override
	public void clear() {
		this.head = null;	
	}
	
	@Override
	public boolean contains(Object obj) {
		if (obj == null) throw new NullPointerException();
		Node current = this.head;
		while (current != null) {
			if (current.getValue().equals(obj)) return true;
			current = current.getNext();
		}
		return false;
	}
	
	@Override
	public boolean remove(Object obj) {
		if (obj == null) throw new NullPointerException();
		Node current = this.head;
		Node previous = null;
		while (current != null && !current.getValue().equals(obj)) {
			previous = current;
			current = current.getNext();
		}
		if (current != null) {
			if (previous != null) previous.setNext(current.getNext());	
			else this.head = current.getNext();
			this.size--;
			return true;			
		}
		return false;

	}
	
	@Override
	public Object[] toArray() {
		Object[] result = new Object[this.size];
		Node current = this.head;
		for (int i = 0; i < this.size; i++) {
			result[i] = current.getValue();
			current = current.getNext();
		}
		return result;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LocalIterator();
	}
	
	@SuppressWarnings("unchecked")
	public <U> U[] toArray(U[] a) {
		if (a == null) throw new NullPointerException();
		if (this.head == null) {  //se la lista è vuota tutto l'array settato a null
			for (int i = 0; i < a.length; i++) {
				a[i] = null;
			}
			return a;
		}
		Class<?> class_a = a.getClass().getComponentType(); //calcolo tipo runtime degli elementi di a
		Class<?> class_l = this.head.getValue().getClass(); //calcolo tipo degli elementi della lista
		if (!class_a.isAssignableFrom(class_l)) throw new ArrayStoreException();  //tipi non compatibili
		Node current = this.head;
		if (a.length >= this.size) { //aggiungo gli elementi della lista nell'array e poi i rimanenti slot sono settati a null
			for (int i = 0; i < a.length; i++) {
				if (current != null) {
					a[i] = (U) current.getValue();
					current = current.getNext();
				}
				else a[i] = null;
			}
			return a;
		}
		U[] result = (U[]) Array.newInstance(class_a, this.size); //creo un nuovo array perchè quello passato è troppo piccolo
		for (int i = 0; i < result.length; i++) {
			result[i] = (U) current.getValue();
			current = current.getNext();
		}
		return result;
	}
	
	public void accept(Consumer<T> consumer) {
		Node current = this.head;
		while (current != null) {
			T value = current.getValue();
			consumer.accept(value);
			current = current.getNext();
		}
	}
	
	public <R extends Comparable<R>> SortedLinkedList<R> apply(Function<T,R> function){
		SortedLinkedList<R> result = new SortedLinkedList<R>();
		Node current = this.head;
		while (current != null) {
			T value = current.getValue();
			R transformed = function.apply(value);
			result.add(transformed);
			current = current.getNext();
		}
		return result;
	}
	
	@Override
	public String toString() {
		String result = "[";
		Node current = this.head;
		while (current != null) {
			result += current.getValue().toString();
			if (current.getNext() != null)  result += ", ";
			current = current.getNext();	
		}

		result += "]";
		return result;
	}
	
	private boolean addHead(Node node) {
		this.head = node;
		this.size++;
		return true;
	}
	
	private boolean addTailOrdered(Node node) {
		Node previous = null;
		Node current = this.head;
		while (current != null && node.getValue().compareTo(current.getValue()) > 0) {
			previous = current;
			current = current.getNext();
		}
		if (current == null) previous.setNext(node);
		else {
			node.setNext(current);
			if (previous != null) previous.setNext(node);
			else this.head = node;
		}
		this.size++;
		return true;
	}
	
	private class LocalIterator implements Iterator<T>{
		private Node current = SortedLinkedList.this.head;
		
		@Override
		public boolean hasNext() {
			return this.current != null;
		}
		
		@Override
		public T next() {
			T result = current.getValue();
			current = current.getNext();
			return result;
		}
		
		@Override
		public String toString() {
			return current.getValue().toString();
		}		
	}
	
	
	/* versione classe anonima
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>(){
			private Node current = SortedLinkedList.this.head;
			
			@Override
			public boolean hasNext() {
				return this.current != null;
			}
			
			@Override
			public T next() {
				T result = current.getValue();
				current = current.getNext();
				return result;
			}
			
			@Override
			public String toString() {
				return current.getValue().toString();
			}
		};
	}
	*/
	
	private class Node{
		private T value = null;
		private Node next = null;
		
		public Node(T value, Node next) {
			this.setValue(value);
			this.setNext(next);
		}
		
		public T getValue() {
			return this.value;
		}
		
		public Node getNext() {
			return this.next;
		}
		
		public void setValue(T value) {
			this.value = value;
		}
		
		public void setNext(Node next) {
			this.next = next;
		}		
		
	}
	

}

