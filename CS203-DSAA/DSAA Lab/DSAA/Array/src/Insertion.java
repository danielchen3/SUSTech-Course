public class Insertion {
    protected void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (isgreater(a[j - 1], a[j]))
                    exchange(a, j - 1, j);
                else continue;
            }
        }
    }

    protected boolean isgreater(Comparable u, Comparable v) {
        return u.compareTo(v) > 0;
    }

    protected void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
