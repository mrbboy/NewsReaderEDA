package uk.ac.kent.gacc2.newsreadereda;

/**
 * Created by gacc2 on 12/04/16.
 */
public class Article {

    private String title;
    private String shortInfo;
    private String date;

    private int articleId;
    private String imageUrl;
    private String contents;
    private String webpage;

    public Article(int articleId, String title, String shortInfo, String date, String imageUrl, String contents, String webpage) {

        this.articleId = articleId;
        this.title = title;
        this.shortInfo = shortInfo;
        this.date = date;
        this.imageUrl = imageUrl;
        this.contents = contents;
        this.webpage = webpage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortInfo() {
        return shortInfo;
    }

    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }
}
