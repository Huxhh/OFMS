package org.classsix.ofms.common.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiang on 2017/5/17.
 * 面向运气，面向心情，面向Bug。
 */
public class BaikePageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    public static String res = "";

    @Override
    public void process(Page page) {
        List list = page.getHtml().xpath("//div[@class='lemma-summary']//div[@class='para']").all();
        page.putField("film", list);
        res = (String) list.get(0);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static String getText(String film){
        if (film.contains(" ")){
            film = film.split(" ")[0];
        }
        Spider.create(new BaikePageProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("http://baike.baidu.com/item/"+film)
                //启动爬虫
                .run();
        return res;
    }


    public static void main(String[] args) {

        System.out.println(BaikePageProcessor.getText("aoa"));
    }
}
