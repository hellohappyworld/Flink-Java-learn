package com.scallion.bean;

/**
 * created by gaowj.
 * created on 2021-05-13.
 * function: 订单日志信息类
 * origin ->
 */
public class OrderDoneLogRecord {
    private int merchandiseId; //商品ID
    private double price; //商品单价
    private double couponMoney; //商品优惠券
    private int rebateAmount; //返利数量

    public OrderDoneLogRecord() {
    }

    public OrderDoneLogRecord(int merchandiseId, double price, double couponMoney, int rebateAmount) {
        this.merchandiseId = merchandiseId;
        this.price = price;
        this.couponMoney = couponMoney;
        this.rebateAmount = rebateAmount;
    }

    public int getMerchandiseId() {
        System.out.println("order:"+merchandiseId);
        return merchandiseId;
    }

    public void setMerchandiseId(int merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(double couponMoney) {
        this.couponMoney = couponMoney;
    }

    public int getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(int rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    @Override
    public String toString() {
        return "OrderDoneLogRecord{" +
                "merchandiseId='" + merchandiseId + '\'' +
                ", price=" + price +
                ", couponMoney=" + couponMoney +
                ", rebateAmount=" + rebateAmount +
                '}';
    }
}
