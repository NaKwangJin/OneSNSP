package com.example.onesns;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.onesns.dialog.LogoutDialog;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;

    private TextView userInfoNameView;
    private TextView userInfoEmailView;

    private Button logoutBtn;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new MainContentFragment())
                    .commit();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new MainContentFragment())
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.item1:
                //Toast.makeText(this, "item1 clicked..", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new FriendRecyclerView())
                            .addToBackStack(null)
                            .commit();
                break;
            case R.id.item2:
                //Toast.makeText(this, "item2 clicked..", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,new ChatListFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.item3:
                //Toast.makeText(this, "item3 clicked..", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,new FindFriendFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.item4:
                // Toast.makeText(this, "item4 clicked..", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new CalenderActivity())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.item5:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new WithdrawFragment())
                        .addToBackStack(null)
                        .commit();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;

    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initLayout() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);

        logoutBtn = (Button)toolbar.findViewById(R.id.logout_btn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogoutDialog logoutDialog = new LogoutDialog(MainActivity.this, "");
                logoutDialog.show();
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.dl_main_drawer_root);
        navigationView = (NavigationView) findViewById(R.id.nv_main_navigation_root);
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        );
        drawerLayout.addDrawerListener(drawerToggle);
        navigationView.setNavigationItemSelectedListener(this);

        // Get HeaderView from Nav //
        View navView = navigationView.getHeaderView(0);
        userInfoNameView = navView.findViewById(R.id.userInfoName);
        userInfoEmailView = navView.findViewById(R.id.userInfoEmail);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("UserSession",MODE_PRIVATE);
        userInfoNameView.setText(sp.getString("UID","{UNKNOWN}"));
        userInfoEmailView.setText(sp.getString("UEMAIL","{UNKNOWN}"));

    }

    public interface onKeyBackPressedListener {
        void onBackKey();
    }

    private onKeyBackPressedListener mOnKeyBackPressedListener;

    public void setOnKeyBackPressedListener(onKeyBackPressedListener listener) {
        mOnKeyBackPressedListener = listener;
    }

    @Override
    public void onBackPressed() {
        if (mOnKeyBackPressedListener != null) {
            mOnKeyBackPressedListener.onBackKey();
        } else {
            if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                Toast.makeText(this, "종료하려면 한번 더 누르세요", Toast.LENGTH_SHORT);
            } else if (getSupportFragmentManager().findFragmentByTag("CHATROOMFRAGMENT") != null && getSupportFragmentManager().findFragmentByTag("CHATROOMFRAGMENT").isVisible()){
                Intent broadInt = new Intent("OnChatRoomExit");
                sendBroadcast(broadInt);
                super.onBackPressed();
            } else {
                super.onBackPressed();
            }
        }
    }
}
