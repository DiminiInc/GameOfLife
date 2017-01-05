package com.coursework.main;

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.io.IOException;

public class UpdateModule implements Runnable{
    private String currentVersion = "0.1.1.0(beta)"; //current app version
    private String newVersion= ""; //string for new version

    @Override public void run()
    {
        File file0 = new File("VersionNumber.txt"); //file for app version
        File file = new File("GameOfLife.jar"); // file of app
        File file2 = new File("GameOfLife(oldversion).jar"); //file of old app file
        file2.delete(); //delete old files
        file0.delete();
        file2.deleteOnExit();
        file0.deleteOnExit();
        try
        {
            URL website = new URL("http://dimini.tk/global/site_files/GameOfLife/VersionNumber.txt"); //version number file on web
            try
            {
                ReadableByteChannel rbc = Channels.newChannel(website.openStream()); //download new version to file
                FileOutputStream fos = new FileOutputStream("VersionNumber.txt");//file version file
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                fos.close();
                rbc.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            try(FileReader reader = new FileReader(file0))
            {
                int c;
                while((c=reader.read())!=-1)//read file to string
                {
                    newVersion = newVersion+((char)c);
                }
                reader.close();
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        if ((!currentVersion.equals(newVersion))&&(!newVersion.equals(""))) //if new version available
        {
            int choice=JOptionPane.showConfirmDialog(null,"New update available. Download now?\nCurrent version: "+currentVersion+"\nNew version: "+newVersion, "New update", JOptionPane.YES_NO_OPTION); //show update dialog
            if (choice==0) //if confirmed to update
            {
                boolean success = file.renameTo(file2);//rename current file
                try
                {
                    URL website = new URL("http://dimini.tk/global/site_files/GameOfLife/GameOfLife.jar");//web app file
                    try
                    {
                        ReadableByteChannel rbc = Channels.newChannel(website.openStream());//download app to file
                        FileOutputStream fos = new FileOutputStream("GameOfLife.jar");
                        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                        fos.close();
                        rbc.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                catch (MalformedURLException e)
                {
                    e.printStackTrace();
                }
                try
                {
                    Process proc = Runtime.getRuntime().exec("java -jar GameOfLife.jar"); //launch updated app
                    InputStream in = proc.getInputStream();
                    InputStream err = proc.getErrorStream();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                System.exit(0);//close old app
            }
        }
    }
}
