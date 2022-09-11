package com.project.wouldyou.controller;

import com.project.wouldyou.controller.dto.response.MoonResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class MoonController {

    @CrossOrigin
    @GetMapping("/LunPhInfoService/getLunPhInfo")
    public MoonResponse moon(@RequestParam Integer solYear,
                     @RequestParam Integer solMonth,
                     @RequestParam Integer solDay) throws Exception {

        String requestUrl = "http://apis.data.go.kr/B090041/openapi/service/LunPhInfoService/getLunPhInfo?" +
                "solYear=" + solYear + "&" +
                "solMonth=" + String.format("%02d", solMonth) + "&" +
                "solDay=" + String.format("%02d", solDay) + "&" +
                "ServiceKey=EEXySxuzBB5HXz%2BW5BAximCisyOLiQpbJOwldraXuVQ2sY2sboz5%2BUJ8U2girScqMprcbbPF3xpf4s67SROQKA%3D%3D";

        URL url = new URL(requestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        Document doc = parseXML(conn.getInputStream());

        XPath xpath = XPathFactory.newInstance().newXPath();
        String res = (String) xpath.evaluate("/response/body/items/item/lunAge", doc, XPathConstants.STRING);

        if(res.length()<1) return new MoonResponse("\uD83C\uDF14");

        double doubleResponse = Double.parseDouble(res);

        if(Double.parseDouble(res) < 4.0) {
            return new MoonResponse("\uD83C\uDF11");
        } else if(doubleResponse < 8.0) {
            return new MoonResponse("\uD83C\uDF12");
        } else if(doubleResponse < 12.0) {
            return new MoonResponse("\uD83C\uDF13");
        } else if(doubleResponse < 14.0) {
            return new MoonResponse("\uD83C\uDF14");
        } else if(doubleResponse < 16.0) {
            return new MoonResponse("\uD83C\uDF15");
        } else if(doubleResponse < 20.0) {
            return new MoonResponse("\uD83C\uDF16");
        } else if(doubleResponse < 26.0) {
            return new MoonResponse("\uD83C\uDF17");
        } else if(doubleResponse < 28.0) {
            return new MoonResponse("\uD83C\uDF18");
        } else {
            return new MoonResponse("\uD83C\uDF11");
        }
    }

    private Document parseXML(InputStream stream) throws Exception{

        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;

        objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
        objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

        doc = objDocumentBuilder.parse(stream);

        return doc;
    }

}