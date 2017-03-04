package com.estepa.josem.guiasemanasantaestepa2017;

import android.content.Context;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by xatox on 28/2/17.
 */

public class Comunes {

    /**
     * Resumen: guarda una cadena en la memoria interna de aplicacion
     * @param context
     * @param string
     * @param filename
     */
    public static void saveStringInFile (Context context, String string, String filename) throws IOException {
        FileOutputStream outputStream = null;
        outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
        outputStream.write(string.getBytes());
        outputStream.close();
    }

    /**
     * Resumen: parsea una cadena en un Document
     * @param xml
     * @return Document
     */
    public static Document parserStringToDoc (String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        InputSource is = null;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            is = new InputSource(new StringReader(xml));
            doc = builder.parse(is);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        doc.getDocumentElement().normalize();
        return doc;
    }
}
