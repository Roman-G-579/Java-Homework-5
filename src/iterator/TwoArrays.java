package iterator;

import java.util.Iterator;

public class TwoArrays implements Iterable<Integer> {

    private int[] a1, a2;

    public TwoArrays(int[] a1, int[] a2) {
        this.a1 = a1;
        this.a2 = a2;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new CustomIterator();
    }

    private class CustomIterator implements Iterator<Integer> {
        private int a1Index;
        private int a2Index;
        private boolean a1Next;
        private boolean a1finished;
        private boolean a2finished;

        @Override
        public boolean hasNext() {

            //both arrays have at least one more number in them
            if (a1Index < a1.length && a2Index < a2.length) {
                return true;
            }

            //the first array is finished
            if (a1Index == a1.length && a2Index < a2.length) {
                a1finished = true;
                return true;
            }

            //the second array is finished
            if (a1Index < a1.length && a2Index == a2.length) {
                a2finished = true;
                return true;
            }

            //both arrays have finished
            if (a1.length == a1Index && a2.length == a2Index) {
                return false;
            }

            throw new IllegalArgumentException();
        }

        @Override
        public Integer next() {
            a1Next = !a1Next;

            //if its a1's turn and it hasn't finished yet, or a2 is finished
            if ((!a1Next && !a2finished) || a1finished) {
                return a2[a2Index++];
            }

            //its a2's turn and it hasn't finished yet, or a1 is finished
            return a1[a1Index++];
        }
    }
}
