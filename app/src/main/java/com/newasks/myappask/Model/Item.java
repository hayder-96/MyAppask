package com.newasks.myappask.Model;


public class Item {
    private int id;
    private String ask;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answercoect;

    public Item(int id, String ask, String answer1, String answer2, String answer3, String answer4, String answercoect) {
        this.id = id;
        this.ask = ask;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answercoect = answercoect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getAnswer1() {
        return answer1;
    }


    public String getAnswer2() {
        return answer2;
    }


    public String getAnswer3() {
        return answer3;
    }


    public String getAnswer4() {
        return answer4;
    }


    public String getAnswercoect() {
        return answercoect;
    }


}


