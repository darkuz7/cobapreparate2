package mx.edu.cobaev.preparate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import mx.edu.cobaev.preparate.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {


    public static Database db_sqlite;
    public static Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.requestWindowFeature(Window.FEATURE_ACTION_BAR);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide(); //<< this

        activity = this;
        db_sqlite = new Database(activity);
        final SQLiteDatabase db = db_sqlite.getWritableDatabase();
        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Cursor tutor = db.rawQuery("select * from tutor", null);
                String datos = "no";
                if (tutor.moveToFirst()){
                    datos = tutor.getString(1);
                }
                Log.i("datos d5 ", datos);



                if (datos.length() > 2 ){
                    Intent myIntent = new Intent(MainActivity.this, Principal.class);
                    // myIntent.putExtra("key", value); //Optional parameters
                    startActivity(myIntent);

                } else {
                    Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                    // myIntent.putExtra("key", value); //Optional parameters
                    startActivity(myIntent);
                }

                finish();

            }
        }.start();
    }
}