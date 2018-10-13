/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.hw03.impl;
import cz.muni.fi.pb162.hw03.ReadWriteIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;


/**
 *
 * @author Ondrej Urbanovsky
 */
public class ReadWriteIOImpl implements ReadWriteIO{

    @Override
    public String streamToString(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        reader.close();
        out.append('\n');
        return out.toString();
        
    }

    @Override
    public String fileToString(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        return streamToString(is);
    }

    @Override
    public void stringToStream(String string, OutputStream outputStream) throws IOException {
        outputStream.write(string.getBytes(Charset.forName("UTF-8")));
    }

    @Override
    public void stringToFile(String string, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        this.stringToStream(string, os);
        os.close();
    }
    
}
