package com.estepa.josem.guiasemanasantaestepa2017.tareas;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.estepa.josem.guiasemanasantaestepa2017.Comunes;
import com.estepa.josem.guiasemanasantaestepa2017.bbdd.Consultas;
import com.estepa.josem.guiasemanasantaestepa2017.bbdd.Inserts;
import com.estepa.josem.guiasemanasantaestepa2017.clases.DetalleHdad;
import com.estepa.josem.guiasemanasantaestepa2017.clases.Hermandad;
import com.estepa.josem.guiasemanasantaestepa2017.clases.ImagenHdad;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
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

        List<Hermandad> listaHdades = parseaXMLHermandades(result);

        String rutaImgAGuardar = "", urlImgAGuardar = "", nombreImgAGuardar, extImgAGuardar = "";

        Consultas consultas = new Consultas(context);
        Inserts insert = new Inserts(context);

        if (consultas.isEmptyHdades()) {

            List<Long> listaIdsAddHdades = insert.insertarHdades(listaHdades);

            for (int x = 0; x < listaHdades.size(); x++) {
                /*String rutaLogoLista = listaHdades.get(x).getRutaLogoLista();
                String urlImagen = URL_PRINCIPAL + rutaLogoLista;
                String nombreImagen = rutaLogoLista.substring(rutaLogoLista.lastIndexOf("/") + 1, rutaLogoLista.lastIndexOf("."));
                String extension = rutaLogoLista.substring(rutaLogoLista.lastIndexOf("."));*/

                hola caracola
                rutaImgAGuardar = listaHdades.get(x).getImagenesHdad().getRutaLogoListaExt();
                urlImgAGuardar = URL_PRINCIPAL + rutaImgAGuardar;
                nombreImgAGuardar = rutaImgAGuardar.substring(rutaImgAGuardar.lastIndexOf("/") + 1, rutaImgAGuardar.lastIndexOf("."));
                extImgAGuardar = rutaImgAGuardar.substring(rutaImgAGuardar.lastIndexOf("."));

                TareaGuardaImagenes tareaGuardaImagenes = new TareaGuardaImagenes(context);
                tareaGuardaImagenes.execute(urlImgAGuardar, DIR_IMAGENESLISTA, nombreImgAGuardar, extImgAGuardar);
            }

            //Log.d("MIO", "onPostExecute: listaIdsAddHdades: " + listaIdsAddHdades.size());
        }



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
     *                       <contenido>Contenido DetalleHdad</contenido>
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
        NodeList nListNodeHdades = doc.getElementsByTagName(NOMBRE_NODO_HERMANDAD);

        int idHdad = 0;
        String nombreHermandad = null;

        ImagenHdad imagenHdad = new ImagenHdad();

        List<Hermandad> listaHdades = new ArrayList<Hermandad>();
        List<DetalleHdad> listaDetalleHdads = new ArrayList<DetalleHdad>();
        //List<String> listaImagenes = new ArrayList<String>();

        for (int x = 0; x < nListNodeHdades.getLength(); x++) {
            Node nNodeHdad = nListNodeHdades.item(x);

            if (nNodeHdad.getNodeType() == Node.ELEMENT_NODE) {
                Element eElementHdad = (Element) nNodeHdad;

                idHdad = Integer.parseInt(eElementHdad.getAttribute(NOMBRE_NODO_IDHERMANDAD));

                //Log.d("MIO", "onPostExecute: " + idHdad);
            }

            NodeList nListNodeHdad = nNodeHdad.getChildNodes();

            for (int y = 0; y < nListNodeHdad.getLength(); y++) {

                Node nNodeHdadHijo = nListNodeHdad.item(y);

                if (nNodeHdadHijo.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElementHdadHijo = (Element) nNodeHdadHijo;

                    if (eElementHdadHijo.getNodeName().equals(NOMBRE_NODO_NOMBRE_HERMANDAD)) {

                        nombreHermandad = eElementHdadHijo.getTextContent();

                    } else if (eElementHdadHijo.getNodeName().equals(NOMBRE_NODO_IMAGENES)) {

                        NodeList imagenes = eElementHdadHijo.getChildNodes();
                        imagenHdad = parseaImagenesHermandad(imagenes);
                        imagenHdad.setIdHdad(idHdad);
                        //rutaLogoLista = listaImagenes.get(0);
                        //rutaLogoMediano = listaImagenes.get(1);
                        //rutaLogoGrande = listaImagenes.get(2);

                    } else if (eElementHdadHijo.getNodeName().equals(NOMBRE_NODO_DETALLES)) {

                        NodeList detalles = eElementHdadHijo.getChildNodes();
                        listaDetalleHdads = parseaDetallesHermandad(detalles, idHdad);

                    }
                }
            }

            Hermandad hdad = new Hermandad(idHdad, nombreHermandad, imagenHdad, listaDetalleHdads);
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
    private ImagenHdad parseaImagenesHermandad(NodeList listaNodosImagenes) {

        ImagenHdad imagenHdad = new ImagenHdad();
        //List<String> listaImagenes = new ArrayList<String>();
        String imgLista = null, imgMediana = null, imgGrande = null;

        for (int x = 0; x < listaNodosImagenes.getLength(); x++) {

            Node imagen = listaNodosImagenes.item(x);

            if (imagen.getNodeType() == Node.ELEMENT_NODE) {
                Element eElementImg = (Element) imagen;

                if (eElementImg.getNodeName().equals(NOMBRE_NODO_IMAGEN_LISTA)) {

                    imgLista = eElementImg.getTextContent();
                    //listaImagenes.add(imgLista);
                    imagenHdad.setRutaLogoListaExt(imgLista);

                } else if (eElementImg.getNodeName().equals(NOMBRE_NODO_IMAGEN_MEDIANA)) {

                    imgMediana = eElementImg.getTextContent();
                    //listaImagenes.add(imgMediana);
                    imagenHdad.setRutaLogoMedianoExt(imgMediana);

                } else if (eElementImg.getNodeName().equals(NOMBRE_NODO_IMAGEN_GRANDE)) {

                    imgGrande = eElementImg.getTextContent();
                    //listaImagenes.add(imgGrande);
                    imagenHdad.setRutaLogoGrandeExt(imgGrande);

                }
            }
        }

        return imagenHdad;
    }

    /**
     * RESUMEN: Parsea la siguiente parte del xml getHermandades:
     *              <detalles>
     *                   <detalle idDetalle="n">
     *                       <titulo idTitulo="n">Nombre Titulo</titulo>
     *                       <contenido>Contenido DetalleHdad</contenido>
     *                   </detalle>
     *                   .
     *                   .
     *                   .
     *               </detalles>
     * @param listaNodosDetalles
     * @return listaDetalles
     */
    private List<DetalleHdad> parseaDetallesHermandad(NodeList listaNodosDetalles, int idHdad) {

        List<DetalleHdad> listaDetalleHdads = new ArrayList<DetalleHdad>();
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

            DetalleHdad detalleHdad = new DetalleHdad(idDetalle, idHdad, idTitulo, titulo, contenido);
            listaDetalleHdads.add(detalleHdad);
        }

        return listaDetalleHdads;
    }
}
