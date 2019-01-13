package com.accp.pojo;

public class TbDetail extends TbDetailKey {
    private String goodsname;

    private Double goodsnum;

    private Double goodsprice;

    private Double goodsmoneyamt;

    private Integer ispresent;

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public Double getGoodsnum() {
        return goodsnum;
    }

    public void setGoodsnum(Double goodsnum) {
        this.goodsnum = goodsnum;
    }

    public Double getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(Double goodsprice) {
        this.goodsprice = goodsprice;
    }

    public Double getGoodsmoneyamt() {
        return goodsmoneyamt;
    }

    public void setGoodsmoneyamt(Double goodsmoneyamt) {
        this.goodsmoneyamt = goodsmoneyamt;
    }

    public Integer getIspresent() {
        return ispresent;
    }

    public void setIspresent(Integer ispresent) {
        this.ispresent = ispresent;
    }
}