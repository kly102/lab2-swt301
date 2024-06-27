/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.GroupFavorite;
import model.Order;
import model.Product;

/**
 *
 * @author buiph
 */
public class GroupFavoriteDBContext extends DBContext {

    public int add(int idUser, int idProduct) {
        try {
            String sql = ""
                      + "INSERT INTO [GroupFavorite]\n"
                      + "           ( [productId] ,[userId])\n"
                      + "     VALUES\n"
                      + "           ( ? ,?)";
            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1,  idProduct);
            stm.setInt(2, idUser);
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

    public int delete(int idUser, int idProduct) {
        try {
            String sql = ""
                      + "DELETE FROM [GroupFavorite]\n"
                      + "      WHERE userId = ? AND productId = ?";
            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, idUser);
            stm.setInt(2, idProduct);
            stm.executeUpdate();

           stm.executeUpdate();
            
        } catch (Exception ex) {
            Logger.getLogger(OrderDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<GroupFavorite> getAllByIdUser(int idUser) {
        ArrayList<GroupFavorite> groupFavorites = new ArrayList<>();
        try {
            String sql = ""
                      + "SELECT [favoriteId]\n"
                      + "      ,[productId]\n"
                      + "      ,[userId]\n"
                      + "  FROM  [GroupFavorite]\n"
                      + "  WHERE [userId] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, idUser);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                GroupFavorite groupFavorite = new GroupFavorite();

                groupFavorite.setAccount(new AcountDBContext().getAccountById(rs.getInt("userId")));
                groupFavorite.setFavoriteId(rs.getInt("favoriteId"));
                groupFavorite.setProduct(new ProductDBContext().getProductById(rs.getInt("productId")));

                groupFavorites.add(groupFavorite);
            }
        } catch (Exception ex) {
            System.out.println("Loi o getAllByIdUser " + ex.getMessage());
        }
        return groupFavorites;
    }
    
    public static void main(String[] args) {
        for (GroupFavorite groupFavorite : new GroupFavoriteDBContext().getAllByIdUser(6)) {
            System.out.println(groupFavorite.toString());
        }
    }
}
