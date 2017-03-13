package bananatechnologies.mortgagecalculator;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CalculationView extends AppCompatActivity {
    private String[] choices;
    private DrawerLayout mDrawerLayout;
    private ListView mListView;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_view);

        choices=getResources().getStringArray(R.array.drawer_choices);
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        mListView=(ListView) findViewById(R.id.drawer_list);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mListView.setAdapter(new ArrayAdapter<String>(this,R.layout.menutext,choices));



    }
}
