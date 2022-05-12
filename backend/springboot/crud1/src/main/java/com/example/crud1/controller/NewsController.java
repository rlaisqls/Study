package com.example.crud1.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class NewsController {

    public static HashMap<String, String> map;

    @RequestMapping(value = "crawling", method = RequestMethod.GET)
    public String startCrawl(Model model) throws IOException {
        System.out.println("asdw");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        Date currentTime = new Date();

        String dTime = formatter.format(currentTime);
        String e_date = dTime;

        currentTime.setDate(currentTime.getDate() - 1);
        String s_date = formatter.format(currentTime);

        String query = "성북구";
        String s_from = s_date.replace(".", "");
        String e_to = e_date.replace(".", "");
        int page = 1;
        ArrayList<String> al1 = new ArrayList<>();
        ArrayList<String> al2 = new ArrayList<>();
        ArrayList<String> al3 = new ArrayList<>();

        while (page < 20) {
            String address = "https://search.naver.com/search.naver?where=news&query=" + query + "&sort=1&ds=" + s_date
                    + "&de=" + e_date + "&nso=so%3Ar%2Cp%3Afrom" + s_from + "to" + e_to + "%2Ca%3A&start="
                    + Integer.toString(page);
            Document rawData = Jsoup.connect(address).get();
            System.out.println(address);
            /*
            Elements photoElement = rawData.getElementsByAttributeValue("class","dsc_thumb");
            for (Element articleElement : photoElement) {
                Element imgElement = articleElement.select("img").get(0);
            }*/
            Elements blogOption = rawData.select("div.group_news > ul.list_news > li div.news_area > a");
            for (Element articleElement : blogOption) {
                String articleUrl = articleElement.select("a").attr("href");
                al1.add(articleUrl);

                String articletitle = articleElement.select("a").attr("title");
                al2.add(articletitle);

                Document document =  Jsoup.connect(articleUrl).get();
                Elements articlecontents =  document.select("p");

                String articlecontent = "X";
                for(Element tmp : articlecontents){
                    if(articlecontent.length() < tmp.toString().length()){
                        articlecontent = String.valueOf(tmp);
                    }
                }
                al3.add(articlecontent);
                System.out.println(articlecontent);

                System.out.println(articleUrl+" "+articletitle+" "+articlecontents.size());
            }
            page += 10;
        }
        model.addAttribute("urls", al1);
        model.addAttribute("titles", al2);
        model.addAttribute("content", al3);
        System.out.println(al1.size()+" "+al2.size());
        return "news/news.jsp";
    }

}