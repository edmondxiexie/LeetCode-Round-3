/**
 * Created by Edmond on 3/10/17.
 */
public class Dog extends Animal {
    public Dog(String n) {
        super(n);
    }

    public static void main(String[] args) {
        Dog dog = new Dog("A");
        dog.getName();
    }
}


