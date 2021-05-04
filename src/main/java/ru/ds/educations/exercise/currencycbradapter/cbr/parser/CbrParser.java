package ru.ds.educations.exercise.currencycbradapter.cbr.parser;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import com.sun.org.apache.xerces.internal.dom.TextImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.cbr.web.DailyInfo;
import ru.cbr.web.DailyInfoSoap;
import ru.cbr.web.GetCursOnDateXMLResponse;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Component
@Slf4j
public class CbrParser {

    public static List<Map> getValuteDate(XMLGregorianCalendar onDate) {
        List<Map> currencies = new ArrayList<>(); // будем складывать сюда валюты цбр

        DailyInfo service = new DailyInfo();
        DailyInfoSoap port = service.getDailyInfoSoap();

        GetCursOnDateXMLResponse.GetCursOnDateXMLResult result
                = port.getCursOnDateXML(onDate);

        List<Object> content = result.getContent();
        ElementNSImpl elem = (ElementNSImpl) content.get(0);
        NodeList chCodeList = elem.getElementsByTagName("VchCode");

        //int len = chCodeList.getLength();
        for (int i = 0; i < chCodeList.getLength(); i++) {
            Node valuteChNode = chCodeList.item(i);
            TextImpl textimpl = (TextImpl) valuteChNode.getFirstChild();
            String chVal = textimpl.getData();

            if (chVal != null) {
                Node parent = valuteChNode.getParentNode();
                NodeList nodeList = parent.getChildNodes();
                int paramLength = nodeList.getLength();

                Map<String, Object> currency = new HashMap<>();
                BigDecimal curs = null;

                for (int j = 0; j < paramLength; j++) {
                    Node currentNode = nodeList.item(j);
                    String name = currentNode.getNodeName(); // <tag name="Vcurs">
                    Node currentValue = currentNode.getFirstChild();
                    String value = currentValue.getNodeValue();

                    if (name.equalsIgnoreCase("Vcurs")) {
                        curs = new BigDecimal(value);
                    }
                }
                currency.put("currency", chVal);
                currency.put("curs", curs);
                currencies.add(currency);
            }
        }
        return currencies;
    }

    public static XMLGregorianCalendar localDateToGregorianCalendarXml(LocalDate onDate) {
        XMLGregorianCalendar gregorianCalendar = null;
        try {
            gregorianCalendar = DatatypeFactory
                    .newInstance()
                    .newXMLGregorianCalendar(
                        GregorianCalendar.from(onDate.atStartOfDay(ZoneId.systemDefault()))
            );
            return gregorianCalendar;
        } catch (DatatypeConfigurationException e) {
            log.error("Ошибка при конвертации даты", e);
            return null;
        }
    }
}
