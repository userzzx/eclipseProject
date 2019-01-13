package com.accp.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.pojo.TbBill;
import com.accp.pojo.TbDetail;
import com.accp.service.BillService;
import com.github.pagehelper.Page;

@Controller
public class BillAction {
	@Autowired
	private BillService billService;
	
	@ResponseBody
	@RequestMapping("/test")
	public List<TbBill> test(Model model) {
		List<TbBill> list=billService.test();
		for (TbBill tbBill : list) {
			System.out.println(tbBill.getBillno());
		}
		model.addAttribute("list", list);
//		return "/show";'
		return list;
	}
	
	//返回页面
	@RequestMapping("/queryShow")
	public String queryShow(Model model) {
		List<TbBill> list=billService.queryAllBill();
		
		model.addAttribute("list", list);
		return "/show";
	}
	
	@RequestMapping("/queryByPage")
	@ResponseBody
	public TbBill queryByPage(Integer currentPage) {
		if(currentPage==null)currentPage=1;
		//分页集合
		Page<TbBill> list=(Page<TbBill>) 
				billService.queryByPage(currentPage);
		//获得第一个对象
		if(list!=null && list.size()>0) {
			TbBill bill=list.get(0);
			bill.setPreNum(currentPage<=1?1:currentPage-1);
			
			bill.setNextNum(currentPage>=list.getPages()?
						list.getPages():currentPage+1);
			return bill;
		}
		return null;
	}
	
	
	@RequestMapping("/queryDetailByBillno")
	@ResponseBody
	public List<TbDetail> queryDetailByBillno(String billno) {
		return billService.queryDetailByBillno(billno);
	}
	
	
	@RequestMapping("/deleteByBillno")
	@ResponseBody
	public void deleteByBillno(String billno) {
		billService.deleteBill(billno);
	}
	
	@RequestMapping("/saveBill")
	@ResponseBody
	public Integer saveBill(TbBill bill) {
		try {
			billService.saveBill(bill);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	
	
	@RequestMapping("/queryAllBill")
	@ResponseBody
	public List<TbBill> queryAllBill() {
		return billService.queryAllBill();
	}
	
	
	@RequestMapping("/queryBillByBillno")
	@ResponseBody
	public TbBill queryBillByBillno(String billno) {
		return billService.queryBillByBillno(billno);
	}
	
	
	@RequestMapping("/getDate")
	@ResponseBody
	public String getDate() {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		
		
		return format.format(new Date());
	}
	
	
	@RequestMapping("/getNo")
	@ResponseBody
	public String getNo(String billdate) {
		return billService.getNo(billdate);
	}
}
