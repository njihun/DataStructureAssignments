package Collection;

public class MyStack<T extends Comparable<T>> implements IMyStack<T>{
    private static class NodeForStack<T>{
        NodeForStack<T> next;
        T key;

        public NodeForStack(T key){
            this.key = key;
            next = null;
        }
    }

    private NodeForStack<T> top;
    private int count;

    public MyStack(){
        top = null;
        count = 0;
    }

    @Override
    public T pop() {
        if (isEmpty()){
            throw new IllegalArgumentException("[ERROR] Stack is empty. ");
        }

        T temp = top.key;
        top = top.next;

        count--;
        return temp;
    }

    @Override
    public void push(T data) {
        NodeForStack<T> newTop = new NodeForStack<>(data);

        if (isEmpty()){
            top = newTop;
            count++;
            return;
        }

        newTop.next = top;
        top = newTop;

        count++;
    }

    @Override
    public T peek() {
        return top.key;
    }

    public boolean isExist(T data) {
        NodeForStack<T> currNode = top;

        while (currNode != null){
            if (currNode.key.equals(data)){
                return true;
            }

            currNode = currNode.next;
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public void clear() {
        top = null;
    }
}