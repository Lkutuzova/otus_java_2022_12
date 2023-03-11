package homework.atm;

import java.util.*;

public class ATM {
    private final Container container;
    private final CashCalculator calcCash;

    public ATM() {
        this.container = new ContainerImpl();
        this.calcCash = new CashCalculatorImpl(container);
    }

    public Map<Banknote, Integer> pull(Integer sum) {
        return calcCash.pull(sum);
    }

    public void deposit(List<Banknote> banknotes) {
        calcCash.deposit(banknotes);
    }

    public void depositMap(Map<Banknote, Integer> banknotes) {
        calcCash.depositMap(banknotes);
    }

    public Integer getBalance() {
        return container.getBalance();
    }

    public Map<Banknote, Integer> getBalanceAsMap() {
        return container.getBalanceAsMap();
    }

}
