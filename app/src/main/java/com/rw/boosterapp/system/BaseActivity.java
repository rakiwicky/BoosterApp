package com.rw.boosterapp.system;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.rw.boosterapp.R;
import com.rw.boosterapp.database.daos.MenuGroup;
import com.rw.boosterapp.database.ormlite.DaoFactory;
import com.rw.boosterapp.helper.NavigationHelper;
import com.rw.boosterapp.helper.QuestionnaireHelper;
import com.rw.boosterapp.model.MenuGroupRepo;
import com.rw.boosterapp.model.MenuItemRepo;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rakhita on 4/19/2018.
 */

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, IBaseView {

    private BasePresenter mBasePresenter;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            mBasePresenter = new BasePresenter(this,
                    new MenuGroupRepo(new DaoFactory(this).getMenuGroupDaoInstance()),
                    new MenuItemRepo(new DaoFactory(this).getMenuItemDaoInstance()),
                    new NavigationHelper(this), new QuestionnaireHelper(this));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStart(){
        super.onStart();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView txtTitle = (TextView) toolbar.findViewById(R.id.txtToolbarTitle);
        ImageView imgToolbatLogo = (ImageView) toolbar.findViewById(R.id.imgToolbarLogo);
        setSupportActionBar(toolbar);
        txtTitle.setText(getString(R.string.companyNameText));
        imgToolbatLogo.setImageDrawable(getDrawable(R.drawable.logo_small));

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Menu Initialization
        mMenu = navigationView.getMenu();
        mMenu.clear();
        mBasePresenter.loadMenuItems();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        mBasePresenter.clickMenuItem(itemId);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showMenu(List<MenuGroup> menuGroups) {
        if(menuGroups != null && menuGroups.size() > 0){
            for (MenuGroup menuGroup : menuGroups){
                final SubMenu subMenu = mMenu.addSubMenu(menuGroup.getGroupId(), menuGroup.getId(), menuGroup.getId(), menuGroup.getTitle());
                Collection<com.rw.boosterapp.database.daos.MenuItem> menuItems = menuGroup.getMenuItems();
                for (com.rw.boosterapp.database.daos.MenuItem menuItem : menuItems){
                    if(menuItem.isSkip()) continue;
                    subMenu.add(menuGroup.getGroupId(), menuItem.getId(), menuItem.getId(), menuItem.getTitle());
                }
            }
        }
    }
}
