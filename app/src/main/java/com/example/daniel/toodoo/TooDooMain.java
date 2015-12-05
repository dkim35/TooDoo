package com.example.daniel.toodoo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TooDooMain extends AppCompatActivity {
    static final int ADD_VIEW = 0;
    private static List<TextView> toodooList = new ArrayList<>();
    private ArrayList<String> textList = new ArrayList<>();
    private GestureDetector gestureDetector;
    private LinearLayout layout;
    int counter1 = 0;
//    private ArrayList<String> toodooList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_too_doo_main);
        setResult(RESULT_CANCELED);
        layout = (LinearLayout) findViewById(R.id.toodooMain);
        Log.i("CREAAAAAAAAAAAAAAAATE", "YYYAAAAAAAAAAAAAAAA");
        if (savedInstanceState != null) {
            int counter = PreferenceManager.getDefaultSharedPreferences(this).getInt("key2", 0);
            Log.i("counter on create", Integer.toString(counter));
            for (int i = 0; i < counter; i++) {
                layout.addView(createTooDoo(PreferenceManager.getDefaultSharedPreferences(this).getString(Integer.toString(i), "")), i);
            }
        }
        //this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        //textList = savedInstanceState.getStringArrayList("key");
//        if (savedInstanceState != null) {
//            textList = savedInstanceState.getStringArrayList("key");
//            int counter = 0;
//            Log.d("aaaaaaaa", "aaaaaaaaaaaaaaaaaaaaa");
//            if (textList != null) {
//                for (String t: textList) {
//                    layout.addView(createTooDoo(t));
//                }
//            }
//        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        LinearLayout layout = (LinearLayout) findViewById(R.id.toodooMain);
        super.onRestoreInstanceState(savedInstanceState);
        textList = savedInstanceState.getStringArrayList("key");
        int counter = 0;
        for (String t: textList) {
            toodooList.add(createTooDoo(t));
            layout.addView(createTooDoo(t));
        }
        Log.i("RESTORINSTANCE", "bbbbbbbbbbbbbbbbbbbbb");

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArrayList("key", textList);
        Log.i("SAVEINSTANCE", "cccccccccccccccccccccc");
    }

    protected void onPause() {
        super.onPause();
        //int counter = 0;
//        SharedPreferences.Editor editor1 = getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit();
//        for (String t: textList) {
//            editor1.putString(Integer.toString(counter), t);
//            counter++;
//        }
//        editor1.commit();
//        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().commit();
//        SharedPreferences sharedPreferencesSettings2 = this.getSharedPreferences("PREFERENCE2", MODE_PRIVATE);
//        SharedPreferences.Editor editor2 = sharedPreferencesSettings2.edit();
//        editor2.putInt("key2", counter);
//        editor2.commit();
        SharedPreferences sharedPreferencesSettings2 = this.getSharedPreferences("PREFERENCE2", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferencesSettings2.edit();
        editor2.putInt("key2", 0);
        Log.i("PAAAAAAAAAAUSE", Integer.toString(0));
        editor2.commit();
    }

    protected void onResume() {
        super.onResume();
        layout = (LinearLayout) findViewById(R.id.toodooMain);
        //int counter = PreferenceManager.getDefaultSharedPreferences(this).getInt("key2", 0);
        SharedPreferences prefs2 = this.getSharedPreferences("PREFERENCE2", MODE_PRIVATE);
        int counter = prefs2.getInt("key2", 0);
        Log.i("ONERESUME", Integer.toString(counter));
        SharedPreferences prefs = this.getSharedPreferences("PREFERENCE", MODE_PRIVATE);
//        if (counter > 0) {
//            layout.removeAllViews();
//        }
//        layout.removeAllViews();
//        if (counter == 0) {
//            layout.addView(createTooDoo(prefs.getString("0", "")));
//        }
        for (int i = 0; i < counter; i++) {
            layout.addView(createTooDoo(prefs.getString(Integer.toString(i), ""))); //here
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        int counter = 0;
        SharedPreferences.Editor editor1 = getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit();
        for (String t: textList) {
            editor1.putString(Integer.toString(counter), t).commit();
            counter++;
        }
//        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().commit();
//        SharedPreferences sharedPreferencesSettings2 = this.getSharedPreferences("PREFERENCE2", MODE_PRIVATE);
//        SharedPreferences.Editor editor2 = sharedPreferencesSettings2.edit();
//        editor2.putInt("key2", counter);
//        editor2.commit();
        editor1.commit();
        SharedPreferences sharedPreferencesSettings2 = this.getSharedPreferences("PREFERENCE2", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferencesSettings2.edit();
        editor2.putInt("key2", counter);
        Log.i("DESSSSTROY", Integer.toString(counter));
        editor2.commit();
    }

    private TextView createTooDoo(String subject) {
        final TextView toodoo = new TextView(this);
        final LinearLayout layout = (LinearLayout) findViewById(R.id.toodooMain);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , 180);
        toodoo.setLayoutParams(params);
        toodoo.setId(counter1);
        counter1++;
        params.setMargins(0, 1, 0, 1);
        toodoo.setText(subject);
        toodoo.setTextSize(18);
        toodoo.setTextColor(Color.WHITE);
        toodoo.setBackgroundColor(getResources().getColor(R.color.blue2));
        toodoo.setGravity(Gravity.CENTER_VERTICAL);
        toodoo.setPadding(25, 0, 0, 0);
        toodoo.setTypeface(null, Typeface.BOLD);
        toodoo.setOnTouchListener(new OnSwipeTouchListener(this, toodoo, layout) {
            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            public void onSwipeRight() {
                toodoo.animate().translationX(500).alpha(0f).setDuration(250).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //layout.removeView(toodoo);
                        Log.i("TESTINGANIMATOR", "AAAAAAAAAAAAA");
                        layout.removeView(toodoo);
                        Log.i("REMOVE111111111111111", Boolean.toString(textList.remove(toodoo.getText().toString())));
                        Log.i("REMOVE22222222222222", Boolean.toString(toodooList.remove(toodoo)));
                    }
                })
                        .start();
//                textList.remove(toodoo.getText().toString());
//                toodooList.remove(toodoo);
            }
        });
        return toodoo;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == ADD_VIEW) {
        if(resultCode == RESULT_OK) {
            final LinearLayout layout = (LinearLayout) findViewById(R.id.toodooMain);
            final TextView toodoo = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , 180);
            toodoo.setLayoutParams(params);
            params.setMargins(0, 1, 0, 1);
            String subject = data.getStringExtra("subject");
            String date = data.getStringExtra("date");
            //final TextView toodoo = createTooDoo(subject, date);
            if (date.equals("")) {
                toodoo.setText(subject);
            } else {
                toodoo.setText(date +"    " + subject);
            }
            toodoo.setId(counter1);
            counter1++;
            toodoo.setTextSize(18);
            toodoo.setTextColor(Color.WHITE);
            toodoo.setBackgroundColor(getResources().getColor(R.color.blue2));
            toodoo.setGravity(Gravity.CENTER_VERTICAL);
            toodoo.setPadding(25, 0, 0, 0);
            toodoo.setTypeface(null, Typeface.BOLD);
            toodoo.setOnTouchListener(new OnSwipeTouchListener(this, toodoo, layout) {
                @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
                public void onSwipeRight() {
                    toodoo.animate().translationX(500).alpha(0f).setDuration(250).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            layout.removeView(toodoo);
                            Log.i("REMOVE111111111111111", Boolean.toString(textList.remove(toodoo.getText().toString())));
                            Log.i("REMOVE22222222222222", Boolean.toString(toodooList.remove(toodoo)));
                        }
                    })
                    .start();

                }
