package com.example.myapp;

class QuestionModel {

    private String QuestionString;
    private String Answer;

    public QuestionModel(String questionString, String answer) {
        QuestionString = questionString;
        Answer = answer;
    }

    public String getQuestionString() {
        return QuestionString;
    }

    public void setQuestionString(String questionString) {
        QuestionString = questionString;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }
}

