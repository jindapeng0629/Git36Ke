package com.jindapeng.ke36.equity.childfragment.all;

import java.util.List;

/**
 * Created by dllo on 16/5/24.
 * 全部的实体类
 */
public class AllBean {
    private int code;
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
        /**
         * can_exit : false
         * cf_advantage : [{"adcontent":"管理经验和资源优势强","adname":"创始人"},{"adcontent":"线上+线下完美结合","adname":"团队成员"}]
         * cf_raising : ￥1400万
         * cf_success_raising_offer : ￥1010万
         * company_brief : 健康行业领先的SaaS服务平台
         * company_id : 154573
         * company_logo : https://krplus-pic.b0.upaiyun.com/201512/01045835/5umei1tvdivd9ohk.png!200
         * company_name : 华佗驾到（潼诚一得）
         * crowdFundingId : 180
         * file_list_img : https://krplus-pic.b0.upaiyun.com/201605/17/51e6a0f91d4ee855f8e6777be5e39e8f.png
         * followed : false
         * fundStatus : {"crowd_funding_status":"MAO_DING","desc":"05月27日 10:00 募资","start_time":1464314400000}
         * lead_name : 海鼎资本
         * min_investment : ￥5万
         * payUrl : https://z.36kr.com/m/deposit/confirm?id=180
         * rate : 0.72
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

        public List<DataBean1> getData() {
            return data;
        }

        public void setData(List<DataBean1> data) {
            this.data = data;
        }

        public static class DataBean1 {
            private boolean can_exit;
            private String cf_raising;
            private String cf_success_raising_offer;
            private String company_brief;
            private int company_id;
            private String company_logo;
            private String company_name;
            private int crowdFundingId;
            private String file_list_img;
            private boolean followed;
            /**
             * crowd_funding_status : MAO_DING
             * desc : 05月27日 10:00 募资
             * start_time : 1464314400000
             */

            private FundStatusBean fundStatus;
            private String lead_name;
            private String min_investment;
            private String payUrl;
            private double rate;
            /**
             * adcontent : 管理经验和资源优势强
             * adname : 创始人
             */

            private List<CfAdvantageBean> cf_advantage;

            public boolean isCan_exit() {
                return can_exit;
            }

            public void setCan_exit(boolean can_exit) {
                this.can_exit = can_exit;
            }

            public String getCf_raising() {
                return cf_raising;
            }

            public void setCf_raising(String cf_raising) {
                this.cf_raising = cf_raising;
            }

            public String getCf_success_raising_offer() {
                return cf_success_raising_offer;
            }

            public void setCf_success_raising_offer(String cf_success_raising_offer) {
                this.cf_success_raising_offer = cf_success_raising_offer;
            }

            public String getCompany_brief() {
                return company_brief;
            }

            public void setCompany_brief(String company_brief) {
                this.company_brief = company_brief;
            }

            public int getCompany_id() {
                return company_id;
            }

            public void setCompany_id(int company_id) {
                this.company_id = company_id;
            }

            public String getCompany_logo() {
                return company_logo;
            }

            public void setCompany_logo(String company_logo) {
                this.company_logo = company_logo;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public int getCrowdFundingId() {
                return crowdFundingId;
            }

            public void setCrowdFundingId(int crowdFundingId) {
                this.crowdFundingId = crowdFundingId;
            }

            public String getFile_list_img() {
                return file_list_img;
            }

            public void setFile_list_img(String file_list_img) {
                this.file_list_img = file_list_img;
            }

            public boolean isFollowed() {
                return followed;
            }

            public void setFollowed(boolean followed) {
                this.followed = followed;
            }

            public FundStatusBean getFundStatus() {
                return fundStatus;
            }

            public void setFundStatus(FundStatusBean fundStatus) {
                this.fundStatus = fundStatus;
            }

            public String getLead_name() {
                return lead_name;
            }

            public void setLead_name(String lead_name) {
                this.lead_name = lead_name;
            }

            public String getMin_investment() {
                return min_investment;
            }

            public void setMin_investment(String min_investment) {
                this.min_investment = min_investment;
            }

            public String getPayUrl() {
                return payUrl;
            }

            public void setPayUrl(String payUrl) {
                this.payUrl = payUrl;
            }

            public double getRate() {
                return rate;
            }

            public void setRate(double rate) {
                this.rate = rate;
            }

            public List<CfAdvantageBean> getCf_advantage() {
                return cf_advantage;
            }

            public void setCf_advantage(List<CfAdvantageBean> cf_advantage) {
                this.cf_advantage = cf_advantage;
            }

            public static class FundStatusBean {
                private String crowd_funding_status;
                private String desc;
                private long start_time;

                public String getCrowd_funding_status() {
                    return crowd_funding_status;
                }

                public void setCrowd_funding_status(String crowd_funding_status) {
                    this.crowd_funding_status = crowd_funding_status;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public long getStart_time() {
                    return start_time;
                }

                public void setStart_time(long start_time) {
                    this.start_time = start_time;
                }
            }

            public static class CfAdvantageBean {
                private String adcontent;
                private String adname;

                public String getAdcontent() {
                    return adcontent;
                }

                public void setAdcontent(String adcontent) {
                    this.adcontent = adcontent;
                }

                public String getAdname() {
                    return adname;
                }

                public void setAdname(String adname) {
                    this.adname = adname;
                }
            }
        }
    }
}
