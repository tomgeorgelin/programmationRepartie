import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.io.Serializable;

public class Bande implements Serializable {
	private int numero;
	private ImageIcon image;

	public Bande (int n, ImageIcon img) {
		this.numero = n;
		this.image = img;
	}

	public int getNb() {
		return this.numero;
	}

	public ImageIcon getImage() {
		return this.image;
	}
}