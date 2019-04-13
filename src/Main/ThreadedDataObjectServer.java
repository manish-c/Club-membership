package Main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;



public class ThreadedDataObjectServer
{  public static void main(String[] args )
{
    System.out.println("Inside ThreadedDataObjectServer");
    try
    {  ServerSocket s = new ServerSocket(7007);

        for (;;)
        {  Socket incoming = s.accept( );
            new ThreadedDataObjectHandler(incoming).start();

        }
    }
    catch (Exception e)
    {  System.out.println(e);
    }
}
}

class ThreadedDataObjectHandler extends Thread
{  public ThreadedDataObjectHandler(Socket i)
{
    incoming = i;
}

    private static String checklogin;

    public void run()
    {  try
    { 	ObjectInputStream in =
            new ObjectInputStream(incoming.getInputStream());

        ObjectOutputStream out =
                new ObjectOutputStream(incoming.getOutputStream());
        System.out.println("before DataObject");
        myObject = (DataObject)in.readObject();

        System.out.println("Message read: " + myObject.getMessage());

        //---------------------------------------------------------------------------------------------

        String mess = myObject.getMessage();
        String info[]= mess.split("/", 2);

        System.out.println("info[0]: " + info[0]);
        System.out.println("info[1]: " + info[1]);


        switch(info[0]){
            case "login": checklogin = Login.login_user(info[1]);       // for loging in
                break;
            case "signup": checklogin = Login.signup_user(info[1]);     // for signup
                break;
            case "user": checklogin = Login.sel_user(info[1]);          // for get all users info
                break;
            case "admin": checklogin = Login.admin_c(info[1]);          // check if admin
                break;
            case "insert": checklogin = Login.insert_u(info[1]);        // for inserting new user from admin
                break;
            case "delete": checklogin = Login.delete_u(info[1]);        // for deleting users(Admin), user account(user)
                break;
            case "update": checklogin = Login.update_u(info[1]);        // for updating users(Admin), user account(user)
                break;
            case "graph": checklogin = Login.graph_a(info[1]);          // for generating graph
                break;
            case "graph_m": checklogin = Login.graph_m(info[1]);        // check major for graph
                break;
        }

        myObject.setMessage(checklogin);

        //---------------------------------------------------------------------------------------------
        System.out.println("Message written: " + myObject.getMessage());

        out.writeObject(myObject);

        in.close();

        out.close();

        incoming.close();

    }
    catch (Exception e)
    {  System.out.println(e);
    }
    }

    DataObject myObject = null;
    private Socket incoming;

}
