public class Main {
     
    public static void main  (String [] args)throws Exception {

        Student student1 = new Student(8,0.5,0.6); // w, p , q , i
        Student student2=  new Student(8,0.4,0.7);
        GSolver test;
        Task T1 = new Task (10,1,10);    // B,t1,t2
        Task T2 = new Task (10,3,8);
        test = new GSolver(student1,T1); // Student and Task
        student1.setLoad(60);             // Planned period
        Annealer annealer1 = new Annealer(student1,T1);
        Annealer annealer2= new Annealer(student1,T2);
        annealer1.compute();
        annealer2.compute();
        annealer1.printAll();
        double sol2=annealer2.simulatedAnnealing();
        double solution = annealer1.simulatedAnnealing();
        System.out.println(solution+" "+sol2);
        System.out.println("t1 = "+ annealer1.getDeadline().getStart()+" t2 = "+ annealer1.getDeadline().getFinish());
        System.out.println("t1 = "+ annealer2.getDeadline().getStart()+" t2 = "+ annealer2.getDeadline().getFinish());
    }
    }

