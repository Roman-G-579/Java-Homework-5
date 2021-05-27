package iterator;

import java.util.Iterator;

public class Combined<E> implements Iterable<E>{

    public Combined(Iterable<E> first, Iterable<E> second) {
    }

    @Override
    public Iterator<E> iterator() {
        return new newIterator();
    }

    private class newIterator implements Iterator<E> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }
}
