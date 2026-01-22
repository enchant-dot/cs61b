package deque;

//public class Dog implements Comparable<Dog>{
//
//    public String name;
//    public int size;
//
//    public Dog(String n, int s) {
//        name = n;
//        size = s;
//    }
//
//    @Override
//    public int compareTo(Dog o) {
//        return size-o.size;
//    }
//
//    public void bark() {
//        System.out.println("barking !!!");
//    }
//}

public class Dog implements Comparable<Dog> {

    public String name;
    public int size;

    public Dog(String n, int a) {
        name = n;
        size = a;
    }

    @Override
    public int compareTo(Dog o) {
        return size-o.size;
    }

    public void bark() {
        System.out.println("barkkkk!!");
    }
}