package com.example.myapplication_visual_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;



//이 부분은 weekactivity를 복사한 것이다.
public class weekactivity2 extends AppCompatActivity {

    LinearLayout baseLayout_week;
    Button button1_week;
    Button last_week;
    Button next_week;
    Button todaybutton_week;
    String[] weekview_nuber = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
    "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "  "};
    TextView weekviews_1;
    TextView weekviews_2;
    TextView weekviews_3;
    TextView weekviews_4;
    TextView weekviews_5;
    TextView weekviews_6;
    TextView weekviews_7;

    int count_num;
    int j = 0;
    int addday = 0;
    int addday2 = 0;
    int lastmonth = 0;

    /**
     * 연/월 텍스트뷰
     */
    TextView tvDate_week;
    /**
     * 그리드뷰 어댑터
     */
    weekactivity2.GridAdapter gridAdapter;

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
    Calendar mCal_today;

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
        weekviews_1 = (TextView) findViewById(R.id.weekviews_1);
        weekviews_2 = (TextView) findViewById(R.id.weekviews_2);
        weekviews_3 = (TextView) findViewById(R.id.weekviews_3);
        weekviews_4 = (TextView) findViewById(R.id.weekviews_4);
        weekviews_5 = (TextView) findViewById(R.id.weekviews_5);
        weekviews_6 = (TextView) findViewById(R.id.weekviews_6);
        weekviews_7 = (TextView) findViewById(R.id.weekviews_7);


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










        //gridview 요일 표시
        dayList = new ArrayList<String>();
        dayList.add("일");
        dayList.add("월");
        dayList.add("화");
        dayList.add("수");
        dayList.add("목");
        dayList.add("금");
        dayList.add("토");




