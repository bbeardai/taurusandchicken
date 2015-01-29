package com.taurusandchicken.web.utility;

	import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@SuppressWarnings("restriction")
public class PressMark {
	 public static void alterSizeandPress(String srcImgFile, String pressImgFile){  
	    	//pressImg url
	    	//String pressImg="/Users/bbeardai/git/taurusandchicken/src/main/webapp/resources/images/pressImg.png";
		 	String pressImg="/tcdata/tomcat/webapps/ROOT/resources/images/pressImg.png";
	    	File file = new File(pressImg);
	    	if(file.exists())  
		    	System.out.println("PressImgfileExists");  
		    	else  
		    	System.out.println("PressImgfileNotExists"); 
	    	//srcImgFile url
	    	System.out.println(srcImgFile);
	    	File _file = new File(srcImgFile); 
	    	if(_file.exists())  
	    	System.out.println("fileExists");  
	    	else  
	    	System.out.println("fileNotExists"); 
	    	
	    	
			try {
				//alter picture size
				Image src = ImageIO.read(_file);
				BufferedImage image = new BufferedImage(340,220,BufferedImage.TYPE_INT_RGB);  
				Graphics g=image.createGraphics();
				g.drawImage(src,0,0,340,220,null);  
				
				//pressImg
	            Image src_pressImg = ImageIO.read(file);
	            int wideth_pressImg = src_pressImg.getWidth(null);
	            int height_pressImg = src_pressImg.getHeight(null);
	            g.drawImage(src_pressImg, (340 - wideth_pressImg) / 2,
	                    (220 - height_pressImg) / 2, wideth_pressImg, height_pressImg, null);
				
	            //press end
	            g.dispose();
	            
	            //output Pic
	            FileOutputStream out = new FileOutputStream(srcImgFile);
	            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	            encoder.encode(image);
	            System.out.println("Press Complete");
	            out.close();
	            
				} catch (IOException e) {
					e.printStackTrace();
				}
	    }

}
