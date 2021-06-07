/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanmachine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nele
 */
public class Game {
    private int round=0;
    private int maxRounds=0;
    private int state=1;//1=wait for human answer, 2=wait for next round
    public static String[] questions = new String[100];// = {"What is 333*555?","What do you see?", "Question 3"};
    public static String[] qtypes = new String[100];// = {"simple","imagerecognition", "simple"};
    public static String[] answers = new String[100];// = {"184815","Terrier", "Answer 3"};
    public static String[] qpaths = new String[100];// = {"/media/rohrschach02.jpg","/media/rohrschach01.jpg", "/media/rohrschach03.jpg"};
    private String current_question;
    private String current_answer_machine;
    private String current_qtype;
    private String current_qpath;
    public static String[] chats = new String[100];
    
    public int getState() {
        return(state);
    }
    
    public void readQuestions() {
        BufferedReader csvReader = null;
        try {
            String row;
            int i=0; int j=0;
            //String filepath = new File("").getAbsolutePath();
            //System.out.println("Path: "+filepath);
            //filePath.concat("/src/media/questions.csv");
            String filepath = "E:/develop/java/humanmachine01/src/media/questions.csv";
            //System.out.println("Path: "+filepath);
            csvReader = new BufferedReader(new FileReader(filepath));
            //nr question answer_machine answer_possible answer_category category image
            while ((row = csvReader.readLine()) != null) {
                String media="/media/";
                String[] data = row.split(";");
                System.out.println("Row: "+row+", length: "+data.length);
                if (data.length>5 & data[5]!=null) {
                    if (data[5].equals("chat")) {
                        chats[j]=data[5];
                        j++;
                    }
                    if (!(data[5].equals("chat"))){
                        if (data.length>1) {questions[i]=data[1];} else {questions[i]="";}
                        if (data.length>2) {answers[i]=data[2];} else {answers[i]="";}
                        if (data.length>5) {qtypes[i]=data[5];} else {qtypes[i]="";}
                        if (data.length>6) {qpaths[i]=media.concat(data[6]);} else {qpaths[i]="";}
                        i++;
                    }
                }
            }
            csvReader.close();
            maxRounds=i;
            System.out.println("questions: " + Arrays.toString(questions));
            System.out.println("answers: " + Arrays.toString(answers));
            System.out.println("qtypes: " + Arrays.toString(qtypes));
            System.out.println("qpaths: " + Arrays.toString(qpaths));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                csvReader.close();
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public int getRound() {
        return(round);
    }
    public int getMaxRound() {
        return(maxRounds);
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
    public String getCurrentQPath() {
        return(current_qpath);
    }
    public String qType() {
        return(current_qtype);
    }
    public void setState(int state) {
        this.state=state;
        System.out.println("state: "+state);
    }
    public void nextRound() {
        //System.out.println("qtypes: "+qtypes);
        //System.out.println("round: "+round);
        round=round+1;
        if ((round)>=maxRounds) {
            //last round
            System.out.println("End of game: Round: "+round+ ", state: "+state+", maxRounds: "+maxRounds);
        } else {
            state=1;
            current_question=questions[round];
            System.out.println("Round: "+round+ ", state: "+state+", maxRounds: "+maxRounds+", currentQ: "+current_question.substring(0,10));

            current_answer_machine=answers[round];
            current_qtype=qtypes[round];
            current_qpath="";
            if (current_qtype!=null) {
                if (current_qtype.equals("imagerecognition") || current_qtype.equals("binaryquest")) {
                    current_qpath=qpaths[round];
                }
            }
        }
        
        
    }
    
}
