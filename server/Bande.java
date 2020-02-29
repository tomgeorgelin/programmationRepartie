import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Bande implements Serializable {
	private int numero;
	private BufferedImage image;

	public Bande (int n,BufferedImage img) {
		this.numero = n;
		this.image = img;
	}

	public int getNb() {
		return this.numero;
	}

	public BufferedImage getImage() {
		return this.image;
	}
}