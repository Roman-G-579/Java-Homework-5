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

        //if both of the arrays are not empty yet,
        //iterates between them when adding elements to the list
        while (arrSize1 > 0 && arrSize2 > 0) {
            list.add(a1[index]);
            list.add(a2[index]);
            arrSize1--;
            arrSize2--;
            index++;
        }
        //adds the remainder of array1 to the list
        while (arrSize1 > 0) {
            list.add(a1[index]);
            index++;
            arrSize1--;
        }
        //adds the remainder of array2 to the list
        while (arrSize2 > 0) {
            list.add(a2[index]);
            index++;
            arrSize2--;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new CustomIterator();
    }

    private class CustomIterator implements Iterator<Integer> {
        private int index;

        @Override
        public boolean hasNext() {
            return index < list.size();
        }

        @Override
        public Integer next() {
            Integer val = list.get(index);
            index++;
            return val;
        }
    }
}
