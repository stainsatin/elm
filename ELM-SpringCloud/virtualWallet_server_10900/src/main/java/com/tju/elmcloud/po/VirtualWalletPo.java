package com.tju.elmcloud.po;

public class VirtualWalletPo {
	private String userId;
    private Integer walletId;
    private double balance;

    public VirtualWalletPo(){
        this.balance=0.00;
    }
    public VirtualWalletPo(Integer walletId,double balance){
        this.balance=balance;
        this.walletId=walletId;
    }
    public Integer getWalletId() {
        return walletId;
    }
    public double getBalance() {
        return balance;
    }
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
