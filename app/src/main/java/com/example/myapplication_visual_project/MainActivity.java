package com.example.myapplication_visual_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {


    LinearLayout baseLayout;
    Button button1;
    Button last;
    Button next;
    Button todaybutton;

    int year = 0;
    int ver = 1;
    int monthlist = 0;

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

        tvDate = (TextView)findViewById(R.id.tv_date);
        gridView = (GridView)findViewById(R.id.gridview);



        last = (Button) findViewById(R.id.last) ;
        next = (Button) findViewById(R.id.next) ;
        todaybutton = (Button) findViewById(R.id.todaybutton) ;



        // 오늘에 날짜를 세팅 해준다.
        long now = System.currentTimeMillis();
        final Date date = new Date(now);



        //int h = 30;

        //내일
        final Date today = new Date ( );
        //final Date nextmonth = new Date ( today.getTime ( ) + (long) ( 1000 * 60 * 60 * 24 * h) );



        TimeZone jst = TimeZone.getTimeZone ("JST");
        final Calendar cal = Calendar.getInstance ( jst ); // 주어진 시간대에 맞게 현재 시각으로 초기화된 GregorianCalender 객체를 반환.// 또는 Calendar now = Calendar.getInstance(Locale.KOREA);
        //System.out.println ( cal.get ( Calendar.YEAR ) + "년 " + ( cal.get ( Calendar.MONTH ) + 1 ) + "월 " + cal.get ( Calendar.DATE ) + "일 " + cal.get ( Calendar.HOUR_OF_DAY ) + "시 " +cal.get ( Calendar.MINUTE ) + "분 " + cal.get ( Calendar.SECOND ) + "초 " );




        //final Calendar calendar = Calendar.getInstance(Locale.KOREAN);

        //연,월,일을 따로 저장
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM/yyyy", Locale.KOREAN);
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        //현재 날짜 텍스트뷰에 뿌려줌
        tvDate.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));

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



        //////////////////////////////////////////////////////////////////////////
        //오늘 버튼을 눌렀을때 오늘로 이동
        todaybutton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //초기화
                tvDate.setText("");


                mCal = null;
                gridView.setAdapter(null);


                // 오늘에 날짜를 세팅 해준다.
                long now = System.currentTimeMillis();
                final Date date = new Date(now);



                //int h = 30;

                //내일
                final Date today = new Date ( );
                //final Date nextmonth = new Date ( today.getTime ( ) + (long) ( 1000 * 60 * 60 * 24 * h) );



                TimeZone jst = TimeZone.getTimeZone ("JST");
                final Calendar cal = Calendar.getInstance ( jst ); // 주어진 시간대에 맞게 현재 시각으로 초기화된 GregorianCalender 객체를 반환.// 또는 Calendar now = Calendar.getInstance(Locale.KOREA);
                //System.out.println ( cal.get ( Calendar.YEAR ) + "년 " + ( cal.get ( Calendar.MONTH ) + 1 ) + "월 " + cal.get ( Calendar.DATE ) + "일 " + cal.get ( Calendar.HOUR_OF_DAY ) + "시 " +cal.get ( Calendar.MINUTE ) + "분 " + cal.get ( Calendar.SECOND ) + "초 " );




                //final Calendar calendar = Calendar.getInstance(Locale.KOREAN);

                //연,월,일을 따로 저장
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM/yyyy", Locale.KOREAN);
                final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
                final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
                final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

                //현재 날짜 텍스트뷰에 뿌려줌
                tvDate.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));

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



                //위의 textview 부분에서 날짜를 변경하는 부분.

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
                mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1 - monthlist, 1);
                int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
                //1일 - 요일 매칭 시키기 위해 공백 add
                for (int i = 1; i < dayNum; i++) {

                    dayList.add("");
                }




                setCalendarDate(mCal.get(Calendar.MONTH) - 1);
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

                if ((cal.get(Calendar.MONTH) + ver) == 13) {
                    //그 다음년도로 이동
                    ver = 0;
                    year++;
                    tvDate.setText((cal.get(Calendar.YEAR) + year) + "/" + (cal.get(Calendar.MONTH) - 5 + ver));

                }else if((cal.get(Calendar.YEAR) + year) != 2020) {
                    tvDate.setText((cal.get(Calendar.YEAR) + year) + "/" + (cal.get(Calendar.MONTH) -5 + ver));
                }else {

                    tvDate.setText((cal.get(Calendar.YEAR) + year) + "/" + (cal.get(Calendar.MONTH) + ver));
                }








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
                mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date))-1 + monthlist, 1);
                int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
                //1일 - 요일 매칭 시키기 위해 공백 add
                for (int i = 1; i < dayNum; i++) {

                    dayList.add("");
                }




                setCalendarDate(mCal.get(Calendar.MONTH) - 1);
                gridAdapter = new GridAdapter(getApplicationContext(), dayList);
                gridView.setAdapter(gridAdapter);








                /*




                //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
                mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);
                int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
                //1일 - 요일 매칭 시키기 위해 공백 add
                for (int i = 1; i < dayNum; i++) {
                    dayList.add("");
                }




                setCalendarDate(mCal.get(Calendar.MONTH) - 1);
                gridAdapter = new GridAdapter(getApplicationContext(), dayList);
                gridView.setAdapter(gridAdapter);






                /*
                setCalendarDate(mCal.get(Calendar.MONTH));
                gridAdapter = new GridAdapter(getApplicationContext(), dayList);
                gridView.setAdapter(gridAdapter);







                /*
                String j = curMonthFormat.format(date);


                //현재 날짜 텍스트뷰에 뿌려줌
                tvDate.setText(curYearFormat.format(date) + "/" + j);

                //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
                mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) + 1, 1);
                int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
                //1일 - 요일 매칭 시키기 위해 공백 add
                for (int i = 1; i < dayNum; i++) {
                    dayList.add("");
                }


                /*

                setCalendarDate(mCal.get(Calendar.MONTH) + 1);
                gridAdapter = new GridAdapter(getApplicationContext(), dayList);
                gridView.setAdapter(gridAdapter);



                 */





            }
        });





    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater mInflater = getMenuInflater();

        if ( v == button1) {
            //menu.setHeaderTitle("s");

            mInflater.inflate(R.menu.menu1, menu);
        }


    }







    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //super.onOptionsItemSelected(item);

        Toast toast = Toast.makeText(getApplicationContext(),"", Toast.LENGTH_LONG);

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



    /**
     * 그리드뷰 어댑터
     *
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

            ViewHolder holder = null;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
                holder = new ViewHolder();

                holder.tvItemGridView = (TextView)convertView.findViewById(R.id.tv_item_gridview);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
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