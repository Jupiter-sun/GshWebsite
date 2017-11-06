package cn.yfjz.core.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.File;

public class XmlUtil {
    private static final Log logger = LogFactory.getLog(XmlUtil.class);

    public static Document load(String path) {
        return load(new File(path));
    }

    public static Document load(File file) {
        Document localDocument = null;
        try {
            SAXReader localSAXReader = new SAXReader();
            localSAXReader.setEncoding("UTF-8");
            localDocument = localSAXReader.read(file);
        } catch (Exception e) {
            logger.error("load XML File error:" + e.getMessage());
        }
        return localDocument;
    }
}
