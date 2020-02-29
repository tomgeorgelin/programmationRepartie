import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.rmi.NotBoundException;
import java.util.*;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.io.*;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

public class CutImage implements ServiceCutImage {
	private ArrayList<ServiceReverseImage> clientList;
	transient ArrayList<Bande> bandeList;

	private ArrayList<Bande> flipBandes;

	private BufferedImage bufferedImage;

	public CutImage(String file) throws RemoteException {
		this.clientList = new ArrayList<>();
		this.bandeList = new ArrayList<>();
		this.flipBandes = new ArrayList<>();
		try{
			this.bufferedImage = ImageIO.read(new File(file));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		cutImages();
	}

	public void enregistrerClient(ServiceReverseImage c) throws RemoteException {
		this.clientList.add(c);
		System.out.println("client ajouté");
		for (int i = 0 ; i < this.bandeList.size() ; i++) {
			this.flipBandes.add(this.clientList.get(i%this.clientList.size()).traitement(this.bandeList.get(i)));
			if (i == this.bandeList.size()-1) {
				BufferedImage result = new BufferedImage(this.bufferedImage.getWidth(), this.bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
    			Graphics g = result.getGraphics();
    			int x = 0, y = 0;
    			for(Bande image : this.flipBandes){

					Image imgI = image.getImage().getImage();
					BufferedImage imgB = new BufferedImage(imgI.getWidth(null), imgI.getHeight(null),BufferedImage.TYPE_INT_ARGB);
					/*Graphics2D bGr = imgB.createGraphics();
					bGr.drawImage(imgI, 0, 0, null);
					bGr.dispose();*/

			        g.drawImage(imgB, x, y, null);
			        x += 20;
			        if(x > result.getWidth()){
			            x = 0;
			            y += imgB.getHeight();
			        }
    			}
    			try {
    				ImageIO.write(result,"png",new File("result.png"));
    			} 
    			catch (Exception e) {
    				e.printStackTrace();
    			}
    			System.out.println("imageCréée");
			}

		}
	}

	public void cutImages() throws RemoteException {
		int height, nbB;

		if ((Math.ceil(this.bufferedImage.getHeight()/20))%20 == 0) {
			height = (int)Math.ceil(this.bufferedImage.getHeight()/20)+1;
			nbB = 20;
		} else {
			height = (int)Math.ceil(this.bufferedImage.getHeight()/20);
			nbB = 21;
		}
		try {
			for (int y = 0; y < nbB ; y++) {
				if (this.bufferedImage.getHeight() - height*y < height) {
				  height = (this.bufferedImage.getHeight() - height*y);
				}
				this.bandeList.add(new Bande(y,new ImageIcon(this.bufferedImage.getSubimage(0,height,this.bufferedImage.getWidth(), height))));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}