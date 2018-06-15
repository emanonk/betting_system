package com.emanon.application.thirdpartyservice;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by mmkamm on 05/06/2018.
 */
@Service
public class JsoupSpiderService {

    private static final Logger LOG = LoggerFactory.getLogger(JsoupSpiderService.class);

    public Elements getLinks(String webUrl) throws IOException {

        LOG.info("Jsoup.getLinks fetching "+webUrl);

        Document doc = Jsoup.connect(webUrl).get();
        Elements links = doc.select("a[href]");

        LOG.info("Links:"+links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }

        return links;
    }

    public Elements getMedia(String webUrl) throws IOException {


        LOG.info("Jsoup.getMedia fetching "+webUrl);

        Document doc = Jsoup.connect(webUrl).get();
        Elements media = doc.select("[src]");

        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img"))
                print(" * %s: <%s> %sx%s (%s)",
                    src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                    trim(src.attr("alt"), 20));
            else
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }

        return media;
    }

    public Elements getImports(String webUrl) throws IOException {


        LOG.info("Jsoup.getImports fetching "+webUrl);

        Document doc = Jsoup.connect(webUrl).get();
        Elements imports = doc.select("link[href]");

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
        }

        return imports;
    }





    public String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }

    public void print(String msg, Object... args) {
        LOG.info(String.format(msg, args));
    }
}
