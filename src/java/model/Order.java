/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Order {

    private int id;
    private Account account;
    private double totalPrice;
    private String note;
    private String createdDate;
    private Shipping shipping;
    private boolean status;

    public Order() {
    }

    public Order(Account account, double totalPrice, String note, Shipping shipping) {
        this.account = account;
        this.totalPrice = totalPrice;
        this.note = note;
        this.shipping = shipping;
    }

    public Order(Account account, double totalPrice, String note, String createdDate, Shipping shippingId) {
        this.account = account;
        this.totalPrice = totalPrice;
        this.note = note;
        this.createdDate = createdDate;
        this.shipping = shippingId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

}
