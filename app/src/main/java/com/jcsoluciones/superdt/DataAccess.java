package com.jcsoluciones.superdt;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Admin on 04/01/2017.
 */

class DataAccess {
    public static  String HOST="http://192.168.56.1:8000/";

    public static  HttpURLConnection getHttpConnection(String url, String type){
        URL uri = null;
        HttpURLConnection con = null;
        try{
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(HOST);
            stringBuffer.append(url);
            uri = new URL(stringBuffer.toString());
            con = (HttpURLConnection) uri.openConnection();
            con.setRequestMethod(type); //type: POST, PUT, DELETE, GET
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setConnectTimeout(60000); //60 secs
            con.setReadTimeout(60000); //60 secs
            con.setRequestProperty("Accept-Encoding", "Your Encoding");
            con.setRequestProperty("Content-Type", "Your Encoding");
        }catch(Exception e){
            Log.d("DATA", "connection i/o failed" );
        }
        return con;
    }
    public static String ApiSuperDT(String url, String type, String reqbody){
        HttpURLConnection con = null;
        String result = null;
        try {
            con = getHttpConnection( url , type);
            //you can add any request body here if you want to post
            if( reqbody != null){
                con.setDoInput(true);
                con.setDoOutput(true);
                DataOutputStream out = new  DataOutputStream(con.getOutputStream());
                out.writeBytes(reqbody);
                out.flush();
                out.close();

            }
            con.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String temp = null;
            StringBuilder sb = new StringBuilder();
            while((temp = in.readLine()) != null){
                sb.append(temp).append(" ");
            }
            result = sb.toString();
            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("DATA",e.getMessage());
        }
        return result;
    }
}
