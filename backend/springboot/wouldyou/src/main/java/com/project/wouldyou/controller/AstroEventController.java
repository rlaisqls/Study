package com.project.wouldyou.controller;

import com.project.wouldyou.controller.dto.response.AstroEventResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

@RestController
public class AstroEventController {

    static HashMap<String, Document> map = new HashMap<>();

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/AstroEventInfoService/getAstroEventInfo")
    public AstroEventResponse astroEvent(@RequestParam Integer solYear,
                                         @RequestParam Integer solMonth,
                                         @RequestParam Integer solDay) throws Exception {

        String locdate = solYear + String.format("%02d", solMonth) + String.format("%02d", solDay);

        if (map.containsKey(solYear + String.format("%02d", solMonth))) {
            return getAstroEvent(locdate, map.get(solYear + String.format("%02d", solMonth)));
        }
        System.out.println("api 호출");

        String requestUrl = "http://apis.data.go.kr/B090041/openapi/service/AstroEventInfoService/getAstroEventInfo?" +
                "solYear=" + solYear + "&" +
                "solMonth=" + String.format("%02d", solMonth) + "&" +
                "ServiceKey=EEXySxuzBB5HXz%2BW5BAximCisyOLiQpbJOwldraXuVQ2sY2sboz5%2BUJ8U2girScqMprcbbPF3xpf4s67SROQKA%3D%3D";

        URL url = new URL(requestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        Document doc = parseXML(conn.getInputStream());
        map.put(solYear + String.format("%02d", solMonth), doc);

        return getAstroEvent(locdate, doc);
    }

    private AstroEventResponse getAstroEvent(String locdate, Document doc) throws XPathExpressionException {

        XPath xpath = XPathFactory.newInstance().newXPath();

        NodeList astroEvents = (NodeList) xpath.evaluate("/response/body/items/item/astroEvent", doc, XPathConstants.NODESET);
        NodeList astroTitles = (NodeList) xpath.evaluate("/response/body/items/item/astroTitle", doc, XPathConstants.NODESET);
        NodeList locdates = (NodeList) xpath.evaluate("/response/body/items/item/locdate", doc, XPathConstants.NODESET);

        boolean flag = false;
        AstroEventResponse response = new AstroEventResponse(new ArrayList<>());

        for (int i = 0; i < astroEvents.getLength(); i++) {

            if (Objects.equals(locdate, locdates.item(i).getTextContent())) {
                flag = true;
                String astroEvent = astroEvents.item(i).getTextContent();
                String astroTitle = astroTitles.item(i).getTextContent();
                if (astroTitle.length() < 1) {
                    response.addEvent(astroEvents.item(i).getTextContent(), astroEvents.item(i).getTextContent());
                } else {
                    response.addEvent(astroEvents.item(i).getTextContent(), astroTitles.item(i).getTextContent());
                }
            } else if (flag) {
                break;
            }
        }

        return response;
    }

    private Document parseXML(InputStream stream) throws Exception {

        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;

        objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
        objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

        doc = objDocumentBuilder.parse(stream);

        return doc;
    }
}