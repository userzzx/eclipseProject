package com.accp.mapper;

import java.util.List;

import com.accp.pojo.TbDetail;
import com.accp.pojo.TbDetailKey;

public interface TbDetailMapper {
    int deleteByPrimaryKey(TbDetailKey key);

    int insert(TbDetail record);

    int insertSelective(TbDetail record);

    TbDetail selectByPrimaryKey(TbDetailKey key);

    int updateByPrimaryKeySelective(TbDetail record);

    int updateByPrimaryKey(TbDetail record);
    
    List<TbDetail> queryDetailByBillno(String billno);
    
    int deleteByBillno(String billno);
    
}