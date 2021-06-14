package iterator;

import java.util.Iterator;

public class Combined<E> implements Iterable<E> {

    private Iterable<E> first;
    private Iterable<E> second;
    private Iterator<E> iterator1;
    private Iterator<E> iterator2;

    public Combined(Iterable<E> first, Iterable<E> second) {
        this.first = first;
        this.second = second;
        iterator1 = first.iterator();
        iterator2 = second.iterator();
    }

    @Override
    public Iterator<E> iterator() {
        return new newIterator();
    }

    private class newIterator implements Iterator<E> {

        private int index;
        private boolean firstFinished;
        private boolean secondFinished;
        private boolean firstNext;

        @Override
        public boolean hasNext() {
            if (iterator1.hasNext() && iterator2.hasNext()) {
                return true;
            }

            //if the first iterator does not have a next value
            if (!iterator1.hasNext()) {
                //if the second iterator does not have a next value,
                //both of the iterators does not have a next value, returns false
                if (!iterator2.hasNext()) {
                    return false;
                }
                //only the second iterator can continue
                firstFinished = true;
                return true;
            }
            //only the first iterator can continue
            secondFinished = true;
            return true;
        }

        @Override
        public E next() {
            firstNext = !firstNext;

            //if its the first iterator turn and its not done yet,
            //or if the second iterator is finished
            if (firstNext && !firstFinished || secondFinished) {
                return iterator1.next();
            }
            return iterator2.next();
        }
    }
}
