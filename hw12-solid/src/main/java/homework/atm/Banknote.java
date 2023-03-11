package homework.atm;

public enum Banknote {
    NOMINAL_50(50),
    NOMINAL_100(100),
    NOMINAL_200(200),
    NOMINAL_500(500),
    NOMINAL_1000(1000),
    NOMINAL_5000(5000);

    private final int value;

    Banknote(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
