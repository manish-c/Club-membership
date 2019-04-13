package Main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    static Prop prop_o = new Prop();

    public static String connect(String message) {
        String mes = null;
        try {

            DataObject myObject = new DataObject();

            myObject.setMessage(message);

            System.out.println("Message sent : " + myObject.getMessage());


            Socket socketToServer = new Socket(prop_o.HOST, prop_o.PORT);
            //Socket socketToServer = new Socket("localhost", 7007);

            ObjectOutputStream myOutputStream =
                    new ObjectOutputStream(socketToServer.getOutputStream());

            ObjectInputStream myInputStream =
                    new ObjectInputStream(socketToServer.getInputStream());

            myOutputStream.writeObject(myObject);

            myObject = (DataObject)myInputStream.readObject();

            System.out.println("Messaged received : " + myObject.getMessage());

            myOutputStream.close();

            myInputStream.close();

            socketToServer.close();

            mes = myObject.getMessage();
        } catch (Exception e) {
            System.out.println(e);
        }
        return mes;
    }
}