
import java.io.*;



public class Annealer {
    private  int m1;  // number of transitions that improves strictly the value of obj function
    private  int m2; // other transitions
    private final int m0=100; // total transitions
    private final GSolver solver;
    private double deltaF; // the average of the cost differences
    private double c; // initial temperature
    private double X; // acceptance rate
    private final Student S;
    private final Task T;
    private double alpha=0.995;
    private double epsilon;
    private StartFinish deadline;

    public Annealer(Student S, Task T) {
        this.S=S;
        this.T=T;
       solver=new GSolver(S,T);
    }
   public StartFinish getDeadline(){
        return deadline;
   }
    public void compute(){
        double currentSolution;
        double nextSolution;
        double delta=0;
        int t=T.getT2() - T.getT1();
        StartFinish position = new StartFinish(0,t);
         currentSolution=solver.probOfComplete(position.getStart(),position.getFinish());

        for (int i=0;i<m0;i++){

            System.out.print(" Iteration =" +i + " ");
            position=solver.getNeighbour(position.getStart(),position.getFinish()); // new neighbor
            System.out.println("t1="+position.getStart()+" "+"t2="+position.getFinish());
            nextSolution=solver.probOfComplete(position.getStart(),position.getFinish());
        if (nextSolution>currentSolution){
            m1++;
            delta+=nextSolution-currentSolution;
        }
            currentSolution=nextSolution;
            System.out.print("Current =" + currentSolution);

        }
        m2=m0-m1;
        deltaF=delta/m1;
        X=0.8;
       double f = (double)m2/((double)m2*X -(double)m1*(1-X));
       System.out.println("f "+f);
        c = deltaF/(Math.log(f));
        epsilon=c/m0;
        
    }
    public void printAll (){
        System.out.println();
        System.out.println("Total iterations :" + m0);
        System.out.println("Increasing :"+m1);
        System.out.println ("Other :"+m2 + " Average cost difference :" + deltaF);
        System.out.println(" Acceptance rate ( defined by the user ) : " + X);
        System.out.println("Initial value in the cooling process :" + c);
        System.out.println( "Stopping criterion : The algorithm\n" +
                "is stopped when the temperature reaches : epsilon = " +epsilon);
    }
    public void writeInFile(double nextSolution, int i){
        String s;
        try (final FileWriter writer = new FileWriter("C:/values.txt", true))
        {
               s=Integer.toString(i);
                writer.write(s);
                s= ";";
                writer.write(s);
                 s =Double.toString(nextSolution);
                writer.write(s);
                
                writer.write(System.lineSeparator());
                System.out.println(s);
            
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Task getT() {
        return T;
    }

    public double getInitialTemperature() {
        return c;
    }

    public Student getS() {
        return S;
    }
    public  double simulatedAnnealing () {
        this.compute();
        double ck=c; // temperature
        double r;
        double acceptProbability;
        double bestSolution=0;
        StartFinish bestDeadline=new StartFinish(0,0);
        StartFinish currentDeadline=new StartFinish(0,0);
  double currentSolution;
  double nextSolution;
        int t=T.getT2() - T.getT1();
  StartFinish deadline =  new StartFinish(0,t);
  currentSolution=solver.probOfComplete(deadline.getStart(),deadline.getFinish());
  currentDeadline=deadline;
  while (ck>epsilon) {
      System.out.println("Current temperature : " + ck);
 for (int i=0; i<m0;i++) {
     r=Math.random();

     int t1 = deadline.getStart();
     int t2 = deadline.getFinish();
     deadline = solver.getNeighbour(t1,t2);
     nextSolution = solver.probOfComplete(t1, t2);
     if(nextSolution>bestSolution){
         bestSolution=currentSolution;
         bestDeadline=deadline;
     }
     if (nextSolution > currentSolution) {
         currentSolution = nextSolution;
         currentDeadline=deadline;
     } else {
         acceptProbability = Math.exp((currentSolution - nextSolution) / ck);
         if (r < acceptProbability) {
             currentSolution = nextSolution;
             currentDeadline=deadline;
         }

     }


 }
      ck = alpha * ck;
       }
  this.deadline=bestDeadline;
  return bestSolution;
    }
}
