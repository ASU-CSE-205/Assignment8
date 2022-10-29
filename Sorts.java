import java.util.Comparator;
import java.util.ArrayList;

public class Sorts {
    public static void sort(ArrayList<Hotel> reviewList, Comparator<Hotel> xComparator) {
        quickSort(reviewList, xComparator, 0, reviewList.size() - 1);
    }

    public static void quickSort(ArrayList<Hotel> reviewList, Comparator<Hotel> xComparator, int front, int back) {
        if (front < back - 1) {
            int p = partition(reviewList, xComparator, front, back);
            quickSort(reviewList, xComparator, front, p);
            quickSort(reviewList, xComparator, p + 1, back);
        }
    }

    public static int partition(ArrayList<Hotel> reviewList, Comparator<Hotel> xComparator, int front, int back) {
        Hotel pivot = reviewList.get(front);
        int i = front - 1;
        int j = back + 1;

        while (i < j) {
            i++;
            while (xComparator.compare(pivot, reviewList.get(i)) > 0) {
                i++;
            }
            j--;
            while (xComparator.compare(pivot, reviewList.get(j)) < 0) {
                j--;
            }
            if (i < j) {
                Hotel temp = reviewList.get(i);
                reviewList.set(i, reviewList.get(j));
                reviewList.set(j, temp);
            }
        }
        return j;
    }
}


