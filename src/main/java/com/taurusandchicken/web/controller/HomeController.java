package com.taurusandchicken.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.taurusandchicken.web.dao.AddressDAO;
import com.taurusandchicken.web.dao.IdphotoDAO;
import com.taurusandchicken.web.dao.OrderDAO;
import com.taurusandchicken.web.dao.UserDAO;
import com.taurusandchicken.web.module.*;







/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	UserDAO userDAO;
	@Autowired
	IdphotoDAO idphotoDAO;
	@Autowired
	AddressDAO addressDAO;
	@Autowired
	OrderDAO orderDAO;
	@Autowired
	OrderDAO shiporderDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		model.addAttribute("username", username);
		if(!username.equalsIgnoreCase("anonymousUser")){
			model.addAttribute("username", userDAO.findByUserName(username).getnickname());
		}
		
	      
	      
	      /*
	      String sn=userService.getSN(name);
	      if(sn!=null){
	    	  model.addAttribute("username", sn);
	    	  System.out.println(sn+"home page sn");
	      }else{
	    	  model.addAttribute("username", name);
	    	  System.out.println(sn+"home page sns");
	      }
	      */
	      //List<Picture> piclist = pictureService.homePicture();
	      //model.addAttribute("piclist", piclist);
		
		return "home";
	}
	
	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public String reg(Locale locale, Model model,
						@ModelAttribute("nickname")String nickname,
						@ModelAttribute("sign_up_username")String username,
						@ModelAttribute("sign_up_password")String password) throws UnsupportedEncodingException{
		
		//username=new String(username.getBytes("gb2312"),"UTF-8");
		System.out.println(username);
		//email=new String(email.getBytes("ISO-8859-1"),"UTF-8");
		userDAO.addUser(nickname, username,  password);
		//String info = "http://localhost:8080/myweb/comf?sn="+username;
		//Email.sendMail(email, info);
		System.out.println(username);
		System.out.println(username);
		System.out.println("处理中!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return "redirect:/";
	}
	@RequestMapping(value = "/loginfailure", method = RequestMethod.GET)
	public String loginfailure(Locale locale, Model model
						) throws UnsupportedEncodingException{
		return "loginfailure";
	}
	
	@RequestMapping(value = "/address", method = RequestMethod.GET)
	public String address(Locale locale, Model model
						) throws UnsupportedEncodingException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		List<Idphoto> piclist = idphotoDAO.findByUserName(username);
		model.addAttribute("piclist", piclist);
		User user = userDAO.findByUserName(username);
		model.addAttribute("username", user.getnickname());
		return "address";
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String s403(Locale locale, Model model
						) throws UnsupportedEncodingException{
		return "403";
	}
	@RequestMapping(value = "/loginsignin", method = RequestMethod.GET)
	public String loginsignin(Locale locale, Model model
						) throws UnsupportedEncodingException{
		System.out.println("000000000000000000000000000000000");
		return "loginsignin";
	}
	@RequestMapping(value = "/idphoto", method = RequestMethod.GET)
	public String idphoto(Locale locale, Model model
						) throws UnsupportedEncodingException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		model.addAttribute("username", userDAO.findByUserName(username).getnickname());
		List<Idphoto> piclist = idphotoDAO.findByUserName(username);
		model.addAttribute("piclist", piclist);
		return "idphoto";
	}
	
	@RequestMapping(value = "/uploadid", method = RequestMethod.POST)
	public String handleUploadData(@RequestParam("file")CommonsMultipartFile file, 
			@ModelAttribute("name")String name,
			ModelMap map, HttpServletRequest request, Model model
			) throws FileNotFoundException, Exception{
		if (!file.isEmpty()) {
				boolean flag = false;
			   //String path = this.servletContext.getRealPath("/src/main/webapp/resources/temp/");  
			   //System.out.println(path);
			   String fileName = file.getOriginalFilename();
			   String fileType = fileName.substring(fileName.lastIndexOf("."));
			   System.out.println(fileType); 
			   String path = request.getSession().getServletContext().getRealPath("/")+"resources/upload";
			   System.out.println(path);
			   
			   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			   String username = auth.getName();
			   System.out.println(username);
			   
			   Idphoto idphoto = new Idphoto(path,fileType, name, userDAO.findByUserName(username));
			   File file2 = new File(path,idphoto.getIdphotoid() + fileType);
			   System.out.println(fileType);
			   idphotoDAO.addUser(idphoto);
			   
			   //map.addAttribute("newpic", picture.getPictureid());
			   //map.addAttribute("newpicobj", picture);
			   try {
				    file.getFileItem().write(file2); 
				    System.out.println("----------------");
			   } catch (Exception e) {
				    e.printStackTrace();
			   }
			   
			
			
				return "redirect:idphoto";
			}else{
				return "redirect:idphoto";
			}
	}
	
	@RequestMapping(value = "/addressupdate", method = RequestMethod.GET)
	public String addressupdate(Locale locale, Model model,
						@ModelAttribute("line1")String line1,
						@ModelAttribute("line2")String line2,
						@ModelAttribute("province")String province,
						@ModelAttribute("city")String city,
						@ModelAttribute("zip")String zip,
						@ModelAttribute("phone")String phone,
						@ModelAttribute("memo")String memo,
						@ModelAttribute("idphotoid")String idphotoid
						) throws UnsupportedEncodingException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userDAO.findByUserName(username);
		Idphoto idphoto = idphotoDAO.findByIdphotoid(idphotoid);
		Address address = new Address(line1,province,city,zip,phone,memo,user,idphoto);
		addressDAO.addAddress(address);
		
		return "redirect:viewaddress";
	}
	
	@RequestMapping(value = "/viewaddress", method = RequestMethod.GET)
	public String viewaddress(Locale locale, Model model) throws UnsupportedEncodingException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		model.addAttribute("username", userDAO.findByUserName(username).getnickname());
		List<Address> addresslist = addressDAO.findByUserName(username);
		model.addAttribute("addresslist", addresslist);
		
		return "viewaddress";
	}
	@RequestMapping(value = "/vieworder", method = RequestMethod.GET)
	public String vieworder(Locale locale, Model model) throws UnsupportedEncodingException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		model.addAttribute("username", userDAO.findByUserName(username).getnickname());
		List<Shiporder> orderlist = orderDAO.findByUserName(username);
		model.addAttribute("orderlist", orderlist);
		
		return "vieworder";
	}
	@RequestMapping(value = "/neworder", method = RequestMethod.GET)
	public String neworder(Locale locale, Model model,
			@ModelAttribute("orderid")String orderid
			) throws UnsupportedEncodingException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		model.addAttribute("username", userDAO.findByUserName(username).getnickname());
		List<Address> addresslist = addressDAO.findByUserName(username);
		model.addAttribute("addresslist", addresslist);
		model.addAttribute("orderid", orderid);

		
		return "neworder";
	}
	
	@RequestMapping(value = "/addorder", method = RequestMethod.GET)
	public String addorder(Locale locale, Model model,
						@ModelAttribute("orderaddressid")String orderaddressid,
						@ModelAttribute("orderid")String orderid
						) throws UnsupportedEncodingException{
		System.out.print(orderaddressid);
		System.out.print(orderid);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userDAO.findByUserName(username);
		Address address = addressDAO.findById(orderaddressid);
		Shiporder shiporder = new Shiporder(orderid, user, address);
		shiporderDAO.addOrder(shiporder);
		return "redirect:vieworder";
	}
	
	@RequestMapping(value = "/viewallorder", method = RequestMethod.GET)
	public String viewallorder(Locale locale, Model model) throws UnsupportedEncodingException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		model.addAttribute("username", userDAO.findByUserName(username).getnickname());
		List<Shiporder> orderlist = orderDAO.Allorder();
		model.addAttribute("orderlist", orderlist);
		
		return "viewallorder";
	}
	@RequestMapping(value = "/shiped", method = RequestMethod.GET)
	public String shiped(Locale locale, Model model,
						@ModelAttribute("shiporderid")String shiporderid
						) throws UnsupportedEncodingException{
		Shiporder shiporder = shiporderDAO.findById(shiporderid);
		shiporder.setStatus("Shioped");
		shiporderDAO.updateOrder(shiporder);
		return "redirect:viewallorder";
	}
	
}
