/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Order;
import model.Product;

/**
 *
 * @author Admin
 */
public class OrderDBcontext extends DBContext {
    
    public int createReturnId(Order order) {
        try {
            String sql = "INSERT INTO [dbo].[Orders]\n"
                      + "           ([account_id]\n"
                      + "           ,[totalPrice]\n"
                      + "           ,[note]\n"
                      + "           ,[shipping_id]"
                      + "             , status"
                      + ")\n"
                      + "     VALUES\n"
                      + "           (?,?,?,?,0)";
            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, order.getAccount().getUid());
            stm.setDouble(2, order.getTotalPrice());
            stm.setString(3, order.getNote());
            stm.setInt(4, order.getShipping().getId());
            stm.executeUpdate();
            
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(OrderDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public ArrayList<Order> getAllOrderByIDAccount(int id , String sort) {
        ArrayList<Order> Orders = new ArrayList<>();
        try {
            String sql = "SELECT  [id]\n"
                      + "      ,[account_id]\n"
                      + "      ,[totalPrice]\n"
                      + "      ,[note]\n"
                      + "      ,[create_date]\n"
                      + "      ,[shipping_id]"
                      + "     , status\n"
                      + "  FROM [Orders]  "
                      + "   WHERE account_id = ? "
                      + "  ORDER BY [id] " + sort;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setAccount(new AcountDBContext().getAccountById(rs.getInt("account_id")));
                order.setTotalPrice(rs.getDouble("totalPrice"));
                order.setNote(rs.getString("note"));
                order.setCreatedDate(rs.getString("create_date"));
                order.setShipping(new ShippingDBcontext().get(rs.getInt("id")));
                order.setStatus(rs.getBoolean("status"));
                
                Orders.add(order);
            }
        } catch (Exception ex) {
            System.out.println("Loi o getAllOrder " + ex.getMessage());
        }
        return Orders;
    }
    
    public ArrayList<Order> getAllOrder() {
        ArrayList<Order> Orders = new ArrayList<>();
        try {
            String sql = "SELECT  [id]\n"
                      + "      ,[account_id]\n"
                      + "      ,[totalPrice]\n"
                      + "      ,[note]\n"
                      + "      ,[create_date]\n"
                      + "      ,[shipping_id]"
                      + "     , status\n"
                      + "  FROM [Orders] "
                      + "  ORDER BY [status] ASC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setAccount(new AcountDBContext().getAccountById(rs.getInt("account_id")));
                order.setTotalPrice(rs.getDouble("totalPrice"));
                order.setNote(rs.getString("note"));
                order.setCreatedDate(rs.getString("create_date"));
                order.setShipping(new ShippingDBcontext().get(rs.getInt("id")));
                order.setStatus(rs.getBoolean("status"));
                
                Orders.add(order);
            }
        } catch (Exception ex) {
            System.out.println("Loi o getAllOrder " + ex.getMessage());
        }
        return Orders;
    }
     public ArrayList<Order> getAllOrderByStatus(int status) {
        ArrayList<Order> Orders = new ArrayList<>();
        try {
            String sql = "SELECT  [id]\n"
                      + "      ,[account_id]\n"
                      + "      ,[totalPrice]\n"
                      + "      ,[note]\n"
                      + "      ,[create_date]\n"
                      + "      ,[shipping_id]"
                      + "     , status\n"
                      + "  FROM [Orders] "
                    + "where status = ?"
                      + "  ORDER BY [status] ASC";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, status);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setAccount(new AcountDBContext().getAccountById(rs.getInt("account_id")));
                order.setTotalPrice(rs.getDouble("totalPrice"));
                order.setNote(rs.getString("note"));
                order.setCreatedDate(rs.getString("create_date"));
                order.setShipping(new ShippingDBcontext().get(rs.getInt("id")));
                order.setStatus(rs.getBoolean("status"));
                
                Orders.add(order);
            }
        } catch (Exception ex) {
            System.out.println("Loi o getAllOrder " + ex.getMessage());
        }
        return Orders;
    }
    public ArrayList<Order> getAllOrderByStatus(String status) {
        ArrayList<Order> Orders = new ArrayList<>();
        try {
            String sql = "SELECT  [id]\n"
                      + "      ,[account_id]\n"
                      + "      ,[totalPrice]\n"
                      + "      ,[note]\n"
                      + "      ,[create_date]\n"
                      + "      ,[shipping_id]"
                      + "     , status\n"
                      + "  FROM [Orders] "
                    + "    where tatus = ? "
                      + "  ORDER BY [status] ASC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            stm.setString(1, status); 
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setAccount(new AcountDBContext().getAccountById(rs.getInt("account_id")));
                order.setTotalPrice(rs.getDouble("totalPrice"));
                order.setNote(rs.getString("note"));
                order.setCreatedDate(rs.getString("create_date"));
                order.setShipping(new ShippingDBcontext().get(rs.getInt("id")));
                order.setStatus(rs.getBoolean("status"));
                
                Orders.add(order);
            }
        } catch (Exception ex) {
            System.out.println("Loi o getAllOrder " + ex.getMessage());
        }
        return Orders;
    }
    public void updateActive(int id, boolean ac) {
        
        try {
            String sql = "UPDATE [Orders]\n"
                      + "   SET \n"
                      + "	[status] = ? \n"
                      + " WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, ac);
            stm.setInt(2, id);
            stm.executeUpdate();
            
        } catch (Exception ex) {
            System.out.println("Loi o updateActive " + ex.getMessage());
        }
        
    }
    
}
