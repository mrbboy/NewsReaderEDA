package uk.ac.kent.gacc2.newsreadereda;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArticleListActivity extends AppCompatActivity implements ArticleListFragment.OnArticleItemClickedListener{

    private boolean hasTwoPanes;

    //navigation bar
    private String[] mNavTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void onArticleItemClicked(int position) {

        if(hasTwoPanes) {

            ArticleDetailsFragment fragment = (ArticleDetailsFragment) getFragmentManager().findFragmentById(R.id.details_frame);
            fragment.updateDetails(position);

        } else {

            Intent intent = new Intent(this, ArticleDetailsActivity.class);
            intent.putExtra("ITEM_ID", position);
            startActivity(intent);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        if(findViewById(R.id.details_frame) == null){
            hasTwoPanes = false;
        } else {
            hasTwoPanes =  true;

            //in case a tablet is used, both fragments are needed on the same activity
            FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
            ArticleDetailsFragment fragment1 = new ArticleDetailsFragment();
            transaction1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction1.replace(R.id.details_frame, fragment1);
            transaction1.addToBackStack(null);
            transaction1.commit();

        }

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        ArticleListFragment fragment = new ArticleListFragment();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        //taken from developer.android.com and adjusted to fit the needs of this project
        mNavTitles = getResources().getStringArray(R.array.nav_list);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.nav_list_item, mNavTitles));

        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        //v7 of Actionbar is used, hence a toolbar is required
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //action bar
        mDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

        mDrawerToggle.setDrawerIndicatorEnabled(true);
       // mDrawerLayout.setDrawerListener(mDrawerToggle);

        //setting the navigation click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    }

    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    public class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {

            //position of Main activity on nav bar
            if (position == 0) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ArticleListFragment fragment = new ArticleListFragment();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.replace(R.id.content_frame, fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
            //position of About fragment on nav bar
            else if (position == 1) {

                FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
                AboutFragment fragment1 = new AboutFragment();
                transaction1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                if(hasTwoPanes){ transaction1.replace(R.id.details_frame, fragment1);}
                else{
                    transaction1.replace(R.id.content_frame, fragment1);}
                transaction1.addToBackStack(null);
                transaction1.commit();

            }

            //close the drawer by closing the layout(Linear Layout, drawerPanel)
            mDrawerLayout.closeDrawer(mDrawerList);

            //setting the appropriate title for each page
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

            if (position == 0) {

                toolbar.setTitle("Articles");

            }
            else

                toolbar.setTitle(mNavTitles[position].toString());

        }

    }

}
