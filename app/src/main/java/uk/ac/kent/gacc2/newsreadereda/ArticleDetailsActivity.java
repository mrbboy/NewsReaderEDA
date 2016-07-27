package uk.ac.kent.gacc2.newsreadereda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class ArticleDetailsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);


        Intent intent = getIntent();
        int itemId = intent.getIntExtra("ITEM_ID", 0);

        //get the fragment
        ArticleDetailsFragment fragment = (ArticleDetailsFragment) getFragmentManager().findFragmentById(R.id.details_fragment);
        fragment.updateDetails(itemId);

        //setting up the toolbar for the details activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
}
