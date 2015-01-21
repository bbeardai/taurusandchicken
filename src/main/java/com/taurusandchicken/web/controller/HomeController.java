package com.taurusandchicken.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.taurusandchicken.web.dao.AddressDAO;
import com.taurusandchicken.web.dao.IdphotoDAO;
import com.taurusandchicken.web.dao.OrderDAO;
import com.taurusandchicken.web.dao.OrderitemDAO;
import com.taurusandchicken.web.dao.UserDAO;
import com.taurusandchicken.web.module.*;







/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({"username"})
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
	@Autowired
	OrderitemDAO orderitemDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest req) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		model.addAttribute("username", username);
		System.out.println(username+"homepage");
		if(!username.equalsIgnoreCase("anonymousUser")){
			req.getSession().setAttribute("nickname", userDAO.findByUserName(username).getnickname());
			
			
		}
		
		return "home";
	}
	
	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public String reg(Locale locale, Model model,
						@ModelAttribute("nickname")String nickname,
						@ModelAttribute("sign_up_username")String username,
						@ModelAttribute("sign_up_password")String password) throws UnsupportedEncodingException{
		
		if(userDAO.findByUserName(username)==null){
			userDAO.addUser(nickname, username,  password);
			
			model.addAttribute("message", "恭喜注册成功");
			return "loginsignin";
		}else{
			model.addAttribute("message", "这个邮箱已经注册过了");
			return "loginsignin";
		}
		
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
		return "loginsignin";
	}
	@RequestMapping(value = "/idphoto", method = RequestMethod.GET)
	public String idphoto(Locale locale, Model model
						) throws UnsupportedEncodingException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
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
		List<Address> addresslist = addressDAO.findByUserName(username);
		model.addAttribute("addresslist", addresslist);
		
		return "viewaddress";
	}
	@RequestMapping(value = "/vieworder", method = RequestMethod.GET)
	public String vieworder(Locale locale, Model model) throws UnsupportedEncodingException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
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
		
		List<Shiporder> orderlist = orderDAO.Allorder();
		model.addAttribute("orderlist", orderlist);
		
		return "viewallorder";
	}
	
	@RequestMapping(value = "/newzhiyouorder", method = RequestMethod.GET)
	public String newzhiyouorder(Locale locale, Model model) throws UnsupportedEncodingException{
		
		return "newzhiyouorder";
	}
	
	@RequestMapping(value = "/shiped", method = RequestMethod.GET)
	public String shiped(Locale locale, Model model,
						@ModelAttribute("shiporderid")String shiporderid
						) throws UnsupportedEncodingException{
		Shiporder shiporder = shiporderDAO.findById(shiporderid);
		shiporder.setStatus("已发货");
		shiporderDAO.updateOrder(shiporder);
		return "redirect:viewallorder";
	}
	@RequestMapping(value = "/checked", method = RequestMethod.GET)
	public String chicked(Locale locale, Model model,
						@ModelAttribute("shiporderid")String shiporderid
						) throws UnsupportedEncodingException{
		Shiporder shiporder = shiporderDAO.findById(shiporderid);
		shiporder.setStatus("已验证");
		shiporderDAO.updateOrder(shiporder);
		return "redirect:viewallorder";
	}
	
	@RequestMapping(value = "/addzhiyouorder", method = RequestMethod.GET)
	public String addzhiyouorder(Locale locale, Model model,
						@ModelAttribute("line1")String line1,
						@ModelAttribute("province")String province,
						@ModelAttribute("city")String city,
						@ModelAttribute("zip")String zip,
						@ModelAttribute("phone")String phone,
						@ModelAttribute("memo")String memo,
						@ModelAttribute("idphotoid")String idphotoid,
						String taobaoid,
						String name,
						String shiporderid,
						String paydate,
						String shopname
						) throws UnsupportedEncodingException{
		Shiporder shiporder = shiporderDAO.findById(shiporderid);
		if(shiporder==null){
			Idphoto idphoto = new Idphoto(name);
			Address address = new Address(line1,province,city,zip,phone,memo,idphoto);
			shiporder = new Shiporder(shiporderid, paydate, taobaoid, address, shopname);
			idphotoDAO.addUser(idphoto);
			addressDAO.addAddress(address);
			shiporderDAO.addOrder(shiporder);
			model.addAttribute("order", shiporder);
			return "addorderitem";
		}else{
			model.addAttribute("message", "此订单已存在");
			return "newzhiyouorder";
		}
		
	}
	
	@RequestMapping(value = "/addorderitem", method = RequestMethod.GET)
	public String addorderitem(Locale locale, Model model) throws UnsupportedEncodingException{
		return "addorderitem";
	}
	
	@RequestMapping(value = "/additem", method = RequestMethod.GET)
	public String additem(Locale locale, Model model,
			String productid,
			int quantity,
			String size,
			String shiporderid
			) throws UnsupportedEncodingException{
		
		Shiporder shiporder = shiporderDAO.findById(shiporderid);
		Orderitem orderitem = new Orderitem(productid, quantity, size, shiporder);
		orderitemDAO.addOrderitem(orderitem);
		Shiporder shiporder1 = shiporderDAO.findById(shiporderid);
		model.addAttribute("order", shiporder1);
		return "addorderitem";
	}
	
	@RequestMapping(value = "/zhiyoucheck", method = RequestMethod.GET)
	public String zhiyoucheck(Locale locale, Model model) throws UnsupportedEncodingException{
		return "zhiyoucheck";
	}
	@RequestMapping(value = "/zhiyoucheckaction", method = RequestMethod.GET)
	public String zhiyoucheckaction(Locale locale, 
			String shiporderid,
			String name,
			String phone,
			String username,
			String idnumber,
			Model model) throws UnsupportedEncodingException{
		Shiporder shiporder = shiporderDAO.findById(shiporderid);
		if(shiporder==null){
			model.addAttribute("check", "没有此订单，请检查订单号");
			return "zhiyoucheck";
		}else{
			if(!shiporder.getStatus().equalsIgnoreCase("未上传身份证")){
				model.addAttribute("check", "身份证已上传");
				return "zhiyoucheck";
			}else{
				if(shiporder.getAddress().getPhone().equalsIgnoreCase(phone)&&shiporder.getAddress().getIdphoto().getName().equalsIgnoreCase(name)){
					if(userDAO.findByUserName(username)==null){
						userDAO.addUser(username, username, UUID.randomUUID().toString());
						shiporder.setUser(userDAO.findByUserName(username));
						shiporderDAO.updateOrder(shiporder);
					}
					Idphoto idphoto = idphotoDAO.findByidnumber(idnumber);
					if(idphoto==null){
						model.addAttribute("check", "checked");
						model.addAttribute("idnumber", idnumber);
						model.addAttribute("shiporderid", shiporderid);
						return "zhiyouidphoto";
					}else{
						if(idphoto.getName().equalsIgnoreCase(name)){
							Address address = shiporder.getAddress();
							address.setIdphoto(idphoto);
							addressDAO.updateAddress(address);
							shiporder.setStatus("已上传身份证");
							shiporderDAO.updateOrder(shiporder);
							
							model.addAttribute("check", "身份证已在档案中，无需上传，谢谢");
							return "zhiyoucheck";
						}else{
							model.addAttribute("check", "身份证信息不匹配");
							return "zhiyoucheck";
						}
					}
					
					
				}else{
					model.addAttribute("check", "订单信息不匹配");
					return "zhiyoucheck";
				}
			}
		}
		
	}
	
	@RequestMapping(value = "/zhiyouidphoto", method = RequestMethod.GET)
	public String zhiyouidphoto(Locale locale, Model model,HttpServletRequest req) throws UnsupportedEncodingException{
		if(req.getSession().getAttribute("check")==null){
			return "zhiyoucheck";
		}else{
			if(req.getSession().getAttribute("check").toString().equalsIgnoreCase("checked")){
				
				return "zhiyouidphoto";
			}else{
				return "zhiyoucheck";
			}
		}		
	}
	
	@RequestMapping(value = "/zhiyouuploadid", method = RequestMethod.POST)
	public String zhiyouuploadid(
			@RequestParam("filezm")CommonsMultipartFile filezm,
			@RequestParam("filebm")CommonsMultipartFile filebm,
			@ModelAttribute("shiporderid")String shiporderid,
			@ModelAttribute("idnumber")String idnumber,
			ModelMap map, HttpServletRequest request, Model model
			) throws FileNotFoundException, Exception{
		if (!filezm.isEmpty()&&!filebm.isEmpty()) {
				boolean flag = false;
			   
			   String fileNamezm = filezm.getOriginalFilename();
			   String fileTypezm = fileNamezm.substring(fileNamezm.lastIndexOf("."));
			   String fileNamebm = filezm.getOriginalFilename();
			   String fileTypebm = fileNamebm.substring(fileNamebm.lastIndexOf("."));
			   
			   if(fileTypezm.equalsIgnoreCase(fileTypebm)){
				   String fileType = fileTypebm;
				   System.out.println(fileType); 
				   String path = request.getSession().getServletContext().getRealPath("/")+"resources/upload";
				   System.out.println(path);
				   
				   Shiporder shiporder = shiporderDAO.findById(shiporderid);
				   
				   Idphoto idphoto = shiporder.getAddress().getIdphoto();
				   idphoto.setPath(path);
				   idphoto.setType(fileType);
				   idphoto.setIdnumber(idnumber);
				   File filezm2 = new File(path,idphoto.getIdphotoid() + "ZM"+fileType);
				   File filebm2 = new File(path,idphoto.getIdphotoid() + "BM"+fileType);
				   System.out.println(fileType);
				   idphotoDAO.updateIdphoto(idphoto);
				   shiporder.setStatus("已上传身份证");
				   shiporderDAO.updateOrder(shiporder);
				   
				   
				   try {
					    filezm.getFileItem().write(filezm2); 
					    System.out.println("----------------");
				   } catch (Exception e) {
					    e.printStackTrace();
				   }
				   try {
					    filebm.getFileItem().write(filebm2); 
					    System.out.println("----------------");
				   } catch (Exception e) {
					    e.printStackTrace();
				   }
				   
				
				   model.addAttribute("check", "上传成功，谢谢");
					return "zhiyoucheck";
			   }else{
				   model.addAttribute("check", "文件类型不同");
					return "redirect:zhiyouuploadid";
			   }
			}else{
				model.addAttribute("check", "请选择文件");
				return "redirect:zhiyouuploadid";
			}
	}
	
}
