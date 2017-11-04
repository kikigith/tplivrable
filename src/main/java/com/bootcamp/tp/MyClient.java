/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.tp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author ARESKO
 */
public class MyClient {

    public static void main(String[] args) {

//        String string = "";
//        try {
//
//            // Step1: Let's 1st read file from fileSystem
//            // Change CrunchifyJSON.txt path here
//            InputStream livrableInputStream = new FileInputStream("C:\\Users\\ARESKO\\Documents\\NetBeansProjects\\BOOTCAMP PROJECT\\web-service\\tplivrable\\livrable.json");
//            InputStreamReader livrableReader = new InputStreamReader(livrableInputStream);
//            BufferedReader br = new BufferedReader(livrableReader);
//            String line;
//            while ((line = br.readLine()) != null) {
//                string += line + "\n";
//            }
//
//            JSONObject jsonObject = new JSONObject(string);
//            System.out.println(jsonObject);
//
//            // Step2: Now pass JSON File Data to REST Service
//            try {
//                URL url = new URL("http://localhost:8080/service/livrables/create");
//                URLConnection connection = url.openConnection();
//                connection.setDoOutput(true);
//                connection.setRequestProperty("Content-Type", "application/json");
//                connection.setConnectTimeout(5000);
//                connection.setReadTimeout(5000);
//                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
//                out.write(jsonObject.toString());
//                out.close();
//
//                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//                while (in.readLine() != null) {
//                }
//                System.out.println("\n Livrable REST Service Invoked Successfully..");
//                in.close();
//            } catch (Exception e) {
//                System.out.println("\n Error while calling Livrable REST Service");
//                System.out.println(e);
//            }
//
//            br.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
