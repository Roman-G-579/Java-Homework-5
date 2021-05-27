package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TwoArrays implements Iterable<Integer> {

    private List<Integer> list;

    public TwoArrays(int[] a1, int[] a2) {
        int arrSize1 = a1.length;
        int arrSize2 = a2.length;
        int index = 0;
        list = new ArrayList<>();

        while (arrSize1 > 0 && arrSize2 > 0) {
            list.add(a1[index]);
            list.add(a2[index]);
            arrSize1++;
            arrSize2++;
            index++;
        }
        while (arrSize1 > 0) {
            list.add(a1[index]);
            index++;
            arrSize1--;
        }
        while (arrSize2 > 0) {
            list.add(a2[index]);
            index++;
            arrSize2--;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new CustomIterator(list);
    }

    private class CustomIterator implements Iterator<Integer> {

        private List<Integer> internalList;
        private int index;

        public CustomIterator(List<Integer> internalList) {
            this.internalList = internalList;
        }

        @Override
        public boolean hasNext() {
            return index < internalList.size() - 1;
        }

        @Override
        public Integer next() {
            Integer val = internalList.get(index);
            index++;
            return val;
        }
    }
}
