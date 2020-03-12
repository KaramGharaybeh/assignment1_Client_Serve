package com.company;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int  serverRandomNum;
    private static DataOutputStream outOfFirst;
    private static DataOutputStream outOfSecond;
    private static Socket first_visitor;
    private static Socket second_visitor;
    public static boolean isTheNum(int n)
    {
    if (n==serverRandomNum) {

        return true;
    }
    else {

        return false;
    }
    }
    /*
    private static  void result(int w)
    {
        if(w==1)
        {
            outOfFirst.writeUTF("You will try to guess this number between 1 and 10 \n You have 5 tries to guess it");
            outOfSecond.writeUTF("You will try to guess this number between 1 and 10 \n You have 5 tries to guess it");

        }
        else
        {

        }
    }
*/
    public static void main(String[] args) {
        try {
            int winner=0;
            serverRandomNum = (int)((Math.random()*((10-1)+1))+1);
            System.out.println("test "+serverRandomNum);
            ServerSocket Server=new ServerSocket(3324);
             first_visitor=Server.accept();
             second_visitor=Server.accept();
             outOfFirst  =new DataOutputStream(first_visitor.getOutputStream());
             outOfSecond  =new DataOutputStream(second_visitor.getOutputStream());
            DataInputStream inOfFirst =new  DataInputStream(first_visitor.getInputStream());
            DataInputStream inOfSecond=new  DataInputStream(second_visitor.getInputStream());
            outOfFirst.writeUTF("You will try to guess this number between 1 and 10 \n You have 5 tries to guess it");
            outOfSecond.writeUTF("You will try to guess this number between 1 and 10 \n You have 5 tries to guess it");

            for (int k=1; k<=10;k++) {
                if(k%2==0)
                {
                    outOfFirst.writeUTF( "Enter the number, it's your turn:   ");
                    int temp= inOfFirst.readInt();
                    if (isTheNum(temp))
                    {
                        winner=1 ;
                        break;
                    }
                }
                else
                {
                    outOfSecond.writeUTF("Enter the number, it's your turn:   ");
                    int temp= inOfSecond.readInt();
                    if (isTheNum(temp))
                    {
                        winner=2;
                        break;
                    }
                }

            }

            if(winner==1)
            {
                outOfFirst.writeUTF(" yes you are pass ");
                outOfSecond.writeUTF(" Unfortunately, you lost ");
                System.out.println(" the first_visitor is pass\n");
            }
            else if(winner==2)
            {
                outOfFirst.writeUTF("Unfortunately, you lost ");
                outOfSecond.writeUTF(" yes you are pass  ");
                System.out.println(" the second_visitor is pass");
            }
            else
                {
                    outOfFirst.writeUTF("Unfortunately, you lost ");
                    outOfSecond.writeUTF("Unfortunately, you lost ");
                    System.out.println(" the no one  is pass :( ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
