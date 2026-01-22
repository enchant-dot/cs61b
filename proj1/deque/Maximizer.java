package deque;

//public class Maximizer {
//    public static Comparable max(Comparable[] a){
//       int max = 0;
//       for (int i =0; i < a.length; i++) {
//           int cmp = a[i].compareTo(a[max]);
//           if (cmp >0) {
//               max = i;
//           }
//       }
//       return a[max];
//    }
//}

public class Maximizer {
    public static Comparable max(Comparable[] a) {
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            int cmp = a[i].compareTo(a[max]);
            if (cmp > 0) {
                max = i;
            }
        }
        return a[max];
    }
}