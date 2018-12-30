package kr.tjeit.a20181230_test.datas;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Board {

    private int id;
    private int author;
    private String title;
    private String content;
    private String type;
    private Calendar written_at = Calendar.getInstance();

//    JSON을 기반으로 Board 객체를 리턴하는 static 메소드
    public static Board getBoardFromJson(JSONObject jsonObject){
        Board board = new Board();

        try {
            board.id = jsonObject.getInt("id");
            board.author = jsonObject.getInt("author");
            board.title = jsonObject.getString("title");
            board.content = jsonObject.getString("content");
            board.type = jsonObject.getString("type");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String written_at_string = jsonObject.getString("written_at");
            board.written_at.setTime(sdf.parse(written_at_string));

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return board;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Calendar getWritten_at() {
        return written_at;
    }

    public void setWritten_at(Calendar written_at) {
        this.written_at = written_at;
    }
}
