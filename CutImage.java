import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;


public class CutImage extends JPanel {
  String image;
  Image i;
  BufferedImage bufferedImage;

  public CutImage(String m) {
    this.image = m;
    i = new ImageIcon(this.image).getImage();

    try{
      bufferedImage = ImageIO.read(new File(m));
    }catch(IOException e){
  }
  }

  public void paint(Graphics g){

    Graphics2D g2d = (Graphics2D) g;
    g2d.drawImage(i, 0, 0, null);

    ArrayList<BufferedImage> listCut = new  ArrayList<BufferedImage>();
    int height;

    //check si la taille de l'image est un multiple de 20 px
    if ((Math.ceil(bufferedImage.getHeight()/20))%20 == 0) {
      height = (int)Math.ceil(bufferedImage.getHeight()/20)+1;
    } else {
      height = (int)Math.ceil(bufferedImage.getHeight()/20);
    }
    System.out.println(height);
    try{
      System.out.println(bufferedImage.getWidth());
      System.out.println(bufferedImage.getHeight());
      for (int y = 0; y < height; y++) {
        int hei = height;
        if (bufferedImage.getHeight() - height*y < 20) {
          hei = bufferedImage.getHeight() - height*y;
        }
        listCut.add(bufferedImage.getSubimage(0,height*y,bufferedImage.getWidth(), hei));
      }



    }catch(Exception e){

    }
      int c = 0;
      int idy = 0;
      for (BufferedImage img : listCut) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-img.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx,
        AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage bimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        try {
          ImageIO.write(bimg, "jpg", new File("res_" + (idy++) + ".jpg"));

        } catch(Exception e) {}

        bimg = op.filter(img, null);
        g2d.drawImage(bimg, null, bufferedImage.getHeight(), c);
        c+=height;
      }

  }

  public static void main(String[] args) {

    JFrame frame = new JFrame("Flip image");
    frame.add(new CutImage(args[0]));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 600);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

  }
}
