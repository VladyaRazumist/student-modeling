/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmodel;

/**
 *
 * @author леха
 */
public class SetParameters {
    private  int m1;
    private  int m2=0;
    private  int m0;
    private final NumPassed np;
    private double delta;
    private double c;
    private double X;
    private final Student S;
    private final Task T;
    private StartFinish sf;
    public SetParameters (Student S, Task T) {
        this.m0 = 100;
        this.S=S;
        this.T=T;
        np=new NumPassed(S,T);
    }
    public void Compute() throws Exception{
        int  L=S.getlenL();
        int  t1= T.getT1();
        int  t2= T.getT2();
      
        double currentSol;
        double nextSolution;
        int t12 = (int)Math.floor(((t1+t2)/2)/2);
        int middle = (int)Math.floor(L/2); // initial state
        t1=middle-t12;
        t2=middle+t12;
        StartFinish sfn; // next
        StartFinish sf = new StartFinish(t1,t2);
        currentSol=np.ProbOfComplete(t1, t2);
        nextSolution= currentSol;
        
        for (int i=0;i<m0;i++){
            System.out.print("Iteration =" +i + " ");
            sfn=np.getNeighbour(sf.getStart(), sf.getFinish());
            System.out.println("t1="+sf.getStart()+" "+"t2="+sf.getFinish());
            nextSolution=np.ProbOfComplete (sfn.getStart(),sfn.getFinish());
            System.out.print("Current =" + currentSol+" " + "Next =" + nextSolution+" ");
           
             if (nextSolution>currentSol){
                  
                 currentSol=nextSolution;
                 sf=sfn;
                  m2++;
                 
             }
             
             
        }
        System.out.println("m2 =" + m2);
        
        
    }
    
}