        mCal = Calendar.getInstance();
        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);
        final int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        //1일 - 요일 매칭 시키기 위해 공백 add
        for (int i = 1; i < dayNum; i++) {
            dayList.add("");
        }
        setCalendarDate(mCal.get(Calendar.MONTH) + 1);

        gridAdapter = new weekactivity2.GridAdapter(getApplicationContext(), dayList);
        gridView_week.setAdapter(gridAdapter);







        //각 주마다 요일 배치하기.

        mCal_today = Calendar.getInstance();
        mCal_today.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, Integer.parseInt(curDayFormat.format(date)));


        if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {  //일요일
            switch(curDayFormat.format(date)) { //오늘이 몇 일인가?
                case "1": case "2": case "3": case "4": case "5": case "6": case "7"://1일이면


                        weekviews_1.setText(weekview_nuber[0]);
                        weekviews_2.setText(weekview_nuber[1]);
                        weekviews_3.setText(weekview_nuber[2]);
                        weekviews_4.setText(weekview_nuber[3]);
                        weekviews_5.setText(weekview_nuber[4]);
                        weekviews_6.setText(weekview_nuber[5]);
                        weekviews_7.setText(weekview_nuber[6]);

                    break;
                case "8": case "9": case "10": case "11": case "12": case "13": case "14"://1일이면

                        weekviews_1.setText(weekview_nuber[7]);
                        weekviews_2.setText(weekview_nuber[8]);
                        weekviews_3.setText(weekview_nuber[9]);
                        weekviews_4.setText(weekview_nuber[10]);
                        weekviews_5.setText(weekview_nuber[11]);
                        weekviews_6.setText(weekview_nuber[12]);
                        weekviews_7.setText(weekview_nuber[13]);

                    break;
                case "15": case "16": case "17": case "18": case "19": case "20": case "21":   //2일이면
                 //15~21
                        weekviews_1.setText(weekview_nuber[14]);
                        weekviews_2.setText(weekview_nuber[15]);
                        weekviews_3.setText(weekview_nuber[16]);
                        weekviews_4.setText(weekview_nuber[17]);
                        weekviews_5.setText(weekview_nuber[18]);
                        weekviews_6.setText(weekview_nuber[19]);
                        weekviews_7.setText(weekview_nuber[20]);

                    break;
                case "22": case "23": case "24": case "25": case "26": case "27": case "28":    //3일이면
              //22~28
                        weekviews_1.setText(weekview_nuber[21]);
                        weekviews_2.setText(weekview_nuber[22]);
                        weekviews_3.setText(weekview_nuber[23]);
                        weekviews_4.setText(weekview_nuber[24]);
                        weekviews_5.setText(weekview_nuber[25]);
                        weekviews_6.setText(weekview_nuber[26]);
                        weekviews_7.setText(weekview_nuber[27]);

                    break;
                case "29": case "30": case "31":   //4일이면
            //29~31
                        weekviews_1.setText(weekview_nuber[28]);
                        weekviews_2.setText(weekview_nuber[29]);
                        weekviews_3.setText(weekview_nuber[30]);
                        weekviews_4.setText(weekview_nuber[31]);
                        weekviews_5.setText(weekview_nuber[31]);
                        weekviews_6.setText(weekview_nuber[31]);
                        weekviews_7.setText(weekview_nuber[31]);


                    break;

            }
        }


        ///////
        if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {  //월요일
            switch(curDayFormat.format(date)) { //오늘이 몇 일인가?
                case "1": case "2": case "3": case "4": case "5": case "6": //1일이면


                    //1~6
                        weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                        weekviews_2.setText(weekview_nuber[0]);
                        weekviews_3.setText(weekview_nuber[1]);
                        weekviews_4.setText(weekview_nuber[2]);
                        weekviews_5.setText(weekview_nuber[3]);
                        weekviews_6.setText(weekview_nuber[4]);
                        weekviews_7.setText(weekview_nuber[5]);

                    break;
                case "7": case "8": case "9": case "10": case "11": case "12": case "13": //1일이면
   //7~13
                        weekviews_1.setText(weekview_nuber[6]);
                        weekviews_2.setText(weekview_nuber[7]);
                        weekviews_3.setText(weekview_nuber[8]);
                        weekviews_4.setText(weekview_nuber[9]);
                        weekviews_5.setText(weekview_nuber[10]);
                        weekviews_6.setText(weekview_nuber[11]);
                        weekviews_7.setText(weekview_nuber[12]);

                    break;
                case "14": case "15": case "16": case "17": case "18": case "19": case "20":   //2일이면
                     //14~20
                        weekviews_1.setText(weekview_nuber[13]);
                        weekviews_2.setText(weekview_nuber[14]);
                        weekviews_3.setText(weekview_nuber[15]);
                        weekviews_4.setText(weekview_nuber[16]);
                        weekviews_5.setText(weekview_nuber[17]);
                        weekviews_6.setText(weekview_nuber[18]);
                        weekviews_7.setText(weekview_nuber[19]);

                    break;
                case "21": case "22": case "23": case "24": case "25": case "26": case "27":     //3일이면
                      //21~27
                        weekviews_1.setText(weekview_nuber[20]);
                        weekviews_2.setText(weekview_nuber[21]);
                        weekviews_3.setText(weekview_nuber[22]);
                        weekviews_4.setText(weekview_nuber[23]);
                        weekviews_5.setText(weekview_nuber[24]);
                        weekviews_6.setText(weekview_nuber[25]);
                        weekviews_7.setText(weekview_nuber[26]);

                    break;
                case "28": case "29": case "30": case "31":   //4일이면
                    //28~31
                        weekviews_1.setText(weekview_nuber[27]);
                        weekviews_2.setText(weekview_nuber[28]);
                        weekviews_3.setText(weekview_nuber[29]);
                        weekviews_4.setText(weekview_nuber[30]);
                        weekviews_5.setText(weekview_nuber[31]);
                        weekviews_6.setText(weekview_nuber[31]);
                        weekviews_7.setText(weekview_nuber[31]);

                    break;

            }
        }


        ///////
        if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {  //화요일
            switch(curDayFormat.format(date)) { //오늘이 몇 일인가?
                case "1": case "2": case "3": case "4":   //1일이면


                    //1~5
                    weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                    weekviews_2.setText(weekview_nuber[31]);
                    weekviews_3.setText(weekview_nuber[0]);
                    weekviews_4.setText(weekview_nuber[1]);
                    weekviews_5.setText(weekview_nuber[2]);
                    weekviews_6.setText(weekview_nuber[3]);
                    weekviews_7.setText(weekview_nuber[4]);

                    break;
                case "5": case "6": case "7": case "8": case "9": case "10": case "11":   //1일이면
                    //6~12
                    weekviews_1.setText(weekview_nuber[5]);
                    weekviews_2.setText(weekview_nuber[6]);
                    weekviews_3.setText(weekview_nuber[7]);
                    weekviews_4.setText(weekview_nuber[8]);
                    weekviews_5.setText(weekview_nuber[9]);
                    weekviews_6.setText(weekview_nuber[10]);
                    weekviews_7.setText(weekview_nuber[11]);

                    break;
                case "12": case "13": case "14": case "15": case "16": case "17": case "18":     //2일이면
                    //13~19
                    weekviews_1.setText(weekview_nuber[12]);
                    weekviews_2.setText(weekview_nuber[13]);
                    weekviews_3.setText(weekview_nuber[14]);
                    weekviews_4.setText(weekview_nuber[15]);
                    weekviews_5.setText(weekview_nuber[16]);
                    weekviews_6.setText(weekview_nuber[17]);
                    weekviews_7.setText(weekview_nuber[18]);

                    break;
                case "19": case "20": case "21": case "22": case "23": case "24": case "25":       //3일이면
                    //20~26
                    weekviews_1.setText(weekview_nuber[19]);
                    weekviews_2.setText(weekview_nuber[20]);
                    weekviews_3.setText(weekview_nuber[21]);
                    weekviews_4.setText(weekview_nuber[22]);
                    weekviews_5.setText(weekview_nuber[23]);
                    weekviews_6.setText(weekview_nuber[24]);
                    weekviews_7.setText(weekview_nuber[25]);

                    break;
                case "26": case "27": case "28": case "29": case "30": case "31":   //4일이면
                    //27~31
                    weekviews_1.setText(weekview_nuber[26]);
                    weekviews_2.setText(weekview_nuber[27]);
                    weekviews_3.setText(weekview_nuber[28]);
                    weekviews_4.setText(weekview_nuber[29]);
                    weekviews_5.setText(weekview_nuber[30]);
                    weekviews_6.setText(weekview_nuber[31]);
                    weekviews_7.setText(weekview_nuber[31]);

                    break;

            }
        }



        ///////
        if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {  //수요일
            switch(curDayFormat.format(date)) { //오늘이 몇 일인가?
                case "1": case "2": case "3": case "4":   //1일이면


                    //1~4
                    weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                    weekviews_2.setText(weekview_nuber[31]);
                    weekviews_3.setText(weekview_nuber[31]);
                    weekviews_4.setText(weekview_nuber[0]);
                    weekviews_5.setText(weekview_nuber[1]);
                    weekviews_6.setText(weekview_nuber[2]);
                    weekviews_7.setText(weekview_nuber[3]);

                    break;
                case "5": case "6": case "7": case "8": case "9": case "10": case "11":   //1일이면
                    //5~11
                    weekviews_1.setText(weekview_nuber[4]);
                    weekviews_2.setText(weekview_nuber[5]);
                    weekviews_3.setText(weekview_nuber[6]);
                    weekviews_4.setText(weekview_nuber[7]);
                    weekviews_5.setText(weekview_nuber[8]);
                    weekviews_6.setText(weekview_nuber[9]);
                    weekviews_7.setText(weekview_nuber[10]);

                    break;
                case "12": case "13": case "14": case "15": case "16": case "17": case "18":     //2일이면
                    //12~18
                    weekviews_1.setText(weekview_nuber[11]);
                    weekviews_2.setText(weekview_nuber[12]);
                    weekviews_3.setText(weekview_nuber[13]);
                    weekviews_4.setText(weekview_nuber[14]);
                    weekviews_5.setText(weekview_nuber[15]);
                    weekviews_6.setText(weekview_nuber[16]);
                    weekviews_7.setText(weekview_nuber[17]);

                    break;
                case "19": case "20": case "21": case "22": case "23": case "24": case "25":       //3일이면
                    //19~25
                    weekviews_1.setText(weekview_nuber[18]);
                    weekviews_2.setText(weekview_nuber[29]);
                    weekviews_3.setText(weekview_nuber[20]);
                    weekviews_4.setText(weekview_nuber[21]);
                    weekviews_5.setText(weekview_nuber[22]);
                    weekviews_6.setText(weekview_nuber[23]);
                    weekviews_7.setText(weekview_nuber[24]);

                    break;
                case "26": case "27": case "28": case "29": case "30": case "31":   //4일이면
                    //26~31
                    weekviews_1.setText(weekview_nuber[25]);
                    weekviews_2.setText(weekview_nuber[26]);
                    weekviews_3.setText(weekview_nuber[27]);
                    weekviews_4.setText(weekview_nuber[28]);
                    weekviews_5.setText(weekview_nuber[29]);
                    weekviews_6.setText(weekview_nuber[30]);
                    weekviews_7.setText(weekview_nuber[31]);

                    break;

            }
        }



        ///////
        if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {  //목요일
            switch(curDayFormat.format(date)) { //오늘이 몇 일인가?
                case "1": case "2": case "3":   //1일이면


                    //1~3
                    weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                    weekviews_2.setText(weekview_nuber[31]);
                    weekviews_3.setText(weekview_nuber[31]);
                    weekviews_4.setText(weekview_nuber[31]);
                    weekviews_5.setText(weekview_nuber[0]);
                    weekviews_6.setText(weekview_nuber[1]);
                    weekviews_7.setText(weekview_nuber[2]);

                    break;
                case "4": case "5":case "6": case "7": case "8": case "9": case "10":   //1일이면
                    //4~10
                    weekviews_1.setText(weekview_nuber[3]);
                    weekviews_2.setText(weekview_nuber[4]);
                    weekviews_3.setText(weekview_nuber[5]);
                    weekviews_4.setText(weekview_nuber[6]);
                    weekviews_5.setText(weekview_nuber[7]);
                    weekviews_6.setText(weekview_nuber[8]);
                    weekviews_7.setText(weekview_nuber[9]);

                    break;
                case "11": case "12": case "13": case "14": case "15": case "16": case "17":     //2일이면
                    //11~17
                    weekviews_1.setText(weekview_nuber[10]);
                    weekviews_2.setText(weekview_nuber[11]);
                    weekviews_3.setText(weekview_nuber[12]);
                    weekviews_4.setText(weekview_nuber[13]);
                    weekviews_5.setText(weekview_nuber[14]);
                    weekviews_6.setText(weekview_nuber[15]);
                    weekviews_7.setText(weekview_nuber[16]);

                    break;
                case "18": case "19": case "20": case "21": case "22": case "23": case "24":       //3일이면
                    //18~24
                    weekviews_1.setText(weekview_nuber[17]);
                    weekviews_2.setText(weekview_nuber[18]);
                    weekviews_3.setText(weekview_nuber[19]);
                    weekviews_4.setText(weekview_nuber[20]);
                    weekviews_5.setText(weekview_nuber[21]);
                    weekviews_6.setText(weekview_nuber[22]);
                    weekviews_7.setText(weekview_nuber[23]);

                    break;
                case "25": case "26": case "27": case "28": case "29": case "30": case "31":   //4일이면
                    //25~31
                    weekviews_1.setText(weekview_nuber[24]);
                    weekviews_2.setText(weekview_nuber[25]);
                    weekviews_3.setText(weekview_nuber[26]);
                    weekviews_4.setText(weekview_nuber[27]);
                    weekviews_5.setText(weekview_nuber[28]);
                    weekviews_6.setText(weekview_nuber[29]);
                    weekviews_7.setText(weekview_nuber[30]);

                    break;

            }
        }



        ///////
        if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {  //금요일
            switch(curDayFormat.format(date)) { //오늘이 몇 일인가?
                case "1": case "2":   //1일이면


                    //1~2
                    weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                    weekviews_2.setText(weekview_nuber[31]);
                    weekviews_3.setText(weekview_nuber[31]);
                    weekviews_4.setText(weekview_nuber[31]);
                    weekviews_5.setText(weekview_nuber[31]);
                    weekviews_6.setText(weekview_nuber[0]);
                    weekviews_7.setText(weekview_nuber[1]);

                    break;
                case "3": case "4": case "5": case "6": case "7": case "8": case "9":   //1일이면
                    //4~10
                    weekviews_1.setText(weekview_nuber[2]);
                    weekviews_2.setText(weekview_nuber[3]);
                    weekviews_3.setText(weekview_nuber[4]);
                    weekviews_4.setText(weekview_nuber[5]);
                    weekviews_5.setText(weekview_nuber[6]);
                    weekviews_6.setText(weekview_nuber[7]);
                    weekviews_7.setText(weekview_nuber[8]);

                    break;
                case "10": case "11": case "12": case "13": case "14": case "15": case "16":     //2일이면
                    //11~17
                    weekviews_1.setText(weekview_nuber[9]);
                    weekviews_2.setText(weekview_nuber[10]);
                    weekviews_3.setText(weekview_nuber[11]);
                    weekviews_4.setText(weekview_nuber[12]);
                    weekviews_5.setText(weekview_nuber[13]);
                    weekviews_6.setText(weekview_nuber[14]);
                    weekviews_7.setText(weekview_nuber[15]);

                    break;
                case "17": case "18": case "19": case "20": case "21": case "22": case "23":       //3일이면
                    //18~24
                    weekviews_1.setText(weekview_nuber[16]);
                    weekviews_2.setText(weekview_nuber[17]);
                    weekviews_3.setText(weekview_nuber[18]);
                    weekviews_4.setText(weekview_nuber[19]);
                    weekviews_5.setText(weekview_nuber[20]);
                    weekviews_6.setText(weekview_nuber[21]);
                    weekviews_7.setText(weekview_nuber[22]);

                    break;
                case "24": case "25": case "26": case "27": case "28": case "29": case "30":    //4일이면
                    //25~31
                    weekviews_1.setText(weekview_nuber[23]);
                    weekviews_2.setText(weekview_nuber[24]);
                    weekviews_3.setText(weekview_nuber[25]);
                    weekviews_4.setText(weekview_nuber[26]);
                    weekviews_5.setText(weekview_nuber[27]);
                    weekviews_6.setText(weekview_nuber[28]);
                    weekviews_7.setText(weekview_nuber[29]);

                    break;

                case "31":
                    weekviews_1.setText(weekview_nuber[30]);
                    weekviews_2.setText(weekview_nuber[31]);
                    weekviews_3.setText(weekview_nuber[31]);
                    weekviews_4.setText(weekview_nuber[31]);
                    weekviews_5.setText(weekview_nuber[31]);
                    weekviews_6.setText(weekview_nuber[31]);
                    weekviews_7.setText(weekview_nuber[31]);

                    break;

            }
        }


        ///////
        if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {  //토요일
            switch(curDayFormat.format(date)) { //오늘이 몇 일인가?
                case "1":    //1일이면


                    //1~2
                    weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                    weekviews_2.setText(weekview_nuber[31]);
                    weekviews_3.setText(weekview_nuber[31]);
                    weekviews_4.setText(weekview_nuber[31]);
                    weekviews_5.setText(weekview_nuber[31]);
                    weekviews_6.setText(weekview_nuber[31]);
                    weekviews_7.setText(weekview_nuber[0]);

                    break;
                case "2": case "3": case "4": case "5": case "6": case "7": case "8":    //1일이면
                    //4~10
                    weekviews_1.setText(weekview_nuber[1]);
                    weekviews_2.setText(weekview_nuber[2]);
                    weekviews_3.setText(weekview_nuber[3]);
                    weekviews_4.setText(weekview_nuber[4]);
                    weekviews_5.setText(weekview_nuber[5]);
                    weekviews_6.setText(weekview_nuber[6]);
                    weekviews_7.setText(weekview_nuber[7]);

                    break;
                case "9": case "10": case "11": case "12": case "13": case "14": case "15":      //2일이면
                    //11~17
                    weekviews_1.setText(weekview_nuber[8]);
                    weekviews_2.setText(weekview_nuber[9]);
                    weekviews_3.setText(weekview_nuber[10]);
                    weekviews_4.setText(weekview_nuber[11]);
                    weekviews_5.setText(weekview_nuber[12]);
                    weekviews_6.setText(weekview_nuber[13]);
                    weekviews_7.setText(weekview_nuber[14]);

                    break;
                case "16": case "17": case "18": case "19": case "20": case "21": case "22":        //3일이면
                    //18~24
                    weekviews_1.setText(weekview_nuber[15]);
                    weekviews_2.setText(weekview_nuber[16]);
                    weekviews_3.setText(weekview_nuber[17]);
                    weekviews_4.setText(weekview_nuber[18]);
                    weekviews_5.setText(weekview_nuber[19]);
                    weekviews_6.setText(weekview_nuber[20]);
                    weekviews_7.setText(weekview_nuber[21]);

                    break;
                case "23": case "24": case "25": case "26": case "27": case "28": case "29":     //4일이면
                    //25~31
                    weekviews_1.setText(weekview_nuber[22]);
                    weekviews_2.setText(weekview_nuber[23]);
                    weekviews_3.setText(weekview_nuber[24]);
                    weekviews_4.setText(weekview_nuber[25]);
                    weekviews_5.setText(weekview_nuber[26]);
                    weekviews_6.setText(weekview_nuber[27]);
                    weekviews_7.setText(weekview_nuber[28]);

                    break;

                case "30": case "31":
                    weekviews_1.setText(weekview_nuber[29]);
                    weekviews_2.setText(weekview_nuber[30]);
                    weekviews_3.setText(weekview_nuber[31]);
                    weekviews_4.setText(weekview_nuber[31]);
                    weekviews_5.setText(weekview_nuber[31]);
                    weekviews_6.setText(weekview_nuber[31]);
                    weekviews_7.setText(weekview_nuber[31]);

                    break;

            }
        }








        ///////////////////////////////////////////////////////////////////////////
        //오늘로 이동
        todaybutton_week.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {


                //초기화
                tvDate_week.setText("");

                // 오늘에 날짜를 세팅 해준다.
                long now = System.currentTimeMillis();
                final Date date = new Date(now);

                addday = 0;


                //내일
                final Date today = new Date();
                //final Date nextmonth = new Date ( today.getTime ( ) + (long) ( 1000 * 60 * 60 * 24 * h) );


                TimeZone jst = TimeZone.getTimeZone("JST");
                final Calendar cal = Calendar.getInstance(jst); // 주어진 시간대에 맞게 현재 시각으로 초기화된 GregorianCalender 객체를 반환.// 또는 Calendar now = Calendar.getInstance(Locale.KOREA);

                //연,월,일을 따로 저장
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM/yyyy", Locale.KOREAN);
                final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
                final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
                final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

                //현재 날짜 텍스트뷰에 뿌려줌
                tvDate_week.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));









                //gridview 요일 표시
                dayList = new ArrayList<String>();
                dayList.add("일");
                dayList.add("월");
                dayList.add("화");
                dayList.add("수");
                dayList.add("목");
                dayList.add("금");
                dayList.add("토");




                mCal = Calendar.getInstance();
                //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
                mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);
                final int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
                //1일 - 요일 매칭 시키기 위해 공백 add
                for (int i = 1; i < dayNum; i++) {
                    dayList.add("");
                }
                setCalendarDate(mCal.get(Calendar.MONTH) + 1);

                gridAdapter = new weekactivity2.GridAdapter(getApplicationContext(), dayList);
                gridView_week.setAdapter(gridAdapter);







                //각 주마다 요일 배치하기.

                mCal_today = Calendar.getInstance();
                mCal_today.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, Integer.parseInt(curDayFormat.format(date)));


                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {  //일요일
                    switch(curDayFormat.format(date)) { //오늘이 몇 일인가?
                        case "1": case "2": case "3": case "4": case "5": case "6": case "7"://1일이면


                            weekviews_1.setText(weekview_nuber[0]);
                            weekviews_2.setText(weekview_nuber[1]);
                            weekviews_3.setText(weekview_nuber[2]);
                            weekviews_4.setText(weekview_nuber[3]);
                            weekviews_5.setText(weekview_nuber[4]);
                            weekviews_6.setText(weekview_nuber[5]);
                            weekviews_7.setText(weekview_nuber[6]);

                            break;
                        case "8": case "9": case "10": case "11": case "12": case "13": case "14"://1일이면

                            weekviews_1.setText(weekview_nuber[7]);
                            weekviews_2.setText(weekview_nuber[8]);
                            weekviews_3.setText(weekview_nuber[9]);
                            weekviews_4.setText(weekview_nuber[10]);
                            weekviews_5.setText(weekview_nuber[11]);
                            weekviews_6.setText(weekview_nuber[12]);
                            weekviews_7.setText(weekview_nuber[13]);

                            break;
                        case "15": case "16": case "17": case "18": case "19": case "20": case "21":   //2일이면
                            //15~21
                            weekviews_1.setText(weekview_nuber[14]);
                            weekviews_2.setText(weekview_nuber[15]);
                            weekviews_3.setText(weekview_nuber[16]);
                            weekviews_4.setText(weekview_nuber[17]);
                            weekviews_5.setText(weekview_nuber[18]);
                            weekviews_6.setText(weekview_nuber[19]);
                            weekviews_7.setText(weekview_nuber[20]);

                            break;
                        case "22": case "23": case "24": case "25": case "26": case "27": case "28":    //3일이면
                            //22~28
                            weekviews_1.setText(weekview_nuber[21]);
                            weekviews_2.setText(weekview_nuber[22]);
                            weekviews_3.setText(weekview_nuber[23]);
                            weekviews_4.setText(weekview_nuber[24]);
                            weekviews_5.setText(weekview_nuber[25]);
                            weekviews_6.setText(weekview_nuber[26]);
                            weekviews_7.setText(weekview_nuber[27]);

                            break;
                        case "29": case "30": case "31":   //4일이면
                            //29~31
                            weekviews_1.setText(weekview_nuber[28]);
                            weekviews_2.setText(weekview_nuber[29]);
                            weekviews_3.setText(weekview_nuber[30]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[31]);


                            break;

                    }
                }


                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {  //월요일
                    switch(curDayFormat.format(date)) { //오늘이 몇 일인가?
                        case "1": case "2": case "3": case "4": case "5": case "6": //1일이면


                            //1~6
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[0]);
                            weekviews_3.setText(weekview_nuber[1]);
                            weekviews_4.setText(weekview_nuber[2]);
                            weekviews_5.setText(weekview_nuber[3]);
                            weekviews_6.setText(weekview_nuber[4]);
                            weekviews_7.setText(weekview_nuber[5]);

                            break;
                        case "7": case "8": case "9": case "10": case "11": case "12": case "13": //1일이면
                            //7~13
                            weekviews_1.setText(weekview_nuber[6]);
                            weekviews_2.setText(weekview_nuber[7]);
                            weekviews_3.setText(weekview_nuber[8]);
                            weekviews_4.setText(weekview_nuber[9]);
                            weekviews_5.setText(weekview_nuber[10]);
                            weekviews_6.setText(weekview_nuber[11]);
                            weekviews_7.setText(weekview_nuber[12]);

                            break;
                        case "14": case "15": case "16": case "17": case "18": case "19": case "20":   //2일이면
                            //14~20
                            weekviews_1.setText(weekview_nuber[13]);
                            weekviews_2.setText(weekview_nuber[14]);
                            weekviews_3.setText(weekview_nuber[15]);
                            weekviews_4.setText(weekview_nuber[16]);
                            weekviews_5.setText(weekview_nuber[17]);
                            weekviews_6.setText(weekview_nuber[18]);
                            weekviews_7.setText(weekview_nuber[19]);

                            break;
                        case "21": case "22": case "23": case "24": case "25": case "26": case "27":     //3일이면
                            //21~27
                            weekviews_1.setText(weekview_nuber[20]);
                            weekviews_2.setText(weekview_nuber[21]);
                            weekviews_3.setText(weekview_nuber[22]);
                            weekviews_4.setText(weekview_nuber[23]);
                            weekviews_5.setText(weekview_nuber[24]);
                            weekviews_6.setText(weekview_nuber[25]);
                            weekviews_7.setText(weekview_nuber[26]);

                            break;
                        case "28": case "29": case "30": case "31":   //4일이면
                            //28~31
                            weekviews_1.setText(weekview_nuber[27]);
                            weekviews_2.setText(weekview_nuber[28]);
                            weekviews_3.setText(weekview_nuber[29]);
                            weekviews_4.setText(weekview_nuber[30]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[31]);

                            break;

                    }
                }


                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {  //화요일
                    switch(curDayFormat.format(date)) { //오늘이 몇 일인가?
                        case "1": case "2": case "3": case "4":   //1일이면


                            //1~5
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[0]);
                            weekviews_4.setText(weekview_nuber[1]);
                            weekviews_5.setText(weekview_nuber[2]);
                            weekviews_6.setText(weekview_nuber[3]);
                            weekviews_7.setText(weekview_nuber[4]);

                            break;
                        case "5": case "6": case "7": case "8": case "9": case "10": case "11":   //1일이면
                            //6~12
                            weekviews_1.setText(weekview_nuber[5]);
                            weekviews_2.setText(weekview_nuber[6]);
                            weekviews_3.setText(weekview_nuber[7]);
                            weekviews_4.setText(weekview_nuber[8]);
                            weekviews_5.setText(weekview_nuber[9]);
                            weekviews_6.setText(weekview_nuber[10]);
                            weekviews_7.setText(weekview_nuber[11]);

                            break;
                        case "12": case "13": case "14": case "15": case "16": case "17": case "18":     //2일이면
                            //13~19
                            weekviews_1.setText(weekview_nuber[12]);
                            weekviews_2.setText(weekview_nuber[13]);
                            weekviews_3.setText(weekview_nuber[14]);
                            weekviews_4.setText(weekview_nuber[15]);
                            weekviews_5.setText(weekview_nuber[16]);
                            weekviews_6.setText(weekview_nuber[17]);
                            weekviews_7.setText(weekview_nuber[18]);

                            break;
                        case "19": case "20": case "21": case "22": case "23": case "24": case "25":       //3일이면
                            //20~26
                            weekviews_1.setText(weekview_nuber[19]);
                            weekviews_2.setText(weekview_nuber[20]);
                            weekviews_3.setText(weekview_nuber[21]);
                            weekviews_4.setText(weekview_nuber[22]);
                            weekviews_5.setText(weekview_nuber[23]);
                            weekviews_6.setText(weekview_nuber[24]);
                            weekviews_7.setText(weekview_nuber[25]);

                            break;
                        case "26": case "27": case "28": case "29": case "30": case "31":   //4일이면
                            //27~31
                            weekviews_1.setText(weekview_nuber[26]);
                            weekviews_2.setText(weekview_nuber[27]);
                            weekviews_3.setText(weekview_nuber[28]);
                            weekviews_4.setText(weekview_nuber[29]);
                            weekviews_5.setText(weekview_nuber[30]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[31]);

                            break;

                    }
                }



                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {  //수요일
                    switch(curDayFormat.format(date)) { //오늘이 몇 일인가?
                        case "1": case "2": case "3": case "4":   //1일이면


                            //1~4
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[0]);
                            weekviews_5.setText(weekview_nuber[1]);
                            weekviews_6.setText(weekview_nuber[2]);
                            weekviews_7.setText(weekview_nuber[3]);

                            break;
                        case "5": case "6": case "7": case "8": case "9": case "10": case "11":   //1일이면
                            //5~11
                            weekviews_1.setText(weekview_nuber[4]);
                            weekviews_2.setText(weekview_nuber[5]);
                            weekviews_3.setText(weekview_nuber[6]);
                            weekviews_4.setText(weekview_nuber[7]);
                            weekviews_5.setText(weekview_nuber[8]);
                            weekviews_6.setText(weekview_nuber[9]);
                            weekviews_7.setText(weekview_nuber[10]);

                            break;
                        case "12": case "13": case "14": case "15": case "16": case "17": case "18":     //2일이면
                            //12~18
                            weekviews_1.setText(weekview_nuber[11]);
                            weekviews_2.setText(weekview_nuber[12]);
                            weekviews_3.setText(weekview_nuber[13]);
                            weekviews_4.setText(weekview_nuber[14]);
                            weekviews_5.setText(weekview_nuber[15]);
                            weekviews_6.setText(weekview_nuber[16]);
                            weekviews_7.setText(weekview_nuber[17]);

                            break;
                        case "19": case "20": case "21": case "22": case "23": case "24": case "25":       //3일이면
                            //19~25
                            weekviews_1.setText(weekview_nuber[18]);
                            weekviews_2.setText(weekview_nuber[29]);
                            weekviews_3.setText(weekview_nuber[20]);
                            weekviews_4.setText(weekview_nuber[21]);
                            weekviews_5.setText(weekview_nuber[22]);
                            weekviews_6.setText(weekview_nuber[23]);
                            weekviews_7.setText(weekview_nuber[24]);

                            break;
                        case "26": case "27": case "28": case "29": case "30": case "31":   //4일이면
                            //26~31
                            weekviews_1.setText(weekview_nuber[25]);
                            weekviews_2.setText(weekview_nuber[26]);
                            weekviews_3.setText(weekview_nuber[27]);
                            weekviews_4.setText(weekview_nuber[28]);
                            weekviews_5.setText(weekview_nuber[29]);
                            weekviews_6.setText(weekview_nuber[30]);
                            weekviews_7.setText(weekview_nuber[31]);

                            break;

                    }
                }



                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {  //목요일
                    switch(curDayFormat.format(date)) { //오늘이 몇 일인가?
                        case "1": case "2": case "3":   //1일이면


                            //1~3
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[0]);
                            weekviews_6.setText(weekview_nuber[1]);
                            weekviews_7.setText(weekview_nuber[2]);

                            break;
                        case "4": case "5":case "6": case "7": case "8": case "9": case "10":   //1일이면
                            //4~10
                            weekviews_1.setText(weekview_nuber[3]);
                            weekviews_2.setText(weekview_nuber[4]);
                            weekviews_3.setText(weekview_nuber[5]);
                            weekviews_4.setText(weekview_nuber[6]);
                            weekviews_5.setText(weekview_nuber[7]);
                            weekviews_6.setText(weekview_nuber[8]);
                            weekviews_7.setText(weekview_nuber[9]);

                            break;
                        case "11": case "12": case "13": case "14": case "15": case "16": case "17":     //2일이면
                            //11~17
                            weekviews_1.setText(weekview_nuber[10]);
                            weekviews_2.setText(weekview_nuber[11]);
                            weekviews_3.setText(weekview_nuber[12]);
                            weekviews_4.setText(weekview_nuber[13]);
                            weekviews_5.setText(weekview_nuber[14]);
                            weekviews_6.setText(weekview_nuber[15]);
                            weekviews_7.setText(weekview_nuber[16]);

                            break;
                        case "18": case "19": case "20": case "21": case "22": case "23": case "24":       //3일이면
                            //18~24
                            weekviews_1.setText(weekview_nuber[17]);
                            weekviews_2.setText(weekview_nuber[18]);
                            weekviews_3.setText(weekview_nuber[19]);
                            weekviews_4.setText(weekview_nuber[20]);
                            weekviews_5.setText(weekview_nuber[21]);
                            weekviews_6.setText(weekview_nuber[22]);
                            weekviews_7.setText(weekview_nuber[23]);

                            break;
                        case "25": case "26": case "27": case "28": case "29": case "30": case "31":   //4일이면
                            //25~31
                            weekviews_1.setText(weekview_nuber[24]);
                            weekviews_2.setText(weekview_nuber[25]);
                            weekviews_3.setText(weekview_nuber[26]);
                            weekviews_4.setText(weekview_nuber[27]);
                            weekviews_5.setText(weekview_nuber[28]);
                            weekviews_6.setText(weekview_nuber[29]);
                            weekviews_7.setText(weekview_nuber[30]);

                            break;

                    }
                }



                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {  //금요일
                    switch(curDayFormat.format(date)) { //오늘이 몇 일인가?
                        case "1": case "2":   //1일이면


                            //1~2
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[0]);
                            weekviews_7.setText(weekview_nuber[1]);

                            break;
                        case "3": case "4": case "5": case "6": case "7": case "8": case "9":   //1일이면
                            //4~10
                            weekviews_1.setText(weekview_nuber[2]);
                            weekviews_2.setText(weekview_nuber[3]);
                            weekviews_3.setText(weekview_nuber[4]);
                            weekviews_4.setText(weekview_nuber[5]);
                            weekviews_5.setText(weekview_nuber[6]);
                            weekviews_6.setText(weekview_nuber[7]);
                            weekviews_7.setText(weekview_nuber[8]);

                            break;
                        case "10": case "11": case "12": case "13": case "14": case "15": case "16":     //2일이면
                            //11~17
                            weekviews_1.setText(weekview_nuber[9]);
                            weekviews_2.setText(weekview_nuber[10]);
                            weekviews_3.setText(weekview_nuber[11]);
                            weekviews_4.setText(weekview_nuber[12]);
                            weekviews_5.setText(weekview_nuber[13]);
                            weekviews_6.setText(weekview_nuber[14]);
                            weekviews_7.setText(weekview_nuber[15]);

                            break;
                        case "17": case "18": case "19": case "20": case "21": case "22": case "23":       //3일이면
                            //18~24
                            weekviews_1.setText(weekview_nuber[16]);
                            weekviews_2.setText(weekview_nuber[17]);
                            weekviews_3.setText(weekview_nuber[18]);
                            weekviews_4.setText(weekview_nuber[19]);
                            weekviews_5.setText(weekview_nuber[20]);
                            weekviews_6.setText(weekview_nuber[21]);
                            weekviews_7.setText(weekview_nuber[22]);

                            break;
                        case "24": case "25": case "26": case "27": case "28": case "29": case "30":    //4일이면
                            //25~31
                            weekviews_1.setText(weekview_nuber[23]);
                            weekviews_2.setText(weekview_nuber[24]);
                            weekviews_3.setText(weekview_nuber[25]);
                            weekviews_4.setText(weekview_nuber[26]);
                            weekviews_5.setText(weekview_nuber[27]);
                            weekviews_6.setText(weekview_nuber[28]);
                            weekviews_7.setText(weekview_nuber[29]);

                            break;

                        case "31":
                            weekviews_1.setText(weekview_nuber[30]);
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[31]);

                            break;

                    }
                }


                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {  //토요일
                    switch(curDayFormat.format(date)) { //오늘이 몇 일인가?
                        case "1":    //1일이면


                            //1~2
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[0]);

                            break;
                        case "2": case "3": case "4": case "5": case "6": case "7": case "8":    //1일이면
                            //4~10
                            weekviews_1.setText(weekview_nuber[1]);
                            weekviews_2.setText(weekview_nuber[2]);
                            weekviews_3.setText(weekview_nuber[3]);
                            weekviews_4.setText(weekview_nuber[4]);
                            weekviews_5.setText(weekview_nuber[5]);
                            weekviews_6.setText(weekview_nuber[6]);
                            weekviews_7.setText(weekview_nuber[7]);

                            break;
                        case "9": case "10": case "11": case "12": case "13": case "14": case "15":      //2일이면
                            //11~17
                            weekviews_1.setText(weekview_nuber[8]);
                            weekviews_2.setText(weekview_nuber[9]);
                            weekviews_3.setText(weekview_nuber[10]);
                            weekviews_4.setText(weekview_nuber[11]);
                            weekviews_5.setText(weekview_nuber[12]);
                            weekviews_6.setText(weekview_nuber[13]);
                            weekviews_7.setText(weekview_nuber[14]);

                            break;
                        case "16": case "17": case "18": case "19": case "20": case "21": case "22":        //3일이면
                            //18~24
                            weekviews_1.setText(weekview_nuber[15]);
                            weekviews_2.setText(weekview_nuber[16]);
                            weekviews_3.setText(weekview_nuber[17]);
                            weekviews_4.setText(weekview_nuber[18]);
                            weekviews_5.setText(weekview_nuber[19]);
                            weekviews_6.setText(weekview_nuber[20]);
                            weekviews_7.setText(weekview_nuber[21]);

                            break;
                        case "23": case "24": case "25": case "26": case "27": case "28": case "29":     //4일이면
                            //25~31
                            weekviews_1.setText(weekview_nuber[22]);
                            weekviews_2.setText(weekview_nuber[23]);
                            weekviews_3.setText(weekview_nuber[24]);
                            weekviews_4.setText(weekview_nuber[25]);
                            weekviews_5.setText(weekview_nuber[26]);
                            weekviews_6.setText(weekview_nuber[27]);
                            weekviews_7.setText(weekview_nuber[28]);

                            break;

                        case "30": case "31":
                            weekviews_1.setText(weekview_nuber[29]);
                            weekviews_2.setText(weekview_nuber[30]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[31]);

                            break;

                    }
                }






            }
        });
        //////////////////////////////////////////////////////////////////////////////
        //저번주로 이동
        //7일 전 날짜 이용.

        last_week.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                //초기화
                tvDate_week.setText("");


                mCal = null;
                gridView_week.setAdapter(null);

                weekviews_1.setText("");
                weekviews_2.setText("");
                weekviews_3.setText("");
                weekviews_4.setText("");
                weekviews_5.setText("");
                weekviews_6.setText("");
                weekviews_7.setText("");



                // 오늘에 날짜를 세팅 해준다.
                long now = System.currentTimeMillis();
                final Date date = new Date(now);



                TimeZone jst = TimeZone.getTimeZone("JST");
                final Calendar cal = Calendar.getInstance(jst); // 주어진 시간대에 맞게 현재 시각으로 초기화된 GregorianCalender 객체를 반환.// 또는 Calendar now = Calendar.getInstance(Locale.KOREA);

                //연,월,일을 따로 저장
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM/yyyy", Locale.KOREAN);
                final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
                final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
                final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);




                tvDate_week.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));




                    //위의 textview 부분에서 날짜를 변경하는 부분.

                /*
                    ver--;
                if ((cal.get(Calendar.MONTH) + ver) == 0) {
                    //그 전년도로 이동
                    ver = 0;
                    year++;
                    tvDate.setText((cal.get(Calendar.YEAR) - year) + "/" + (cal.get(Calendar.MONTH) + 7 + ver));

                }else if((cal.get(Calendar.YEAR) - year) != 2020) {
                    tvDate.setText((cal.get(Calendar.YEAR) - year) + "/" + (cal.get(Calendar.MONTH) + 7 + ver));
                }else {

                    tvDate.setText((cal.get(Calendar.YEAR) - year) + "/" + (cal.get(Calendar.MONTH) + ver));
                }



                 */




                // Integer.parseInt(curMonthFormat.format(date)) - 1    <= 오늘의 달



                addday = addday - 7;


                //각 주마다 요일 배치하기.

                mCal = Calendar.getInstance();
                mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, Integer.parseInt(curDayFormat.format(date)) - 7);   //오늘날에서 7일전


                /*

                if (mCal.get(Calendar.MONTH) == Integer.parseInt(curMonthFormat.format(date)) - 1){ //이부분 틀린듯
                    //textview 값 가져와서 비교해보자. 오늘 달이랑.
                    //현재 날짜 텍스트뷰에 뿌려줌
                    tvDate_week.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));
                }else { //현재 날짜가 아니라는 뜻
                    lastmonth++;
                    tvDate_week.setText(curYearFormat.format(date) + "/" + (mCal.get(Calendar.MONTH) - lastmonth));
                }


                 */




                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {  //일요일
                    switch(Integer.parseInt(curDayFormat.format(date)) + addday) { //오늘이 몇 일인가?
                        case 1: case 2: case 3: case 4: case 5: case 6: case 7://1일이면


                            weekviews_1.setText(weekview_nuber[0]);
                            weekviews_2.setText(weekview_nuber[1]);
                            weekviews_3.setText(weekview_nuber[2]);
                            weekviews_4.setText(weekview_nuber[3]);
                            weekviews_5.setText(weekview_nuber[4]);
                            weekviews_6.setText(weekview_nuber[5]);
                            weekviews_7.setText(weekview_nuber[6]);

                            break;
                        case 8: case 9: case 10: case 11: case 12: case 13: case 14://1일이면

                            weekviews_1.setText(weekview_nuber[7]);
                            weekviews_2.setText(weekview_nuber[8]);
                            weekviews_3.setText(weekview_nuber[9]);
                            weekviews_4.setText(weekview_nuber[10]);
                            weekviews_5.setText(weekview_nuber[11]);
                            weekviews_6.setText(weekview_nuber[12]);
                            weekviews_7.setText(weekview_nuber[13]);

                            break;
                        case 15: case 16: case 17: case 18: case 19: case 20: case 21:   //2일이면
                            //15~21
                            weekviews_1.setText(weekview_nuber[14]);
                            weekviews_2.setText(weekview_nuber[15]);
                            weekviews_3.setText(weekview_nuber[16]);
                            weekviews_4.setText(weekview_nuber[17]);
                            weekviews_5.setText(weekview_nuber[18]);
                            weekviews_6.setText(weekview_nuber[19]);
                            weekviews_7.setText(weekview_nuber[20]);

                            break;
                        case 22: case 23: case 24: case 25: case 26: case 27: case 28:    //3일이면
                            //22~28
                            weekviews_1.setText(weekview_nuber[21]);
                            weekviews_2.setText(weekview_nuber[22]);
                            weekviews_3.setText(weekview_nuber[23]);
                            weekviews_4.setText(weekview_nuber[24]);
                            weekviews_5.setText(weekview_nuber[25]);
                            weekviews_6.setText(weekview_nuber[26]);
                            weekviews_7.setText(weekview_nuber[27]);

                            break;
                        case 29: case 30: case 31:   //4일이면
                            //29~31
                            weekviews_1.setText(weekview_nuber[28]);
                            weekviews_2.setText(weekview_nuber[29]);
                            weekviews_3.setText(weekview_nuber[30]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[31]);


                            break;

                    }
                }


                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {  //월요일
                    switch(Integer.parseInt(curDayFormat.format(date)) + addday) { //오늘이 몇 일인가?
                        case 1: case 2: case 3: case 4: case 5: case 6: //1일이면


                            //1~6
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[0]);
                            weekviews_3.setText(weekview_nuber[1]);
                            weekviews_4.setText(weekview_nuber[2]);
                            weekviews_5.setText(weekview_nuber[3]);
                            weekviews_6.setText(weekview_nuber[4]);
                            weekviews_7.setText(weekview_nuber[5]);

                            break;
                        case 7: case 8: case 9: case 10: case 11: case 12: case 13: //1일이면
                            //7~13
                            weekviews_1.setText(weekview_nuber[6]);
                            weekviews_2.setText(weekview_nuber[7]);
                            weekviews_3.setText(weekview_nuber[8]);
                            weekviews_4.setText(weekview_nuber[9]);
                            weekviews_5.setText(weekview_nuber[10]);
                            weekviews_6.setText(weekview_nuber[11]);
                            weekviews_7.setText(weekview_nuber[12]);

                            break;
                        case 14: case 15: case 16: case 17: case 18: case 19: case 20:   //2일이면
                            //14~20
                            weekviews_1.setText(weekview_nuber[13]);
                            weekviews_2.setText(weekview_nuber[14]);
                            weekviews_3.setText(weekview_nuber[15]);
                            weekviews_4.setText(weekview_nuber[16]);
                            weekviews_5.setText(weekview_nuber[17]);
                            weekviews_6.setText(weekview_nuber[18]);
                            weekviews_7.setText(weekview_nuber[19]);

                            break;
                        case 21: case 22: case 23: case 24: case 25: case 26: case 27:     //3일이면
                            //21~27
                            weekviews_1.setText(weekview_nuber[20]);
                            weekviews_2.setText(weekview_nuber[21]);
                            weekviews_3.setText(weekview_nuber[22]);
                            weekviews_4.setText(weekview_nuber[23]);
                            weekviews_5.setText(weekview_nuber[24]);
                            weekviews_6.setText(weekview_nuber[25]);
                            weekviews_7.setText(weekview_nuber[26]);

                            break;
                        case 28: case 29: case 30: case 31:   //4일이면
                            //28~31
                            weekviews_1.setText(weekview_nuber[27]);
                            weekviews_2.setText(weekview_nuber[28]);
                            weekviews_3.setText(weekview_nuber[29]);
                            weekviews_4.setText(weekview_nuber[30]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[31]);

                            break;

                    }
                }


                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {  //화요일
                    switch(Integer.parseInt(curDayFormat.format(date)) + addday) { //오늘이 몇 일인가?
                        case 1: case 2: case 3: case 4:   //1일이면


                            //1~5
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[0]);
                            weekviews_4.setText(weekview_nuber[1]);
                            weekviews_5.setText(weekview_nuber[2]);
                            weekviews_6.setText(weekview_nuber[3]);
                            weekviews_7.setText(weekview_nuber[4]);

                            break;
                        case 5: case 6: case 7: case 8: case 9: case 10: case 11:   //1일이면
                            //6~12
                            weekviews_1.setText(weekview_nuber[5]);
                            weekviews_2.setText(weekview_nuber[6]);
                            weekviews_3.setText(weekview_nuber[7]);
                            weekviews_4.setText(weekview_nuber[8]);
                            weekviews_5.setText(weekview_nuber[9]);
                            weekviews_6.setText(weekview_nuber[10]);
                            weekviews_7.setText(weekview_nuber[11]);

                            break;
                        case 12: case 13: case 14: case 15: case 16: case 17: case 18:     //2일이면
                            //13~19
                            weekviews_1.setText(weekview_nuber[12]);
                            weekviews_2.setText(weekview_nuber[13]);
                            weekviews_3.setText(weekview_nuber[14]);
                            weekviews_4.setText(weekview_nuber[15]);
                            weekviews_5.setText(weekview_nuber[16]);
                            weekviews_6.setText(weekview_nuber[17]);
                            weekviews_7.setText(weekview_nuber[18]);

                            break;
                        case 19: case 20: case 21: case 22: case 23: case 24: case 25:       //3일이면
                            //20~26
                            weekviews_1.setText(weekview_nuber[19]);
                            weekviews_2.setText(weekview_nuber[20]);
                            weekviews_3.setText(weekview_nuber[21]);
                            weekviews_4.setText(weekview_nuber[22]);
                            weekviews_5.setText(weekview_nuber[23]);
                            weekviews_6.setText(weekview_nuber[24]);
                            weekviews_7.setText(weekview_nuber[25]);

                            break;
                        case 26: case 27: case 28: case 29: case 30: case 31:   //4일이면
                            //27~31
                            weekviews_1.setText(weekview_nuber[26]);
                            weekviews_2.setText(weekview_nuber[27]);
                            weekviews_3.setText(weekview_nuber[28]);
                            weekviews_4.setText(weekview_nuber[29]);
                            weekviews_5.setText(weekview_nuber[30]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[31]);

                            break;

                    }
                }



                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {  //수요일
                    switch(Integer.parseInt(curDayFormat.format(date)) + addday) { //오늘이 몇 일인가?
                        case 1: case 2: case 3: case 4:   //1일이면


                            //1~4
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[0]);
                            weekviews_5.setText(weekview_nuber[1]);
                            weekviews_6.setText(weekview_nuber[2]);
                            weekviews_7.setText(weekview_nuber[3]);

                            break;
                        case 5: case 6: case 7: case 8: case 9: case 10: case 11:   //1일이면
                            //5~11
                            weekviews_1.setText(weekview_nuber[4]);
                            weekviews_2.setText(weekview_nuber[5]);
                            weekviews_3.setText(weekview_nuber[6]);
                            weekviews_4.setText(weekview_nuber[7]);
                            weekviews_5.setText(weekview_nuber[8]);
                            weekviews_6.setText(weekview_nuber[9]);
                            weekviews_7.setText(weekview_nuber[10]);

                            break;
                        case 12: case 13: case 14: case 15: case 16: case 17: case 18:     //2일이면
                            //12~18
                            weekviews_1.setText(weekview_nuber[11]);
                            weekviews_2.setText(weekview_nuber[12]);
                            weekviews_3.setText(weekview_nuber[13]);
                            weekviews_4.setText(weekview_nuber[14]);
                            weekviews_5.setText(weekview_nuber[15]);
                            weekviews_6.setText(weekview_nuber[16]);
                            weekviews_7.setText(weekview_nuber[17]);

                            break;
                        case 19: case 20: case 21: case 22: case 23: case 24: case 25:       //3일이면
                            //19~25
                            weekviews_1.setText(weekview_nuber[18]);
                            weekviews_2.setText(weekview_nuber[29]);
                            weekviews_3.setText(weekview_nuber[20]);
                            weekviews_4.setText(weekview_nuber[21]);
                            weekviews_5.setText(weekview_nuber[22]);
                            weekviews_6.setText(weekview_nuber[23]);
                            weekviews_7.setText(weekview_nuber[24]);

                            break;
                        case 26: case 27: case 28: case 29: case 30: case 31:   //4일이면
                            //26~31
                            weekviews_1.setText(weekview_nuber[25]);
                            weekviews_2.setText(weekview_nuber[26]);
                            weekviews_3.setText(weekview_nuber[27]);
                            weekviews_4.setText(weekview_nuber[28]);
                            weekviews_5.setText(weekview_nuber[29]);
                            weekviews_6.setText(weekview_nuber[30]);
                            weekviews_7.setText(weekview_nuber[31]);

                            break;

                    }
                }



                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {  //목요일
                    switch(Integer.parseInt(curDayFormat.format(date)) + addday) { //오늘이 몇 일인가?
                        case 1: case 2: case 3:   //1일이면


                            //1~3
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[0]);
                            weekviews_6.setText(weekview_nuber[1]);
                            weekviews_7.setText(weekview_nuber[2]);

                            break;
                        case 4: case 5:case 6: case 7: case 8: case 9: case 10:   //1일이면
                            //4~10
                            weekviews_1.setText(weekview_nuber[3]);
                            weekviews_2.setText(weekview_nuber[4]);
                            weekviews_3.setText(weekview_nuber[5]);
                            weekviews_4.setText(weekview_nuber[6]);
                            weekviews_5.setText(weekview_nuber[7]);
                            weekviews_6.setText(weekview_nuber[8]);
                            weekviews_7.setText(weekview_nuber[9]);

                            break;
                        case 11: case 12: case 13: case 14: case 15: case 16: case 17:     //2일이면
                            //11~17
                            weekviews_1.setText(weekview_nuber[10]);
                            weekviews_2.setText(weekview_nuber[11]);
                            weekviews_3.setText(weekview_nuber[12]);
                            weekviews_4.setText(weekview_nuber[13]);
                            weekviews_5.setText(weekview_nuber[14]);
                            weekviews_6.setText(weekview_nuber[15]);
                            weekviews_7.setText(weekview_nuber[16]);

                            break;
                        case 18: case 19: case 20: case 21: case 22: case 23: case 24:       //3일이면
                            //18~24
                            weekviews_1.setText(weekview_nuber[17]);
                            weekviews_2.setText(weekview_nuber[18]);
                            weekviews_3.setText(weekview_nuber[19]);
                            weekviews_4.setText(weekview_nuber[20]);
                            weekviews_5.setText(weekview_nuber[21]);
                            weekviews_6.setText(weekview_nuber[22]);
                            weekviews_7.setText(weekview_nuber[23]);

                            break;
                        case 25: case 26: case 27: case 28: case 29: case 30: case 31:   //4일이면
                            //25~31
                            weekviews_1.setText(weekview_nuber[24]);
                            weekviews_2.setText(weekview_nuber[25]);
                            weekviews_3.setText(weekview_nuber[26]);
                            weekviews_4.setText(weekview_nuber[27]);
                            weekviews_5.setText(weekview_nuber[28]);
                            weekviews_6.setText(weekview_nuber[29]);
                            weekviews_7.setText(weekview_nuber[30]);

                            break;

                    }
                }



                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {  //금요일
                    switch(Integer.parseInt(curDayFormat.format(date)) + addday) { //오늘이 몇 일인가?
                        case 1: case 2:   //1일이면


                            //1~2
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[0]);
                            weekviews_7.setText(weekview_nuber[1]);

                            break;
                        case 3: case 4: case 5: case 6: case 7: case 8: case 9:   //1일이면
                            //4~10
                            weekviews_1.setText(weekview_nuber[2]);
                            weekviews_2.setText(weekview_nuber[3]);
                            weekviews_3.setText(weekview_nuber[4]);
                            weekviews_4.setText(weekview_nuber[5]);
                            weekviews_5.setText(weekview_nuber[6]);
                            weekviews_6.setText(weekview_nuber[7]);
                            weekviews_7.setText(weekview_nuber[8]);

                            break;
                        case 10: case 11: case 12: case 13: case 14: case 15: case 16:     //2일이면
                            //11~17
                            weekviews_1.setText(weekview_nuber[9]);
                            weekviews_2.setText(weekview_nuber[10]);
                            weekviews_3.setText(weekview_nuber[11]);
                            weekviews_4.setText(weekview_nuber[12]);
                            weekviews_5.setText(weekview_nuber[13]);
                            weekviews_6.setText(weekview_nuber[14]);
                            weekviews_7.setText(weekview_nuber[15]);

                            break;
                        case 17: case 18: case 19: case 20: case 21: case 22: case 23:       //3일이면
                            //18~24
                            weekviews_1.setText(weekview_nuber[16]);
                            weekviews_2.setText(weekview_nuber[17]);
                            weekviews_3.setText(weekview_nuber[18]);
                            weekviews_4.setText(weekview_nuber[19]);
                            weekviews_5.setText(weekview_nuber[20]);
                            weekviews_6.setText(weekview_nuber[21]);
                            weekviews_7.setText(weekview_nuber[22]);

                            break;
                        case 24: case 25: case 26: case 27: case 28: case 29: case 30:    //4일이면
                            //25~31
                            weekviews_1.setText(weekview_nuber[23]);
                            weekviews_2.setText(weekview_nuber[24]);
                            weekviews_3.setText(weekview_nuber[25]);
                            weekviews_4.setText(weekview_nuber[26]);
                            weekviews_5.setText(weekview_nuber[27]);
                            weekviews_6.setText(weekview_nuber[28]);
                            weekviews_7.setText(weekview_nuber[29]);

                            break;

                        case 31:
                            weekviews_1.setText(weekview_nuber[30]);
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[31]);

                            break;

                    }
                }


                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {  //토요일
                    switch(Integer.parseInt(curDayFormat.format(date)) + addday) { //오늘이 몇 일인가?
                        case 1:    //1일이면


                            //1~2
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[0]);

                            break;
                        case 2: case 3: case 4: case 5: case 6: case 7: case 8:    //1일이면
                            //4~10
                            weekviews_1.setText(weekview_nuber[1]);
                            weekviews_2.setText(weekview_nuber[2]);
                            weekviews_3.setText(weekview_nuber[3]);
                            weekviews_4.setText(weekview_nuber[4]);
                            weekviews_5.setText(weekview_nuber[5]);
                            weekviews_6.setText(weekview_nuber[6]);
                            weekviews_7.setText(weekview_nuber[7]);

                            break;
                        case 9: case 10: case 11: case 12: case 13: case 14: case 15:      //2일이면
                            //11~17
                            weekviews_1.setText(weekview_nuber[8]);
                            weekviews_2.setText(weekview_nuber[9]);
                            weekviews_3.setText(weekview_nuber[10]);
                            weekviews_4.setText(weekview_nuber[11]);
                            weekviews_5.setText(weekview_nuber[12]);
                            weekviews_6.setText(weekview_nuber[13]);
                            weekviews_7.setText(weekview_nuber[14]);

                            break;
                        case 16: case 17: case 18: case 19: case 20: case 21: case 22:        //3일이면
                            //18~24
                            weekviews_1.setText(weekview_nuber[15]);
                            weekviews_2.setText(weekview_nuber[16]);
                            weekviews_3.setText(weekview_nuber[17]);
                            weekviews_4.setText(weekview_nuber[18]);
                            weekviews_5.setText(weekview_nuber[19]);
                            weekviews_6.setText(weekview_nuber[20]);
                            weekviews_7.setText(weekview_nuber[21]);

                            break;
                        case 23: case 24: case 25: case 26: case 27: case 28: case 29:     //4일이면
                            //25~31
                            weekviews_1.setText(weekview_nuber[22]);
                            weekviews_2.setText(weekview_nuber[23]);
                            weekviews_3.setText(weekview_nuber[24]);
                            weekviews_4.setText(weekview_nuber[25]);
                            weekviews_5.setText(weekview_nuber[26]);
                            weekviews_6.setText(weekview_nuber[27]);
                            weekviews_7.setText(weekview_nuber[28]);

                            break;

                        case 30: case 31:
                            weekviews_1.setText(weekview_nuber[29]);
                            weekviews_2.setText(weekview_nuber[30]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[31]);

                            break;

                    }
                }





















            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //다음주로 이동

        next_week.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //초기화
                tvDate_week.setText("");


                mCal = null;
                gridView_week.setAdapter(null);

                weekviews_1.setText("");
                weekviews_2.setText("");
                weekviews_3.setText("");
                weekviews_4.setText("");
                weekviews_5.setText("");
                weekviews_6.setText("");
                weekviews_7.setText("");



                // 오늘에 날짜를 세팅 해준다.
                long now = System.currentTimeMillis();
                final Date date = new Date(now);



                TimeZone jst = TimeZone.getTimeZone("JST");
                final Calendar cal = Calendar.getInstance(jst); // 주어진 시간대에 맞게 현재 시각으로 초기화된 GregorianCalender 객체를 반환.// 또는 Calendar now = Calendar.getInstance(Locale.KOREA);

                //연,월,일을 따로 저장
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM/yyyy", Locale.KOREAN);
                final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
                final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
                final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

                //현재 날짜 텍스트뷰에 뿌려줌
                tvDate_week.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));







                addday = addday + 7;


                //각 주마다 요일 배치하기.

                mCal = Calendar.getInstance();
                mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, Integer.parseInt(curDayFormat.format(date)) + 7);   //오늘날에서 7일전


                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {  //일요일
                    switch(Integer.parseInt(curDayFormat.format(date)) + addday) { //오늘이 몇 일인가?
                        case 1: case 2: case 3: case 4: case 5: case 6: case 7://1일이면


                            weekviews_1.setText(weekview_nuber[0]);
                            weekviews_2.setText(weekview_nuber[1]);
                            weekviews_3.setText(weekview_nuber[2]);
                            weekviews_4.setText(weekview_nuber[3]);
                            weekviews_5.setText(weekview_nuber[4]);
                            weekviews_6.setText(weekview_nuber[5]);
                            weekviews_7.setText(weekview_nuber[6]);

                            break;
                        case 8: case 9: case 10: case 11: case 12: case 13: case 14://1일이면

                            weekviews_1.setText(weekview_nuber[7]);
                            weekviews_2.setText(weekview_nuber[8]);
                            weekviews_3.setText(weekview_nuber[9]);
                            weekviews_4.setText(weekview_nuber[10]);
                            weekviews_5.setText(weekview_nuber[11]);
                            weekviews_6.setText(weekview_nuber[12]);
                            weekviews_7.setText(weekview_nuber[13]);

                            break;
                        case 15: case 16: case 17: case 18: case 19: case 20: case 21:   //2일이면
                            //15~21
                            weekviews_1.setText(weekview_nuber[14]);
                            weekviews_2.setText(weekview_nuber[15]);
                            weekviews_3.setText(weekview_nuber[16]);
                            weekviews_4.setText(weekview_nuber[17]);
                            weekviews_5.setText(weekview_nuber[18]);
                            weekviews_6.setText(weekview_nuber[19]);
                            weekviews_7.setText(weekview_nuber[20]);

                            break;
                        case 22: case 23: case 24: case 25: case 26: case 27: case 28:    //3일이면
                            //22~28
                            weekviews_1.setText(weekview_nuber[21]);
                            weekviews_2.setText(weekview_nuber[22]);
                            weekviews_3.setText(weekview_nuber[23]);
                            weekviews_4.setText(weekview_nuber[24]);
                            weekviews_5.setText(weekview_nuber[25]);
                            weekviews_6.setText(weekview_nuber[26]);
                            weekviews_7.setText(weekview_nuber[27]);

                            break;
                        case 29: case 30: case 31:   //4일이면
                            //29~31
                            weekviews_1.setText(weekview_nuber[28]);
                            weekviews_2.setText(weekview_nuber[29]);
                            weekviews_3.setText(weekview_nuber[30]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[31]);


                            break;

                    }
                }


                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {  //월요일
                    switch(Integer.parseInt(curDayFormat.format(date)) + addday) { //오늘이 몇 일인가?
                        case 1: case 2: case 3: case 4: case 5: case 6: //1일이면


                            //1~6
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[0]);
                            weekviews_3.setText(weekview_nuber[1]);
                            weekviews_4.setText(weekview_nuber[2]);
                            weekviews_5.setText(weekview_nuber[3]);
                            weekviews_6.setText(weekview_nuber[4]);
                            weekviews_7.setText(weekview_nuber[5]);

                            break;
                        case 7: case 8: case 9: case 10: case 11: case 12: case 13: //1일이면
                            //7~13
                            weekviews_1.setText(weekview_nuber[6]);
                            weekviews_2.setText(weekview_nuber[7]);
                            weekviews_3.setText(weekview_nuber[8]);
                            weekviews_4.setText(weekview_nuber[9]);
                            weekviews_5.setText(weekview_nuber[10]);
                            weekviews_6.setText(weekview_nuber[11]);
                            weekviews_7.setText(weekview_nuber[12]);

                            break;
                        case 14: case 15: case 16: case 17: case 18: case 19: case 20:   //2일이면
                            //14~20
                            weekviews_1.setText(weekview_nuber[13]);
                            weekviews_2.setText(weekview_nuber[14]);
                            weekviews_3.setText(weekview_nuber[15]);
                            weekviews_4.setText(weekview_nuber[16]);
                            weekviews_5.setText(weekview_nuber[17]);
                            weekviews_6.setText(weekview_nuber[18]);
                            weekviews_7.setText(weekview_nuber[19]);

                            break;
                        case 21: case 22: case 23: case 24: case 25: case 26: case 27:     //3일이면
                            //21~27
                            weekviews_1.setText(weekview_nuber[20]);
                            weekviews_2.setText(weekview_nuber[21]);
                            weekviews_3.setText(weekview_nuber[22]);
                            weekviews_4.setText(weekview_nuber[23]);
                            weekviews_5.setText(weekview_nuber[24]);
                            weekviews_6.setText(weekview_nuber[25]);
                            weekviews_7.setText(weekview_nuber[26]);

                            break;
                        case 28: case 29: case 30: case 31:   //4일이면
                            //28~31
                            weekviews_1.setText(weekview_nuber[27]);
                            weekviews_2.setText(weekview_nuber[28]);
                            weekviews_3.setText(weekview_nuber[29]);
                            weekviews_4.setText(weekview_nuber[30]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[31]);

                            break;

                    }
                }


                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {  //화요일
                    switch(Integer.parseInt(curDayFormat.format(date)) + addday) { //오늘이 몇 일인가?
                        case 1: case 2: case 3: case 4:   //1일이면


                            //1~5
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[0]);
                            weekviews_4.setText(weekview_nuber[1]);
                            weekviews_5.setText(weekview_nuber[2]);
                            weekviews_6.setText(weekview_nuber[3]);
                            weekviews_7.setText(weekview_nuber[4]);

                            break;
                        case 5: case 6: case 7: case 8: case 9: case 10: case 11:   //1일이면
                            //6~12
                            weekviews_1.setText(weekview_nuber[5]);
                            weekviews_2.setText(weekview_nuber[6]);
                            weekviews_3.setText(weekview_nuber[7]);
                            weekviews_4.setText(weekview_nuber[8]);
                            weekviews_5.setText(weekview_nuber[9]);
                            weekviews_6.setText(weekview_nuber[10]);
                            weekviews_7.setText(weekview_nuber[11]);

                            break;
                        case 12: case 13: case 14: case 15: case 16: case 17: case 18:     //2일이면
                            //13~19
                            weekviews_1.setText(weekview_nuber[12]);
                            weekviews_2.setText(weekview_nuber[13]);
                            weekviews_3.setText(weekview_nuber[14]);
                            weekviews_4.setText(weekview_nuber[15]);
                            weekviews_5.setText(weekview_nuber[16]);
                            weekviews_6.setText(weekview_nuber[17]);
                            weekviews_7.setText(weekview_nuber[18]);

                            break;
                        case 19: case 20: case 21: case 22: case 23: case 24: case 25:       //3일이면
                            //20~26
                            weekviews_1.setText(weekview_nuber[19]);
                            weekviews_2.setText(weekview_nuber[20]);
                            weekviews_3.setText(weekview_nuber[21]);
                            weekviews_4.setText(weekview_nuber[22]);
                            weekviews_5.setText(weekview_nuber[23]);
                            weekviews_6.setText(weekview_nuber[24]);
                            weekviews_7.setText(weekview_nuber[25]);

                            break;
                        case 26: case 27: case 28: case 29: case 30: case 31:   //4일이면
                            //27~31
                            weekviews_1.setText(weekview_nuber[26]);
                            weekviews_2.setText(weekview_nuber[27]);
                            weekviews_3.setText(weekview_nuber[28]);
                            weekviews_4.setText(weekview_nuber[29]);
                            weekviews_5.setText(weekview_nuber[30]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[31]);

                            break;

                    }
                }



                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {  //수요일
                    switch(Integer.parseInt(curDayFormat.format(date)) + addday) { //오늘이 몇 일인가?
                        case 1: case 2: case 3: case 4:   //1일이면


                            //1~4
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[0]);
                            weekviews_5.setText(weekview_nuber[1]);
                            weekviews_6.setText(weekview_nuber[2]);
                            weekviews_7.setText(weekview_nuber[3]);

                            break;
                        case 5: case 6: case 7: case 8: case 9: case 10: case 11:   //1일이면
                            //5~11
                            weekviews_1.setText(weekview_nuber[4]);
                            weekviews_2.setText(weekview_nuber[5]);
                            weekviews_3.setText(weekview_nuber[6]);
                            weekviews_4.setText(weekview_nuber[7]);
                            weekviews_5.setText(weekview_nuber[8]);
                            weekviews_6.setText(weekview_nuber[9]);
                            weekviews_7.setText(weekview_nuber[10]);

                            break;
                        case 12: case 13: case 14: case 15: case 16: case 17: case 18:     //2일이면
                            //12~18
                            weekviews_1.setText(weekview_nuber[11]);
                            weekviews_2.setText(weekview_nuber[12]);
                            weekviews_3.setText(weekview_nuber[13]);
                            weekviews_4.setText(weekview_nuber[14]);
                            weekviews_5.setText(weekview_nuber[15]);
                            weekviews_6.setText(weekview_nuber[16]);
                            weekviews_7.setText(weekview_nuber[17]);

                            break;
                        case 19: case 20: case 21: case 22: case 23: case 24: case 25:       //3일이면
                            //19~25
                            weekviews_1.setText(weekview_nuber[18]);
                            weekviews_2.setText(weekview_nuber[29]);
                            weekviews_3.setText(weekview_nuber[20]);
                            weekviews_4.setText(weekview_nuber[21]);
                            weekviews_5.setText(weekview_nuber[22]);
                            weekviews_6.setText(weekview_nuber[23]);
                            weekviews_7.setText(weekview_nuber[24]);

                            break;
                        case 26: case 27: case 28: case 29: case 30: case 31:   //4일이면
                            //26~31
                            weekviews_1.setText(weekview_nuber[25]);
                            weekviews_2.setText(weekview_nuber[26]);
                            weekviews_3.setText(weekview_nuber[27]);
                            weekviews_4.setText(weekview_nuber[28]);
                            weekviews_5.setText(weekview_nuber[29]);
                            weekviews_6.setText(weekview_nuber[30]);
                            weekviews_7.setText(weekview_nuber[31]);

                            break;

                    }
                }



                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {  //목요일
                    switch(Integer.parseInt(curDayFormat.format(date)) + addday) { //오늘이 몇 일인가?
                        case 1: case 2: case 3:   //1일이면


                            //1~3
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[0]);
                            weekviews_6.setText(weekview_nuber[1]);
                            weekviews_7.setText(weekview_nuber[2]);

                            break;
                        case 4: case 5:case 6: case 7: case 8: case 9: case 10:   //1일이면
                            //4~10
                            weekviews_1.setText(weekview_nuber[3]);
                            weekviews_2.setText(weekview_nuber[4]);
                            weekviews_3.setText(weekview_nuber[5]);
                            weekviews_4.setText(weekview_nuber[6]);
                            weekviews_5.setText(weekview_nuber[7]);
                            weekviews_6.setText(weekview_nuber[8]);
                            weekviews_7.setText(weekview_nuber[9]);

                            break;
                        case 11: case 12: case 13: case 14: case 15: case 16: case 17:     //2일이면
                            //11~17
                            weekviews_1.setText(weekview_nuber[10]);
                            weekviews_2.setText(weekview_nuber[11]);
                            weekviews_3.setText(weekview_nuber[12]);
                            weekviews_4.setText(weekview_nuber[13]);
                            weekviews_5.setText(weekview_nuber[14]);
                            weekviews_6.setText(weekview_nuber[15]);
                            weekviews_7.setText(weekview_nuber[16]);

                            break;
                        case 18: case 19: case 20: case 21: case 22: case 23: case 24:       //3일이면
                            //18~24
                            weekviews_1.setText(weekview_nuber[17]);
                            weekviews_2.setText(weekview_nuber[18]);
                            weekviews_3.setText(weekview_nuber[19]);
                            weekviews_4.setText(weekview_nuber[20]);
                            weekviews_5.setText(weekview_nuber[21]);
                            weekviews_6.setText(weekview_nuber[22]);
                            weekviews_7.setText(weekview_nuber[23]);

                            break;
                        case 25: case 26: case 27: case 28: case 29: case 30: case 31:   //4일이면
                            //25~31
                            weekviews_1.setText(weekview_nuber[24]);
                            weekviews_2.setText(weekview_nuber[25]);
                            weekviews_3.setText(weekview_nuber[26]);
                            weekviews_4.setText(weekview_nuber[27]);
                            weekviews_5.setText(weekview_nuber[28]);
                            weekviews_6.setText(weekview_nuber[29]);
                            weekviews_7.setText(weekview_nuber[30]);

                            break;

                    }
                }



                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {  //금요일
                    switch(Integer.parseInt(curDayFormat.format(date)) + addday) { //오늘이 몇 일인가?
                        case 1: case 2:   //1일이면


                            //1~2
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[0]);
                            weekviews_7.setText(weekview_nuber[1]);

                            break;
                        case 3: case 4: case 5: case 6: case 7: case 8: case 9:   //1일이면
                            //4~10
                            weekviews_1.setText(weekview_nuber[2]);
                            weekviews_2.setText(weekview_nuber[3]);
                            weekviews_3.setText(weekview_nuber[4]);
                            weekviews_4.setText(weekview_nuber[5]);
                            weekviews_5.setText(weekview_nuber[6]);
                            weekviews_6.setText(weekview_nuber[7]);
                            weekviews_7.setText(weekview_nuber[8]);

                            break;
                        case 10: case 11: case 12: case 13: case 14: case 15: case 16:     //2일이면
                            //11~17
                            weekviews_1.setText(weekview_nuber[9]);
                            weekviews_2.setText(weekview_nuber[10]);
                            weekviews_3.setText(weekview_nuber[11]);
                            weekviews_4.setText(weekview_nuber[12]);
                            weekviews_5.setText(weekview_nuber[13]);
                            weekviews_6.setText(weekview_nuber[14]);
                            weekviews_7.setText(weekview_nuber[15]);

                            break;
                        case 17: case 18: case 19: case 20: case 21: case 22: case 23:       //3일이면
                            //18~24
                            weekviews_1.setText(weekview_nuber[16]);
                            weekviews_2.setText(weekview_nuber[17]);
                            weekviews_3.setText(weekview_nuber[18]);
                            weekviews_4.setText(weekview_nuber[19]);
                            weekviews_5.setText(weekview_nuber[20]);
                            weekviews_6.setText(weekview_nuber[21]);
                            weekviews_7.setText(weekview_nuber[22]);

                            break;
                        case 24: case 25: case 26: case 27: case 28: case 29: case 30:    //4일이면
                            //25~31
                            weekviews_1.setText(weekview_nuber[23]);
                            weekviews_2.setText(weekview_nuber[24]);
                            weekviews_3.setText(weekview_nuber[25]);
                            weekviews_4.setText(weekview_nuber[26]);
                            weekviews_5.setText(weekview_nuber[27]);
                            weekviews_6.setText(weekview_nuber[28]);
                            weekviews_7.setText(weekview_nuber[29]);

                            break;

                        case 31:
                            weekviews_1.setText(weekview_nuber[30]);
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[31]);

                            break;

                    }
                }


                ///////
                if (mCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {  //토요일
                    switch(Integer.parseInt(curDayFormat.format(date)) + addday) { //오늘이 몇 일인가?
                        case 1:    //1일이면


                            //1~2
                            weekviews_1.setText(weekview_nuber[31]);    //빈 값.
                            weekviews_2.setText(weekview_nuber[31]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[0]);

                            break;
                        case 2: case 3: case 4: case 5: case 6: case 7: case 8:    //1일이면
                            //4~10
                            weekviews_1.setText(weekview_nuber[1]);
                            weekviews_2.setText(weekview_nuber[2]);
                            weekviews_3.setText(weekview_nuber[3]);
                            weekviews_4.setText(weekview_nuber[4]);
                            weekviews_5.setText(weekview_nuber[5]);
                            weekviews_6.setText(weekview_nuber[6]);
                            weekviews_7.setText(weekview_nuber[7]);

                            break;
                        case 9: case 10: case 11: case 12: case 13: case 14: case 15:      //2일이면
                            //11~17
                            weekviews_1.setText(weekview_nuber[8]);
                            weekviews_2.setText(weekview_nuber[9]);
                            weekviews_3.setText(weekview_nuber[10]);
                            weekviews_4.setText(weekview_nuber[11]);
                            weekviews_5.setText(weekview_nuber[12]);
                            weekviews_6.setText(weekview_nuber[13]);
                            weekviews_7.setText(weekview_nuber[14]);

                            break;
                        case 16: case 17: case 18: case 19: case 20: case 21: case 22:        //3일이면
                            //18~24
                            weekviews_1.setText(weekview_nuber[15]);
                            weekviews_2.setText(weekview_nuber[16]);
                            weekviews_3.setText(weekview_nuber[17]);
                            weekviews_4.setText(weekview_nuber[18]);
                            weekviews_5.setText(weekview_nuber[19]);
                            weekviews_6.setText(weekview_nuber[20]);
                            weekviews_7.setText(weekview_nuber[21]);

                            break;
                        case 23: case 24: case 25: case 26: case 27: case 28: case 29:     //4일이면
                            //25~31
                            weekviews_1.setText(weekview_nuber[22]);
                            weekviews_2.setText(weekview_nuber[23]);
                            weekviews_3.setText(weekview_nuber[24]);
                            weekviews_4.setText(weekview_nuber[25]);
                            weekviews_5.setText(weekview_nuber[26]);
                            weekviews_6.setText(weekview_nuber[27]);
                            weekviews_7.setText(weekview_nuber[28]);

                            break;

                        case 30: case 31:
                            weekviews_1.setText(weekview_nuber[29]);
                            weekviews_2.setText(weekview_nuber[30]);
                            weekviews_3.setText(weekview_nuber[31]);
                            weekviews_4.setText(weekview_nuber[31]);
                            weekviews_5.setText(weekview_nuber[31]);
                            weekviews_6.setText(weekview_nuber[31]);
                            weekviews_7.setText(weekview_nuber[31]);

                            break;

                    }
                }














            }
        });


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

        Toast toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);

        switch (item.getItemId()) {
            case R.id.month:

                toast.setText("month");
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);

                break;


            case R.id.week:
                toast.setText("week");
                Intent homeIntents = new Intent(this, weekactivity2.class);
                startActivity(homeIntents);

                break;
        }

        toast.show();

        return super.onOptionsItemSelected(item);
    }



    /**
     * 해당 월에 표시할 일 수 구함
     *
     * @param month
     */
    public void setCalendarDate(int month) {
        mCal.set(Calendar.MONTH, month - 1);

        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            dayList.add("" + (i + 1));
        }




    }



    /**
     * 그리드뷰 어댑터
     *
     */
    public class GridAdapter extends BaseAdapter {

        private final List<String> list;

        private final LayoutInflater inflater;

        /**
         * 생성자
         *
         * @param context
         * @param list
         */
        public GridAdapter(Context context, List<String> list) {
            this.list = list;
            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            MainActivity.ViewHolder holder = null;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
                holder = new MainActivity.ViewHolder();

                holder.tvItemGridView = (TextView)convertView.findViewById(R.id.tv_item_gridview);

                convertView.setTag(holder);
            } else {
                holder = (MainActivity.ViewHolder)convertView.getTag();
            }
            holder.tvItemGridView.setText("" + getItem(position));



            //해당 날짜 텍스트 컬러,배경 변경
            mCal = Calendar.getInstance();
            //오늘 day 가져옴
            Integer today = mCal.get(Calendar.DAY_OF_MONTH);
            String sToday = String.valueOf(today);
            if (sToday.equals(getItem(position))) { //오늘 day 텍스트 컬러 변경
                holder.tvItemGridView.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            return convertView;
        }
    }

    public static class ViewHolder {
        TextView tvItemGridView;
    }

}