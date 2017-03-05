package com.estepa.josem.guiasemanasantaestepa2017.tareas;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by xatox on 4/3/17.
 */

public class TareaGuardaImagenes extends AsyncTask<String, String, Bitmap> {

    private Context context;
    private String nombreArchivo = "", extension = "", directorio = "", url;
    private int pos;

    //constructor
    public TareaGuardaImagenes(Context context) {
        this.context = context;
        //this.urlWSArticulos = context.getString(R.string.baseURL) + context.getString(R.string.WSarticulos);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        url = params[0];
        directorio = params[1];
        nombreArchivo = params[2];
        extension = params[3];
        //pos = Integer.parseInt(params[3]);

        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        String absolutePath = guardarImagen(directorio, nombreArchivo, extension, bitmap);

        Log.d("MIO", "onPostExecute: imagen aparentemente guardada, absolutePath: " + absolutePath);
    }

    private String guardarImagen (String directorio, String nombre, String extension, Bitmap imagen) {

        ContextWrapper cw = new ContextWrapper(context);
        File dirImages = cw.getDir(directorio, Context.MODE_PRIVATE);
        File myPath = new File(dirImages, nombre + extension);
        FileOutputStream fos = null;

        try{

            fos = new FileOutputStream(myPath);
            imagen.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();

        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }

        return myPath.getAbsolutePath();
    }
}
