package com.jindapeng.ke36.details;

/**
 * Created by dllo on 16/5/19.
 */
public class DetailsBean {

    /**
     * code : 0
     * data : {"summary":"我们从计算机上可以学到的，其实是极少数极少数聪明人的思维方式和方法论。","publishTime":1451708442000,"updateTime":1463640532000,"columnId":70,"commentCount":3,"featureImg":"http://a.36krcnd.com/nil_class/f9b83fa2-9461-4c24-b1f5-558fb4f8ec06/yestone_HD_1101463988.jpg","type":"article","postId":5041796,"content":"不能容错的系统肯定是脆弱的","title":"不能容错的系统肯定是脆弱的","favoriteCount":65,"myFavorites":false,"columnName":"深度","user":{"avatar":"https://krplus-pic.b0.upaiyun.com/201603/22055321/cg0huhoojxi0iipw.jpg!800","name":"36氪的朋友们","ssoId":2690},"viewCount":21722}
     * msg : 操作成功！
     */

    private int code;
    /**
     * summary : 我们从计算机上可以学到的，其实是极少数极少数聪明人的思维方式和方法论。
     * publishTime : 1451708442000
     * updateTime : 1463640532000
     * columnId : 70
     * commentCount : 3
     * featureImg : http://a.36krcnd.com/nil_class/f9b83fa2-9461-4c24-b1f5-558fb4f8ec06/yestone_HD_1101463988.jpg
     * type : article
     * postId : 5041796
     * content : 不能容错的系统肯定是脆弱的
     * title : 不能容错的系统肯定是脆弱的
     * favoriteCount : 65
     * myFavorites : false
     * columnName : 深度
     * user : {"avatar":"https://krplus-pic.b0.upaiyun.com/201603/22055321/cg0huhoojxi0iipw.jpg!800","name":"36氪的朋友们","ssoId":2690}
     * viewCount : 21722
     */

    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private String summary;
        private long publishTime;
        private long updateTime;
        private int columnId;
        private int commentCount;
        private String featureImg;
        private String type;
        private int postId;
        private String content;
        private String title;
        private int favoriteCount;
        private boolean myFavorites;
        private String columnName;
        /**
         * avatar : https://krplus-pic.b0.upaiyun.com/201603/22055321/cg0huhoojxi0iipw.jpg!800
         * name : 36氪的朋友们
         * ssoId : 2690
         */

        private UserBean user;
        private int viewCount;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getColumnId() {
            return columnId;
        }

        public void setColumnId(int columnId) {
            this.columnId = columnId;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public String getFeatureImg() {
            return featureImg;
        }

        public void setFeatureImg(String featureImg) {
            this.featureImg = featureImg;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getPostId() {
            return postId;
        }

        public void setPostId(int postId) {
            this.postId = postId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getFavoriteCount() {
            return favoriteCount;
        }

        public void setFavoriteCount(int favoriteCount) {
            this.favoriteCount = favoriteCount;
        }

        public boolean isMyFavorites() {
            return myFavorites;
        }

        public void setMyFavorites(boolean myFavorites) {
            this.myFavorites = myFavorites;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public static class UserBean {
            private String avatar;
            private String name;
            private int ssoId;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSsoId() {
                return ssoId;
            }

            public void setSsoId(int ssoId) {
                this.ssoId = ssoId;
            }
        }
    }
}
