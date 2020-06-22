
package com.example.myapplication_visual_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    LinearLayout baseLayout;
    Button button1;
    Button last;
    Button next;
    Button todaybutton;

    int year = 0;
    int ver = 1;
    int monthlist = 0;
    String file_month;

    View dialogView;
    String fileName;
    EditText edtDiary;

    int yearcount = 0;


    /**
     * 연/월 텍스트뷰
     */
    TextView tvDate;
    /**
     * 그리드뷰 어댑터
     */
    GridAdapter gridAdapter;

    /**
     * 일 저장 할 리스트
     */
    ArrayList<String> dayList;


    /**
     * 그리드뷰
     */
    GridView gridView;

    /**
     * 캘린더 변수
     */
    Calendar mCal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle("달력");

        baseLayout = (LinearLayout) findViewById(R.id.baseLayout);
        button1 = (Button) findViewById(R.id.button1);
        registerForContextMenu(button1);

        tvDate = (TextView) findViewById(R.id.tv_date);
        gridView = (GridView) findViewById(R.id.gridview);


        last = (Button) findViewById(R.id.last);
        next = (Button) findViewById(R.id.next);
        todaybutton = (Button) findViewById(R.id.todaybutton);


        // 오늘에 날짜를 세팅 해준다.
        long now = System.currentTimeMillis();
        final Date date = new Date(now);



        //내일
        final Date today = new Date();
        //final Date nextmonth = new Date ( today.getTime ( ) + (long) ( 1000 * 60 * 60 * 24 * h) );


        TimeZone jst = TimeZone.getTimeZone("JST");
        final Calendar cal = Calendar.getInstance(jst); // 주어진 시간대에 맞게 현재 시각으로 초기화된 GregorianCalender 객체를 반환.// 또는 Calendar now = Calendar.getInstance(Locale.KOREA);
        //System.out.println ( cal.get ( Calendar.YEAR ) + "년 " + ( cal.get ( Calendar.MONTH ) + 1 ) + "월 " + cal.get ( Calendar.DATE ) + "일 " + cal.get ( Calendar.HOUR_OF_DAY ) + "시 " +cal.get ( Calendar.MINUTE ) + "분 " + cal.get ( Calendar.SECOND ) + "초 " );



        //연,월,일을 따로 저장
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM/yyyy", Locale.KOREAN);
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("M", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        //현재 날짜 텍스트뷰에 뿌려줌
        tvDate.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));
        file_month = curMonthFormat.format(date);

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

        gridAdapter = new GridAdapter(getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);

        //gridView클릭시..
        gridView.setOnItemClickListener(this);




        //////////////////////////////////////////////////////////////////////////
        //오늘 버튼을 눌렀을때 오늘로 이동
        todaybutton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //초기화
                tvDate.setText("");


                mCal = null;
                gridView.setAdapter(null);
                ver = 1;
                monthlist = 0;
                yearcount = 0;
                year = 1;


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
                final SimpleDateFormat curMonthFormat = new SimpleDateFormat("M", Locale.KOREA);
                final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

                //현재 날짜 텍스트뷰에 뿌려줌
                tvDate.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));
                file_month = curMonthFormat.format(date);

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

                gridAdapter = new GridAdapter(getApplicationContext(), dayList);
                gridView.setAdapter(gridAdapter);


            }


        });


        //////////////////////////////////////////////////////////////////////////////
        //저번달로 이동

        last.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //curMonthFormat.add(Calendar.MONTH, -1);

                //calendar.add(Calendar.MONTH, -1);


                //초기화
                tvDate.setText("");


                mCal = null;
                gridView.setAdapter(null);


                ver--;

                tvDate.setText((cal.get(Calendar.YEAR) + yearcount) + "/" + (cal.get(Calendar.MONTH) + ver));
                if ((cal.get(Calendar.MONTH) + ver) <= 0){
                    yearcount--;
                    //ver  바꿈.
                    //만약 현재가 1월이면..
                    //ver = 11
                    if (cal.get(Calendar.MONTH) == 1) {
                        ver = 11;
                    }
                    //만약 현재가 2월이면..
                    if (cal.get(Calendar.MONTH) == 2) {
                        ver = 10;
                    }
                    //만약 현재가 3월이면..
                    if (cal.get(Calendar.MONTH) == 3) {
                        ver = 9;
                    }
                    //만약 현재가 4월이면..
                    if (cal.get(Calendar.MONTH) == 4) {
                        ver = 8;
                    }
                    //만약 현재가 5월이면..
                    if (cal.get(Calendar.MONTH) == 5) {
                        ver = 7;
                    }
                    //만약 현재가 6월이면..
                    if (cal.get(Calendar.MONTH) == 6) {
                        ver = 6;
                    }
                    //만약 현재가 7월이면..
                    if (cal.get(Calendar.MONTH) == 7) {
                        ver = 5;
                    }
                    //만약 현재가 8월이면..
                    if (cal.get(Calendar.MONTH) == 8) {
                        ver = 4;
                    }
                    //만약 현재가 9월이면..
                    if (cal.get(Calendar.MONTH) == 9) {
                        ver = 3;
                    }
                    //만약 현재가 10월이면..
                    if (cal.get(Calendar.MONTH) == 10) {
                        ver = 2;
                    }
                    //만약 현재가 11월이면..
                    if (cal.get(Calendar.MONTH) == 11) {
                        ver = 1;
                    }
                    //만약 현재가 12월이면..
                    if (cal.get(Calendar.MONTH) == 12) {
                        ver = 0;
                    }
                    tvDate.setText((cal.get(Calendar.YEAR) + yearcount) + "/" + (cal.get(Calendar.MONTH) + ver));
                }




                file_month = String.valueOf((cal.get(Calendar.MONTH) + ver));


                //밑의 gridview부분. 날짜를 표시해준다.

                gridAdapter = null; //날짜 부분에 널 값을 넣어준다. 초기화 시켜준다.


                dayList = new ArrayList<>();    //list 초기화.


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


                monthlist--;


                //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
                mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1 + monthlist, 1);
                int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
                //1일 - 요일 매칭 시키기 위해 공백 add
                for (int i = 1; i < dayNum; i++) {

                    dayList.add("");
                }


                setCalendarDate(mCal.get(Calendar.MONTH) + 1);
                gridAdapter = new GridAdapter(getApplicationContext(), dayList);
                gridView.setAdapter(gridAdapter);


            }
        });


        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //다음달로 이동

        next.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //curMonthFormat.add(Calendar.MONTH, -1);

                //일단 1년치 왔다 갔다는 만들었음.


                //textview 초기화
                tvDate.setText("");

                //gridview 초기화
                gridView.setAdapter(null);

                ver++;


                tvDate.setText((cal.get(Calendar.YEAR) + yearcount) + "/" + (cal.get(Calendar.MONTH) + ver));
                if ((cal.get(Calendar.MONTH) + ver) > 12){
                    yearcount++;
                    //ver  바꿈.
                    //만약 현재가 1월이면..
                    //ver = 11
                    if (cal.get(Calendar.MONTH) == 1) {
                        ver = 0;
                    }
                    //만약 현재가 2월이면..
                    if (cal.get(Calendar.MONTH) == 2) {
                        ver = -1;
                    }
                    //만약 현재가 3월이면..
                    if (cal.get(Calendar.MONTH) == 3) {
                        ver = -2;
                    }
                    //만약 현재가 4월이면..
                    if (cal.get(Calendar.MONTH) == 4) {
                        ver = -3;
                    }
                    //만약 현재가 5월이면..
                    if (cal.get(Calendar.MONTH) == 5) {
                        ver = -4;
                    }
                    //만약 현재가 6월이면..
                    if (cal.get(Calendar.MONTH) == 6) {
                        ver = -5;
                    }
                    //만약 현재가 7월이면..
                    if (cal.get(Calendar.MONTH) == 7) {
                        ver = -6;
                    }
                    //만약 현재가 8월이면..
                    if (cal.get(Calendar.MONTH) == 8) {
                        ver = -7;
                    }
                    //만약 현재가 9월이면..
                    if (cal.get(Calendar.MONTH) == 9) {
                        ver = -8;
                    }
                    //만약 현재가 10월이면..
                    if (cal.get(Calendar.MONTH) == 10) {
                        ver = -9;
                    }
                    //만약 현재가 11월이면..
                    if (cal.get(Calendar.MONTH) == 11) {
                        ver = -10;
                    }
                    //만약 현재가 12월이면..
                    if (cal.get(Calendar.MONTH) == 12) {
                        ver = -11;
                    }
                    tvDate.setText((cal.get(Calendar.YEAR) + yearcount) + "/" + (cal.get(Calendar.MONTH) + ver));
                }

                file_month = String.valueOf((cal.get(Calendar.MONTH) + ver));


                //밑의 gridview부분. 날짜를 표시해준다.

                gridAdapter = null; //날짜 부분에 널 값을 넣어준다. 초기화 시켜준다.


                dayList = new ArrayList<>();    //list 초기화.


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

                monthlist++;


                //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
                mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1 + monthlist, 1);
                int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
                //1일 - 요일 매칭 시키기 위해 공백 add
                for (int i = 1; i < dayNum; i++) {

                    dayList.add("");
                }


                setCalendarDate(mCal.get(Calendar.MONTH) + 1);
                gridAdapter = new GridAdapter(getApplicationContext(), dayList);
                gridView.setAdapter(gridAdapter);


            }

        });


    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater mInflater = getMenuInflater();

        if (v == button1) {
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        View dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog, null);
        //AlertDialog.Builder dlg = new AlartDialog.Builder(MainActivity.this)


    }



    /*
    //만들어질 다이얼로그
    @Override
    public Dialog onCreateDialog(int id) {
        super.onCreateDialog(id);
    }
     */


    /**
     * 그리드뷰 어댑터
     */
    public class GridAdapter extends BaseAdapter {

        final List<String> list;

        final LayoutInflater inflater;

        /**
         * 생성자
         *
         * @param context
         * @param list
         */
        public GridAdapter(Context context, List<String> list) {
            this.list = list;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
                holder = new ViewHolder();

                holder.tvItemGridView = (TextView) convertView.findViewById(R.id.tv_item_gridview); //item_calendar-gridview.xml안에 있는 것.

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvItemGridView.setText("" + getItem(position));  //네모부분에 보여주는 날짜(숫자)부분


            //날짜 클릭시 (gridview) 대화상자 보이기 (dialog)
            final ViewHolder finalHolder = holder;
            final ViewHolder finalHolder1 = holder;
            holder.tvItemGridView.setOnLongClickListener((new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    dialogView = (View) View.inflate(MainActivity.this,
                            R.layout.dialog, null);


                    //제목
                    final EditText dlogEdt1 = (EditText) dialogView.findViewById(R.id.dlgEdt1);

                    //시작 시간
                    final EditText dlogEdt2 = (EditText) dialogView.findViewById(R.id.dlgEdt2);

                    //시작 분
                    final EditText dlogEdt3 = (EditText) dialogView.findViewById(R.id.dlgEdt3);

                    //종료 시간
                    final EditText dlogEdt4 = (EditText) dialogView.findViewById(R.id.dlgEdt4);

                    //종료 분
                    final EditText dlogEdt5 = (EditText) dialogView.findViewById(R.id.dlgEdt5);

                    //장소
                    final EditText dlogEdt6 = (EditText) dialogView.findViewById(R.id.dlgEdt6);

                    //메모
                    final EditText dlogEdt7 = (EditText) dialogView.findViewById(R.id.dlgEdt7);


                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
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
                                //시작 시간
                                String value2 = dlogEdt2.getText().toString();
                                //시작 분
                                String value3 = dlogEdt3.getText().toString();
                                //종료 시간
                                String value4 = dlogEdt4.getText().toString();
                                //종료 분
                                String value5 = dlogEdt5.getText().toString();
                                //장소
                                String value6 = dlogEdt6.getText().toString();
                                //메모
                                String value7 = dlogEdt7.getText().toString();


                                //제목
                                FileOutputStream outFs = openFileOutput(file_month + "_" + getItem(position) + "_" + "1" + ".txt",
                                        Context.MODE_PRIVATE);
                                //윗 부분에서 "file.txt"부분이 파일 제목인데,
                                //이 부분을 파일 제목에 써줘야함.


                                //시작 시간
                                FileOutputStream outFs2 = openFileOutput(file_month + "_" + getItem(position) + "_" + "2" + ".txt",
                                        Context.MODE_PRIVATE);


                                //시작 분
                                FileOutputStream outFs3 = openFileOutput(file_month + "_" + getItem(position) + "_" + "3" + ".txt",
                                        Context.MODE_PRIVATE);

                                //종료 시간
                                FileOutputStream outFs4 = openFileOutput(file_month + "_" + getItem(position) + "_" + "4" + ".txt",
                                        Context.MODE_PRIVATE);


                                //종료 분
                                FileOutputStream outFs5 = openFileOutput(file_month + "_" + getItem(position) + "_" + "5" + ".txt",
                                        Context.MODE_PRIVATE);


                                //장소
                                FileOutputStream outFs6 = openFileOutput(file_month + "_" + getItem(position) + "_" + "6" + ".txt",
                                        Context.MODE_PRIVATE);


                                //메모
                                FileOutputStream outFs7 = openFileOutput(file_month + "_" + getItem(position) + "_" + "7" + ".txt",
                                        Context.MODE_PRIVATE);


                                //제목
                                outFs.write(value.getBytes());
                                outFs.close();
                                //시작시간
                                outFs2.write(value2.getBytes());
                                outFs2.close();
                                //시작 분
                                outFs3.write(value3.getBytes());
                                outFs3.close();
                                //종료 시간
                                outFs4.write(value4.getBytes());
                                outFs4.close();
                                //종료 분
                                outFs5.write(value5.getBytes());
                                outFs5.close();
                                //장소
                                outFs6.write(value6.getBytes());
                                outFs6.close();
                                //메모
                                outFs7.write(value7.getBytes());
                                outFs7.close();


                                Toast.makeText(getApplicationContext(), "글이 작성됨",
                                        Toast.LENGTH_SHORT).show();

                                finalHolder1.tvItemGridView.setTextColor(Color.RED);

                            } catch (IOException e) {
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
                            boolean inFs = deleteFile(file_month + "_" + getItem(position) + "_" + "1" + ".txt");

                                /*
                                byte[] txt = new byte[30];
                                inFs.read(txt);
                                String str = new String(txt);
                                dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                                inFs.close();
                                 */


                            //시작시간
                            boolean inFs2 = deleteFile(file_month + "_" + getItem(position) + "_" + "2" + ".txt");
                                /*
                                byte[] txt2 = new byte[30];
                                inFs2.read(txt2);
                                String str2 = new String(txt2);
                                dlogEdt2.setText(str2); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs2.close();
                                 */


                            //시작 분
                            boolean inFs3 = deleteFile(file_month + "_" + getItem(position) + "_" + "3" + ".txt");
                                /*
                                byte[] txt3 = new byte[30];
                                inFs3.read(txt3);
                                String str3 = new String(txt3);
                                dlogEdt3.setText(str3); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs3.close();
                                 */

                            //종료 시간
                            boolean inFs4 = deleteFile(file_month + "_" + getItem(position) + "_" + "4" + ".txt");
                                /*
                                byte[] txt4 = new byte[30];
                                inFs4.read(txt4);
                                String str4 = new String(txt4);
                                dlogEdt4.setText(str4); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs4.close();
                                 */


                            //종료 분
                            boolean inFs5 = deleteFile(file_month + "_" + getItem(position) + "_" + "5" + ".txt");
                                /*
                                byte[] txt5 = new byte[30];
                                inFs5.read(txt5);
                                String str5 = new String(txt5);
                                dlogEdt5.setText(str5); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs5.close();
                                 */

                            //장소
                            boolean inFs6 = deleteFile(file_month + "_" + getItem(position) + "_" + "6" + ".txt");
                                /*
                                byte[] txt6 = new byte[30];
                                inFs6.read(txt6);
                                String str6 = new String(txt6);
                                dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs6.close();
                                 */


                            //메모
                            boolean inFs7 = deleteFile(file_month + "_" + getItem(position) + "_" + "7" + ".txt");
                                /*
                                byte[] txt7 = new byte[30];
                                inFs7.read(txt7);
                                String str7 = new String(txt7);
                                dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                                inFs7.close();
                                 */
                                //holder.tvItemGridView.setTextColor(getResources().getColor(R.color.BLACK));

                            finalHolder.tvItemGridView.setTextColor(Color.BLACK);






                        }


                    });
                    dlg.show();


                    //이 밑 부분은 파일을 읽어오는 부분이다.
                    try {
                        //제목
                        FileInputStream inFs = openFileInput(file_month + "_" + getItem(position) + "_" + "1" + ".txt");
                        byte[] txt = new byte[30];
                        inFs.read(txt);
                        String str = new String(txt);


                        dlogEdt1.setText(str);  //제목부분 EditText부분에 내용 넣어주기.
                        inFs.close();


                        //시작시간
                        FileInputStream inFs2 = openFileInput(file_month + "_" + getItem(position) + "_" + "2" + ".txt");
                        byte[] txt2 = new byte[30];
                        inFs2.read(txt2);
                        String str2 = new String(txt2);


                        dlogEdt2.setText(str2); //시작시간 부분 EditText부분에 내용 넣어주기.
                        inFs2.close();


                        //시작 분
                        FileInputStream inFs3 = openFileInput(file_month + "_" + getItem(position) + "_" + "3" + ".txt");
                        byte[] txt3 = new byte[30];
                        inFs3.read(txt3);
                        String str3 = new String(txt3);


                        dlogEdt3.setText(str3); //시작시간 부분 EditText부분에 내용 넣어주기.
                        inFs3.close();

                        //종료 시간
                        FileInputStream inFs4 = openFileInput(file_month + "_" + getItem(position) + "_" + "4" + ".txt");
                        byte[] txt4 = new byte[30];
                        inFs4.read(txt4);
                        String str4 = new String(txt4);


                        dlogEdt4.setText(str4); //시작시간 부분 EditText부분에 내용 넣어주기.
                        inFs4.close();


                        //종료 분
                        FileInputStream inFs5 = openFileInput(file_month + "_" + getItem(position) + "_" + "5" + ".txt");
                        byte[] txt5 = new byte[30];
                        inFs5.read(txt5);
                        String str5 = new String(txt5);


                        dlogEdt5.setText(str5); //시작시간 부분 EditText부분에 내용 넣어주기.
                        inFs5.close();


                        //장소
                        FileInputStream inFs6 = openFileInput(file_month + "_" + getItem(position) + "_" + "6" + ".txt");
                        byte[] txt6 = new byte[30];
                        inFs6.read(txt6);
                        String str6 = new String(txt6);


                        dlogEdt6.setText(str6); //시작시간 부분 EditText부분에 내용 넣어주기.
                        inFs6.close();


                        //메모
                        FileInputStream inFs7 = openFileInput(file_month + "_" + getItem(position) + "_" + "7" + ".txt");
                        byte[] txt7 = new byte[30];
                        inFs7.read(txt7);
                        String str7 = new String(txt7);


                        dlogEdt7.setText(str7); //시작시간 부분 EditText부분에 내용 넣어주기.
                        inFs7.close();


                    } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.
                        Toast.makeText(getApplicationContext(), "내용 없음",
                                Toast.LENGTH_SHORT).show();
                    }

                    return false;
                }




            }));

            //이 밑 부분은 파일을 읽어오는 부분이다.
            try {
                //제목
                final FileInputStream inFs = openFileInput(file_month + "_" + getItem(position) + "_" + "1" + ".txt");
                byte[] txt = new byte[30];
                inFs.read(txt);
                inFs.close();


                //시작시간
                FileInputStream inFs2 = openFileInput(file_month + "_" + getItem(position) + "_" + "2" + ".txt");
                byte[] txt2 = new byte[30];
                inFs2.read(txt2);
                inFs2.close();


                //시작 분
                FileInputStream inFs3 = openFileInput(file_month + "_" + getItem(position) + "_" + "3" + ".txt");
                byte[] txt3 = new byte[30];
                inFs3.read(txt3);
                inFs3.close();

                //종료 시간
                FileInputStream inFs4 = openFileInput(file_month + "_" + getItem(position) + "_" + "4" + ".txt");
                byte[] txt4 = new byte[30];
                inFs4.read(txt4);
                inFs4.close();


                //종료 분
                FileInputStream inFs5 = openFileInput(file_month + "_" + getItem(position) + "_" + "5" + ".txt");
                byte[] txt5 = new byte[30];
                inFs5.read(txt5);
                inFs5.close();


                //장소
                FileInputStream inFs6 = openFileInput(file_month + "_" + getItem(position) + "_" + "6" + ".txt");
                byte[] txt6 = new byte[30];
                inFs6.read(txt6);
                inFs6.close();


                //메모
                FileInputStream inFs7 = openFileInput(file_month + "_" + getItem(position) + "_" + "7" + ".txt");
                byte[] txt7 = new byte[30];
                inFs7.read(txt7);
                inFs7.close();

                if (inFs != null) {    //파일 내용이 빈 것이 아니라면..




                    holder.tvItemGridView.setTextColor(Color.RED);






                }



            } catch (IOException e) { //파일이 없는 경우 오류가 발생하며 메시지를 보여준다.

            }





            return convertView;
        }
    }

    public static class ViewHolder {
        TextView tvItemGridView;
    }

}