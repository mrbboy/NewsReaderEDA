package uk.ac.kent.gacc2.newsreadereda;


import android.os.Bundle;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleDetailsFragment extends Fragment implements ArticlesModel.OnContentsUpdateListener {

    private TextView articleTitle;
    private TextView articleDate;
    private TextView articleWebsite;
    private TextView articleContent;
    private NetworkImageView articlePhoto;
    private String resizeImg;

    public ArticleDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article_details, container, false);

        articleTitle = (TextView) view.findViewById(R.id.article_title);
        articleDate = (TextView) view.findViewById(R.id.article_date);
        articleWebsite = (TextView) view.findViewById(R.id.article_website);
        articleContent = (TextView) view.findViewById(R.id.aricle_content);
        articlePhoto = (NetworkImageView) view.findViewById(R.id.article_photo);

        return  view;
    }

    public void updateDetails(int id){

        //article object retrieved using the id
        ArticlesModel.getInstance().setOnContentsUpdateListener(this);
        Article article = ArticlesModel.getInstance().getArticleList().get(id);
        ArticlesModel.getInstance().ContentsItem(article.getArticleId());

    }


    @Override
    public void onDetailsUpdate(ArrayList<Article> detailsList) {

        articleTitle.setText(detailsList.get(0).getTitle());
        articleDate.setText(detailsList.get(0).getDate());
        articleWebsite.setText(detailsList.get(0).getWebpage());
        articleContent.setText(detailsList.get(0).getContents());

        resizeImg = "http://www.efstratiou.info/projects/newsfeed/timthumb.php?src=" + detailsList.get(0).getImageUrl() + "&w=600&h=600";
        articlePhoto.setImageUrl(resizeImg, ArticlesApp.getInstance().getImageLoader());

    }
}
