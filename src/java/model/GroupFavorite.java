/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author buiph
 */
public class GroupFavorite {

    private int favoriteId;
    private Account account;
    private Product product;

    public GroupFavorite() {
    }

    public GroupFavorite(int favoriteId, Account account, Product product) {
        this.favoriteId = favoriteId;
        this.account = account;
        this.product = product;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "GroupFavorite{" + "favoriteId=" + favoriteId + ", account=" + account + ", product=" + product + '}';
    }

}
