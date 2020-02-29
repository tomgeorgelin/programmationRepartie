import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Main {
	public static void main (String[] args) {
		try {
			ServiceCutImage ci = new CutImage(args[0]);

			Registry reg = LocateRegistry.getRegistry();

			ServiceCutImage sci = (ServiceCutImage) UnicastRemoteObject.exportObject(ci, 0);

			reg.rebind("reverseImage", sci);


		} catch(Exception e) {
		  e.printStackTrace();
		}
	}


}
