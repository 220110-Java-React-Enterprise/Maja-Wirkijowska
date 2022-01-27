package Util;

import java.util.Iterator;

public class MyLinkedList<T> implements MyList<T>, Iterable<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    // returns size of list
    public int getSize() {
        return size;
    }

    @Override
    // returns true if list has no elements
    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    @Override
    //add to end of list
    public void add(T t) {
        Node<T> newNode = new Node<T>(t);
        if (size == 0){
            head = newNode;
            tail = newNode;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }

    @Override
    //returns element at a given index
    public T get(int index) {
        Node<T> getNode = head;
        for (int i = 0; i < index; i++) {
            getNode = getNode.next;
        }
        return getNode.t;
    }

//    @Override
//    public void remove(int index) {}
//
//    public void removeFromFront() {
//        if (isEmpty()) {
//            System.out.println("List is already empty");
//        }
//        Node<T> removeNode = head;
//        if (head.getNext() == null) {
//            tail = null;
//        } else {
//            head.getNext().setPrev(null);
//        }
//        head = head.getNext();
//        size--;
//        removeNode.setNext(null);
//        System.out.println("Last element removed");
//    }

    //Iterator
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> cursor = head;


            @Override
            public boolean hasNext() {
                if (cursor == null){
                    return false;
                }
                return true;
            }

            @Override
            public T next() {
                T t = cursor.t;
                cursor = cursor.next;
                return t;
            }
        };

    }
    private class Node<T> {
        private T t;
        private Node<T> next;
        private Node<T> prev;

        public Node() {}
        public Node(T t){
            this.t = t;
        }
        public Node(T t, Node<T> next){
            this.t = t;
            this.next = next;
        }
        public Node(T t, Node<T> next, Node<T> prev){
            this.t = t;
            this.next = next;
            this.prev = prev;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }
    }







}