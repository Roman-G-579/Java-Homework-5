package iterator;

import java.util.Iterator;
import java.util.List;

public class Combined<E> implements Iterable<E> {

    private Iterable<E> first;
    private Iterable<E> second;
    private List<E> list;

    public Combined(Iterable<E> first, Iterable<E> second) {
        this.first = first;
        this.second = second;
        Iterator<E> iterator1 = first.iterator();
        Iterator<E> iterator2 = second.iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            list.add(iterator1.next());
            list.add(iterator2.next());
        }
        while (iterator1.hasNext()) {
            list.add(iterator1.next());
        }

        while (iterator2.hasNext()) {
            list.add(iterator2.next());
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new newIterator();
    }

    private class newIterator implements Iterator<E> {

        private int index;

        @Override
        public boolean hasNext() {
            if () {

            }
        }

        @Override
        public E next() {

        }
    }
}
