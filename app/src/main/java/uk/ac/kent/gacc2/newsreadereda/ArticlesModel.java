package uk.ac.kent.gacc2.newsreadereda;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by gacc2 on 12/04/16.
 */
public class ArticlesModel {

    private static ArticlesModel ourInstance = new ArticlesModel();
    public static ArticlesModel getInstance() {
        return ourInstance;
    }
    private ArrayList<Article> articleList = new ArrayList<Article>();
    private ArrayList<Article> contentList = new ArrayList<Article>();

    private ArticlesModel() {
        //Add some dummy data
    }

    public ArrayList<Article> getArticleList() {
        return articleList;
    }
    public ArrayList<Article> getContentList() { return contentList; }

    private Response.Listener<JSONArray> netListener = new Response.Listener<JSONArray>(){

        @Override
        public void onResponse(JSONArray response) {

            //clean articleList from old data
            articleList.clear();
            try{
                for(int i = 0; i < response.length(); i++){
                    JSONObject obj = response.getJSONObject(i);

                    Article article = new Article(
                            obj.getInt("record_id"),
                            obj.getString("title"),
                            obj.getString("short_info"),
                            obj.getString("date"),
                            obj.getString("image_url"),
                            null,
                            null

                    );
                    articleList.add(article);
                }

            } catch (JSONException e){
                //parsing error in json data
            }
            notifyListener();

        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    };

    public void loadData(){

        //create a request obejct
        JsonArrayRequest request = new JsonArrayRequest("http://www.efstratiou.info/projects/newsfeed/getList.php", netListener, errorListener);

        //submit request
        ArticlesApp.getInstance().getRequestQueue().add(request);

    }

    private OnListUpdateListener listUpdateListener;

    public void  setOnListUpdateListener(OnListUpdateListener listUpdateListener){

        this.listUpdateListener = listUpdateListener;

    }

    public  interface OnListUpdateListener{

        void onListUpdate(ArrayList<Article> articleList);

    }

    public void notifyListener(){

        if (listUpdateListener != null)
            listUpdateListener.onListUpdate(articleList);

    }

    private Response.Listener<JSONObject> contentsListener = new Response.Listener<JSONObject>(){

        @Override
        public void onResponse(JSONObject response) {

            //clean articleList from old data
            contentList.clear();
            try{
                Article article = new Article(
                        response.getInt("record_id"),
                        response.getString("title"),
                        response.getString("short_info"),
                        response.getString("date"),
                        response.getString("image_url"),
                        response.getString("contents"),
                        response.getString("web_page")

                );
                contentList.add(article);

            } catch (JSONException e){
                //parsing error in json data
            }
            notifyDetailsListener();

        }
    };
    private Response.ErrorListener errorDListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
        }
    };

    private OnContentsUpdateListener contentsUpdateListener;

    public void setOnContentsUpdateListener(OnContentsUpdateListener contentsUpdateListener){

        this.contentsUpdateListener = contentsUpdateListener;

    }

    public  interface OnContentsUpdateListener {

        void onDetailsUpdate(ArrayList<Article> detailsList);

    }

    public void notifyDetailsListener(){

        if (contentsUpdateListener != null)
            contentsUpdateListener.onDetailsUpdate(contentList);

    }

    public void ContentsItem(int articleId){

        String url = "http://www.efstratiou.info/projects/newsfeed/getItem.php?id=" + articleId;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, contentsListener, errorDListener);
        ArticlesApp.getInstance().getRequestQueue().add(request);
    }

    public void SearchString (String string){

        JsonArrayRequest searchString = new JsonArrayRequest(string, netListener, errorListener);
        ArticlesApp.getInstance().getRequestQueue().add(searchString);

    }

}
