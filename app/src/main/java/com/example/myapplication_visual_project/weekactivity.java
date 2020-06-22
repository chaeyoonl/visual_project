package com.example.myapplication_visual_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static android.widget.Toast.LENGTH_LONG;
import static java.security.AccessController.getContext;

public class weekactivity extends AppCompatActivity {

    LinearLayout baseLayout_week;
    Button button1_week;
    Button last_week;
    Button next_week;
    Button todaybutton_week;
    String[] weekview_nuber = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
            "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "  "};
    Button weekviews_1;
    Button weekviews_2;
    Button weekviews_3;
    Button weekviews_4;
    Button weekviews_5;
    Button weekviews_6;
    Button weekviews_7;

    int count_num;
    int j = 0;
    int addday = 0;
    int addday2 = 0;
    int lastmonth = 0;

    int week_num = 0;

    /**
     * 연/월 텍스트뷰
     */
    TextView tvDate_week;
    /**
     * 그리드뷰 어댑터
     */
    //weekactivity.GridAdapter gridAdapter;

    /**
     * 일 저장 할 리스트
     */
    ArrayList<String> dayList;


    /**
     * 그리드뷰
     */
    GridView gridView_week;

    /**
     * 캘린더 변수
     */
    Calendar mCal;
    Calendar mCal_now;

    int month_test = 1;


    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn10;
    Button btn11;
    Button btn12;


    View dialogView, toastView;


    String file_month;
    String month_text;


    TextView text_1;
    TextView text_2;
    TextView text_3;
    TextView text_4;
    TextView text_5;
    TextView text_6;
    TextView text_7;
    TextView text_8;
    TextView text_9;
    TextView text_10;
    TextView text_11;
    TextView text_12;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weekactivity);
        setTitle("달력");

        baseLayout_week = (LinearLayout) findViewById(R.id.baseLayout_week);
        button1_week = (Button) findViewById(R.id.button1_week);
        registerForContextMenu(button1_week);


        tvDate_week = (TextView) findViewById(R.id.tv_date_week);
        gridView_week = (GridView) findViewById(R.id.gridview_week);


        todaybutton_week = (Button) findViewById(R.id.todaybutton_week);
        last_week = (Button) findViewById(R.id.last_week);
        next_week = (Button) findViewById(R.id.next_week);
        weekviews_1 = (Button) findViewById(R.id.weekviews_1);
        weekviews_2 = (Button) findViewById(R.id.weekviews_2);
        weekviews_3 = (Button) findViewById(R.id.weekviews_3);
        weekviews_4 = (Button) findViewById(R.id.weekviews_4);
        weekviews_5 = (Button) findViewById(R.id.weekviews_5);
        weekviews_6 = (Button) findViewById(R.id.weekviews_6);
        weekviews_7 = (Button) findViewById(R.id.weekviews_7);


        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn10 = (Button) findViewById(R.id.btn10);
        btn11 = (Button) findViewById(R.id.btn11);
        btn12 = (Button) findViewById(R.id.btn12);



        text_1 = (TextView) findViewById(R.id.text_1);
        text_2 = (TextView) findViewById(R.id.text_2);
        text_3 = (TextView) findViewById(R.id.text_3);
        text_4 = (TextView) findViewById(R.id.text_4);
        text_5 = (TextView) findViewById(R.id.text_5);
        text_6 = (TextView) findViewById(R.id.text_6);
        text_7 = (TextView) findViewById(R.id.text_7);
        text_8 = (TextView) findViewById(R.id.text_8);
        text_9 = (TextView) findViewById(R.id.text_9);
        text_10 = (TextView) findViewById(R.id.text_10);
        text_11 = (TextView) findViewById(R.id.text_11);
        text_12 = (TextView) findViewById(R.id.text_12);


        // get Current Week of the year
        Calendar calendar = Calendar.getInstance();
        Log.v("Current Week", String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR)));
        int current_week = calendar.get(Calendar.WEEK_OF_YEAR);
        int week_start_day = calendar.getFirstDayOfWeek(); // this will get the starting day os week in integer format i-e 1 if monday
        //Toast.makeText(getContext(),"Current Week is"+current_week +"Start Day is"+week_start_day,Toast.LENGTH_SHORT).show();


        // get the starting and ending date
        // Set the calendar to sunday of the current week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        System.out.println("Current week = " + Calendar.DAY_OF_WEEK);

        // Print dates of the current week starting on Sunday
        DateFormat df = new SimpleDateFormat("d", Locale.getDefault());
        String startDate = "", midDate_1 = "", midDate_2 = "", midDate_3 = "", midDate_4 = "", midDate_5 = "", endDate = "";

        startDate = df.format(calendar.getTime());

        calendar.add(Calendar.DATE, 1);
        midDate_1 = df.format(calendar.getTime());

        calendar.add(Calendar.DATE, 1);
        midDate_2 = df.format(calendar.getTime());

        calendar.add(Calendar.DATE, 1);
        midDate_3 = df.format(calendar.getTime());

        calendar.add(Calendar.DATE, 1);
        midDate_4 = df.format(calendar.getTime());

        calendar.add(Calendar.DATE, 1);
        midDate_5 = df.format(calendar.getTime());


        calendar.add(Calendar.DATE, 1);
        endDate = df.format(calendar.getTime());

        weekviews_1.setText(startDate);
        weekviews_2.setText(midDate_1);
        weekviews_3.setText(midDate_2);
        weekviews_4.setText(midDate_3);
        weekviews_5.setText(midDate_4);
        weekviews_6.setText(midDate_5);
        weekviews_7.setText(endDate);


        // 오늘에 날짜를 세팅 해준다.
        long now = System.currentTimeMillis();
        final Date date = new Date(now);


        //int h = 30;

        //내일
        final Date today = new Date();
        //final Date nextmonth = new Date ( today.getTime ( ) + (long) ( 1000 * 60 * 60 * 24 * h) );


        TimeZone jst = TimeZone.getTimeZone("JST");
        final Calendar cal = Calendar.getInstance(jst); // 주어진 시간대에 맞게 현재 시각으로 초기화된 GregorianCalender 객체를 반환.// 또는 Calendar now = Calendar.getInstance(Locale.KOREA);
        //System.out.println ( cal.get ( Calendar.YEAR ) + "년 " + ( cal.get ( Calendar.MONTH ) + 1 ) + "월 " + cal.get ( Calendar.DATE ) + "일 " + cal.get ( Calendar.HOUR_OF_DAY ) + "시 " +cal.get ( Calendar.MINUTE ) + "분 " + cal.get ( Calendar.SECOND ) + "초 " );


        //final Calendar calendar = Calendar.getInstance(Locale.KOREAN);

        //연,월,일을 따로 저장
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM/yyyy", Locale.KOREAN);
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        //현재 날짜 텍스트뷰에 뿌려줌
        tvDate_week.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));


        ///////////////////////////////////////////////////////////////////////////
        //오늘로 이동
        todaybutton_week.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                week_num = 0;


                // get Current Week of the year
                Calendar calendar = Calendar.getInstance();
                Log.v("Current Week", String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR)));
                int current_week = calendar.get(Calendar.WEEK_OF_YEAR);
                int week_start_day = calendar.getFirstDayOfWeek(); // this will get the starting day os week in integer format i-e 1 if monday
                //Toast.makeText(getContext(),"Current Week is"+current_week +"Start Day is"+week_start_day,Toast.LENGTH_SHORT).show();


                // get the starting and ending date
                // Set the calendar to sunday of the current week
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                //System.out.println("Current week = " + Calendar.DAY_OF_WEEK);

                // Print dates of the current week starting on Sunday
                DateFormat df = new SimpleDateFormat("d", Locale.getDefault());
                String startDate = "", midDate_1 = "", midDate_2 = "", midDate_3 = "", midDate_4 = "", midDate_5 = "", endDate = "";

                startDate = df.format(calendar.getTime());

                calendar.add(Calendar.DATE, 1);
                midDate_1 = df.format(calendar.getTime());

                calendar.add(Calendar.DATE, 1);
                midDate_2 = df.format(calendar.getTime());

                calendar.add(Calendar.DATE, 1);
                midDate_3 = df.format(calendar.getTime());

                calendar.add(Calendar.DATE, 1);
                midDate_4 = df.format(calendar.getTime());

                calendar.add(Calendar.DATE, 1);
                midDate_5 = df.format(calendar.getTime());


                calendar.add(Calendar.DATE, 1);
                endDate = df.format(calendar.getTime());

                weekviews_1.setText(startDate);
                weekviews_2.setText(midDate_1);
                weekviews_3.setText(midDate_2);
                weekviews_4.setText(midDate_3);
                weekviews_5.setText(midDate_4);
                weekviews_6.setText(midDate_5);
                weekviews_7.setText(endDate);


                month_test = 1;

                // 오늘에 날짜를 세팅 해준다.
                long now = System.currentTimeMillis();
                final Date date = new Date(now);


                //int h = 30;

                //내일
                final Date today = new Date();
                //final Date nextmonth = new Date ( today.getTime ( ) + (long) ( 1000 * 60 * 60 * 24 * h) );


                TimeZone jst = TimeZone.getTimeZone("JST");
                final Calendar cal = Calendar.getInstance(jst); // 주어진 시간대에 맞게 현재 시각으로 초기화된 GregorianCalender 객체를 반환.// 또는 Calendar now = Calendar.getInstance(Locale.KOREA);
                //System.out.println ( cal.get ( Calendar.YEAR ) + "년 " + ( cal.get ( Calendar.MONTH ) + 1 ) + "월 " + cal.get ( Calendar.DATE ) + "일 " + cal.get ( Calendar.HOUR_OF_DAY ) + "시 " +cal.get ( Calendar.MINUTE ) + "분 " + cal.get ( Calendar.SECOND ) + "초 " );


                //final Calendar calendar = Calendar.getInstance(Locale.KOREAN);

                //연,월,일을 따로 저장
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM/yyyy", Locale.KOREAN);
                final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
                final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
                final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

                //현재 날짜 텍스트뷰에 뿌려줌
                tvDate_week.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));


            }
        });
        //////////////////////////////////////////////////////////////////////////////
        //저번주로 이동
        //7일 전 날짜 이용.

        last_week.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {


                // get Current Week of the year
                Calendar calendar = Calendar.getInstance();
                Log.v("Current Week", String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR)));
                int current_week = calendar.get(Calendar.WEEK_OF_YEAR);
                int week_start_day = calendar.getFirstDayOfWeek(); // this will get the starting day os week in integer format i-e 1 if monday
                //Toast.makeText(getContext(),"Current Week is"+current_week +"Start Day is"+week_start_day,Toast.LENGTH_SHORT).show();


                week_num = week_num - 7;

                // get the starting and ending date
                // Set the calendar to sunday of the current week
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                //System.out.println("Current week = " + Calendar.DAY_OF_WEEK);

                // Print dates of the current week starting on Sunday
                DateFormat df = new SimpleDateFormat("d", Locale.getDefault());
                String startDate = "", midDate_1 = "", midDate_2 = "", midDate_3 = "", midDate_4 = "", midDate_5 = "", endDate = "";


                calendar.add(Calendar.DATE, week_num);
                startDate = df.format(calendar.getTime());    //

                calendar.add(Calendar.DATE, 1);  //
                midDate_1 = df.format(calendar.getTime());

                calendar.add(Calendar.DATE, 1);  //
                midDate_2 = df.format(calendar.getTime());

                calendar.add(Calendar.DATE, 1);  //
                midDate_3 = df.format(calendar.getTime());

                calendar.add(Calendar.DATE, 1);  //
                midDate_4 = df.format(calendar.getTime());

                calendar.add(Calendar.DATE, 1);
                midDate_5 = df.format(calendar.getTime());    //


                calendar.add(Calendar.DATE, 1);  //
                endDate = df.format(calendar.getTime());

                weekviews_1.setText(startDate);
                weekviews_2.setText(midDate_1);
                weekviews_3.setText(midDate_2);
                weekviews_4.setText(midDate_3);
                weekviews_5.setText(midDate_4);
                weekviews_6.setText(midDate_5);
                weekviews_7.setText(endDate);


                //만약 현재의 날짜(달)과 이 내용상의 날짜(달)이 다르다면..
                //변수의 숫자를 하나 깎고, 그 숫자를 더하여 날짜를 한달 전으로 만든다.

