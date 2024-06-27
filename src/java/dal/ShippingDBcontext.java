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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;
import model.Shipping;

/**
 *
 * @author Admin
 */
public class ShippingDBcontext extends DBContext {

    public int createReturnId(Shipping shipping) {
        try {
            String sql = "INSERT INTO [dbo].[Shipping]\n"
                    + "           ([name]\n"
                    + "           ,[phone]\n"
                    + "           ,[address])\n"
                    + "     VALUES\n"
                    + "           (?,?,?)";

            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, shipping.getName());
            stm.setString(2, shipping.getPhone());
            stm.setString(3, shipping.getAddress());
            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(ShippingDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Shipping get(int id) {
        Shipping shipping = new Shipping();
        try {
            String sql = "SELECT  [id]\n"
                    + "      ,[name]\n"
                    + "      ,[phone]\n"
                    + "      ,[address]\n"
                    + "  FROM [Shipping]\n"
                    + "  WHERE [id] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                shipping.setAddress(rs.getString("address"));
                shipping.setId(rs.getInt("id"));
                shipping.setName(rs.getString("name"));
                shipping.setPhone(rs.getString("phone"));
            }
        } catch (Exception ex) {
            System.out.println("Loi o get " + ex.getMessage());
        }
        return shipping;
    }

}