//                @Override
//                public boolean onTouch(View arg0, MotionEvent arg1) {
//                    if (gestureDetector.onTouchEvent(arg1)) {
////                        Intent intent = new Intent(TooDooMain.this, TooDooAdd.class);
////                        startActivity(intent);
//                        return true;
//                    }
//                    return false;
//                }
//                public void onClick() {
//                    Intent intent = new Intent(TooDooMain.this, TooDooAdd.class);
//                    startActivity(intent);
//                }
            });
//            toodoo.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    Intent intent = new Intent(TooDooMain.this, TooDooAdd.class);
//                    startActivity(intent);
//                    return true;
//                }

                //@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
                //@Override
//                public void onLongClick(View v) {
////                    Intent intent = new Intent(TooDooMain.this, TooDooAdd.class);
////                    startActivity(intent);
//                    toodoo.animate().translationX(500).alpha(0f).setDuration(250).setListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            layout.removeView(toodoo);
//                        }
//                    })
//                            .start();
//                    toodooList.remove(toodoo);
//                }
            //});
            //layout.view
            toodooList.add(toodoo);
            Log.i("TOOOODOOOADDED", "TOOOOOOOOOOOOOOOODOOOOOOOOOOO");
            Collections.sort(toodooList, new toodooComparator());
            textList.clear();
            for (TextView t: toodooList) {
                Log.i("TEXTLIST", "ADDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDED");
                textList.add(t.getText().toString());
            }

            //java.util.Collections.sort(toodooList);
            //toodooList.
            int counter = 0;
            //layout.removeAllViews();
            for (TextView t: toodooList) {
                Log.i("TOODOOLIST", t.getText().toString());
                if (t.equals(toodoo)) {
                    layout.addView(toodoo, counter);
                    Log.i("TOOODOOLIST", Integer.toString(counter));
                    break;
                }
                //layout.removeView(toodoo);
//                if (counter > 0) {
//                    ((ViewGroup) toodoo.getParent()).removeView(toodoo);
//                }
//                layout.addView(toodoo);
                Log.i("TOOODOOLIST", Integer.toString(counter));
                counter++;
            }
            //layout.addView(toodoo);
        } else if (resultCode == RESULT_CANCELED) {
            startActivity(new Intent(this, TooDooMain.class));
        }
    }
    class toodooComparator implements Comparator<TextView> {
        @Override
        public int compare(TextView lhs, TextView rhs) {
            String l = lhs.getText().toString();
            String r = rhs.getText().toString();
            return l.compareTo(r);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_too_doo_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.addTooDoo:
                Intent intent = new Intent(this, TooDooAdd.class);
                startActivityForResult(intent, ADD_VIEW);
                return true;
            //case R.id.removeTooDoo:
//                TextView testText = (TextView) findViewById(R.id.testText);
//                testText.setText("Hi");
                //return true;
        }
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
