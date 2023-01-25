package homework;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {
    //todo: 3. надо реализовать методы этого класса

    private TreeMap<Customer, String> map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> firstItem = map.firstEntry();
        if (firstItem == null) {
            return null;
        }
        return Map.entry(new Customer(firstItem.getKey().getId(), firstItem.getKey().getName(), firstItem.getKey().getScores()), firstItem.getValue());

    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> nextItem = map.higherEntry(customer);
        if (nextItem == null) {
            return null;
        }
        return Map.entry(new Customer(nextItem.getKey().getId(), nextItem.getKey().getName(), nextItem.getKey().getScores()), nextItem.getValue());
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }

    private Map.Entry<Customer, String> copyEntry(Map.Entry<Customer, String> entry) {
        if (entry == null) return null;
        return Map.entry(
                new Customer(entry.getKey().getId(), entry.getKey().getName(), entry.getKey().getScores()),
                entry.getValue()
        );
    }
}
