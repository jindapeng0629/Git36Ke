package com.jindapeng.ke36.news.header;

/**
 * Created by dllo on 16/5/17.
 */
public class HeaderBean {
    /**
     * position : 0
     * image_url : http://p.chanyouji.cn/203484/1461668985596p1ah945en3pi1mpf1s651d02cvq2.jpg
     * advert_type : Article
     * content : 627
     * rotation : true
     */

    private String position;
    private String image_url;
    private String advert_type;
    private String content;
    private boolean rotation;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getAdvert_type() {
        return advert_type;
    }

    public void setAdvert_type(String advert_type) {
        this.advert_type = advert_type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRotation() {
        return rotation;
    }

    public void setRotation(boolean rotation) {
        this.rotation = rotation;
    }
}
