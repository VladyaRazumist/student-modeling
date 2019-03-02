import java.util.LinkedList;
import java.util.List;

public class ManyTasksSolver {
    Task tasks [];
    Student student;
    List < Double >solutions = new LinkedList<>();
    List <StartFinish> deadlines = new LinkedList<>();
    public ManyTasksSolver (Student s, Task[] t){
        this.tasks=t;
        this.student=s;
    }
    public void solve(){
        double solution;
        for (Task task : tasks){
            Annealer annealer = new Annealer(student,task);
            solution=annealer.simulatedAnnealing();
            annealer.printAll();
            this.solutions.add(solution);
            this.deadlines.add(annealer.getDeadline());

        }
    }

}
