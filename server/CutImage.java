import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.util.*;

import javax.swing.ImageIcon;
import java.io.*;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

public class CutImage implements ServiceCutImage {
	private ArrayList<ServiceReverseImage> clientList;
	transient ArrayList<Bande> bandeList;
	private BufferedImage bufferedImage;

	public CutImage(String file) {
		this.clientList = new ArrayList<>();
		this.bandeList = new ArrayList<>();
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
			this.clientList.get(i%this.clientList.size()).traitement(this.bandeList.get(i));
		}
	}

	public void cutImages() {
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
				this.bandeList.add(new Bande(y-1,new ImageIcon(this.bufferedImage.getSubimage(0,height,this.bufferedImage.getWidth(), height))));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}