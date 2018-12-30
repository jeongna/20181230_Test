package kr.tjeit.a20181230_test;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.tjeit.a20181230_test.datas.Board;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseActivity {


    List<Board> boards = new ArrayList<Board>();

    private android.widget.TextView userNameTxt;
    private android.widget.TextView userIdTxt;
    private android.widget.TextView userEmailTxt;
    private android.widget.TextView userPhoneTxt;
    private android.widget.ListView boardListView;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    void getBoardsFromServer(){
        OkHttpClient client = new OkHttpClient();

//        밑의 두줄이 GET만의 방식. POST/PUT과의 차이점
        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://api-dev.lebit.kr/board").newBuilder();
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().header("X-Http-Token",token) //헤더가 필요한 경우에만 작성
                .url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseBody = response.body().string();
                Log.d("게시판조회", responseBody);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getBoardsFromServer();
    }

    @Override
    public void setValues() {

        token = getIntent().getStringExtra("토큰");
        String loginId = getIntent().getStringExtra("로그인아이디");
        String loginEmail = getIntent().getStringExtra("이메일");
        String loginName = getIntent().getStringExtra("이름");
        String loginPhone = getIntent().getStringExtra("폰번");


        userNameTxt.setText(loginName);
        userIdTxt.setText(loginId);
        userEmailTxt.setText(loginEmail);
        userPhoneTxt.setText(loginPhone);


    }

    @Override
    public void bindViews() {
        this.userPhoneTxt = (TextView) findViewById(R.id.userPhoneTxt);
        this.userEmailTxt = (TextView) findViewById(R.id.userEmailTxt);
        this.userIdTxt = (TextView) findViewById(R.id.userIdTxt);
        this.userNameTxt = (TextView) findViewById(R.id.userNameTxt);
        this.boardListView = (ListView) findViewById(R.id.boardListView);
    }
}
