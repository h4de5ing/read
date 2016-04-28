package com.code19.read.model;

import java.util.List;

/**
 * Created by Gh0st on 2016/4/27 027.
 */
public class NewModel {


    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-04-13 12:02","title":"《连线》杂志：看扎克伯格将如何统治世界？","description":"腾讯科技","picUrl":"http://mat1.gtimg.com/tech/00Jamesdu/2014/index/remark/2.png","url":"http://tech.qq.com/a/20160413/034253.htm"},{"ctime":"2016-04-13 13:49","title":"盘点PayPal创始人彼得&#183;泰尔崛起之路","description":"腾讯科技","picUrl":"http://img1.gtimg.com/tech/pics/hv1/201/229/2051/133424871.jpg","url":"http://tech.qq.com/a/20160413/039137.htm"},{"ctime":"2016-04-13 11:54","title":"滴滴的\u201c大脑\u201d将由这位人工智能科学家来掌控","description":"腾讯科技","picUrl":"http://img1.gtimg.com/tech/pics/hv1/207/219/2051/133422327.png","url":"http://tech.qq.com/a/20160413/033541.htm"},{"ctime":"2016-04-13 11:02","title":"这才是真相：职业黑客卖漏洞给FBI破解iPhone","description":"腾讯科技","picUrl":"http://img1.gtimg.com/tech/pics/hv1/169/209/2051/133419739.jpg","url":"http://tech.qq.com/a/20160413/029243.htm"},{"ctime":"2016-04-13 11:18","title":"消费者怒了：零部件短缺Oculus或延迟数月交货","description":"腾讯科技","picUrl":"http://mat1.gtimg.com/tech/00Jamesdu/2014/index/remark/2.png","url":"http://tech.qq.com/a/20160413/030702.htm"},{"ctime":"2016-04-13 11:18","title":"去哪儿又惹航空公司不开心 新品上线一天遭下线","description":"腾讯科技","picUrl":"http://img1.gtimg.com/tech/pics/hv1/217/214/2051/133421062.jpg","url":"http://tech.qq.com/a/20160413/030709.htm"},{"ctime":"2016-04-13 10:12","title":"调查称苹果设备称霸美青少年市场 市场份额高","description":"腾讯科技","picUrl":"http://mat1.gtimg.com/tech/00Jamesdu/2014/index/remark/2.png","url":"http://tech.qq.com/a/20160413/024588.htm"},{"ctime":"2016-04-13 10:25","title":"Messenger和WhatsApp日处理信息量是短信三倍","description":"腾讯科技","picUrl":"http://img1.gtimg.com/tech/pics/hv1/8/197/2051/133416518.jpg","url":"http://tech.qq.com/a/20160413/025917.htm"},{"ctime":"2016-04-13 10:28","title":"Snapchat以惊人速度抢夺Instagram青少年用户","description":"腾讯科技","picUrl":"http://mat1.gtimg.com/tech/00Jamesdu/2014/index/remark/2.png","url":"http://tech.qq.com/a/20160413/026247.htm"},{"ctime":"2016-04-13 10:29","title":"贝索斯：私营太空领域将重现互联网般创业热潮","description":"腾讯科技","picUrl":"http://img1.gtimg.com/tech/pics/hv1/89/203/2051/133418129.jpg","url":"http://tech.qq.com/a/20160413/026382.htm"}]
     */

    private int code;   //200
    private String msg; //success
    /**
     * ctime : 2016-04-13 12:02
     * title : 《连线》杂志：看扎克伯格将如何统治世界？
     * description : 腾讯科技
     * picUrl : http://mat1.gtimg.com/tech/00Jamesdu/2014/index/remark/2.png
     * url : http://tech.qq.com/a/20160413/034253.htm
     */

    private List<NewslistEntity> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistEntity> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistEntity> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistEntity {
        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
