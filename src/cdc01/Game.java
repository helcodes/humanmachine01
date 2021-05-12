/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdc01;

/**
 *
 * @author nele
 */
public class Game {
    private int round=-1;
    private int state=1;//1=wait for human answer, 2=wait for next round
    public static String[] questions = {"What do you see?","What is 333*555?", "Question 3"};
    public static String[] qtype = {"imagerecognition","simple", "simple"};
    public static String[] answers = {"Terrier","184815", "Answer 3"};
    private String current_question;
    private String current_answer_machine;
    private String current_qtype;
    
    
    public int getState() {
        return(state);
    }
    
    
    public String getCurrentQuestion() {
        return(current_question);
    }
    public String getCurrentAnswerMachine() {
        return(current_answer_machine);
    }
    public String getCurrentQType() {
        return(current_qtype);
    }
    
    public String qType() {
        return(current_qtype);
    }
    
    public void setState(int state) {
        this.state=state;
        System.out.println("state: "+state);
    }
    public void nextRound() {
        round=round+1;
        state=1;
        System.out.println("Round: "+round+ ", state: "+state);
        
        current_question=questions[round];
        current_answer_machine=answers[round];
        current_qtype=qtype[round];
        
        
    }
    
}
