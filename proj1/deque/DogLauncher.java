package deque;

public class DogLauncher {
    public static void main(String[] args) {
        Dog d1 = new Dog("elephw", 4);
        Dog d2 = new Dog("holly", 56);
        Dog d3 = new Dog("jelly", 45);

        Dog[] dogs = new Dog[]{d1, d2, d3};

        Dog d = (Dog) Maximizer.max(dogs);

        System.out.println(d.name);
    }
}
