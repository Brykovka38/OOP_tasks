public class NokOperation implements IntegerOperations {
     @Override 
    public String getName() {
        return "Наименьшее общее кратное";
    }

    @Override 
    public String getSign() {
        return "НОК";
    }

    @Override 
    public int estimate (int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        NodOperation nod = new NodOperation();
        return (a / nod.estimate(a, b)) * b;
        
    }
}

