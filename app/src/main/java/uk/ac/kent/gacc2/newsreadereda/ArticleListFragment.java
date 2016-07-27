package uk.ac.kent.gacc2.newsreadereda;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnArticleItemClickedListener} interface
 * to handle interaction events.
 */
public class ArticleListFragment extends Fragment implements ArticlesModel.OnListUpdateListener {

    RecyclerView articleListView;
    private LinearLayoutManager layoutManager;
    private ArticleListAdapter adapter;
    private OnArticleItemClickedListener mListener;

    public ArticleListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);

        //wire recycler view
        articleListView = (RecyclerView) view.findViewById(R.id.article_list_view);

        //setup layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        //attache to recycler view
        articleListView.setLayoutManager(layoutManager);

        //setup adapter
        adapter = new ArticleListAdapter(getActivity());

        //attach to recycler view
        articleListView.setAdapter(adapter);
        articleListView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        //connect to the model
        ArticlesModel model = ArticlesModel.getInstance();
        model.setOnListUpdateListener(this);
        model.loadData();

        //activating the nav
        setHasOptionsMenu(true);

        return view;

    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {

        super.onCreateOptionsMenu(menu, menuInflater);

        //search widget implementation was taken from developer.android.com and
        //adjusted for the requirements of this project

        // Inflate the options menu from XML
        //MenuInflater inflater = this.getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.search_widget, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) this.getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getActivity().getComponentName()));
        //searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        SearchView.OnQueryTextListener qTextListener = new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String term) {

                //search term being passed to the fragment
                SearchTerm(term);
                return true;
            }

            public boolean onQueryTextChange(String string) {
                return true;
            }
        };

        searchView.setOnQueryTextListener(qTextListener);

        searchView.setOnCloseListener(new SearchView.OnCloseListener(){

            @Override
            public boolean onClose() {

                ArticlesModel model = ArticlesModel.getInstance();
                model.loadData();

                return false;

            }

        });

    }

    public void SearchTerm(String term) {

        ArticlesModel model = ArticlesModel.getInstance();
        model.SearchString("http://www.efstratiou.info/projects/newsfeed/getList.php?titleHas="+ term);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnArticleItemClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnArticleItemClickedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListUpdate(ArrayList<Article> arrayList) {

        if (adapter != null)
            adapter.notifyDataSetChanged();

    }

    public interface OnArticleItemClickedListener {

        void onArticleItemClicked(int position);
    }

}
