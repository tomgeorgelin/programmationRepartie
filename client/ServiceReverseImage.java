import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.awt.image.BufferedImage;
interface ServiceReverseImage extends Remote {
	
	public Bande traitement(Bande img) throws RemoteException;

}