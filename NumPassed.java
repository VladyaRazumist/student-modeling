/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmodel;
import java.util.*;
/**
 *
 * @author леха
 */
public class NumPassed {
    private final Student S;
    private final Task T;
    private double [] G;
    private double P;
    private double N; // number passed
    public NumPassed(Student S, Task T)
    {
        this.S=S;
        this.T=T;
        
    }
    public double getProb(){
        return P;
    }
    public double getNumberOfCompleted() {
        return N;
    }
    public double ProbOfComplete(int t11,int t22)
    {
       if (t11>t22) {
           System.out.println("t1>t2");
           return -1;  
       }
     
       double B=T.getB();
       int t1=t11;
       int t2=t22;
       this.G=new double [S.getlenL()];
       double q=S.getQ();
       double p=S.getP();
       int t12=t2-t1;
       int h=0; // counter, where G[k]>0
       G[0]=Math.max(0,B*q-S.getLoad(t2-1));
       
    
        if (G[0]>0) {
         h++; 
        
        }
        for (int k=1;k<t12-1;k++)
           {
               
               G[k]=B*Math.pow(q,k+1)-G[k-1]*q*(1-p)-S.getLoad(t2-k-1);
               G[k]=Math.max(0,G[k]);
               if (G[k]>0) {
                   h=h+1;
               }
        
        
    }
        System.out.println();
        double P=1-Math.pow(p, h);
        this.P=P;
        this.N=P*S.getWeight();
        return N;
}
  public StartFinish getNeighbour (int t1, int t2)throws InvalidDistanceException {
     double random1=Math.random();
     double random2=Math.random();
     int max=T.getT2();
     int min=T.getT1();
     if ((t2-t1)>max || (t2-t1)<min){
         throw new InvalidDistanceException("Invalid distance");
     }
      if (random1<0.5){ // move t1
          if (random2<0.5){ // move t1 left
              if (t1!=0 && (t2-t1)!=max) {
                  t1=t1-1;
                  if (((t2-t1)==max || t1==0)&& (t2-t1)!=min ) {
                      t1=t1+1;          // or right 
                  } 
              }
          }
          if (random2>=0.5){ // move t1 right
              if((t2-t1)!=min){
                  t1=t1+1;
                  if ((t2-t1)==min && t1!=0 && (t2-t1)!=max ){
                      t1=t1-1; // or left
                  }
              }
          }
      }
      if (random1>=0.5) {  // move t2
           if (random2>=0.5 && t2!=S.getlenL() && (t2-t1)!=max){ // move t2 right   
               t2=t2+1;
               if ((t2==S.getlenL() || (t2-t1)==max)&& (t2-t1)!=min) { // or left
                   t2=t2-1;
               }
           }    
           if (random2<0.5 && (t2-t1)!=min){ // move t2 left
               t2=t2-1; 
               if ((t2-t1)==min && t2!=S.getlenL() && (t2-t1)!=max){
                   t2=t2+1;
               }
           } 
  }
      StartFinish sf=new StartFinish(t1,t2);
      return sf;
  }
    
    public  void FindSolution (Student S, Task T) throws InvalidDistanceException {
        double ck; // temperature parameter 
        int Lk=1000; // number of transitions generated at iteration k;
        double alpha = 0.995;
        int  L=S.getlenL();
        int  t1= T.getT1();
        int  t2= T.getT2();
        int min=t1;
        int max=t2;
        double nextSolution;
        int t12 = (int)Math.floor(((t1+t2)/2)/2);
        int middle = (int)Math.floor(L/2); // initial state
        t1=middle-t12;
        t2=middle+t12;
        System.out.println(t1 +" "+ t2);
        double currentSolution=ProbOfComplete(t1,t2);
        StartFinish sfl=new StartFinish(t1,t2);
        StartFinish sf=new StartFinish(t1,t2);
        
         sf=getNeighbour(t1,t2,min,max);
        
        nextSolution =ProbOfComplete(sf.getStart(),sf.getFinish());
        
        if (nextSolution>currentSolution){
            currentSolution=nextSolution;
        }
}
}