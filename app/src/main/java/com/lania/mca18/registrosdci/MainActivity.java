package com.lania.mca18.registrosdci;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lania.mca18.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        appBarLayout=(AppBarLayout)findViewById(R.id.appbarid);
        viewPager= (ViewPager)findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //agregar fragments
        adapter.addFragment(new ListadoActivity(),"Listado");
        adapter.addFragment (new LectorActivity(),"Registro I/O");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }


}
