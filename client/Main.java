import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;

public class Main {
    public static void main (String[] args)throws RemoteException, NotBoundException {
      try {
        String ip = "localhost";
        int port = 1099;
        Registry annuaire = LocateRegistry.getRegistry(ip, port);
        ServiceCutImage service = (ServiceCutImage) annuaire.lookup("reverseImage");
        ReverseImage ri = new ReverseImage();

        ServiceReverseImage l = (ServiceReverseImage) UnicastRemoteObject.exportObject(ri, 0);


        service.enregistrerClient(ri);

      } catch(Exception e) {
        e.printStackTrace();
      }
    }
}