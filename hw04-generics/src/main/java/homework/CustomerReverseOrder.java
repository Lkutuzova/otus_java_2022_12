package homework;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class CustomerReverseOrder {
    //todo: 2. надо реализовать методы этого класса

    //private final Stack<Customer> custArr = new Stack<>();
    private final Deque<Customer> custArr = new ArrayDeque<>();

    public void add(Customer customer) {
        custArr.push(customer);
    }

    public Customer take() {
        return custArr.pop();
    }

}
