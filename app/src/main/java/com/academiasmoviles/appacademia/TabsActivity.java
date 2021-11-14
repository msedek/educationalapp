package com.academiasmoviles.appacademia;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.academiasmoviles.appacademia.Adapter.SectionsPagerAdapter;
import com.academiasmoviles.appacademia.Fragment.AyudaFragment;
import com.academiasmoviles.appacademia.Fragment.ContactoFragment;
import com.academiasmoviles.appacademia.Fragment.GarantiaFragment;
import com.academiasmoviles.appacademia.Fragment.MisCentrosFragment;
import com.academiasmoviles.appacademia.Fragment.MisionFragment;
import com.academiasmoviles.appacademia.Fragment.VisionFragment;

public class TabsActivity extends AppCompatActivity {

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        // ActionBar: Cambiamos el titulo
        setTitle("Ayuda");

        // ActionBar: Habilitamos el botón atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Setear el adaptador al viewpager
        mViewPager = (ViewPager) findViewById(R.id.pager);

        setupViewPager(mViewPager);

        // Preparar las pestañas
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);

        tabs.addTab(tabs.newTab());
        tabs.addTab(tabs.newTab());
        tabs.addTab(tabs.newTab());

        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabs.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager)
    {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(GarantiaFragment.newInstance(), "Garantía");
        adapter.addFragment(MisCentrosFragment.newInstance(), "Mis centros");
        adapter.addFragment(ContactoFragment.newInstance(), "Contacto");

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:

                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
