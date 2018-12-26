/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmodel;
import java.io.*;


/**
 *
 * @author леха
 */
public class ParametersSetter {
    private  int m1;
    private  int m2=0;
    private  int m0;
    private final GSolver np;
    private double delta;
    private double c;
    private double X;
    private final Student S;
    private final Task T;
    private StartFinish sf;
    public ParametersSetter (Student S, Task T) {
        this.m0 = 100;
                
        this.S=S;
        this.T=T;
        np=new GSolver(S,T);
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
        sfn=sf;
        for (int i=0;i<m0;i++){
            System.out.print("Iteration =" +i + " ");
            sfn=np.getNeighbour(sfn.getStart(), sfn.getFinish());
            System.out.println("t1="+sfn.getStart()+" "+"t2="+sfn.getFinish());
            nextSolution=np.ProbOfComplete (sfn.getStart(),sfn.getFinish());
            System.out.print("Current =" + currentSol+" " + "Next =" + nextSolution+" ");
           FileWriter(nextSolution,i);
             if (nextSolution>currentSol){
                  
                 currentSol=nextSolution;
                 sf=sfn;
                  m2++;
                 
             }
             
             
        }
        System.out.println("m2 =" + m2);
        
        
    }
    public void FileWriter(double nextSolution, int i){
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
}
