package uk.ac.kent.gacc2.newsreadereda;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by gacc2 on 12/04/16.
 */
public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder>{

    private Context context;
    private ArticleListFragment.OnArticleItemClickedListener listener;

    private ArticlesModel model =  ArticlesModel.getInstance();

    public ArticleListAdapter(Context ctx) {
        super();
        context = ctx;

        if (context instanceof ArticleListFragment.OnArticleItemClickedListener){

            listener = (ArticleListFragment.OnArticleItemClickedListener) context;

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView date;
        private NetworkImageView photo;
        private String resizeImg;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.article_title);
            date = (TextView) itemView.findViewById(R.id.article_date);
            photo = (NetworkImageView) itemView.findViewById(R.id.photo);

            //click listener
            itemView.setOnClickListener(new View.OnClickListener(){

                 public void onClick(View view){

                     if(listener != null){

                         listener.onArticleItemClicked(getAdapterPosition());

                     }

                 }

            });

        }

        public void setData(Article article){

            //set values
            title.setText(article.getTitle());
            date.setText(article.getDate());
            resizeImg = "http://www.efstratiou.info/projects/newsfeed/timthumb.php?src=" + article.getImageUrl() + "&w=100&h=100";
            photo.setImageUrl(resizeImg, ArticlesApp.getInstance().getImageLoader());

        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //create the new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_article_item, parent, false);
        //save view in the view holder
        ViewHolder vh = new ViewHolder(v);
        //return view holder
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //get the relevant object
        Article article = model.getArticleList().get(position);

        //populate the view
        holder.setData(article);

    }

    @Override
    public int getItemCount() {

        return model.getArticleList().size();

    }
}
