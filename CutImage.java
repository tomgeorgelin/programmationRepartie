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
    g2d.drawImage(i, 10, 10, null);

    ArrayList<BufferedImage> listCut = new  ArrayList<BufferedImage>();
    try{
      System.out.println(bufferedImage.getWidth());
      System.out.println(bufferedImage.getHeight());
      for (int y = 0; y < bufferedImage.getHeight(); y += 20) {
        listCut.add(bufferedImage.getSubimage(0,y,bufferedImage.getWidth(), 20));
      }



    }catch(Exception e){}
      int c =-10;
      int idy = 0;
      for (BufferedImage img : listCut) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-img.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx,
        AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage bimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        ImageIO.write(bimg, "jpg", new File("res_" + idy++ + ".jpg"));

        bimg = op.filter(img, null);
        g2d.drawImage(bimg, null, 290, c+=22);
      }

  }

  public static void main(String[] args) {

    JFrame frame = new JFrame("Flip image");
    frame.add(new CutImage(args[0]));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(570, 230);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

  }
}
