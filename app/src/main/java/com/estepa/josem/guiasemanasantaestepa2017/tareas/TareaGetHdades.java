package com.estepa.josem.guiasemanasantaestepa2017.tareas;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.estepa.josem.guiasemanasantaestepa2017.Comunes;
import com.estepa.josem.guiasemanasantaestepa2017.clases.Detalle;
import com.estepa.josem.guiasemanasantaestepa2017.clases.Hermandad;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.estepa.josem.guiasemanasantaestepa2017.Constantes.*;

/**
 * Created by xatox on 28/2/17.
 */

public class TareaGetHdades extends AsyncTask<String, String, String> {

    Context context;

    public TareaGetHdades(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        String xml = null;

        try {

            url = new URL(URL_GETHERMANDADES);
            urlConnection = (HttpURLConnection) url.openConnection();

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            } else {
                in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            }

            xml = IOUtils.toString(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if(urlConnection!=null)
                urlConnection.disconnect();
        }

        return xml;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //Log.d("MIO", "onPostExecute: " + result);

        List<Hermandad> listaHdades = parseaXMLHermandades(result);

        Log.d("MIO", "onPostExecute: ");

    }


    /**
     * RESUMEN: Parsea la siguiente parte del xml getHermandades:
     *
     *           <hermandad idHermandad="n">
     *               <nombreHermandad>Nombre de la Hermandad</nombreHermandad>
     *               <imagenes>
     *                   <imgLogoLista>Ruta Imagen de la Lista</imgLogoLista>
     *                   <imgLogoMediano>Ruta Imagen Mediana</imgLogoMediano>
     *                   <imgLogoGrande>Ruta Imagen Grande</imgLogoGrande>
     *               </imagenes>
     *               <detalles>
     *                   <detalle idDetalle="n">
     *                       <titulo idTitulo="n">Nombre Titulo</titulo>
     *                       <contenido>Contenido Detalle</contenido>
     *                   </detalle>
     *                   .
     *                   .
     *                   .
     *               </detalles>
     *           </hermandad>
     *           .
     *           .
     *           .
     *
     * @param result
     * @return listaHdades
     */
    public List<Hermandad> parseaXMLHermandades(String result) {

        Document doc = Comunes.parserStringToDoc(result);
        NodeList nList = doc.getElementsByTagName(NOMBRE_NODO_HERMANDAD);

        int idHdad = 0;
        String nombreHermandad = null, rutaLogoLista = null, rutaLogoMediano = null, rutaLogoGrande = null;

        List<Hermandad> listaHdades = new ArrayList<Hermandad>();
        List<Detalle> listaDetalles = new ArrayList<Detalle>();
        List<String> listaImagenes = new ArrayList<String>();

        for (int x = 0; x < nList.getLength(); x++) {
            Node nNode = nList.item(x);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                idHdad = Integer.parseInt(eElement.getAttribute(NOMBRE_NODO_IDHERMANDAD));

                Log.d("MIO", "onPostExecute: " + idHdad);
            }

            NodeList nList2 = nNode.getChildNodes();

            for (int y = 0; y < nList2.getLength(); y++) {

                Node nNode2 = nList2.item(y);

                if (nNode2.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode2;

                    if (eElement.getNodeName().equals(NOMBRE_NODO_NOMBRE_HERMANDAD)) {

                        nombreHermandad = eElement.getTextContent();

                    } else if (eElement.getNodeName().equals(NOMBRE_NODO_IMAGENES)) {

                        NodeList imagenes = eElement.getChildNodes();
                        listaImagenes = parseaImagenesHermandad(imagenes);
                        rutaLogoLista = listaImagenes.get(0);
                        rutaLogoMediano = listaImagenes.get(1);
                        rutaLogoGrande = listaImagenes.get(2);

                    } else if (eElement.getNodeName().equals(NOMBRE_NODO_DETALLES)) {

                        NodeList detalles = eElement.getChildNodes();
                        listaDetalles = parseaDetallesHermandad(detalles);

                    }
                }
            }

            Hermandad hdad = new Hermandad(idHdad, nombreHermandad, rutaLogoLista, rutaLogoMediano, rutaLogoGrande, listaDetalles);
            listaHdades.add(hdad);
        }

