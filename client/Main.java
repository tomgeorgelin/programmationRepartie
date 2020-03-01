import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.io.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.Image;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Main implements Serializable {
	public static void main (String[] args)  {
		try {
			String ip = args[1];
	        int port = 1099;
	        Registry annuaire = LocateRegistry.getRegistry(ip, port);
	        ServiceCutImage service = (ServiceCutImage) annuaire.lookup("reverseImage");
	        service.initialisation(new ImageIcon(ImageIO.read(new File(args[0]))));
    		ImageIcon result = service.lancerDistribution();
	        try {
	        	Image imgI = result.getImage();
				BufferedImage imgB = new BufferedImage(imgI.getWidth(null), imgI.getHeight(null),BufferedImage.TYPE_INT_RGB);
				Graphics2D bGr = imgB.createGraphics();
		    	bGr.drawImage(imgI, 0, 0, null);
		    	bGr.dispose();
				ImageIO.write(imgB,"jpg",new File("result.jpg"));
				System.out.println("L image a ete recreer avec succes");
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		} 
		catch(Exception e) {
		  e.printStackTrace();
		}
	}
}