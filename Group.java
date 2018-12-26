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
public class Group {
    private final int numberOfStudents; // types of students actually
    private final Student [] students;
    
    
    public Group(int nofS,double [] p, double [] q, int [] w){   
    this.numberOfStudents=nofS; 
    this.students = new Student[numberOfStudents]; // array of student`s types
        for (int i=0;i<numberOfStudents;i++) {
            students[i]=new Student(w[i],p[i],q[i]);
        }
        
    }
        
     
        public Student getStudent(int i) {
            return this.students[i];
        }
        public void PrintGroup () {
            for (Student S:students)
            {
                int i=1;
                System.out.println("N="+ i + "  " + "p="+S.getP() +"  "+"q="+S.getQ()+"  "+"w="+S.getWeight());
                i++;
            } 
        }
      
                
        }