        return listaHdades;
    }

    /**
     * RESUMEN: Parsea la siguiente parte del xml getHermandades:
     *               <imagenes>
     *                   <imgLogoLista>Ruta Imagen de la Lista</imgLogoLista>
     *                   <imgLogoMediano>Ruta Imagen Mediana</imgLogoMediano>
     *                   <imgLogoGrande>Ruta Imagen Grande</imgLogoGrande>
     *               </imagenes>
     *
     *
     * @param listaNodosImagenes
     * @return listaImagenes
     */
    private List<String> parseaImagenesHermandad(NodeList listaNodosImagenes) {

        List<String> listaImagenes = new ArrayList<String>();
        String imgLista = null, imgMediana = null, imgGrande = null;

        for (int x = 0; x < listaNodosImagenes.getLength(); x++) {

            Node imagen = listaNodosImagenes.item(x);

            if (imagen.getNodeType() == Node.ELEMENT_NODE) {
                Element eElementImg = (Element) imagen;

                if (eElementImg.getNodeName().equals(NOMBRE_NODO_IMAGEN_LISTA)) {

                    imgLista = eElementImg.getTextContent();
                    listaImagenes.add(imgLista);

                } else if (eElementImg.getNodeName().equals(NOMBRE_NODO_IMAGEN_MEDIANA)) {

                    imgMediana = eElementImg.getTextContent();
                    listaImagenes.add(imgMediana);

                } else if (eElementImg.getNodeName().equals(NOMBRE_NODO_IMAGEN_GRANDE)) {

                    imgGrande = eElementImg.getTextContent();
                    listaImagenes.add(imgGrande);

                }
            }
        }

        return listaImagenes;
    }

    /**
     * RESUMEN: Parsea la siguiente parte del xml getHermandades:
     *              <detalles>
     *                   <detalle idDetalle="n">
     *                       <titulo idTitulo="n">Nombre Titulo</titulo>
     *                       <contenido>Contenido Detalle</contenido>
     *                   </detalle>
     *                   .
     *                   .
     *                   .
     *               </detalles>
     * @param listaNodosDetalles
     * @return listaDetalles
     */
    private List<Detalle> parseaDetallesHermandad(NodeList listaNodosDetalles) {

        List<Detalle> listaDetalles = new ArrayList<Detalle>();
        int idTitulo = 0, idDetalle = 0;
        String titulo = null, contenido = null;

        for (int x = 0; x < listaNodosDetalles.getLength(); x++) {

            Node nodoDetalle = listaNodosDetalles.item(x);

            if (nodoDetalle.getNodeType() == Node.ELEMENT_NODE) {

                Element eElementDetalle = (Element) nodoDetalle;

                if (eElementDetalle.getNodeName().equals(NOMBRE_NODO_DETALLE)) {

                    idDetalle = Integer.parseInt(eElementDetalle.getAttribute(NOMBRE_NODO_IDDETALLE));
                    NodeList detallesHijos = eElementDetalle.getChildNodes();

                    for (int y = 0; y < detallesHijos.getLength(); y++) {

                        Node detalleHijos = detallesHijos.item(y);

                        if (detalleHijos.getNodeType() == Node.ELEMENT_NODE) {

                            Element eElementDetalleHijo = (Element) detalleHijos;

                            if (eElementDetalleHijo.getNodeName().equals(NOMBRE_NODO_TITULO)) {

                                idTitulo = Integer.parseInt(eElementDetalleHijo.getAttribute(NOMBRE_NODO_IDTITULO));
                                titulo = eElementDetalleHijo.getTextContent();

                            } else if (eElementDetalleHijo.getNodeName().equals(NOMBRE_NODO_CONTENIDO)) {

                                contenido = eElementDetalleHijo.getTextContent();
                            }
                        }
                    }
                }
            }

            Detalle detalle = new Detalle(idDetalle, idTitulo, titulo, contenido);
            listaDetalles.add(detalle);
        }

        return listaDetalles;
    }
}
