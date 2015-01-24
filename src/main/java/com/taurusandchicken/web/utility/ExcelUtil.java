package com.taurusandchicken.web.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import com.taurusandchicken.web.module.Address;
import com.taurusandchicken.web.module.Idphoto;
import com.taurusandchicken.web.module.Orderitem;
import com.taurusandchicken.web.module.Shiporder;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelUtil {

	public ExcelUtil() {
		// TODO Auto-generated constructor stub
	}

	public static List<Shiporder> addOrderByExcel(String res)
			throws BiffException, IOException {

		List<Shiporder> orders = new ArrayList<Shiporder>();
		Workbook workbook = Workbook.getWorkbook(new File(res));

		Sheet sheet = workbook.getSheet(0);
		int i = 1;
		boolean flag = true;
		while (flag) {

			Shiporder shiporder = new Shiporder();
			Cell shiporderid = sheet.getCell(0, i);
			Set<Orderitem> items = new HashSet<Orderitem>();

			if (!shiporderid.getContents().equalsIgnoreCase("")) {
				Address address = new Address();
				Idphoto idphoto = new Idphoto();
				shiporder.setShiporderid(shiporderid.getContents().toString());
				shiporder.setTaobaoid(sheet.getCell(1, i).getContents());
				shiporder.setPaydate(sheet.getCell(5, i).getContents());
				address.setProvince(sheet.getCell(11, i).getContents());
				address.setCity(sheet.getCell(12, i).getContents());
				address.setLine1(sheet.getCell(13, i).getContents() + sheet.getCell(14, i).getContents());
				address.setPhone(sheet.getCell(16, i).getContents());
				address.setZip(sheet.getCell(18, i).getContents());
				shiporder.setMemo(sheet.getCell(9, i).getContents());
				idphoto.setName(sheet.getCell(15, i).getContents());

				address.setIdphoto(idphoto);
				shiporder.setAddress(address);
				i++;
				
				boolean littleflag = true;
				while (littleflag) {
					System.out.println("========" + i + "============");
					Orderitem orderitem = new Orderitem();
					Cell derial = sheet.getCell(1, i);
					StringTokenizer st = new StringTokenizer(
							derial.getContents());

					boolean flag1 = true;
					while (st.hasMoreTokens()) {
						String lolo = st.nextToken();
						if (lolo.contains(";")) {
							int l = lolo.indexOf(";") + 1;
							orderitem.setSize(lolo.substring(l));
							flag1 = false;

						}
					}
					if (flag1) {
						orderitem.setSize("请填写尺寸");
					}

					StringTokenizer st2 = new StringTokenizer(
							derial.getContents());

					boolean flag2 = true;
					while (st2.hasMoreTokens()) {
						String lolo = st2.nextToken();
						if (lolo.contains("商家") && lolo.length() > 5) {
							int l = 5;
							orderitem.setProductid(lolo.substring(l));
							flag2 = false;

						}
					}
					if (flag2) {
						orderitem.setProductid("请填写商品编号");
					}

					StringTokenizer st3 = new StringTokenizer(
							derial.getContents());

					boolean flag3 = true;
					while (st3.hasMoreTokens()) {
						String lolo = st3.nextToken();
						if (lolo.contains("数量")) {
							int l = 3;
							orderitem.setQuantity(Integer.parseInt(lolo
									.substring(l)));
							flag3 = false;

						}
					}
					if (flag3) {
						orderitem.setQuantity(0);
					}
					
					i++;
					
					items.add(orderitem);
					
					
					
					Cell f2 = sheet.getCell(0, i);
					Cell t4 = sheet.getCell(2, i);
					if (!f2.getContents().equalsIgnoreCase("")||!t4.getContents().equalsIgnoreCase("")) {
						littleflag = false;
					}
					
					
				}
				shiporder.setOrderitems(items);
				
			}

			orders.add(shiporder);

			Cell f1 = sheet.getCell(0, i);
			Cell t3 = sheet.getCell(2, i);
			if (f1.getContents().equalsIgnoreCase("")
					&& !t3.getContents().equalsIgnoreCase("")) {
				flag = false;
			}
			

		}

		workbook.close();
		return orders;
	}

}
