package homework.atm;

import java.util.*;

public class ContainerImpl implements Container {
    private final TreeMap<Banknote, Integer> moneyBox = new TreeMap<>(Comparator.comparing(Banknote::getValue).reversed());

    public void put(Banknote banknot, Integer count){
        if(count == 0)
            throw new IllegalArgumentException(String.format("Нельзя внести сумму : %d", count));

        moneyBox.put(banknot, getCountBanknots(banknot) + count);
    }

    public Map<Banknote, Integer> extract(Map<Banknote, Integer> mapForExtract) {
        final Map<Banknote, Integer> money = new HashMap<>();
        mapForExtract.entrySet()
                .stream()
                .map(entry -> extractMap( entry.getKey(),entry.getValue()))
                .forEach(money::putAll);
        return money;
    }

    public NavigableSet<Banknote> getSetBanknot() {
        return moneyBox.navigableKeySet();
    }

    public Integer getCountBanknots(Banknote banknot) {
        return moneyBox.getOrDefault(banknot,0);
    }

    public Integer getBalance(){
        return moneyBox.entrySet()
                .stream()
                .map(x -> x.getValue()*x.getKey().getValue())
                .reduce(0,Integer::sum);
    }

    public Map<Banknote, Integer> getBalanceAsMap(){
        return Map.copyOf(moneyBox);
    }

    private Map<Banknote, Integer> extractMap(Banknote banknot, Integer count) {
        if(moneyBox.getOrDefault(banknot,0) < count)
            throw  new IllegalArgumentException("Недостаточно средств в банкомате");

        moneyBox.compute(banknot,(k,v) -> v - count);
        return Map.of(banknot, count);
    }

}
