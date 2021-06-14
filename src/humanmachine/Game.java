/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanmachine;

import java.io.BufferedReader;
//import java.io.File;
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
    private int categories=0;
    private int maxRounds=0;
    private int state=1;//1=wait for human answer, 2=wait for next round
    public static String[] questions = new String[100];// = {"What is 333*555?","What do you see?", "Question 3"};
    public static String[] qcategories = new String[100];// = {"simple","imagerecognition", "simple"};
    public static String[] answers = new String[100];// = {"184815","Terrier", "Answer 3"};
    public static String[] correct_answers = new String[100];// = {"184815","Terrier", "Answer 3"};
    public static String[] qpaths = new String[100];// = {"/media/rohrschach02.jpg","/media/rohrschach01.jpg", "/media/rohrschach03.jpg"};
    public static String[] qu_descriptions = new String[100];
    public static String[] i_descriptions = new String[100];
    public static String[] description_categories = new String[100];
    private String current_question;
    private String current_answer_machine;
    private String current_correct_answer;
    private String current_qcategory;
    private String current_qpath;
    private String current_description_category;
    private String current_qu_description;//description for the question, added to the question
    private String current_i_description;//additional information for the question, available through the i-button
    public static String[] chats = new String[100];
    public static String[] chat_paths = new String[100];
    
    public int getState() {
        return(state);
    }
    
    public void readQuestions() {
        BufferedReader csvReader = null;
        BufferedReader csvReader2 = null;
        try {
            String row;
            String row2;
            int i=0; int j=0; int k=0;
            //String filepath = new File("").getAbsolutePath();
            //System.out.println("Path: "+filepath);
            //filePath.concat("/src/media/questions.csv");
            //String qu_filepath = "E:/develop/java/humanmachine01/src/media/questions.csv";
            //String descr_filepath = "E:/develop/java/humanmachine01/src/media/descriptions.csv";
            String userdir = System.getProperty("user.dir");
            System.out.println("Working Directory = " + userdir);
            String qu_filepath = userdir.concat("/src/media/questions.csv");
            String descr_filepath = userdir.concat("/src/media/descriptions.csv");
            //System.out.println("Path: "+filepath);
            csvReader = new BufferedReader(new FileReader(qu_filepath));
            //nr question answer_machine answer_possible answer_category category image
            while ((row = csvReader.readLine()) != null) {
                String media="/media/";
                String[] data = row.split(";");
                System.out.println("Row: "+row+", length: "+data.length);
                if (data.length>5 & data[5]!=null) {
                    if (data[5].equals("chat")) {
                        //System.out.println("2: "+data[1]);
                        chats[j]=data[1];
                        if (data.length>6) {chat_paths[j]=media.concat(data[6]);} else {chat_paths[j]="";}
                        j++;
                    }
                    if (!(data[5].equals("chat"))){
                        if (data.length>1) {questions[i]=data[1];} else {questions[i]="";}
                        if (data.length>2) {answers[i]=data[2];} else {answers[i]="";}
                        if (data.length>3) {correct_answers[i]=data[3];} else {correct_answers[i]="";}
                        if (data.length>5) {qcategories[i]=data[5];} else {qcategories[i]="";}
                        if (data.length>6) {qpaths[i]=media.concat(data[6]);} else {qpaths[i]="";}
                        i++;
                    }
                }
            }
            csvReader.close();
            maxRounds=i;
            System.out.println("questions: " + Arrays.toString(questions));
            System.out.println("answers: " + Arrays.toString(answers));
            System.out.println("correct_answers: " + Arrays.toString(correct_answers));
            System.out.println("qcategories: " + Arrays.toString(qcategories));
            System.out.println("qpaths: " + Arrays.toString(qpaths));
            System.out.println("chats: " + Arrays.toString(chats));
            System.out.println("chat_paths: " + Arrays.toString(chat_paths));
            
            csvReader2 = new BufferedReader(new FileReader(descr_filepath));
            //nr question answer_machine answer_possible answer_category category image
            while ((row2 = csvReader2.readLine()) != null) {
                String[] data2 = row2.split(";");
                System.out.println("Row: "+row2+", length: "+data2.length);
                if (data2.length>0) {description_categories[k]  =data2[0];} else {description_categories[k]="";}
                if (data2.length>1) {qu_descriptions[k]         =data2[1];} else {qu_descriptions[k]="";}
                if (data2.length>3) {i_descriptions[k]          =data2[2];} else {i_descriptions[k]="";}
                if (data2.length>2) {i_descriptions[k]         =i_descriptions[k].concat("\n\n"+data2[3]);}
                k++;
            }
            categories=k;
            csvReader2.close();
            System.out.println("description_categories: "+Arrays.toString(description_categories));
            System.out.println("qu_descriptions: "+Arrays.toString(qu_descriptions));
            System.out.println("i_descriptions: "+Arrays.toString(i_descriptions));
            
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
    public String getCurrentQCategory() {
        return(current_qcategory);
    }
    public String getCurrentQPath() {
        return(current_qpath);
    }
    public String getQCategory() {
        return(current_qcategory);
    }
    
    public String getCurrentDescriptionCategory() {
        return(current_description_category);
    }
    public String getCurrentQDescription() {
        return(current_qu_description);
    }
    public String getCurrentCorrectAnswer() {
        return(current_correct_answer);
    }
    public String getIDescription() {
        return(current_i_description);
    }
    
    private String[] languages = {"pt", "en", "es"};
    private Integer indexOf(String[] myarr, String string){
       for (int i=0; i<categories; i++)
          if(myarr[i].equals(string)) return i;
       return -1;
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
            System.out.println("Round x: "+round+ ", state: "+state+", maxRounds: "+maxRounds+", currentQ: "+current_question.substring(0,7));

            current_answer_machine=answers[round];
            current_correct_answer=correct_answers[round];
            current_qcategory=qcategories[round];
            current_qpath="";
            if (current_qcategory!=null) {
                if (current_qcategory.equals("imagerecognition") || current_qcategory.equals("div") || current_qcategory.equals("Computer language")) {
                    current_qpath=qpaths[round];
                }
            }
            
            //add additional descriptions
            int current_description_index=-1;
            if (description_categories!=null) {current_description_index=indexOf(description_categories,current_qcategory);}
            
            if (current_description_index>-1) {
                current_qu_description=qu_descriptions[current_description_index];
                current_i_description=i_descriptions[current_description_index]; 
            } else {
                current_qu_description="";
                current_i_description=""; 
            }
        }
        
        
    }
    
}
