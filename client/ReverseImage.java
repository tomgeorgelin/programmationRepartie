import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;

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
		if (!this.bandeList.contains(img)) {
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
	        tx.translate(-img.getImage().getWidth(null), 0);
	        AffineTransformOp op = new AffineTransformOp(tx,
	        AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	        BufferedImage bimg = new BufferedImage(img.getImage().getWidth(null), img.getImage().getHeight(null), BufferedImage.TYPE_INT_RGB);

	        bimg = op.filter((BufferedImage)img.getImage(), null);
	        return new Bande(img.getNb(),bimg);
		}
		else {
			 return null;	
		}
	}
}