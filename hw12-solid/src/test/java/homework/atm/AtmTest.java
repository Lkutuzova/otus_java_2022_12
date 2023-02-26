package homework.atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static homework.atm.Banknote.*;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AtmTest {
    private ATM atm;

    @BeforeEach
    void setBefore() {
        atm = new ATM();
    }

    @Test
    @DisplayName("Проверка внесения денег и работы функции Баланса - getBalance")
    void balanceATM() {
        var moneyMap = Map.of(
                NOMINAL_50, 6,
                NOMINAL_100, 2,
                NOMINAL_200, 1
        );
        atm.depositMap(moneyMap);
        assertThat(atm.getBalance()).isEqualTo(700);
    }

    @Test
    @DisplayName("Проверка внесения денег и работы функции Баланса - getBalanceAsMap")
    void balanceMapATM() {
        var expectedMap = Map.of(
                NOMINAL_100, 2,
                NOMINAL_200, 1,
                NOMINAL_500, 1,
                NOMINAL_5000, 1
        );
        atm.deposit(List.of(NOMINAL_5000, NOMINAL_100, NOMINAL_200, NOMINAL_100, NOMINAL_500));

        assertThat(atm.getBalanceAsMap()).isEqualTo(expectedMap);
    }

    @Test
    @DisplayName("Вносим деньги, часть снимаем, проверяем остаток")
    void pullSomeMoney() {
        var moneyMap = Map.of(
                NOMINAL_50, 1,
                NOMINAL_100, 2,
                NOMINAL_200, 2,
                NOMINAL_1000, 1,
                NOMINAL_5000, 1
        );
        atm.depositMap(moneyMap);

        var expectedMoney = Map.of(
                NOMINAL_50, 1,
                NOMINAL_100, 2,
                NOMINAL_200, 2
        );
        assertThat(atm.pull(650)).isEqualTo(expectedMoney);

        var expectedHolder = Map.of(
                NOMINAL_50, 0,
                NOMINAL_100, 0,
                NOMINAL_200, 0,
                NOMINAL_1000, 1,
                NOMINAL_5000, 1 );
        assertThat(atm.getBalanceAsMap()).isEqualTo(expectedHolder);
    }

    @Test
    @DisplayName("Запросили сумму, превышающую возможности банкомата")
    void askForTooMuchMoney() {
        var moneyMap = Map.of(
                NOMINAL_50, 1,
                NOMINAL_100, 1,
                NOMINAL_500, 1,
                NOMINAL_1000, 1,
                NOMINAL_5000, 1
        );
        atm.depositMap(moneyMap);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> atm.pull(9600));
    }

    @Test
    @DisplayName("Тест на обработку исключений class ContainerImpl")
    void depositIllegalAmountBanknot() {
        var moneyMap = Map.of(
                NOMINAL_50, 0,
                NOMINAL_100, 0,
                NOMINAL_500, 0,
                NOMINAL_1000, 0,
                NOMINAL_5000, 0
        );
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> atm.depositMap(moneyMap));
    }

    @Test
    @DisplayName("Тест показывает, что бынкомат выдает деньги минимальными оставшимися купюрами")
    void pullCorrectNominalOfBanknotes() {
        var moneyMap = Map.of(
                NOMINAL_50, 6,
                NOMINAL_100, 2,
                NOMINAL_500, 4,
                NOMINAL_1000, 2
        );
        atm.depositMap(moneyMap);

        atm.pull(2250);

        var expected = Map.of(
                NOMINAL_50, 5,
                NOMINAL_100, 0,
                NOMINAL_500, 4,
                NOMINAL_1000, 0
        );
        assertThat(atm.getBalanceAsMap()).isEqualTo(expected);
    }

}
