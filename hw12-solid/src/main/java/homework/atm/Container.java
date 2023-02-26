package homework.atm;

import java.util.Map;
import java.util.NavigableSet;

public interface Container {

    Map<Banknote, Integer> extract(Map<Banknote, Integer> mapForExtract);

    Map<Banknote, Integer> getBalanceAsMap();

    NavigableSet<Banknote> getSetBanknot();

    Integer getCountBanknots(Banknote banknot);

    Integer getBalance();

    void put(Banknote banknot, Integer count);

}
