package org.keetech.a1902017_project1_randomgugudan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int RanNum1;
    int RanNum2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Gugudan Mania");
        Button btnOK = findViewById(R.id.btnOK);
        Button btnRandom = findViewById(R.id.btnRandom);
        final EditText edt1 = findViewById(R.id.edt1);
        final EditText edt2 = findViewById(R.id.edt2);
        final EditText edt3 = findViewById(R.id.edt3);

        ListView listView1 = findViewById(R.id.listView1); // XMl ListView 연결
        final ArrayList<String> answerList = new ArrayList<>(); //String 형 배열 생성
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,answerList); //어댑터생성(list)
        listView1.setAdapter(adapter);//ListView에 다가 adapter를 넣어줌




        btnRandom.setOnClickListener(new View.OnClickListener() { //난수 발생 버튼 이벤트
            @Override
            public void onClick(View v) {
                RanNum1 = new Random().nextInt(8)+2; //난수 2개 발생
                RanNum2 = new Random().nextInt(8)+2;

                edt1.setText(String.valueOf(RanNum1)); //첫번째 숫자
                edt2.setText(String.valueOf(RanNum2)); //두번째 숫자
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answer = Integer.parseInt(edt3.getText().toString());//입력한 답
                int Canswer = RanNum1 * RanNum2; // 정답

                if(answer == Canswer){  //정답 일때
                    answerList.clear(); //배열 안의 값을 비워줌
                    adapter.notifyDataSetChanged(); // =refresh 기능
                   Toast result = Toast.makeText(MainActivity.this,"정답입니다",Toast.LENGTH_SHORT);
                   result.show();
                }else{ //오답일때
                    Toast result = Toast.makeText(MainActivity.this,"틀렸습니다! 공부하세요!",Toast.LENGTH_SHORT);
                    result.show();
                    answerList.clear(); //배열 안의 값을 비워줌
                    for(int i=1;i<10;i++){
                        answerList.add(RanNum1 + "X " + i + "=" +  RanNum1*i);
                        adapter.notifyDataSetChanged(); // adpater 리스트를 이제 실제로 뿌려주는 것
                    }
                }
            }
        });
    }
}
