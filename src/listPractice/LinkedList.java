package listPractice;

/*
링크드 리스트 구현

삽입, 삭제가 쉬운 자료구조

 */

class Node<T> {
  private Node<T> prev;
  private T value;
  private Node<T> next;

  public Node() {
  }

  public Node(T value) {
    this.value = value;
  }

  public Node<T> getPrev() {
    return prev;
  }

  public T getValue() {
    return value;
  }

  public Node<T> getNext() {
    return next;
  }

  public void setPrev(Node<T> prev) {
    this.prev = prev;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public void setNext(Node<T> next) {
    this.next = next;
  }
}

public class LinkedList<T> {
  private final Node<T> cursor = new Node<>();
  private Node<T> tail;

  private int totalSize = -1;

  // 삽입
  public void add(Node<T> node) {
    Node<T> currentNode = cursor;
    if (currentNode.getNext() == null) {
      currentNode.setNext(node);
      node.setPrev(currentNode);
    }
    else {
      tail.setNext(node);
      node.setPrev(tail);
    }
    tail = node;
    totalSize++;
  }
  
  // 인덱스 넘겨서 하나 가져오기
  public T getValue(int index) {
    Node<T> currentNode = cursor;
    for (int i = 0; i <= index; i++) {
      currentNode = currentNode.getNext();
      if (currentNode.getNext() == null) {
        throw new IllegalArgumentException(String.format("해당 인덱스를 잘못입력하셨습니다. %d ~ %d 인덱스까지 존재합니다.", 0, totalSize));
      }
    }
    return currentNode.getValue();
  }

  public void printAll() {
    Node<T> currentNode = cursor;
    while (currentNode.getNext() != null) {
      System.out.print(currentNode.getNext().getValue() + " ");
      currentNode = currentNode.getNext();
    }
  }
}
