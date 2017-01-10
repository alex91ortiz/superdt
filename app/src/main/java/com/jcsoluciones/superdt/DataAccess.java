package com.jcsoluciones.superdt;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

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

    public static String TOKEN="";

    public static  String HOST="http://superdt-solucionesjc.rhcloud.com/";

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
            if(!TOKEN.isEmpty())
                con.setRequestProperty("Authorization", "Token "+ TOKEN);
            con.setRequestProperty("Content-Type", " application/json");
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
                out.write(reqbody.getBytes("UTF-8"));
                out.flush();
                out.close();

            }
            con.connect();
            int responseCode = con.getResponseCode();
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

    public static JSONObject apiAuth(String mPassword,String mEmail){
        JSONObject parames = new JSONObject();
        JSONObject token = null;
        try {
            parames.put("username",mEmail);
            parames.put("password",mPassword);
            String result = DataAccess.ApiSuperDT("api-token-auth/","POST",parames.toString());
            token = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return token;
    }

    public static JSONObject getUser(String mEmail){
        JSONObject user = null;
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("polls/users/");
            stringBuffer.append("?");
            stringBuffer.append("username=");
            stringBuffer.append(mEmail);
            String result = DataAccess.ApiSuperDT(stringBuffer.toString(),"GET",null);
            user = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
