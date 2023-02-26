package homework.atm;

import java.util.*;

public class CashCalculatorImpl implements CashCalculator {
    private final Container container;

    public CashCalculatorImpl(Container container){
        this.container = container;
    }

    public void deposit(Collection<Banknote> banknotes) {
        Map<Banknote, Integer> nominalMap = new HashMap<>();
        banknotes.forEach(banknote -> nominalMap.put(banknote, nominalMap.getOrDefault(banknote, 0) + 1));
        depositMap(nominalMap);
    }

    public void depositMap(Map<Banknote, Integer> mapOfBanknots){
        mapOfBanknots.forEach(container::put);
    }

    public Map<Banknote, Integer> pull(Integer sum){
        var sortBanknot = container.getSetBanknot();
        var calcBanknots = calculate(sum, sortBanknot);
        return container.extract(calcBanknots);
    }

    private Map<Banknote, Integer> calculate(Integer sum, Set<Banknote> sortBanknot){
        Map<Banknote, Integer> mapForExtract = new HashMap<>();

        var tmpSum = 0;
        for(var nominal: sortBanknot){
            var countBanknot = (sum- tmpSum)/nominal.getValue();
            if(countBanknot > 0 ){
                int allBanknots = container.getCountBanknots(nominal);
                int countBanknotPull = Math.min(allBanknots, countBanknot);
                if(countBanknotPull > 0){
                    mapForExtract.put(nominal,countBanknotPull);
                    tmpSum += countBanknotPull * nominal.getValue();
                    if(tmpSum == sum){
                        break;
                    }
                }
            }
        }

        if (tmpSum != sum){
            throw new RuntimeException(String.format("Недостаточно средств в банкомате"));
        }

        return mapForExtract;
    }

}
