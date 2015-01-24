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
public class IDphotoUtil {

	public static String alterSize(String srcImgFile){  
    	File _file = new File(srcImgFile);  
    	if(_file.exists())  
    	System.out.println("Œƒº˛¥Ê‘⁄");  
    	else  
    	System.out.println("Œƒº˛≤ª¥Ê‘⁄");  
    	int i = srcImgFile.indexOf(".");  
    	int sLen = srcImgFile.length();  
    	String suffix = srcImgFile.substring(i, sLen); //¥¯µ„+∫Û◊∫√˚  
    	String urlName=srcImgFile.substring(0,i); //¬∑æ∂+Œƒº˛√˚  
    	String newUrlName=urlName+"(∏±"+suffix;  
    	//System.out.print("–¬Œƒº˛√˚Œ™"+newUrlName);  
    	Image src=null;  
    	try {
			src = ImageIO.read(_file);
    	BufferedImage tag = new BufferedImage(340,220,BufferedImage.TYPE_INT_RGB);  
    	tag.getGraphics().drawImage(src,0,0,340,220,null);  
    	FileOutputStream newimage=new FileOutputStream(newUrlName);  
    	JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);  
    	
    	encoder.encode(tag); //Ω¸JPEG±‡¬Î  
    	newimage.close();  
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		return newUrlName;  
    	}  
    /** *//**
     * ∞—Õº∆¨”°À¢µΩÕº∆¨…œ
     * 
     * @param pressImg --
     *            ÀÆ”°Œƒº˛
     * @param targetImg --
     *            ƒø±ÍŒƒº˛
     */
    public final static String pressImage(String pressImg, String srcImgFile) {
    	String newUrlName="";
    	try {
            //ƒø±ÍŒƒº˛
            File _file = new File(srcImgFile);
            if(_file.exists())  
		    	System.out.println("Œƒº˛¥Ê‘⁄");  
		    	else  
		    	System.out.println("Œƒº˛≤ª¥Ê‘⁄");  
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);

            File _filebiao = new File(pressImg);
            Image src_biao = ImageIO.read(_filebiao);
            int wideth_biao = src_biao.getWidth(null);
            int height_biao = src_biao.getHeight(null);
            g.drawImage(src_biao, (wideth - wideth_biao) / 2,
                    (height - height_biao) / 2, wideth_biao, height_biao, null);
            //ÀÆ”°Œƒº˛Ω· ¯
            g.dispose();
            //–¬Œƒº˛
            int i = srcImgFile.indexOf(".");  
	    	int sLen = srcImgFile.length();  
	    	String suffix = srcImgFile.substring(i, sLen); //¥¯µ„+∫Û◊∫√˚  
	    	String urlName=srcImgFile.substring(0,i); //¬∑æ∂+Œƒº˛√˚  
	    	newUrlName=urlName+"±æ)"+suffix;  
            
            
            FileOutputStream out = new FileOutputStream(newUrlName);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return newUrlName;
    }
}
