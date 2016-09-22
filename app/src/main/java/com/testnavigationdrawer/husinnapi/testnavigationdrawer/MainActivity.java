package com.testnavigationdrawer.husinnapi.testnavigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button btn;

    private Drawer result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ButterKnife.bind(this);

//        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(e -> Toast.makeText(MainActivity.this, "On Clicked", Toast.LENGTH_SHORT).show());

        initializeNavBar(toolbar);
    }

//    @OnClick(R.id.button)
//    public void buttonClicked(View view) {
//        Toast.makeText(MainActivity.this, "On Clicked", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onBackPressed() {
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void initializeNavBar(Toolbar toolbar) {

        AccountHeader accountHeader = createAccountHeader();

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(accountHeader)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(initializeDrawerItem())
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            if (drawerItem.getIdentifier() == 1) {
                                Toast.makeText(MainActivity.this, "position 1", Toast.LENGTH_SHORT).show();
                                return true;
                            } else if (drawerItem.getIdentifier() == 2) {
                                Toast.makeText(MainActivity.this, "position 2", Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        }
//                        startActivity(new Intent(MainActivity.this, ImageGalleryActivity.class));
                        return false;
                    }
                })
                .build();
    }

    @NonNull
    private IDrawerItem[] initializeDrawerItem() {
        return new IDrawerItem[]{new PrimaryDrawerItem()
                .withName(R.string.nav_menu_item_home)
                .withIdentifier(1)
                .withIcon(R.drawable.ic_home),
                new DividerDrawerItem(),
                new SecondaryDrawerItem()
                        .withName(R.string.nav_menu_item_contacts)
                        .withIdentifier(2)
                        .withIcon(R.drawable.ic_contacts),
                new SectionDrawerItem()
                        .withName(R.string.nav_menu_item_1),
                new SectionDrawerItem()
                        .withName(R.string.nav_menu_item_2)};
    }

    private AccountHeader createAccountHeader() {

        IProfile profile = new ProfileDrawerItem()
                .withName("Husin Napi")
                .withEmail("husin.nanda@icore.id")
                .withIcon(R.drawable.ic_account_circle)
                .withIdentifier(100);


        return new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.background)
                .addProfiles(profile)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
