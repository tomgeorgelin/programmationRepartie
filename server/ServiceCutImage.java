import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

interface ServiceCutImage extends Remote {
	public void enregistrerClient(ServiceReverseImage client) throws RemoteException;

	public void cutImages() throws RemoteException;
}