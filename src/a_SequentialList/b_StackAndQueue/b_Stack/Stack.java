package a_SequentialList.b_StackAndQueue.b_Stack;

public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
