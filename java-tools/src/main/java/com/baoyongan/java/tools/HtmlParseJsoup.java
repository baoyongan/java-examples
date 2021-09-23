package com.baoyongan.java.tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class HtmlParseJsoup {

    public static void main(String[] args) throws IOException {
        jsoupExamples("http://mv.cqccms.com.cn/incoc/GSViewEbike!viewCocEbike.action?vinCode=779422160127021");
    }

    private static void jsoupExamples(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements select = doc.select("tr :contains(CCC证书编号)");
        System.out.println(select.text()+select.next().text());
    }
}
