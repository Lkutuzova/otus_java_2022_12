package homework.atm;

import java.util.Collection;
import java.util.Map;

public interface CashCalculator {
    Map<Banknote, Integer> pull(Integer sum);

    void deposit(Collection<Banknote> banknotes);

    void depositMap(Map<Banknote, Integer> mapOfBanknots);

}
