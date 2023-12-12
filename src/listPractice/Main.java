package listPractice;

public class Main {

  public static void main(String[] args) {
    LinkedList<Integer> li = new LinkedList<>();
    li.add(new Node<>(1));
    li.add(new Node<>(2));

    li.printAll();
    System.out.println();

    System.out.println(li.getValue(0));
    System.out.println(li.getValue(1));
    System.out.println(li.getValue(10));
  }
}
