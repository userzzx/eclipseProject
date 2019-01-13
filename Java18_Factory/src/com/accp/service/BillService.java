package com.accp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accp.mapper.TbBillMapper;
import com.accp.mapper.TbDetailMapper;
import com.accp.pojo.TbBill;
import com.accp.pojo.TbDetail;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class BillService {
	@Autowired
	private TbBillMapper billMapper;
	@Autowired
	private TbDetailMapper detailMapper;
	
	public List<TbBill> test() {
		return billMapper.queryAll();
	}
	
	
	public List<TbBill> queryByPage(Integer currentPage) {
		PageHelper.startPage(currentPage, 1);
		 return (Page<TbBill>)billMapper.queryAll();
	}
	
	public List<TbBill> queryAllBill() {
		 return billMapper.queryAll();
	}
	
	/**
	 * 根据主表编号查询明细
	 * @param billno
	 * @return
	 */
	public List<TbDetail> queryDetailByBillno(String billno){
		return detailMapper.queryDetailByBillno(billno);
	}
	
	
	public TbBill queryBillByBillno(String billno){
		return billMapper.selectByPrimaryKey(billno);
	}
	
	
	public void deleteBill(String billno) {
		detailMapper.deleteByBillno(billno);
		billMapper.deleteByPrimaryKey(billno);
	}
	
	/**
	 * 保存
	 * @param bill
	 */
	public void saveBill(TbBill bill) {
		//查询是否有该对象
		TbBill temp=billMapper.selectByPrimaryKey(
				bill.getBillno());
		if(temp!=null) {
			//修改
			billMapper.updateByPrimaryKey(bill);
			detailMapper.deleteByBillno(bill.getBillno());
		}else {
			billMapper.insert(bill);
		}
		//添加明细
		List<TbDetail> list=bill.getDetails();
		int i=0;
		for (TbDetail tbDetail : list) {
			tbDetail.setBillno(bill.getBillno());
			tbDetail.setLineid(i+1);
			detailMapper.insert(tbDetail);
			i++;
		}
	}
	
	public String getNo(String billdate) {
		String billno=
				billMapper.queryBillnoByDate(billdate);
		if(billno==null) {
			return billdate.replaceAll("-", "") + "001";
		}else {
			return "SL" + (Long.parseLong(billno.substring(2)) + 1) + "";
		}
		
	}
	
}
