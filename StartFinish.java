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
public class StartFinish {
    private final int start;
    private final int finish;
    public StartFinish(int t1, int t2){
        start=t1;
        finish=t2;
    }
    public int getStart(){
        return start;
    }
    public int getFinish() {
        return finish;
    }
}
