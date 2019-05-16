package com.example.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {
    TextView questionLable,questionCountLabel,scoreLabel;
    EditText answeredt;
    Button btnSunmit;
    ProgressBar progressBar;
    ArrayList<QuestionModel> questionModelArrayList;
    int currentPosition = 0;
    int NumberofCurrectAnswer = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionCountLabel = (TextView)findViewById(R.id.noQuestion);
        questionLable = (TextView)findViewById(R.id.Question);
        scoreLabel = (TextView)findViewById(R.id.Score);

        answeredt = (EditText)findViewById(R.id.Answer);
        btnSunmit = (Button)findViewById(R.id.Submit);
        progressBar = (ProgressBar)findViewById(R.id.Progress);

        questionModelArrayList = new ArrayList<>();
        setUpQuestion();
        setData();

        btnSunmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAnswer();
            }
        });

    }

    public void CheckAnswer(){

        String answerString = answeredt.getText().toString().trim();
        if(answerString.equalsIgnoreCase(questionModelArrayList.get(currentPosition).getAnswer())){
            NumberofCurrectAnswer++;

            new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Good Job !")
                    .setContentText("Right Answer")
                    //.setConfirmText("Yes,delete it!")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            currentPosition++;
                            setData();
                            answeredt.setText("");
                            //Log.e("Answer","Right");
                            sDialog.dismiss();
                            /*sDialog
                                    .setTitleText("Deleted!")
                                    .setContentText("Your imaginary file has been deleted!")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(null)
                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE); */
                        }
                    })
                    .show();
        }
        else{
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Wrong Answer!")
                    .setContentText("The Right Answer is :"+questionModelArrayList.get(currentPosition).getAnswer())
                    .setConfirmText("OK!")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();
                            Log.e("Answer","Wrong");
                            currentPosition++;
                            setData();
                            answeredt.setText("");
                        }
                    })
                    .show();


        }
        int x = ((currentPosition+1)*100)/questionModelArrayList.size();
        progressBar.setProgress(x);

    }

    public void setUpQuestion(){

        questionModelArrayList.add(new QuestionModel("What is 1+2 ?","3") );
        questionModelArrayList.add(new QuestionModel("What is 8*7 ?","56"));
        questionModelArrayList.add(new QuestionModel("What is 96/12 ?","6"));
        questionModelArrayList.add(new QuestionModel("What is 135/15 ?","7"));
        questionModelArrayList.add(new QuestionModel("What is 8+8 ?","16"));

    }
    public void setData(){
        if (questionModelArrayList.size()>currentPosition){
            questionLable.setText(questionModelArrayList.get(currentPosition).getQuestionString());
            questionCountLabel.setText("Qustion No :" +(currentPosition+1));
            scoreLabel.setText("Score :" +NumberofCurrectAnswer +"/" +questionModelArrayList.size());
        }
        else {
            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("You have Successfully Complete the Quiz")
                    .setContentText("Your Score is :"+NumberofCurrectAnswer + "/" +questionModelArrayList.size())
                    .setConfirmText("Restart")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            currentPosition=0;
                            NumberofCurrectAnswer=0;
                            progressBar.setProgress(0);
                            setData();
                        }
                    })
                    .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            finish();
                        }
                    })
                    .show();
        }

    }

}
