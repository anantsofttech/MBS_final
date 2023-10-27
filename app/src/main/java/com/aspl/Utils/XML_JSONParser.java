package com.aspl.Utils;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XML_JSONParser {

    public String getXmlFromUrl(String url, List<NameValuePair> data) {
        String xml = "";

        try {

            HttpPost httpPost = new HttpPost(url);// replace

            httpPost.setHeader("Authorization",
                    "Content-Type: application/x-www-form-urlencoded");

            try {

                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(
                        data);

                httpPost.setEntity(urlEncodedFormEntity);

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    InputStream inputStream = httpResponse.getEntity()
                            .getContent();
                    InputStreamReader inputStreamReader = new InputStreamReader(
                            inputStream);
                    BufferedReader bufferedReader = new BufferedReader(
                            inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String bufferedStrChunk = null;
                    while ((bufferedStrChunk = bufferedReader.readLine()) != null) {
                        stringBuilder.append(bufferedStrChunk);
                    }

                    xml = stringBuilder.toString();

                    return xml;

                } catch (ClientProtocolException cpe) {
                    Log.d("TEST", "First Exception coz of HttpResponese :"
                            + cpe);
                    cpe.printStackTrace();
                    return xml;
                } catch (IOException ioe) {
                    Log.d("TEST", "Second Exception coz of HttpResponse :"
                            + ioe);
                    ioe.printStackTrace();
                    return xml;
                }

            } catch (UnsupportedEncodingException uee) {
                Log.d("TEST",
                        "An Exception given because of UrlEncodedFormEntity argument :"
                                + uee);
                uee.printStackTrace();
                return xml;
            }
        } catch (Exception e) {

            Log.d("TEST",
                    "An Exception given because of UrlEncodedFormEntity argument :"
                            + e);
            e.printStackTrace();
            return xml;
        }
        // return XML

    }

    public String getXmlFromUrlTest(String url) {
        String xml = "";

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);// replace

            httpPost.setHeader("Authorization",
                    "Content-Type: application/x-www-form-urlencoded");

            try {
                HttpResponse httpResponse = httpClient.execute(httpPost);
                InputStream inputStream = httpResponse.getEntity().getContent();
                InputStreamReader inputStreamReader = new InputStreamReader(
                        inputStream);
                BufferedReader bufferedReader = new BufferedReader(
                        inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String bufferedStrChunk = null;
                while ((bufferedStrChunk = bufferedReader.readLine()) != null) {
                    stringBuilder.append(bufferedStrChunk);
                }

                xml = stringBuilder.toString();

                return xml;

            } catch (ClientProtocolException cpe) {
                Log.d("TEST", "First Exception coz of HttpResponese :" + cpe);
                cpe.printStackTrace();
                return xml;
            } catch (IOException ioe) {
                Log.d("TEST", "Second Exception coz of HttpResponse :" + ioe);
                ioe.printStackTrace();
                return xml;
            }

        } catch (Exception e) {

            Log.d("TEST",
                    "An Exception given because of UrlEncodedFormEntity argument :"
                            + e);
            e.printStackTrace();
            return xml;
        }
        // return XML

    }

    public Document getDomElement(String xml) {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);

        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        }
        // return DOM
        return doc;
    }

    public String getValue(Element item, String str) {
        NodeList n = item.getElementsByTagName(str);
        return this.getElementValue(n.item(0));
    }

    public final String getElementValue(Node elem) {
        Node child;
        if (elem != null) {
            if (elem.hasChildNodes()) {
                for (child = elem.getFirstChild(); child != null; child = child
                        .getNextSibling()) {
                    if (child.getNodeType() == Node.TEXT_NODE) {
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

    public String callJSonWebService(String Url) {
        String xml = null;
        try {

           /* httpPost.setHeader("Authorization",
                    "Content-Type: application/x-www-form-urlencoded");*/
            try {
                //newly added for security protocol 1/1/2020**********

                if(Url.startsWith("http")){

                    HttpClient httpClient = new DefaultHttpClient();

//                    httpClient.getConnectionManager().getSchemeRegistry().register(
//                            new Scheme("https", SSLSocketFactory.getSocketFactory(), 443)
//                    );

                    HttpGet httpget = new HttpGet(Url);// replace
                    HttpResponse httpResponse = httpClient.execute(httpget);
                    InputStream inputStream = httpResponse.getEntity().getContent();

                    InputStreamReader inputStreamReader = new InputStreamReader(
                            inputStream);
                    BufferedReader bufferedReader = new BufferedReader(
                            inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String bufferedStrChunk = null;
                    while ((bufferedStrChunk = bufferedReader.readLine()) != null) {
                        stringBuilder.append(bufferedStrChunk);
                    }

                    xml = stringBuilder.toString();

                    return xml;

                }else{

                    URL url = new URL(Url);

                    HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream();

                    InputStreamReader inputStreamReader = new InputStreamReader(
                            inputStream);
                    BufferedReader bufferedReader = new BufferedReader(
                            inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String bufferedStrChunk = null;
                    while ((bufferedStrChunk = bufferedReader.readLine()) != null) {
                        stringBuilder.append(bufferedStrChunk);
                    }

                    xml = stringBuilder.toString();

                    return xml;

                }

                //end ********


                //old working code commented when added above code ***********
//
//                HttpClient httpClient = new DefaultHttpClient();
//                HttpGet httpget = new HttpGet(Url);
//
//                HttpResponse httpResponse = httpClient.execute(httpget);
//                InputStream inputStream = httpResponse.getEntity().getContent();
//                InputStreamReader inputStreamReader = new InputStreamReader(
//                        inputStream);
//                BufferedReader bufferedReader = new BufferedReader(
//                        inputStreamReader);
//                StringBuilder stringBuilder = new StringBuilder();
//                String bufferedStrChunk = null;
//                while ((bufferedStrChunk = bufferedReader.readLine()) != null) {
//                    stringBuilder.append(bufferedStrChunk);
//                }
//
//                xml = stringBuilder.toString();
//
//                return xml;

                // end *****************

            } catch (ClientProtocolException cpe) {
                Log.d("TEST", "First Exception coz of HttpResponese :" + cpe);
                cpe.printStackTrace();
                return xml;
            } catch (IOException ioe) {
                Log.d("TEST", "Second Exception coz of HttpResponse :" + ioe);
                ioe.printStackTrace();
                return xml;
            }

        } catch (Exception e) {

            Log.d("TEST",
                    "An Exception given because of UrlEncodedFormEntity argument :"
                            + e);
            e.printStackTrace();
            return xml;
        }
    }

}
