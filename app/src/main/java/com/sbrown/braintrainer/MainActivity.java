package com.sbrown.braintrainer;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtTimer, txtProblem, txtScore, txtA, txtB, txtC, txtD, txtStatus;
    Button btnStart;
    int answer, incorrect, problem, score=0;
    CountDownTimer timer;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTimer =(TextView)findViewById(R.id.txtTimer);
        txtTimer.setText("30");
        txtProblem =(TextView)findViewById(R.id.txtProblem);
        txtScore =(TextView)findViewById(R.id.txtScore);
        txtA =(TextView)findViewById(R.id.txtA);
        txtB =(TextView)findViewById(R.id.txtB);
        txtC =(TextView)findViewById(R.id.txtC);
        txtD =(TextView)findViewById(R.id.txtD);
        txtStatus =(TextView)findViewById(R.id.txtStatus);
        btnStart = (Button)findViewById(R.id.btnStart);

    }//onCreate

    public void generateProblem(){
        //int number = random.nextInt(max + 1 -min) + min;
        int max = 20, min = 0;

        int one, two, operator; //operator  0 = +, 1 = -
        one = new Random().nextInt(21);
        two = new Random().nextInt(21);
        operator = new Random().nextInt(2);

        if(operator == 0){
            answer = one + two;
            txtProblem.setText(one + "+" + two);
        }else{
            answer = one - two;
            txtProblem.setText(one + "-" + two);
        }

    }//end generateProblem

    public void generateOptions(){

        int seed = new Random().nextInt(4);

        switch(seed){

            case 0:
                txtA.setText(answer+"");
                txtA.setTag(txtA.getText());
                txtB.setText(new Random().nextInt(41) + "");
                txtB.setTag(txtB.getText());
                txtC.setText(new Random().nextInt(21) + "");
                txtC.setTag(txtC.getText());
                txtD.setText(new Random().nextInt(41) + "");
                txtD.setTag(txtD.getText());
                break;
            case 1:
                txtB.setText(answer+"");
                txtB.setTag(txtB.getText());
                txtA.setText(new Random().nextInt(21) + "");
                txtA.setTag(txtA.getText());
                txtC.setText(new Random().nextInt(41) + "");
                txtC.setTag(txtC.getText());
                txtD.setText(new Random().nextInt(21) + "");
                txtD.setTag(txtD.getText());
                break;
            case 2:
                txtC.setText(answer+"");
                txtC.setTag(txtC.getText());
                txtB.setText(new Random().nextInt(21) + "");
                txtB.setTag(txtB.getText());
                txtA.setText(new Random().nextInt(21) + "");
                txtA.setTag(txtA.getText());
                txtD.setText(new Random().nextInt(41) + "");
                txtD.setTag(txtD.getText());
                break;
            case 3:
                txtD.setText(answer+"");
                txtD.setTag(txtD.getText());
                txtB.setText(new Random().nextInt(21) + "");
                txtB.setTag(txtB.getText());
                txtC.setText(new Random().nextInt(21) + "");
                txtC.setTag(txtC.getText());
                txtA.setText(new Random().nextInt(21) + "");
                txtA.setTag(txtA.getText());
                break;
        }
    }//end generateOptions

    public void onStart(View view){
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setText("30");
        txtScore.setText("0");
        txtStatus.setText("");
        txtProblem.setText("");
        startTimer();

        generateProblem();
        generateOptions();


    }

    public void checkAnswer(View view){

        int given = Integer.parseInt((String)view.getTag());
        if(given == answer){
            mp = MediaPlayer.create(this, R.raw.correct);
            mp.start();
            txtStatus.setTextColor(Color.GREEN);
            txtStatus.setText("Correct!");
            score++;
            txtScore.setText(score+"");
        }else{
            txtStatus.setTextColor(Color.RED);
            txtStatus.setText("Incorrect!");
            mp = MediaPlayer.create(this, R.raw.wrong);
            mp.start();
        }

        generateProblem();
        generateOptions();

    }

    public void startTimer(){


        timer = new CountDownTimer(30000+200,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTimer.setText(millisUntilFinished/1000+"");



            }

            @Override
            public void onFinish() {
                txtTimer.setText("0");
                mp = MediaPlayer.create(getApplicationContext(), R.raw.buzzer);
                mp.start();

                btnStart.setVisibility(View.VISIBLE);

                txtStatus.setText("Your final score: " + score + "!");


            }
        }.start();

    }



}
