public class ModOperation implements IntegerOperations{
     @Override 
    public String getName() {
        return "Остаток от деления";
    }

    @Override 
    public String getSign() {
        return "MOD";
    }

    @Override 
    public int estimate (int a, int b) {
        if (b != 0) {
            return a % b;
        } else {
            throw new ArithmeticException ("Деление на 0 запрещено");
        }
    }
}
