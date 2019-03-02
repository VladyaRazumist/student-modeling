


public class Task {
    private final double B; // price of Task
    private final int t1; // the earliest time to start
    private final int t2; // the latest time to start
    
    
    public Task (double B, int t1, int t2) {
    
        this.B=B;
        this.t1=t1;
        this.t2=t2;
    }
    public double getB() {
            return B;
    }
     public int getT1() {
            return t1;
    }
     public int getT2() {
            return t2;
    }
}
