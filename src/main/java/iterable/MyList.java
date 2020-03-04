package iterable;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyList<T> implements Iterable<T> {
    private T[] data = (T[])(new Object[10]);
    private int count = 0;

    public MyList(){}

    public void add(T t) {
        data[count++] = t; // Horribly unsafe!
    }

    public T get(int idx) {
        if (idx >= 0 && idx < count) {
            return data[idx];
        } else {
            throw new NoSuchElementException("Bad MyList index");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>(){
            int progress = 0;

            @Override
            public boolean hasNext() {
                return progress < count;
            }

            @Override
            public T next() {
                return data[progress++];
            }
        };
    }

    public static void main(String[] args) {
        MyList<String> mls = new MyList<>();
        mls.add("Fred");
        mls.add("Jim");
        mls.add("Sheila");
        System.out.println("items are " + mls.get(0) + ", " + mls.get(1) + ", " + mls.get(2));

        for (String s : mls) {
            System.out.println("> " + s);
        }
        System.out.println("------------------------------");
        Iterator<String> i1 = mls.iterator();
        Iterator<String> i2 = mls.iterator();
        System.out.println("i1: has? " + i1.hasNext() + ", next: " + i1.next());
        System.out.println("i1: has? " + i1.hasNext() + ", next: " + i1.next());
        System.out.println("              i2: has? " + i2.hasNext() + ", next: " + i2.next());
        System.out.println("              i2: has? " + i2.hasNext() + ", next: " + i2.next());
        System.out.println("              i2: has? " + i2.hasNext() + ", next: " + i2.next());
        System.out.println("              i2: has? " + i2.hasNext());
        System.out.println("i1: has? " + i1.hasNext() + ", next: " + i1.next());
        System.out.println("i1: has? " + i1.hasNext());
    }
}
