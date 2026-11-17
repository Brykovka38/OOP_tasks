public class DivOperation implements IntegerOperations {
    @Override 
    public String getName() {
        return "Целочисленное деление";
    }

    @Override 
    public String getSign() {
        return "DIV";
    }

    @Override 
    public int estimate (int a, int b) {
        if (b != 0) {
            return a / b;
        } else {
            throw new ArithmeticException ("Деление на 0 запрещено");
        }
    }
}