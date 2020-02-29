import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;

import java.awt.Graphics2D;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;

class ReverseImage implements ServiceReverseImage {

	ArrayList<Bande> bandeList;

	public ReverseImage() {
		this.bandeList = new ArrayList<>();
	}	

	public Bande traitement(Bande img) {
		Image imgI = img.getImage().getImage();
		BufferedImage imgB = new BufferedImage(imgI.getWidth(null), imgI.getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics2D bGr = imgB.createGraphics();
    	bGr.drawImage(imgI, 0, 0, null);
    	bGr.dispose();
		if (!this.bandeList.contains(img)) {
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
	        tx.translate(-imgB.getWidth(null), 0);
	        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	        BufferedImage bimg = new BufferedImage(imgB.getWidth(null), imgB.getHeight(null), BufferedImage.TYPE_INT_RGB);

	        bimg = op.filter(imgB, null);
	        System.out.println("flip : " + img.getNb());
	        return new Bande(img.getNb(),new ImageIcon(bimg));
		}
		else {
			 return null;	
		}
	}
}