/*
                mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);
                final int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
                //mCal = 이번 달 첫째주 요일 숫자.

                mCal_now.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, week_num);
                final int dayNum_now = mCal_now.get(Calendar.DAY_OF_WEEK);
                //mCal_now = 현재 화면에 보이는 숫자의 날짜(달)의 첫번째 요일 숫자.


 */


                int length1 = startDate.length();
                int length2 = endDate.length();

                if (length1 > length2) {
                    month_test--;

                    //초기화
                    tvDate_week.setText("");


                    //현재 날짜 텍스트뷰에 뿌려줌
                    tvDate_week.setText((cal.get(Calendar.YEAR)) + "/" + (cal.get(Calendar.MONTH) + month_test));


                }


            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //다음주로 이동

        next_week.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {


                // get Current Week of the year
                Calendar calendar = Calendar.getInstance();
                Log.v("Current Week", String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR)));
                int current_week = calendar.get(Calendar.WEEK_OF_YEAR);
                int week_start_day = calendar.getFirstDayOfWeek(); // this will get the starting day os week in integer format i-e 1 if monday
                //Toast.makeText(getContext(),"Current Week is"+current_week +"Start Day is"+week_start_day,Toast.LENGTH_SHORT).show();


                week_num = week_num + 7;

                // get the starting and ending date
                // Set the calendar to sunday of the current week
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                //System.out.println("Current week = " + Calendar.DAY_OF_WEEK);

                // Print dates of the current week starting on Sunday
                DateFormat df = new SimpleDateFormat("d", Locale.getDefault());
                String startDate = "", midDate_1 = "", midDate_2 = "", midDate_3 = "", midDate_4 = "", midDate_5 = "", endDate = "";

                calendar.add(Calendar.DATE, week_num);
                startDate = df.format(calendar.getTime());    //

                calendar.add(Calendar.DATE, 1);  //
                midDate_1 = df.format(calendar.getTime());

                calendar.add(Calendar.DATE, 1);  //
                midDate_2 = df.format(calendar.getTime());

                calendar.add(Calendar.DATE, 1);  //
                midDate_3 = df.format(calendar.getTime());

                calendar.add(Calendar.DATE, 1);  //
                midDate_4 = df.format(calendar.getTime());

                calendar.add(Calendar.DATE, 1);
                midDate_5 = df.format(calendar.getTime());    //


                calendar.add(Calendar.DATE, 1);  //
                endDate = df.format(calendar.getTime());

                weekviews_1.setText(startDate);
                weekviews_2.setText(midDate_1);
                weekviews_3.setText(midDate_2);
                weekviews_4.setText(midDate_3);
                weekviews_5.setText(midDate_4);
                weekviews_6.setText(midDate_5);
                weekviews_7.setText(endDate);

                int length1 = startDate.length();
                int length2 = endDate.length();

                if (length1 > length2) {
                    month_test++;

                    //초기화
                    tvDate_week.setText("");


                    //현재 날짜 텍스트뷰에 뿌려줌
                    tvDate_week.setText((cal.get(Calendar.YEAR)) + "/" + (cal.get(Calendar.MONTH) + month_test));


                }


            }
        });


        //(몇일인지)날짜 부분 클릭시 <첫번째 TextView부분임>
        //변수안에 값을 줌.
        //파일이름을 설정할 때 필요한 부분.
        weekviews_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TextView 클릭될 시 할 코드작성

                month_text = String.valueOf(weekviews_1.getText());





                //btn1부분
                //////////////////////////////////////////////////////////////////////////
                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));




                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);




                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);




                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);





                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_1.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_1.setText("");
                }


                //btn2 부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_2.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                            text_2.setText("");
                }


                //btn3부분
                //////////////////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_3.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                            text_3.setText("");
                }


                //btn4부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_4.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_4.setText("");
                }

                //btn5부분
                ////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_5.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_5.setText("");
                }


                //btn6부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_6.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_6.setText("");
                }

                //btn7부분
                ////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_7.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_7.setText("");
                }

                //btn8부분
                /////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_8.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_8.setText("");
                }

                //btn9부분
                //////////////////////////////////////////////////////////////////


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_9.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_9.setText("");
                }

                //btn10부분
                //////////////////////////////////////////////////////////////


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_10.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_10.setText("");
                }

                //btn11부분
                /////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_11.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_11.setText("");
                }

                //btn12부분
                /////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_12.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_12.setText("");
                }

            }
        });

        //(몇일인지)날짜 부분 클릭시 <첫번째 TextView부분임>
        //변수안에 값을 줌.
        //파일이름을 설정할 때 필요한 부분.
        weekviews_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TextView 클릭될 시 할 코드작성

                month_text = String.valueOf(weekviews_2.getText());


                //btn1부분
                //////////////////////////////////////////////////////////////////////////
                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));




                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);




                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);




                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);





                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_1.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_1.setText("");
                }


                //btn2 부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_2.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_2.setText("");
                }


                //btn3부분
                //////////////////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_3.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_3.setText("");
                }


                //btn4부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_4.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_4.setText("");
                }

                //btn5부분
                ////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_5.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_5.setText("");
                }


                //btn6부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_6.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_6.setText("");
                }

                //btn7부분
                ////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_7.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_7.setText("");
                }

                //btn8부분
                /////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_8.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_8.setText("");
                }

                //btn9부분
                //////////////////////////////////////////////////////////////////


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_9.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_9.setText("");
                }

                //btn10부분
                //////////////////////////////////////////////////////////////


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_10.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_10.setText("");
                }

                //btn11부분
                /////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_11.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_11.setText("");
                }

                //btn12부분
                /////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_12.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_12.setText("");
                }

            }
        });

        //(몇일인지)날짜 부분 클릭시 <첫번째 TextView부분임>
        //변수안에 값을 줌.
        //파일이름을 설정할 때 필요한 부분.
        weekviews_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TextView 클릭될 시 할 코드작성

                month_text = String.valueOf(weekviews_3.getText());


                //btn1부분
                //////////////////////////////////////////////////////////////////////////
                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));




                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);




                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);




                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);





                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_1.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_1.setText("");
                }


                //btn2 부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_2.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_2.setText("");
                }


                //btn3부분
                //////////////////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_3.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_3.setText("");
                }


                //btn4부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_4.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_4.setText("");
                }

                //btn5부분
                ////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_5.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_5.setText("");
                }


                //btn6부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_6.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_6.setText("");
                }

                //btn7부분
                ////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_7.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_7.setText("");
                }

                //btn8부분
                /////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_8.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_8.setText("");
                }

                //btn9부분
                //////////////////////////////////////////////////////////////////


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_9.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_9.setText("");
                }

                //btn10부분
                //////////////////////////////////////////////////////////////


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_10.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_10.setText("");
                }

                //btn11부분
                /////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_11.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_11.setText("");
                }

                //btn12부분
                /////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_12.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_12.setText("");
                }

            }
        });

        //(몇일인지)날짜 부분 클릭시 <첫번째 TextView부분임>
        //변수안에 값을 줌.
        //파일이름을 설정할 때 필요한 부분.
        weekviews_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TextView 클릭될 시 할 코드작성

                month_text = String.valueOf(weekviews_4.getText());

            }
        });

        //(몇일인지)날짜 부분 클릭시 <첫번째 TextView부분임>
        //변수안에 값을 줌.
        //파일이름을 설정할 때 필요한 부분.
        weekviews_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TextView 클릭될 시 할 코드작성

                month_text = String.valueOf(weekviews_5.getText());


                //btn1부분
                //////////////////////////////////////////////////////////////////////////
                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));




                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);




                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);




                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);





                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_1.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_1.setText("");
                }


                //btn2 부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_2.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_2.setText("");
                }


                //btn3부분
                //////////////////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_3.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_3.setText("");
                }


                //btn4부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_4.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_4.setText("");
                }

                //btn5부분
                ////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_5.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_5.setText("");
                }


                //btn6부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_6.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_6.setText("");
                }

                //btn7부분
                ////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_7.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_7.setText("");
                }

                //btn8부분
                /////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_8.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_8.setText("");
                }

                //btn9부분
                //////////////////////////////////////////////////////////////////


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_9.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_9.setText("");
                }

                //btn10부분
                //////////////////////////////////////////////////////////////


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_10.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_10.setText("");
                }

                //btn11부분
                /////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_11.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_11.setText("");
                }

                //btn12부분
                /////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_12.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_12.setText("");
                }

            }
        });

        //(몇일인지)날짜 부분 클릭시 <첫번째 TextView부분임>
        //변수안에 값을 줌.
        //파일이름을 설정할 때 필요한 부분.
        weekviews_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TextView 클릭될 시 할 코드작성

                month_text = String.valueOf(weekviews_6.getText());


                //btn1부분
                //////////////////////////////////////////////////////////////////////////
                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));




                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);




                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);




                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);





                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_1.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_1.setText("");
                }


                //btn2 부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_2.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_2.setText("");
                }


                //btn3부분
                //////////////////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_3.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_3.setText("");
                }


                //btn4부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_4.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_4.setText("");
                }

                //btn5부분
                ////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_5.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_5.setText("");
                }


                //btn6부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_6.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_6.setText("");
                }

                //btn7부분
                ////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_7.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_7.setText("");
                }

                //btn8부분
                /////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_8.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_8.setText("");
                }

                //btn9부분
                //////////////////////////////////////////////////////////////////


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_9.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_9.setText("");
                }

                //btn10부분
                //////////////////////////////////////////////////////////////


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_10.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_10.setText("");
                }

                //btn11부분
                /////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_11.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_11.setText("");
                }

                //btn12부분
                /////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_12.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_12.setText("");
                }

            }
        });

        //(몇일인지)날짜 부분 클릭시 <첫번째 TextView부분임>
        //변수안에 값을 줌.
        //파일이름을 설정할 때 필요한 부분.
        weekviews_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TextView 클릭될 시 할 코드작성

                month_text = String.valueOf(weekviews_7.getText());


                //btn1부분
                //////////////////////////////////////////////////////////////////////////
                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));




                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);




                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);




                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);





                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_1.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_1.setText("");
                }


                //btn2 부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_2.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_2.setText("");
                }


                //btn3부분
                //////////////////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_3.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_3.setText("");
                }


                //btn4부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_4.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_4.setText("");
                }

                //btn5부분
                ////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_5.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_5.setText("");
                }


                //btn6부분
                //////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_6.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_6.setText("");
                }

                //btn7부분
                ////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_7.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_7.setText("");
                }

                //btn8부분
                /////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_8.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_8.setText("");
                }

                //btn9부분
                //////////////////////////////////////////////////////////////////


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_9.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_9.setText("");
                }

                //btn10부분
                //////////////////////////////////////////////////////////////


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_10.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_10.setText("");
                }

                //btn11부분
                /////////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);



                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_11.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_11.setText("");
                }

                //btn12부분
                /////////////////////////////////////////////////////////////

                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);



                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_12.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

                    text_12.setText("");
                }

            }
        });









        ////////////////////////////////////////////////////////////////////////////////////////////////////
        //날짜 클릭시 (gridview) 대화상자 보이기 (dialog)
        btn1.setOnLongClickListener((new View.OnLongClickListener() {   //00~02시 정보

            @Override
            public boolean onLongClick(View v) {
                dialogView = (View) View.inflate(weekactivity.this,
                        R.layout.dialog_week, null);

                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));


                //제목
                final EditText dlogEdt1 = (EditText) dialogView.findViewById(R.id.dlgEdt1);

                //장소
                final EditText dlogEdt6 = (EditText) dialogView.findViewById(R.id.dlgEdt6);

                //메모
                final EditText dlogEdt7 = (EditText) dialogView.findViewById(R.id.dlgEdt7);


                AlertDialog.Builder dlg = new AlertDialog.Builder(weekactivity.this);
                dlg.setTitle("일정 입력");
                dlg.setView(dialogView);    //화면 바뀜

                dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    //입력 버튼을 누르면 처리하는 부분 (파일처리 해야함)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //dialog에서 읽어와서 파일에 넣어주는 부분.


                        //만약 새로 띄워진 dialog화면이 null값이라면..


                        //이제 날짜를 받아와서 파일 제목에 써주는 역할을 해야함.
                        //"x(월)" + "_" + "y(일)" + ".txt"
                        //만약 6월 30일에 글을 쓰면 파일 이름은 => "6_30_1.txt" 이렇게 뜬다.   <= 파일 제목의 경우
                        try {
                            //제목
                            String value = dlogEdt1.getText().toString();

                            //장소
                            String value6 = dlogEdt6.getText().toString();
                            //메모
                            String value7 = dlogEdt7.getText().toString();


                            //제목
                            FileOutputStream outFs = openFileOutput(file_month + "_" + month_text + "_" + "0002" + "_" + "1" + ".txt",
                                    Context.MODE_PRIVATE);
                            //윗 부분에서 "file.txt"부분이 파일 제목인데,
                            //이 부분을 파일 제목에 써줘야함.


                            //장소
                            FileOutputStream outFs6 = openFileOutput(file_month + "_" + month_text + "_" + "0002" + "_" + "2" + ".txt",
                                    Context.MODE_PRIVATE);


                            //메모
                            FileOutputStream outFs7 = openFileOutput(file_month + "_" + month_text + "_" + "0002" + "_" + "3" + ".txt",
                                    Context.MODE_PRIVATE);


                            //제목
                            outFs.write(value.getBytes());
                            outFs.close();

                            //장소
                            outFs6.write(value6.getBytes());
                            outFs6.close();
                            //메모
                            outFs7.write(value7.getBytes());
                            outFs7.close();



                            Toast.makeText(getApplicationContext(), "글이 작성됨",
                                    Toast.LENGTH_SHORT).show();

                            if (outFs != null || outFs6 != null || outFs7 != null){    //하나라도 null이 아니라면?
                                //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                                text_1.setText("등록된 일정이 있습니다.");

                            }
                        } catch (IOException e) {
                            text_1.setText("");
                        }


                    }


                });
                dlg.setNegativeButton("뒤로 가기", null);


                //삭제하는 부분
                dlg.setNeutralButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        //이 밑 부분은 파일을 읽어오는 부분이다.
                        //파일을 삭제해야 한다.

                        //제목
                        boolean inFs = deleteFile(file_month + "_" + month_text + "_" + "0002" + "_" + "1" + ".txt");

                                /*
                                byte[] txt = new byte[30];
                                inFs.read(txt);
                                String str = new String(txt);


                                dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                                inFs.close();




                                 */


                        //장소
                        boolean inFs6 = deleteFile(file_month + "_" + month_text + "_" + "0002" + "_" + "2" + ".txt");
                                /*
                                byte[] txt6 = new byte[30];
                                inFs6.read(txt6);
                                String str6 = new String(txt6);



                                dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs6.close();


                                 */


                        //메모
                        boolean inFs7 = deleteFile(file_month + "_" + month_text + "_" + "0002" + "_" + "3" + ".txt");
                                /*
                                byte[] txt7 = new byte[30];
                                inFs7.read(txt7);
                                String str7 = new String(txt7);



                                dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs7.close();


                                 */


                    }


                });
                dlg.show();


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);
                    String str = new String(txt);


                    dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                    inFs.close();


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);
                    String str6 = new String(txt6);


                    dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs6.close();


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0002" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);
                    String str7 = new String(txt7);


                    dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs7.close();


                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_1.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.
                    Toast.makeText(getApplicationContext(), "내용 없음",
                            Toast.LENGTH_SHORT).show();
                    text_1.setText("");
                }



                return false;
            }


        }));

        //날짜 클릭시 (gridview) 대화상자 보이기 (dialog)
        btn2.setOnLongClickListener((new View.OnLongClickListener() {   //02~04시 정보

            @Override
            public boolean onLongClick(View v) {
                dialogView = (View) View.inflate(weekactivity.this,
                        R.layout.dialog_week, null);

                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));


                //제목
                final EditText dlogEdt1 = (EditText) dialogView.findViewById(R.id.dlgEdt1);

                //장소
                final EditText dlogEdt6 = (EditText) dialogView.findViewById(R.id.dlgEdt6);

                //메모
                final EditText dlogEdt7 = (EditText) dialogView.findViewById(R.id.dlgEdt7);


                AlertDialog.Builder dlg = new AlertDialog.Builder(weekactivity.this);
                dlg.setTitle("일정 입력");
                dlg.setView(dialogView);    //화면 바뀜

                dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    //입력 버튼을 누르면 처리하는 부분 (파일처리 해야함)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //dialog에서 읽어와서 파일에 넣어주는 부분.


                        //만약 새로 띄워진 dialog화면이 null값이라면..


                        //이제 날짜를 받아와서 파일 제목에 써주는 역할을 해야함.
                        //"x(월)" + "_" + "y(일)" + ".txt"
                        //만약 6월 30일에 글을 쓰면 파일 이름은 => "6_30_1.txt" 이렇게 뜬다.   <= 파일 제목의 경우
                        try {
                            //제목
                            String value = dlogEdt1.getText().toString();

                            //장소
                            String value6 = dlogEdt6.getText().toString();
                            //메모
                            String value7 = dlogEdt7.getText().toString();


                            //제목
                            FileOutputStream outFs = openFileOutput(file_month + "_" + month_text + "_" + "0204" + "_" + "1" + ".txt",
                                    Context.MODE_PRIVATE);
                            //윗 부분에서 "file.txt"부분이 파일 제목인데,
                            //이 부분을 파일 제목에 써줘야함.


                            //장소
                            FileOutputStream outFs6 = openFileOutput(file_month + "_" + month_text + "_" + "0204" + "_" + "2" + ".txt",
                                    Context.MODE_PRIVATE);


                            //메모
                            FileOutputStream outFs7 = openFileOutput(file_month + "_" + month_text + "_" + "0204" + "_" + "3" + ".txt",
                                    Context.MODE_PRIVATE);


                            //제목
                            outFs.write(value.getBytes());
                            outFs.close();

                            //장소
                            outFs6.write(value6.getBytes());
                            outFs6.close();
                            //메모
                            outFs7.write(value7.getBytes());
                            outFs7.close();


                            Toast.makeText(getApplicationContext(), "글이 작성됨",
                                    Toast.LENGTH_SHORT).show();

                            if (outFs != null || outFs6 != null || outFs7 != null){    //하나라도 null이 아니라면?
                                //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                                text_2.setText("등록된 일정이 있습니다.");

                            }
                        } catch (IOException e) {
                            text_2.setText("");
                        }


                    }


                });
                dlg.setNegativeButton("뒤로 가기", null);


                //삭제하는 부분
                dlg.setNeutralButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        //이 밑 부분은 파일을 읽어오는 부분이다.
                        //파일을 삭제해야 한다.

                        //제목
                        boolean inFs = deleteFile(file_month + "_" + month_text + "_" + "0204" + "_" + "1" + ".txt");

                                /*
                                byte[] txt = new byte[30];
                                inFs.read(txt);
                                String str = new String(txt);


                                dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                                inFs.close();




                                 */


                        //장소
                        boolean inFs6 = deleteFile(file_month + "_" + month_text + "_" + "0204" + "_" + "2" + ".txt");
                                /*
                                byte[] txt6 = new byte[30];
                                inFs6.read(txt6);
                                String str6 = new String(txt6);



                                dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs6.close();


                                 */


                        //메모
                        boolean inFs7 = deleteFile(file_month + "_" + month_text + "_" + "0204" + "_" + "3" + ".txt");
                                /*
                                byte[] txt7 = new byte[30];
                                inFs7.read(txt7);
                                String str7 = new String(txt7);



                                dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs7.close();


                                 */


                    }


                });
                dlg.show();


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);
                    String str = new String(txt);


                    dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                    inFs.close();


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);
                    String str6 = new String(txt6);


                    dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs6.close();


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0204" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);
                    String str7 = new String(txt7);


                    dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs7.close();

                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_2.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.
                    Toast.makeText(getApplicationContext(), "내용 없음",
                            Toast.LENGTH_SHORT).show();
                    text_2.setText("");
                }

                return false;
            }


        }));

        //날짜 클릭시 (gridview) 대화상자 보이기 (dialog)
        btn3.setOnLongClickListener((new View.OnLongClickListener() {   //04~06시 정보

            @Override
            public boolean onLongClick(View v) {
                dialogView = (View) View.inflate(weekactivity.this,
                        R.layout.dialog_week, null);

                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));


                //제목
                final EditText dlogEdt1 = (EditText) dialogView.findViewById(R.id.dlgEdt1);

                //장소
                final EditText dlogEdt6 = (EditText) dialogView.findViewById(R.id.dlgEdt6);

                //메모
                final EditText dlogEdt7 = (EditText) dialogView.findViewById(R.id.dlgEdt7);


                AlertDialog.Builder dlg = new AlertDialog.Builder(weekactivity.this);
                dlg.setTitle("일정 입력");
                dlg.setView(dialogView);    //화면 바뀜

                dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    //입력 버튼을 누르면 처리하는 부분 (파일처리 해야함)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //dialog에서 읽어와서 파일에 넣어주는 부분.


                        //만약 새로 띄워진 dialog화면이 null값이라면..


                        //이제 날짜를 받아와서 파일 제목에 써주는 역할을 해야함.
                        //"x(월)" + "_" + "y(일)" + ".txt"
                        //만약 6월 30일에 글을 쓰면 파일 이름은 => "6_30_1.txt" 이렇게 뜬다.   <= 파일 제목의 경우
                        try {
                            //제목
                            String value = dlogEdt1.getText().toString();

                            //장소
                            String value6 = dlogEdt6.getText().toString();
                            //메모
                            String value7 = dlogEdt7.getText().toString();


                            //제목
                            FileOutputStream outFs = openFileOutput(file_month + "_" + month_text + "_" + "0406" + "_" + "1" + ".txt",
                                    Context.MODE_PRIVATE);
                            //윗 부분에서 "file.txt"부분이 파일 제목인데,
                            //이 부분을 파일 제목에 써줘야함.


                            //장소
                            FileOutputStream outFs6 = openFileOutput(file_month + "_" + month_text + "_" + "0406" + "_" + "2" + ".txt",
                                    Context.MODE_PRIVATE);


                            //메모
                            FileOutputStream outFs7 = openFileOutput(file_month + "_" + month_text + "_" + "0406" + "_" + "3" + ".txt",
                                    Context.MODE_PRIVATE);


                            //제목
                            outFs.write(value.getBytes());
                            outFs.close();

                            //장소
                            outFs6.write(value6.getBytes());
                            outFs6.close();
                            //메모
                            outFs7.write(value7.getBytes());
                            outFs7.close();


                            Toast.makeText(getApplicationContext(), "글이 작성됨",
                                    Toast.LENGTH_SHORT).show();

                            if (outFs != null || outFs6 != null || outFs7 != null){    //하나라도 null이 아니라면?
                                //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                                text_3.setText("등록된 일정이 있습니다.");

                            }
                        } catch (IOException e) {
                            text_3.setText("");
                        }


                    }


                });
                dlg.setNegativeButton("뒤로 가기", null);


                //삭제하는 부분
                dlg.setNeutralButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        //이 밑 부분은 파일을 읽어오는 부분이다.
                        //파일을 삭제해야 한다.

                        //제목
                        boolean inFs = deleteFile(file_month + "_" + month_text + "_" + "0406" + "_" + "1" + ".txt");

                                /*
                                byte[] txt = new byte[30];
                                inFs.read(txt);
                                String str = new String(txt);


                                dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                                inFs.close();




                                 */


                        //장소
                        boolean inFs6 = deleteFile(file_month + "_" + month_text + "_" + "0406" + "_" + "2" + ".txt");
                                /*
                                byte[] txt6 = new byte[30];
                                inFs6.read(txt6);
                                String str6 = new String(txt6);



                                dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs6.close();


                                 */


                        //메모
                        boolean inFs7 = deleteFile(file_month + "_" + month_text + "_" + "0406" + "_" + "3" + ".txt");
                                /*
                                byte[] txt7 = new byte[30];
                                inFs7.read(txt7);
                                String str7 = new String(txt7);



                                dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs7.close();


                                 */


                    }


                });
                dlg.show();


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);
                    String str = new String(txt);


                    dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                    inFs.close();


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);
                    String str6 = new String(txt6);


                    dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs6.close();


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0406" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);
                    String str7 = new String(txt7);


                    dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs7.close();

                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_3.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.
                    Toast.makeText(getApplicationContext(), "내용 없음",
                            Toast.LENGTH_SHORT).show();
                    text_3.setText("");
                }

                return false;
            }


        }));


        //날짜 클릭시 (gridview) 대화상자 보이기 (dialog)
        btn4.setOnLongClickListener((new View.OnLongClickListener() {   //06~08시 정보

            @Override
            public boolean onLongClick(View v) {
                dialogView = (View) View.inflate(weekactivity.this,
                        R.layout.dialog_week, null);

                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));


                //제목
                final EditText dlogEdt1 = (EditText) dialogView.findViewById(R.id.dlgEdt1);

                //장소
                final EditText dlogEdt6 = (EditText) dialogView.findViewById(R.id.dlgEdt6);

                //메모
                final EditText dlogEdt7 = (EditText) dialogView.findViewById(R.id.dlgEdt7);


                AlertDialog.Builder dlg = new AlertDialog.Builder(weekactivity.this);
                dlg.setTitle("일정 입력");
                dlg.setView(dialogView);    //화면 바뀜

                dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    //입력 버튼을 누르면 처리하는 부분 (파일처리 해야함)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //dialog에서 읽어와서 파일에 넣어주는 부분.


                        //만약 새로 띄워진 dialog화면이 null값이라면..


                        //이제 날짜를 받아와서 파일 제목에 써주는 역할을 해야함.
                        //"x(월)" + "_" + "y(일)" + ".txt"
                        //만약 6월 30일에 글을 쓰면 파일 이름은 => "6_30_1.txt" 이렇게 뜬다.   <= 파일 제목의 경우
                        try {
                            //제목
                            String value = dlogEdt1.getText().toString();

                            //장소
                            String value6 = dlogEdt6.getText().toString();
                            //메모
                            String value7 = dlogEdt7.getText().toString();


                            //제목
                            FileOutputStream outFs = openFileOutput(file_month + "_" + month_text + "_" + "0608" + "_" + "1" + ".txt",
                                    Context.MODE_PRIVATE);
                            //윗 부분에서 "file.txt"부분이 파일 제목인데,
                            //이 부분을 파일 제목에 써줘야함.


                            //장소
                            FileOutputStream outFs6 = openFileOutput(file_month + "_" + month_text + "_" + "0608" + "_" + "2" + ".txt",
                                    Context.MODE_PRIVATE);


                            //메모
                            FileOutputStream outFs7 = openFileOutput(file_month + "_" + month_text + "_" + "0608" + "_" + "3" + ".txt",
                                    Context.MODE_PRIVATE);


                            //제목
                            outFs.write(value.getBytes());
                            outFs.close();

                            //장소
                            outFs6.write(value6.getBytes());
                            outFs6.close();
                            //메모
                            outFs7.write(value7.getBytes());
                            outFs7.close();


                            Toast.makeText(getApplicationContext(), "글이 작성됨",
                                    Toast.LENGTH_SHORT).show();

                            if (outFs != null || outFs6 != null || outFs7 != null){    //하나라도 null이 아니라면?
                                //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                                text_4.setText("등록된 일정이 있습니다.");

                            }
                        } catch (IOException e) {
                            text_4.setText("");
                        }


                    }


                });
                dlg.setNegativeButton("뒤로 가기", null);


                //삭제하는 부분
                dlg.setNeutralButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        //이 밑 부분은 파일을 읽어오는 부분이다.
                        //파일을 삭제해야 한다.

                        //제목
                        boolean inFs = deleteFile(file_month + "_" + month_text + "_" + "0608" + "_" + "1" + ".txt");

                                /*
                                byte[] txt = new byte[30];
                                inFs.read(txt);
                                String str = new String(txt);


                                dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                                inFs.close();




                                 */


                        //장소
                        boolean inFs6 = deleteFile(file_month + "_" + month_text + "_" + "0608" + "_" + "2" + ".txt");
                                /*
                                byte[] txt6 = new byte[30];
                                inFs6.read(txt6);
                                String str6 = new String(txt6);



                                dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs6.close();


                                 */


                        //메모
                        boolean inFs7 = deleteFile(file_month + "_" + month_text + "_" + "0608" + "_" + "3" + ".txt");
                                /*
                                byte[] txt7 = new byte[30];
                                inFs7.read(txt7);
                                String str7 = new String(txt7);



                                dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs7.close();


                                 */


                    }


                });
                dlg.show();


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);
                    String str = new String(txt);


                    dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                    inFs.close();


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);
                    String str6 = new String(txt6);


                    dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs6.close();


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0608" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);
                    String str7 = new String(txt7);


                    dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs7.close();

                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_4.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.
                    Toast.makeText(getApplicationContext(), "내용 없음",
                            Toast.LENGTH_SHORT).show();
                    text_4.setText("");
                }

                return false;
            }


        }));

        //날짜 클릭시 (gridview) 대화상자 보이기 (dialog)
        btn5.setOnLongClickListener((new View.OnLongClickListener() {   //08~10시 정보

            @Override
            public boolean onLongClick(View v) {
                dialogView = (View) View.inflate(weekactivity.this,
                        R.layout.dialog_week, null);

                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));


                //제목
                final EditText dlogEdt1 = (EditText) dialogView.findViewById(R.id.dlgEdt1);

                //장소
                final EditText dlogEdt6 = (EditText) dialogView.findViewById(R.id.dlgEdt6);

                //메모
                final EditText dlogEdt7 = (EditText) dialogView.findViewById(R.id.dlgEdt7);


                AlertDialog.Builder dlg = new AlertDialog.Builder(weekactivity.this);
                dlg.setTitle("일정 입력");
                dlg.setView(dialogView);    //화면 바뀜

                dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    //입력 버튼을 누르면 처리하는 부분 (파일처리 해야함)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //dialog에서 읽어와서 파일에 넣어주는 부분.


                        //만약 새로 띄워진 dialog화면이 null값이라면..


                        //이제 날짜를 받아와서 파일 제목에 써주는 역할을 해야함.
                        //"x(월)" + "_" + "y(일)" + ".txt"
                        //만약 6월 30일에 글을 쓰면 파일 이름은 => "6_30_1.txt" 이렇게 뜬다.   <= 파일 제목의 경우
                        try {
                            //제목
                            String value = dlogEdt1.getText().toString();

                            //장소
                            String value6 = dlogEdt6.getText().toString();
                            //메모
                            String value7 = dlogEdt7.getText().toString();


                            //제목
                            FileOutputStream outFs = openFileOutput(file_month + "_" + month_text + "_" + "0810" + "_" + "1" + ".txt",
                                    Context.MODE_PRIVATE);
                            //윗 부분에서 "file.txt"부분이 파일 제목인데,
                            //이 부분을 파일 제목에 써줘야함.


                            //장소
                            FileOutputStream outFs6 = openFileOutput(file_month + "_" + month_text + "_" + "0810" + "_" + "2" + ".txt",
                                    Context.MODE_PRIVATE);


                            //메모
                            FileOutputStream outFs7 = openFileOutput(file_month + "_" + month_text + "_" + "0810" + "_" + "3" + ".txt",
                                    Context.MODE_PRIVATE);


                            //제목
                            outFs.write(value.getBytes());
                            outFs.close();

                            //장소
                            outFs6.write(value6.getBytes());
                            outFs6.close();
                            //메모
                            outFs7.write(value7.getBytes());
                            outFs7.close();


                            Toast.makeText(getApplicationContext(), "글이 작성됨",
                                    Toast.LENGTH_SHORT).show();

                            if (outFs != null || outFs6 != null || outFs7 != null){    //하나라도 null이 아니라면?
                                //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                                text_5.setText("등록된 일정이 있습니다.");

                            }
                        } catch (IOException e) {
                            text_5.setText("");
                        }


                    }


                });
                dlg.setNegativeButton("뒤로 가기", null);


                //삭제하는 부분
                dlg.setNeutralButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        //이 밑 부분은 파일을 읽어오는 부분이다.
                        //파일을 삭제해야 한다.

                        //제목
                        boolean inFs = deleteFile(file_month + "_" + month_text + "_" + "0810" + "_" + "1" + ".txt");

                                /*
                                byte[] txt = new byte[30];
                                inFs.read(txt);
                                String str = new String(txt);


                                dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                                inFs.close();




                                 */


                        //장소
                        boolean inFs6 = deleteFile(file_month + "_" + month_text + "_" + "0810" + "_" + "2" + ".txt");
                                /*
                                byte[] txt6 = new byte[30];
                                inFs6.read(txt6);
                                String str6 = new String(txt6);



                                dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs6.close();


                                 */


                        //메모
                        boolean inFs7 = deleteFile(file_month + "_" + month_text + "_" + "0810" + "_" + "3" + ".txt");
                                /*
                                byte[] txt7 = new byte[30];
                                inFs7.read(txt7);
                                String str7 = new String(txt7);



                                dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs7.close();


                                 */


                    }


                });
                dlg.show();


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);
                    String str = new String(txt);


                    dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                    inFs.close();


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);
                    String str6 = new String(txt6);


                    dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs6.close();


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "0810" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);
                    String str7 = new String(txt7);


                    dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs7.close();

                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_5.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.
                    Toast.makeText(getApplicationContext(), "내용 없음",
                            Toast.LENGTH_SHORT).show();
                    text_5.setText("");
                }

                return false;
            }


        }));


        //날짜 클릭시 (gridview) 대화상자 보이기 (dialog)
        btn6.setOnLongClickListener((new View.OnLongClickListener() {   //10~12시 정보

            @Override
            public boolean onLongClick(View v) {
                dialogView = (View) View.inflate(weekactivity.this,
                        R.layout.dialog_week, null);

                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));


                //제목
                final EditText dlogEdt1 = (EditText) dialogView.findViewById(R.id.dlgEdt1);

                //장소
                final EditText dlogEdt6 = (EditText) dialogView.findViewById(R.id.dlgEdt6);

                //메모
                final EditText dlogEdt7 = (EditText) dialogView.findViewById(R.id.dlgEdt7);


                AlertDialog.Builder dlg = new AlertDialog.Builder(weekactivity.this);
                dlg.setTitle("일정 입력");
                dlg.setView(dialogView);    //화면 바뀜

                dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    //입력 버튼을 누르면 처리하는 부분 (파일처리 해야함)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //dialog에서 읽어와서 파일에 넣어주는 부분.


                        //만약 새로 띄워진 dialog화면이 null값이라면..


                        //이제 날짜를 받아와서 파일 제목에 써주는 역할을 해야함.
                        //"x(월)" + "_" + "y(일)" + ".txt"
                        //만약 6월 30일에 글을 쓰면 파일 이름은 => "6_30_1.txt" 이렇게 뜬다.   <= 파일 제목의 경우
                        try {
                            //제목
                            String value = dlogEdt1.getText().toString();

                            //장소
                            String value6 = dlogEdt6.getText().toString();
                            //메모
                            String value7 = dlogEdt7.getText().toString();


                            //제목
                            FileOutputStream outFs = openFileOutput(file_month + "_" + month_text + "_" + "1012" + "_" + "1" + ".txt",
                                    Context.MODE_PRIVATE);
                            //윗 부분에서 "file.txt"부분이 파일 제목인데,
                            //이 부분을 파일 제목에 써줘야함.


                            //장소
                            FileOutputStream outFs6 = openFileOutput(file_month + "_" + month_text + "_" + "1012" + "_" + "2" + ".txt",
                                    Context.MODE_PRIVATE);


                            //메모
                            FileOutputStream outFs7 = openFileOutput(file_month + "_" + month_text + "_" + "1012" + "_" + "3" + ".txt",
                                    Context.MODE_PRIVATE);


                            //제목
                            outFs.write(value.getBytes());
                            outFs.close();

                            //장소
                            outFs6.write(value6.getBytes());
                            outFs6.close();
                            //메모
                            outFs7.write(value7.getBytes());
                            outFs7.close();


                            Toast.makeText(getApplicationContext(), "글이 작성됨",
                                    Toast.LENGTH_SHORT).show();

                            if (outFs != null || outFs6 != null || outFs7 != null){    //하나라도 null이 아니라면?
                                //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                                text_6.setText("등록된 일정이 있습니다.");

                            }
                        } catch (IOException e) {
                            text_6.setText("");
                        }


                    }


                });
                dlg.setNegativeButton("뒤로 가기", null);


                //삭제하는 부분
                dlg.setNeutralButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        //이 밑 부분은 파일을 읽어오는 부분이다.
                        //파일을 삭제해야 한다.

                        //제목
                        boolean inFs = deleteFile(file_month + "_" + month_text + "_" + "1012" + "_" + "1" + ".txt");

                                /*
                                byte[] txt = new byte[30];
                                inFs.read(txt);
                                String str = new String(txt);


                                dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                                inFs.close();




                                 */


                        //장소
                        boolean inFs6 = deleteFile(file_month + "_" + month_text + "_" + "1012" + "_" + "2" + ".txt");
                                /*
                                byte[] txt6 = new byte[30];
                                inFs6.read(txt6);
                                String str6 = new String(txt6);



                                dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs6.close();


                                 */


                        //메모
                        boolean inFs7 = deleteFile(file_month + "_" + month_text + "_" + "1012" + "_" + "3" + ".txt");
                                /*
                                byte[] txt7 = new byte[30];
                                inFs7.read(txt7);
                                String str7 = new String(txt7);



                                dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs7.close();


                                 */


                    }


                });
                dlg.show();


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);
                    String str = new String(txt);


                    dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                    inFs.close();


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);
                    String str6 = new String(txt6);


                    dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs6.close();


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1012" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);
                    String str7 = new String(txt7);


                    dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs7.close();

                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_6.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.
                    Toast.makeText(getApplicationContext(), "내용 없음",
                            Toast.LENGTH_SHORT).show();
                    text_6.setText("");
                }

                return false;
            }


        }));


        //날짜 클릭시 (gridview) 대화상자 보이기 (dialog)
        btn7.setOnLongClickListener((new View.OnLongClickListener() {   //12~14시 정보

            @Override
            public boolean onLongClick(View v) {
                dialogView = (View) View.inflate(weekactivity.this,
                        R.layout.dialog_week, null);

                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));


                //제목
                final EditText dlogEdt1 = (EditText) dialogView.findViewById(R.id.dlgEdt1);

                //장소
                final EditText dlogEdt6 = (EditText) dialogView.findViewById(R.id.dlgEdt6);

                //메모
                final EditText dlogEdt7 = (EditText) dialogView.findViewById(R.id.dlgEdt7);


                AlertDialog.Builder dlg = new AlertDialog.Builder(weekactivity.this);
                dlg.setTitle("일정 입력");
                dlg.setView(dialogView);    //화면 바뀜

                dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    //입력 버튼을 누르면 처리하는 부분 (파일처리 해야함)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //dialog에서 읽어와서 파일에 넣어주는 부분.


                        //만약 새로 띄워진 dialog화면이 null값이라면..


                        //이제 날짜를 받아와서 파일 제목에 써주는 역할을 해야함.
                        //"x(월)" + "_" + "y(일)" + ".txt"
                        //만약 6월 30일에 글을 쓰면 파일 이름은 => "6_30_1.txt" 이렇게 뜬다.   <= 파일 제목의 경우
                        try {
                            //제목
                            String value = dlogEdt1.getText().toString();

                            //장소
                            String value6 = dlogEdt6.getText().toString();
                            //메모
                            String value7 = dlogEdt7.getText().toString();


                            //제목
                            FileOutputStream outFs = openFileOutput(file_month + "_" + month_text + "_" + "1214" + "_" + "1" + ".txt",
                                    Context.MODE_PRIVATE);
                            //윗 부분에서 "file.txt"부분이 파일 제목인데,
                            //이 부분을 파일 제목에 써줘야함.


                            //장소
                            FileOutputStream outFs6 = openFileOutput(file_month + "_" + month_text + "_" + "1214" + "_" + "2" + ".txt",
                                    Context.MODE_PRIVATE);


                            //메모
                            FileOutputStream outFs7 = openFileOutput(file_month + "_" + month_text + "_" + "1214" + "_" + "3" + ".txt",
                                    Context.MODE_PRIVATE);


                            //제목
                            outFs.write(value.getBytes());
                            outFs.close();

                            //장소
                            outFs6.write(value6.getBytes());
                            outFs6.close();
                            //메모
                            outFs7.write(value7.getBytes());
                            outFs7.close();


                            Toast.makeText(getApplicationContext(), "글이 작성됨",
                                    Toast.LENGTH_SHORT).show();

                            if (outFs != null || outFs6 != null || outFs7 != null){    //하나라도 null이 아니라면?
                                //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                                text_7.setText("등록된 일정이 있습니다.");

                            }
                        } catch (IOException e) {
                            text_7.setText("");
                        }


                    }


                });
                dlg.setNegativeButton("뒤로 가기", null);


                //삭제하는 부분
                dlg.setNeutralButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        //이 밑 부분은 파일을 읽어오는 부분이다.
                        //파일을 삭제해야 한다.

                        //제목
                        boolean inFs = deleteFile(file_month + "_" + month_text + "_" + "1214" + "_" + "1" + ".txt");

                                /*
                                byte[] txt = new byte[30];
                                inFs.read(txt);
                                String str = new String(txt);


                                dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                                inFs.close();




                                 */


                        //장소
                        boolean inFs6 = deleteFile(file_month + "_" + month_text + "_" + "1214" + "_" + "2" + ".txt");
                                /*
                                byte[] txt6 = new byte[30];
                                inFs6.read(txt6);
                                String str6 = new String(txt6);



                                dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs6.close();


                                 */


                        //메모
                        boolean inFs7 = deleteFile(file_month + "_" + month_text + "_" + "1214" + "_" + "3" + ".txt");
                                /*
                                byte[] txt7 = new byte[30];
                                inFs7.read(txt7);
                                String str7 = new String(txt7);



                                dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs7.close();


                                 */


                    }


                });
                dlg.show();


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);
                    String str = new String(txt);


                    dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                    inFs.close();


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);
                    String str6 = new String(txt6);


                    dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs6.close();


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1214" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);
                    String str7 = new String(txt7);


                    dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs7.close();

                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_7.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.
                    Toast.makeText(getApplicationContext(), "내용 없음",
                            Toast.LENGTH_SHORT).show();
                    text_7.setText("");
                }

                return false;
            }


        }));

        //날짜 클릭시 (gridview) 대화상자 보이기 (dialog)
        btn8.setOnLongClickListener((new View.OnLongClickListener() {   //14~16시 정보

            @Override
            public boolean onLongClick(View v) {
                dialogView = (View) View.inflate(weekactivity.this,
                        R.layout.dialog_week, null);

                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));


                //제목
                final EditText dlogEdt1 = (EditText) dialogView.findViewById(R.id.dlgEdt1);

                //장소
                final EditText dlogEdt6 = (EditText) dialogView.findViewById(R.id.dlgEdt6);

                //메모
                final EditText dlogEdt7 = (EditText) dialogView.findViewById(R.id.dlgEdt7);


                AlertDialog.Builder dlg = new AlertDialog.Builder(weekactivity.this);
                dlg.setTitle("일정 입력");
                dlg.setView(dialogView);    //화면 바뀜

                dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    //입력 버튼을 누르면 처리하는 부분 (파일처리 해야함)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //dialog에서 읽어와서 파일에 넣어주는 부분.


                        //만약 새로 띄워진 dialog화면이 null값이라면..


                        //이제 날짜를 받아와서 파일 제목에 써주는 역할을 해야함.
                        //"x(월)" + "_" + "y(일)" + ".txt"
                        //만약 6월 30일에 글을 쓰면 파일 이름은 => "6_30_1.txt" 이렇게 뜬다.   <= 파일 제목의 경우
                        try {
                            //제목
                            String value = dlogEdt1.getText().toString();

                            //장소
                            String value6 = dlogEdt6.getText().toString();
                            //메모
                            String value7 = dlogEdt7.getText().toString();


                            //제목
                            FileOutputStream outFs = openFileOutput(file_month + "_" + month_text + "_" + "1416" + "_" + "1" + ".txt",
                                    Context.MODE_PRIVATE);
                            //윗 부분에서 "file.txt"부분이 파일 제목인데,
                            //이 부분을 파일 제목에 써줘야함.


                            //장소
                            FileOutputStream outFs6 = openFileOutput(file_month + "_" + month_text + "_" + "1416" + "_" + "2" + ".txt",
                                    Context.MODE_PRIVATE);


                            //메모
                            FileOutputStream outFs7 = openFileOutput(file_month + "_" + month_text + "_" + "1416" + "_" + "3" + ".txt",
                                    Context.MODE_PRIVATE);


                            //제목
                            outFs.write(value.getBytes());
                            outFs.close();

                            //장소
                            outFs6.write(value6.getBytes());
                            outFs6.close();
                            //메모
                            outFs7.write(value7.getBytes());
                            outFs7.close();


                            Toast.makeText(getApplicationContext(), "글이 작성됨",
                                    Toast.LENGTH_SHORT).show();

                            if (outFs != null || outFs6 != null || outFs7 != null){    //하나라도 null이 아니라면?
                                //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                                text_8.setText("등록된 일정이 있습니다.");

                            }
                        } catch (IOException e) {
                            text_8.setText("");
                        }


                    }


                });
                dlg.setNegativeButton("뒤로 가기", null);


                //삭제하는 부분
                dlg.setNeutralButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        //이 밑 부분은 파일을 읽어오는 부분이다.
                        //파일을 삭제해야 한다.

                        //제목
                        boolean inFs = deleteFile(file_month + "_" + month_text + "_" + "1416" + "_" + "1" + ".txt");

                                /*
                                byte[] txt = new byte[30];
                                inFs.read(txt);
                                String str = new String(txt);


                                dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                                inFs.close();




                                 */


                        //장소
                        boolean inFs6 = deleteFile(file_month + "_" + month_text + "_" + "1416" + "_" + "2" + ".txt");
                                /*
                                byte[] txt6 = new byte[30];
                                inFs6.read(txt6);
                                String str6 = new String(txt6);



                                dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs6.close();


                                 */


                        //메모
                        boolean inFs7 = deleteFile(file_month + "_" + month_text + "_" + "1416" + "_" + "3" + ".txt");
                                /*
                                byte[] txt7 = new byte[30];
                                inFs7.read(txt7);
                                String str7 = new String(txt7);



                                dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs7.close();


                                 */


                    }


                });
                dlg.show();


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);
                    String str = new String(txt);


                    dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                    inFs.close();


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);
                    String str6 = new String(txt6);


                    dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs6.close();


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1416" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);
                    String str7 = new String(txt7);


                    dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs7.close();

                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_8.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.
                    Toast.makeText(getApplicationContext(), "내용 없음",
                            Toast.LENGTH_SHORT).show();
                    text_8.setText("");
                }

                return false;
            }


        }));


        //날짜 클릭시 (gridview) 대화상자 보이기 (dialog)
        btn9.setOnLongClickListener((new View.OnLongClickListener() {   //16~18시 정보

            @Override
            public boolean onLongClick(View v) {
                dialogView = (View) View.inflate(weekactivity.this,
                        R.layout.dialog_week, null);

                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));


                //제목
                final EditText dlogEdt1 = (EditText) dialogView.findViewById(R.id.dlgEdt1);

                //장소
                final EditText dlogEdt6 = (EditText) dialogView.findViewById(R.id.dlgEdt6);

                //메모
                final EditText dlogEdt7 = (EditText) dialogView.findViewById(R.id.dlgEdt7);


                AlertDialog.Builder dlg = new AlertDialog.Builder(weekactivity.this);
                dlg.setTitle("일정 입력");
                dlg.setView(dialogView);    //화면 바뀜

                dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    //입력 버튼을 누르면 처리하는 부분 (파일처리 해야함)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //dialog에서 읽어와서 파일에 넣어주는 부분.


                        //만약 새로 띄워진 dialog화면이 null값이라면..


                        //이제 날짜를 받아와서 파일 제목에 써주는 역할을 해야함.
                        //"x(월)" + "_" + "y(일)" + ".txt"
                        //만약 6월 30일에 글을 쓰면 파일 이름은 => "6_30_1.txt" 이렇게 뜬다.   <= 파일 제목의 경우
                        try {
                            //제목
                            String value = dlogEdt1.getText().toString();

                            //장소
                            String value6 = dlogEdt6.getText().toString();
                            //메모
                            String value7 = dlogEdt7.getText().toString();


                            //제목
                            FileOutputStream outFs = openFileOutput(file_month + "_" + month_text + "_" + "1618" + "_" + "1" + ".txt",
                                    Context.MODE_PRIVATE);
                            //윗 부분에서 "file.txt"부분이 파일 제목인데,
                            //이 부분을 파일 제목에 써줘야함.


                            //장소
                            FileOutputStream outFs6 = openFileOutput(file_month + "_" + month_text + "_" + "1618" + "_" + "2" + ".txt",
                                    Context.MODE_PRIVATE);


                            //메모
                            FileOutputStream outFs7 = openFileOutput(file_month + "_" + month_text + "_" + "1618" + "_" + "3" + ".txt",
                                    Context.MODE_PRIVATE);


                            //제목
                            outFs.write(value.getBytes());
                            outFs.close();

                            //장소
                            outFs6.write(value6.getBytes());
                            outFs6.close();
                            //메모
                            outFs7.write(value7.getBytes());
                            outFs7.close();


                            Toast.makeText(getApplicationContext(), "글이 작성됨",
                                    Toast.LENGTH_SHORT).show();

                            if (outFs != null || outFs6 != null || outFs7 != null){    //하나라도 null이 아니라면?
                                //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                                text_9.setText("등록된 일정이 있습니다.");

                            }
                        } catch (IOException e) {
                            text_9.setText("");
                        }


                    }


                });
                dlg.setNegativeButton("뒤로 가기", null);


                //삭제하는 부분
                dlg.setNeutralButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        //이 밑 부분은 파일을 읽어오는 부분이다.
                        //파일을 삭제해야 한다.

                        //제목
                        boolean inFs = deleteFile(file_month + "_" + month_text + "_" + "1618" + "_" + "1" + ".txt");

                                /*
                                byte[] txt = new byte[30];
                                inFs.read(txt);
                                String str = new String(txt);


                                dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                                inFs.close();




                                 */


                        //장소
                        boolean inFs6 = deleteFile(file_month + "_" + month_text + "_" + "1618" + "_" + "2" + ".txt");
                                /*
                                byte[] txt6 = new byte[30];
                                inFs6.read(txt6);
                                String str6 = new String(txt6);



                                dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs6.close();


                                 */


                        //메모
                        boolean inFs7 = deleteFile(file_month + "_" + month_text + "_" + "1618" + "_" + "3" + ".txt");
                                /*
                                byte[] txt7 = new byte[30];
                                inFs7.read(txt7);
                                String str7 = new String(txt7);



                                dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs7.close();


                                 */


                    }


                });
                dlg.show();


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);
                    String str = new String(txt);


                    dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                    inFs.close();


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);
                    String str6 = new String(txt6);


                    dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs6.close();


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1618" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);
                    String str7 = new String(txt7);


                    dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs7.close();

                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_9.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.
                    Toast.makeText(getApplicationContext(), "내용 없음",
                            Toast.LENGTH_SHORT).show();
                    text_9.setText("");
                }

                return false;
            }


        }));


        //날짜 클릭시 (gridview) 대화상자 보이기 (dialog)
        btn10.setOnLongClickListener((new View.OnLongClickListener() {   //18~20시 정보

            @Override
            public boolean onLongClick(View v) {
                dialogView = (View) View.inflate(weekactivity.this,
                        R.layout.dialog_week, null);

                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));


                //제목
                final EditText dlogEdt1 = (EditText) dialogView.findViewById(R.id.dlgEdt1);

                //장소
                final EditText dlogEdt6 = (EditText) dialogView.findViewById(R.id.dlgEdt6);

                //메모
                final EditText dlogEdt7 = (EditText) dialogView.findViewById(R.id.dlgEdt7);


                AlertDialog.Builder dlg = new AlertDialog.Builder(weekactivity.this);
                dlg.setTitle("일정 입력");
                dlg.setView(dialogView);    //화면 바뀜

                dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    //입력 버튼을 누르면 처리하는 부분 (파일처리 해야함)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //dialog에서 읽어와서 파일에 넣어주는 부분.


                        //만약 새로 띄워진 dialog화면이 null값이라면..


                        //이제 날짜를 받아와서 파일 제목에 써주는 역할을 해야함.
                        //"x(월)" + "_" + "y(일)" + ".txt"
                        //만약 6월 30일에 글을 쓰면 파일 이름은 => "6_30_1.txt" 이렇게 뜬다.   <= 파일 제목의 경우
                        try {
                            //제목
                            String value = dlogEdt1.getText().toString();

                            //장소
                            String value6 = dlogEdt6.getText().toString();
                            //메모
                            String value7 = dlogEdt7.getText().toString();


                            //제목
                            FileOutputStream outFs = openFileOutput(file_month + "_" + month_text + "_" + "1820" + "_" + "1" + ".txt",
                                    Context.MODE_PRIVATE);
                            //윗 부분에서 "file.txt"부분이 파일 제목인데,
                            //이 부분을 파일 제목에 써줘야함.


                            //장소
                            FileOutputStream outFs6 = openFileOutput(file_month + "_" + month_text + "_" + "1820" + "_" + "2" + ".txt",
                                    Context.MODE_PRIVATE);


                            //메모
                            FileOutputStream outFs7 = openFileOutput(file_month + "_" + month_text + "_" + "1820" + "_" + "3" + ".txt",
                                    Context.MODE_PRIVATE);


                            //제목
                            outFs.write(value.getBytes());
                            outFs.close();

                            //장소
                            outFs6.write(value6.getBytes());
                            outFs6.close();
                            //메모
                            outFs7.write(value7.getBytes());
                            outFs7.close();


                            Toast.makeText(getApplicationContext(), "글이 작성됨",
                                    Toast.LENGTH_SHORT).show();

                            if (outFs != null || outFs6 != null || outFs7 != null){    //하나라도 null이 아니라면?
                                //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                                text_10.setText("등록된 일정이 있습니다.");

                            }
                        } catch (IOException e) {
                            text_10.setText("");
                        }


                    }


                });
                dlg.setNegativeButton("뒤로 가기", null);


                //삭제하는 부분
                dlg.setNeutralButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        //이 밑 부분은 파일을 읽어오는 부분이다.
                        //파일을 삭제해야 한다.

                        //제목
                        boolean inFs = deleteFile(file_month + "_" + month_text + "_" + "1820" + "_" + "1" + ".txt");

                                /*
                                byte[] txt = new byte[30];
                                inFs.read(txt);
                                String str = new String(txt);


                                dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                                inFs.close();




                                 */


                        //장소
                        boolean inFs6 = deleteFile(file_month + "_" + month_text + "_" + "1820" + "_" + "2" + ".txt");
                                /*
                                byte[] txt6 = new byte[30];
                                inFs6.read(txt6);
                                String str6 = new String(txt6);



                                dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs6.close();


                                 */


                        //메모
                        boolean inFs7 = deleteFile(file_month + "_" + month_text + "_" + "1820" + "_" + "3" + ".txt");
                                /*
                                byte[] txt7 = new byte[30];
                                inFs7.read(txt7);
                                String str7 = new String(txt7);



                                dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs7.close();


                                 */


                    }


                });
                dlg.show();


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);
                    String str = new String(txt);


                    dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                    inFs.close();


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);
                    String str6 = new String(txt6);


                    dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs6.close();


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "1820" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);
                    String str7 = new String(txt7);


                    dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs7.close();

                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_10.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.
                    Toast.makeText(getApplicationContext(), "내용 없음",
                            Toast.LENGTH_SHORT).show();
                    text_10.setText("");
                }

                return false;
            }


        }));


        //날짜 클릭시 (gridview) 대화상자 보이기 (dialog)
        btn11.setOnLongClickListener((new View.OnLongClickListener() {   //20~22시 정보

            @Override
            public boolean onLongClick(View v) {
                dialogView = (View) View.inflate(weekactivity.this,
                        R.layout.dialog_week, null);

                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));


                //제목
                final EditText dlogEdt1 = (EditText) dialogView.findViewById(R.id.dlgEdt1);

                //장소
                final EditText dlogEdt6 = (EditText) dialogView.findViewById(R.id.dlgEdt6);

                //메모
                final EditText dlogEdt7 = (EditText) dialogView.findViewById(R.id.dlgEdt7);


                AlertDialog.Builder dlg = new AlertDialog.Builder(weekactivity.this);
                dlg.setTitle("일정 입력");
                dlg.setView(dialogView);    //화면 바뀜

                dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    //입력 버튼을 누르면 처리하는 부분 (파일처리 해야함)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //dialog에서 읽어와서 파일에 넣어주는 부분.


                        //만약 새로 띄워진 dialog화면이 null값이라면..


                        //이제 날짜를 받아와서 파일 제목에 써주는 역할을 해야함.
                        //"x(월)" + "_" + "y(일)" + ".txt"
                        //만약 6월 30일에 글을 쓰면 파일 이름은 => "6_30_1.txt" 이렇게 뜬다.   <= 파일 제목의 경우
                        try {
                            //제목
                            String value = dlogEdt1.getText().toString();

                            //장소
                            String value6 = dlogEdt6.getText().toString();
                            //메모
                            String value7 = dlogEdt7.getText().toString();


                            //제목
                            FileOutputStream outFs = openFileOutput(file_month + "_" + month_text + "_" + "2022" + "_" + "1" + ".txt",
                                    Context.MODE_PRIVATE);
                            //윗 부분에서 "file.txt"부분이 파일 제목인데,
                            //이 부분을 파일 제목에 써줘야함.


                            //장소
                            FileOutputStream outFs6 = openFileOutput(file_month + "_" + month_text + "_" + "2022" + "_" + "2" + ".txt",
                                    Context.MODE_PRIVATE);


                            //메모
                            FileOutputStream outFs7 = openFileOutput(file_month + "_" + month_text + "_" + "2022" + "_" + "3" + ".txt",
                                    Context.MODE_PRIVATE);


                            //제목
                            outFs.write(value.getBytes());
                            outFs.close();

                            //장소
                            outFs6.write(value6.getBytes());
                            outFs6.close();
                            //메모
                            outFs7.write(value7.getBytes());
                            outFs7.close();


                            Toast.makeText(getApplicationContext(), "글이 작성됨",
                                    Toast.LENGTH_SHORT).show();

                            if (outFs != null || outFs6 != null || outFs7 != null){    //하나라도 null이 아니라면?
                                //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                                text_11.setText("등록된 일정이 있습니다.");

                            }
                        } catch (IOException e) {
                            text_11.setText("");
                        }


                    }


                });
                dlg.setNegativeButton("뒤로 가기", null);


                //삭제하는 부분
                dlg.setNeutralButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        //이 밑 부분은 파일을 읽어오는 부분이다.
                        //파일을 삭제해야 한다.

                        //제목
                        boolean inFs = deleteFile(file_month + "_" + month_text + "_" + "2022" + "_" + "1" + ".txt");

                                /*
                                byte[] txt = new byte[30];
                                inFs.read(txt);
                                String str = new String(txt);


                                dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                                inFs.close();




                                 */


                        //장소
                        boolean inFs6 = deleteFile(file_month + "_" + month_text + "_" + "2022" + "_" + "2" + ".txt");
                                /*
                                byte[] txt6 = new byte[30];
                                inFs6.read(txt6);
                                String str6 = new String(txt6);



                                dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs6.close();


                                 */


                        //메모
                        boolean inFs7 = deleteFile(file_month + "_" + month_text + "_" + "2022" + "_" + "3" + ".txt");
                                /*
                                byte[] txt7 = new byte[30];
                                inFs7.read(txt7);
                                String str7 = new String(txt7);



                                dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs7.close();


                                 */


                    }


                });
                dlg.show();


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);
                    String str = new String(txt);


                    dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                    inFs.close();


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);
                    String str6 = new String(txt6);


                    dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs6.close();


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "2022" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);
                    String str7 = new String(txt7);


                    dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs7.close();

                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_11.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.
                    Toast.makeText(getApplicationContext(), "내용 없음",
                            Toast.LENGTH_SHORT).show();
                    text_11.setText("");
                }

                return false;
            }


        }));


        //날짜 클릭시 (gridview) 대화상자 보이기 (dialog)
        btn12.setOnLongClickListener((new View.OnLongClickListener() {   //22~24시 정보

            @Override
            public boolean onLongClick(View v) {
                dialogView = (View) View.inflate(weekactivity.this,
                        R.layout.dialog_week, null);

                file_month = String.valueOf((cal.get(Calendar.MONTH) + month_test));


                //제목
                final EditText dlogEdt1 = (EditText) dialogView.findViewById(R.id.dlgEdt1);

                //장소
                final EditText dlogEdt6 = (EditText) dialogView.findViewById(R.id.dlgEdt6);

                //메모
                final EditText dlogEdt7 = (EditText) dialogView.findViewById(R.id.dlgEdt7);


                AlertDialog.Builder dlg = new AlertDialog.Builder(weekactivity.this);
                dlg.setTitle("일정 입력");
                dlg.setView(dialogView);    //화면 바뀜

                dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    //입력 버튼을 누르면 처리하는 부분 (파일처리 해야함)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //dialog에서 읽어와서 파일에 넣어주는 부분.


                        //만약 새로 띄워진 dialog화면이 null값이라면..


                        //이제 날짜를 받아와서 파일 제목에 써주는 역할을 해야함.
                        //"x(월)" + "_" + "y(일)" + ".txt"
                        //만약 6월 30일에 글을 쓰면 파일 이름은 => "6_30_1.txt" 이렇게 뜬다.   <= 파일 제목의 경우
                        try {
                            //제목
                            String value = dlogEdt1.getText().toString();

                            //장소
                            String value6 = dlogEdt6.getText().toString();
                            //메모
                            String value7 = dlogEdt7.getText().toString();


                            //제목
                            FileOutputStream outFs = openFileOutput(file_month + "_" + month_text + "_" + "2224" + "_" + "1" + ".txt",
                                    Context.MODE_PRIVATE);
                            //윗 부분에서 "file.txt"부분이 파일 제목인데,
                            //이 부분을 파일 제목에 써줘야함.


                            //장소
                            FileOutputStream outFs6 = openFileOutput(file_month + "_" + month_text + "_" + "2224" + "_" + "2" + ".txt",
                                    Context.MODE_PRIVATE);


                            //메모
                            FileOutputStream outFs7 = openFileOutput(file_month + "_" + month_text + "_" + "2224" + "_" + "3" + ".txt",
                                    Context.MODE_PRIVATE);


                            //제목
                            outFs.write(value.getBytes());
                            outFs.close();

                            //장소
                            outFs6.write(value6.getBytes());
                            outFs6.close();
                            //메모
                            outFs7.write(value7.getBytes());
                            outFs7.close();


                            Toast.makeText(getApplicationContext(), "글이 작성됨",
                                    Toast.LENGTH_SHORT).show();

                            if (outFs != null || outFs6 != null || outFs7 != null){    //하나라도 null이 아니라면?
                                //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                                text_12.setText("등록된 일정이 있습니다.");

                            }
                        } catch (IOException e) {
                            text_12.setText("");
                        }


                    }


                });
                dlg.setNegativeButton("뒤로 가기", null);


                //삭제하는 부분
                dlg.setNeutralButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        //이 밑 부분은 파일을 읽어오는 부분이다.
                        //파일을 삭제해야 한다.

                        //제목
                        boolean inFs = deleteFile(file_month + "_" + month_text + "_" + "2224" + "_" + "1" + ".txt");

                                /*
                                byte[] txt = new byte[30];
                                inFs.read(txt);
                                String str = new String(txt);


                                dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                                inFs.close();




                                 */


                        //장소
                        boolean inFs6 = deleteFile(file_month + "_" + month_text + "_" + "2224" + "_" + "2" + ".txt");
                                /*
                                byte[] txt6 = new byte[30];
                                inFs6.read(txt6);
                                String str6 = new String(txt6);



                                dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs6.close();


                                 */


                        //메모
                        boolean inFs7 = deleteFile(file_month + "_" + month_text + "_" + "2224" + "_" + "3" + ".txt");
                                /*
                                byte[] txt7 = new byte[30];
                                inFs7.read(txt7);
                                String str7 = new String(txt7);



                                dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs7.close();


                                 */


                    }


                });
                dlg.show();


                //이 밑 부분은 파일을 읽어오는 부분이다.
                try {
                    //제목
                    FileInputStream inFs = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "1" + ".txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);
                    String str = new String(txt);


                    dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                    inFs.close();


                    //장소
                    FileInputStream inFs6 = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "2" + ".txt");
                    byte[] txt6 = new byte[30];
                    inFs6.read(txt6);
                    String str6 = new String(txt6);


                    dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs6.close();


                    //메모
                    FileInputStream inFs7 = openFileInput(file_month + "_" + month_text + "_" + "2224" + "_" + "3" + ".txt");
                    byte[] txt7 = new byte[30];
                    inFs7.read(txt7);
                    String str7 = new String(txt7);


                    dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                    inFs7.close();

                    if (inFs != null || inFs6 != null || inFs7 != null){    //하나라도 null이 아니라면?
                        //즉, 하나라도 비어있지 않다면? => 저 세개 중에서 파일 내용이 하나라도 들어있다면
                        text_12.setText("등록된 일정이 있습니다.");

                    }


                } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.
                    Toast.makeText(getApplicationContext(), "내용 없음",
                            Toast.LENGTH_SHORT).show();
                    text_12.setText("");
                }

                return false;
            }


        }));

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater mInflater = getMenuInflater();

        if (v == button1_week) {
            //menu.setHeaderTitle("s");

            mInflater.inflate(R.menu.menu1, menu);
        }


    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //super.onOptionsItemSelected(item);

        Toast toast = Toast.makeText(getApplicationContext(), "", LENGTH_LONG);

        switch (item.getItemId()) {
            case R.id.month:

                toast.setText("month");
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);

                break;


            case R.id.week:
                toast.setText("week");
                Intent homeIntents = new Intent(this, weekactivity.class);
                startActivity(homeIntents);

                break;
        }

        toast.show();

        return super.onOptionsItemSelected(item);
    }

}


