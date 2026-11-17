public class NodOperation implements IntegerOperations {
     @Override 
    public String getName() {
        return "Наибольший общий делитель";
    }

    @Override 
    public String getSign() {
        return "НОД";
    }

    @Override 
    public int estimate (int a, int b) {
        int x;
        while (b != 0) {
            x = b;
            b = a % b;
            a = x;         
        }
        return a;
    }
}

