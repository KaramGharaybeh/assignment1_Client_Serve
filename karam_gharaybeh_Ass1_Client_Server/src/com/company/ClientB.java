package com.company;

import com.sun.org.apache.xpath.internal.objects.XString;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientB {
    private static DataOutputStream out ;
    private static DataInputStream in ;
    public static void main(String[] args) {
        Scanner cin=new Scanner(System.in);
        try {

            Socket CliB = new Socket("localhost", 3324);
            in = new DataInputStream(CliB.getInputStream());
                    out =new DataOutputStream(CliB.getOutputStream());
            System.out.println(in.readUTF());
            String SerMass= in.readUTF();
            while (SerMass!=" yes you are pass  "|| SerMass!= "Unfortunately, you lost ")
            {
                System.out.println(SerMass);
                out.writeInt(cin.nextInt());
                SerMass= in.readUTF();
            }
            System.out.println(in.readUTF());




        }
        catch (IOException e) {
            e.printStackTrace();
        }



    }
}
