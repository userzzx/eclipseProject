package com.accp.mapper;

import java.util.List;

import com.accp.pojo.TbBill;

public interface TbBillMapper {
    int deleteByPrimaryKey(String billno);

    int insert(TbBill record);

    int insertSelective(TbBill record);

    TbBill selectByPrimaryKey(String billno);

    int updateByPrimaryKeySelective(TbBill record);

    int updateByPrimaryKey(TbBill record);
    
    List<TbBill> queryAll();
    
    
    String queryBillnoByDate(String billdate);
}