package bananatechnologies.mortgagecalculator;

import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.astuetz.PagerSlidingTabStrip;

/**
 * @author Sagar Mane
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    CalculationViewPagerAdapter adpter;
    PagerTabStrip header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager vp=(ViewPager) findViewById(R.id.pager);
        adpter=new CalculationViewPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(adpter);


        //
        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(vp);


    }

}
