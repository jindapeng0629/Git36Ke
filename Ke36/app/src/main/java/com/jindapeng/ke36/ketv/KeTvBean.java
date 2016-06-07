package com.jindapeng.ke36.ketv;

import java.util.List;

/**
 * Created by dllo on 16/5/14.
 */
public class KeTvBean {
    /**
     * code : 0
     * data : {"page":1,"totalCount":0,"data":[{"tv":{"videoSource360":"http://7rfkz6.com1.z0.glb.clouddn.com/360p_1304_XMTUzOTMxODU2MA.mp4","id":"603","videoSource720":"http://7rfkz6.com1.z0.glb.clouddn.com/720p_1435_XMTUzOTMxODU2MA.mp4","title":"「氪 TV 测评」HTC Vive 上手体验","duration":"3 分钟","videoSource":"http://7rfkz6.com1.z0.glb.clouddn.com/480p_1407_XMTUzOTMxODU2MA.mp4","publishTime":1460967906000,"youkuUrl":"http://v.youku.com/v_show/id_XMTUzOTMxODU2MA==","videoSource480":"http://7rfkz6.com1.z0.glb.clouddn.com/480p_1407_XMTUzOTMxODU2MA.mp4","featureImg":"http://a.36krcnd.com/nil_class/697500ee-20ef-4d91-af1a-b14aa71aef8d/443ddc1b-9d05-4144-8e12-9e604f7adb2c.jpg","durationLong":230},"columnName":"氪TV","columnId":"tv","type":"tv"}],"pageSize":20,"totalPages":0}
     * msg : 操作成功！
     */

    private int code;
    /**
     * page : 1
     * totalCount : 0
     * data : [{"tv":{"videoSource360":"http://7rfkz6.com1.z0.glb.clouddn.com/360p_1304_XMTUzOTMxODU2MA.mp4","id":"603","videoSource720":"http://7rfkz6.com1.z0.glb.clouddn.com/720p_1435_XMTUzOTMxODU2MA.mp4","title":"「氪 TV 测评」HTC Vive 上手体验","duration":"3 分钟","videoSource":"http://7rfkz6.com1.z0.glb.clouddn.com/480p_1407_XMTUzOTMxODU2MA.mp4","publishTime":1460967906000,"youkuUrl":"http://v.youku.com/v_show/id_XMTUzOTMxODU2MA==","videoSource480":"http://7rfkz6.com1.z0.glb.clouddn.com/480p_1407_XMTUzOTMxODU2MA.mp4","featureImg":"http://a.36krcnd.com/nil_class/697500ee-20ef-4d91-af1a-b14aa71aef8d/443ddc1b-9d05-4144-8e12-9e604f7adb2c.jpg","durationLong":230},"columnName":"氪TV","columnId":"tv","type":"tv"}]
     * pageSize : 20
     * totalPages : 0
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
        private int page;
        private int totalCount;
        private int pageSize;
        private int totalPages;
        /**
         * tv : {"videoSource360":"http://7rfkz6.com1.z0.glb.clouddn.com/360p_1304_XMTUzOTMxODU2MA.mp4","id":"603","videoSource720":"http://7rfkz6.com1.z0.glb.clouddn.com/720p_1435_XMTUzOTMxODU2MA.mp4","title":"「氪 TV 测评」HTC Vive 上手体验","duration":"3 分钟","videoSource":"http://7rfkz6.com1.z0.glb.clouddn.com/480p_1407_XMTUzOTMxODU2MA.mp4","publishTime":1460967906000,"youkuUrl":"http://v.youku.com/v_show/id_XMTUzOTMxODU2MA==","videoSource480":"http://7rfkz6.com1.z0.glb.clouddn.com/480p_1407_XMTUzOTMxODU2MA.mp4","featureImg":"http://a.36krcnd.com/nil_class/697500ee-20ef-4d91-af1a-b14aa71aef8d/443ddc1b-9d05-4144-8e12-9e604f7adb2c.jpg","durationLong":230}
         * columnName : 氪TV
         * columnId : tv
         * type : tv
         */

        private List<DataBean1> data;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public List<DataBean1> getData() {
            return data;
        }

        public void setData(List<DataBean1> data) {
            this.data = data;
        }

        public static class DataBean1 {
            /**
             * videoSource360 : http://7rfkz6.com1.z0.glb.clouddn.com/360p_1304_XMTUzOTMxODU2MA.mp4
             * id : 603
             * videoSource720 : http://7rfkz6.com1.z0.glb.clouddn.com/720p_1435_XMTUzOTMxODU2MA.mp4
             * title : 「氪 TV 测评」HTC Vive 上手体验
             * duration : 3 分钟
             * videoSource : http://7rfkz6.com1.z0.glb.clouddn.com/480p_1407_XMTUzOTMxODU2MA.mp4
             * publishTime : 1460967906000
             * youkuUrl : http://v.youku.com/v_show/id_XMTUzOTMxODU2MA==
             * videoSource480 : http://7rfkz6.com1.z0.glb.clouddn.com/480p_1407_XMTUzOTMxODU2MA.mp4
             * featureImg : http://a.36krcnd.com/nil_class/697500ee-20ef-4d91-af1a-b14aa71aef8d/443ddc1b-9d05-4144-8e12-9e604f7adb2c.jpg
             * durationLong : 230
             */

            private TvBean tv;
            private String columnName;
            private String columnId;
            private String type;

            public TvBean getTv() {
                return tv;
            }

            public void setTv(TvBean tv) {
                this.tv = tv;
            }

            public String getColumnName() {
                return columnName;
            }

            public void setColumnName(String columnName) {
                this.columnName = columnName;
            }

            public String getColumnId() {
                return columnId;
            }

            public void setColumnId(String columnId) {
                this.columnId = columnId;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public static class TvBean {
                private String videoSource360;
                private String id;
                private String videoSource720;
                private String title;
                private String duration;
                private String videoSource;
                private long publishTime;
                private String youkuUrl;
                private String videoSource480;
                private String featureImg;
                private int durationLong;

                public String getVideoSource360() {
                    return videoSource360;
                }

                public void setVideoSource360(String videoSource360) {
                    this.videoSource360 = videoSource360;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getVideoSource720() {
                    return videoSource720;
                }

                public void setVideoSource720(String videoSource720) {
                    this.videoSource720 = videoSource720;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDuration() {
                    return duration;
                }

                public void setDuration(String duration) {
                    this.duration = duration;
                }

                public String getVideoSource() {
                    return videoSource;
                }

                public void setVideoSource(String videoSource) {
                    this.videoSource = videoSource;
                }

                public long getPublishTime() {
                    return publishTime;
                }

                public void setPublishTime(long publishTime) {
                    this.publishTime = publishTime;
                }

                public String getYoukuUrl() {
                    return youkuUrl;
                }

                public void setYoukuUrl(String youkuUrl) {
                    this.youkuUrl = youkuUrl;
                }

                public String getVideoSource480() {
                    return videoSource480;
                }

                public void setVideoSource480(String videoSource480) {
                    this.videoSource480 = videoSource480;
                }

                public String getFeatureImg() {
                    return featureImg;
                }

                public void setFeatureImg(String featureImg) {
                    this.featureImg = featureImg;
                }

                public int getDurationLong() {
                    return durationLong;
                }

                public void setDurationLong(int durationLong) {
                    this.durationLong = durationLong;
                }
            }
        }
    }
}