import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.rmi.NotBoundException;
import java.util.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import java.io.*;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

public class CutImage implements ServiceCutImage {
	private ArrayList<ServiceReverseImage> clientList;
	transient ArrayList<Bande> bandeList;

	private ArrayList<Bande> flipBandes;

	private BufferedImage bufferedImage;
	private int height;

	public CutImage() throws RemoteException {
		this.clientList = new ArrayList<>();
		this.bandeList = new ArrayList<>();
		this.flipBandes = new ArrayList<>();
		
	}

	public void initialisation(ImageIcon img) throws RemoteException {
		this.bandeList = new ArrayList<>();
		this.flipBandes = new ArrayList<>();
		Image imgI = img.getImage();
		this.bufferedImage = new BufferedImage(imgI.getWidth(null), imgI.getHeight(null),BufferedImage.TYPE_INT_RGB);
		Graphics2D bGr = this.bufferedImage.createGraphics();
    	bGr.drawImage(imgI, 0, 0, null);
    	bGr.dispose();
    	/*try {
    		ImageIO.write(this.bufferedImage,"jpg",new File("test.jpg"));

    	} catch (Exception e) {
    		
    	}*/
		//this.bufferedImage = (BufferedImage)file.getImage();
		cutImages();
	}

	public void enregistrerClient(ServiceReverseImage c) throws RemoteException {
		this.clientList.add(c);
		System.out.println("Un nouveau client s est connecte");
		
	}

	public ImageIcon lancerDistribution() throws RemoteException{
		BufferedImage result = new BufferedImage(this.bufferedImage.getWidth(), this.bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0 ; i < this.bandeList.size() ; i++) {
			this.flipBandes.add(this.clientList.get(i%this.clientList.size()).traitement(this.bandeList.get(i)));
			System.out.println("Bande numero : " + i + " donnee au client numero : " + i%this.clientList.size());
			if (i == this.bandeList.size()-1) {
    			Graphics2D g = (Graphics2D)result.getGraphics();
    			int x = 0, y = 0;
    			for(Bande image : this.flipBandes){
					Image imgI = image.getImage().getImage();
					BufferedImage imgB = new BufferedImage(imgI.getWidth(null), imgI.getHeight(null),BufferedImage.TYPE_INT_RGB);
					
					Graphics2D bGr = imgB.createGraphics();
					bGr.drawImage(imgI, 0, 0, null);
					bGr.dispose();
			        g.drawImage(imgB, 0, y, null);
					y+= imgB.getHeight();
    			}
    			System.out.println("L image a bien ete creee");
			}
		}
    	return new ImageIcon(result);
	}

	public void cutImages() throws RemoteException {
		int nbB;

		if ((Math.ceil((double)this.bufferedImage.getHeight()/20))%20 == 0) {
			this.height = (int)Math.ceil((double)this.bufferedImage.getHeight()/20)+1;
			nbB = 20;
		} else {
			this.height = (int)Math.ceil((double)this.bufferedImage.getHeight()/20);
			nbB = 21;
		}


		try {
			int c= 0;
			for (int y = 0; y < nbB+1 ; y++) {
				c= this.height*y;
				if (this.bufferedImage.getHeight() - this.height*y < this.height) {
				  this.height = (this.bufferedImage.getHeight() - this.height*y);

				}
				this.bandeList.add(new Bande(y,new ImageIcon(this.bufferedImage.getSubimage(0,c,this.bufferedImage.getWidth(), this.height))));
			
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}