package com.code19.read.domain;

import java.util.List;

/**
 * Created by Gh0st on 2016/6/3 003.
 */
public class BiliBiliModel {
    public List<BType> list;

    class BType {
        public List<Item> mItemList;
    }

    class Item {

        public String aid;
        public String copyright;
        public int typeid;
        public String typename;
        public String title;
        public String subtitle;
        public int play;
        public int review;
        public int video_review;
        public int favorites;
        public int mid;
        public String author;
        public String description;
        public String create;
        public String pic;
        public int credit;
        public int coins;
        public String duration;
        public int comment;
        public boolean badgepay;

    }

}
