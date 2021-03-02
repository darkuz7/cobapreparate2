package mx.edu.cobaev.preparate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;


public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static Activity fa;
    public static FragmentManager supportFragment;
    public static NavigationView navigationView;
    public static Database db_sqlite;
    public static SQLiteDatabase db;
    public static String elcentrodude;
    String datos = "";
    boolean cargado = true;
    String TAG = "D5 aviso";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fa = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView nombre1 = headerView.findViewById(R.id.nombre1);
        TextView nombre2 = headerView.findViewById(R.id.nombre2);

        supportFragment = getSupportFragmentManager();

        Intent intent = getIntent();
        if (intent.hasExtra("data")) {
            datos = intent.getStringExtra("data");
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("data", datos);
            editor.apply();
        } else {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            datos = preferences.getString("data", "");

        }
        try {
            JSONObject jo = new JSONObject(datos);
            if (jo.has("datos")) {
                JSONObject data = jo.getJSONObject("datos");
                if (data.has("nombre")) {
                    nombre1.setText(data.getString("nombre"));
                    nombre2.setText(data.getString("centronombre"));
                    elcentrodude = data.getString("centro");
                }

            } else {
                elcentrodude = "0";
            }


        } catch (JSONException e) {
            e.printStackTrace();
            elcentrodude = "0";

        }

        db_sqlite = new Database(this);
        db = db_sqlite.getWritableDatabase();

        Log.i("D5", datos);
        xmenu(R.id.nav_home);
        navigationView.getMenu().getItem(0).setChecked(true);
        //showFragmentView(1);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.cobaev, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

  
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.i(TAG, "onNavigationItemSelected: nose que pedo");
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        Class fragmentClass;
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            xmenu(R.id.nav_home);
        } else if (id == R.id.nav_califica) {

            xmenu(R.id.nav_califica);

        } else if (id == R.id.nav_gallery) {

            xmenu(R.id.nav_gallery);


        } else if (id == R.id.nav_privacidad) {

            xmenu(R.id.nav_privacidad);

        } else if (id == R.id.nav_sesion) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Estás seguro de cerrar sesión?")
                    .setCancelable(false)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("data", "");
                            editor.apply();
                            Intent myIntent = new Intent(Principal.this, MainActivity.class);

                            FirebaseMessaging.getInstance().unsubscribeFromTopic("pl" + elcentrodude);
                            FirebaseMessaging.getInstance().unsubscribeFromTopic("general");

                            db.execSQL("delete from tutor");
                            db.execSQL("delete from hijos");

                            // myIntent.putExtra("key", value); //Optional parameters

                            myIntent.putExtra("cierra", "sip");
                            myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(myIntent);
                            finish();


                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        } else if (id == R.id.nav_send) {


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Estás seguro de que quieres salir?")
                    .setCancelable(false)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void xmenu(int id) {
        if (id == R.id.nav_home) {
            showFragmentView(0);
            Log.i("cargar", "Avisos");

        } else if (id == R.id.nav_califica) {
            showFragmentView(1);

            Log.i("cargar", "Eventos");
        } else if (id == R.id.nav_gallery) {
            showFragmentView(2);

            Log.i("cargar", "Eventos");
        } else if (id == R.id.nav_privacidad) {
            showFragmentView(3);

            Log.i("cargar", "Eventos");
        }
    }

    public void showFragmentView(int fragmentId) {
        Fragment f = null;
        Log.i(TAG, "showFragmentView: "+fragmentId);
        if (cargado) {
            switch (fragmentId) {
                case 0:
                    f = homeFragment.newInstance();

                    break;
                case 1:

                    f = AvisoFragment.newInstance("", "");
                    break;
                case 2:

                    f = AvisoFragment.newInstance("", "");
                    break;
                case 3:

                    f = AvisoFragment.newInstance("", "");
                    break;

                default:
                    f = AvisoFragment.newInstance("", "");
                    break;

            }
        } else {
            f = homeFragment.newInstance();
        }
        if (f != null) {
            try {
                supportFragment.beginTransaction()
                        .setCustomAnimations(R.anim.slide_up, R.anim.slide_off).replace(R.id.elfragment1, f).commit();


            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }
}
