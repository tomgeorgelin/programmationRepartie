import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.awt.image.BufferedImage;
import java.rmi.Remote;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.rmi.RemoteException;

interface ServiceCutImage extends Remote {
	public void enregistrerClient(ServiceReverseImage client) throws RemoteException;

	public void cutImages() throws RemoteException;

	public void initialisation(ImageIcon file) throws RemoteException;

	public ImageIcon lancerDistribution() throws RemoteException;
}