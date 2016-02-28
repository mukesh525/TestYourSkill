package com.mukesh.testyourskill.database;



public class Ques_SetGet {
    //int ID;
    private String Category, Ques, A, B, C, D, Answer, ID, UserAnswer = "No Answer";

    boolean RadiobuttonState;
    String question; // hold the question
    public int current = NONE; // hold the answer picked by the user, initial is NONE(see below)
    public static final int NONE = 1000; // No answer selected
    public static final int ANSWER_ONE_SELECTED = 0; // first answer selected
    public static final int ANSWER_TWO_SELECTED = 1; // second answer selected
    public static final int ANSWER_THREE_SELECTED = 2; // third answer selected
    public static final int ANSWER_FOUR_SELECTED = 3; // forth answer selected

    public Ques_SetGet() {


    }

    public void setResult(boolean RadiobuttonState) {
        this.RadiobuttonState = RadiobuttonState;
    }

    public boolean getResult() {
        return RadiobuttonState;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getCategory() {
        return Category;
    }

    public void setQues(String Ques) {
        this.Ques = Ques;
    }

    public String getQues() {
        return Ques;
    }

    public void setA(String A) {
        this.A = A;
    }

    public String getA() {
        return A;
    }

    public void setB(String B) {
        this.B = B;
    }

    public String getB() {
        return B;
    }

    public void setC(String C) {
        this.C = C;
    }

    public String getC() {
        return C;
    }

    public void setD(String D) {
        this.D = D;
    }

    public String getD() {
        return D;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setUserAnswer(String UserAnswer) {
        this.UserAnswer = UserAnswer;
    }

    public String getUserAnswer() {
        return UserAnswer;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

}
