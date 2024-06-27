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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Product;

public class ProductDBContext extends DBContext {

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from product ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImageUrl(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTiltle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCategoryId(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

//    public List<Product> getAllProductsSortAsc() {
//        List<Product> list = new ArrayList<>();
//        try {
//            String sql = "SELECT * FROM Product ORDER BY price DESC";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                Product product = new Product();
//                product.setId(rs.getInt(1));
//                product.setName(rs.getString(2));
//                product.setImageUrl(rs.getString(3));
//                product.setPrice(rs.getDouble(4));
//                product.setTiltle(rs.getString(5));
//                product.setDescription(rs.getString(6));
//                product.setCategoryId(rs.getInt(7));
//                product.setSell_ID(rs.getInt(8));
//                list.add(product);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
//    public static void main(String[] args) {
//        ProductDBContext a = new ProductDBContext();
//        List<Product> list = a.getAllProducts();
//        for (Product product : list) {
//            System.out.println(product.getName());
//        }
//    }
    public List<Product> getAllProductsF(int idUser) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT p.[id]\n"
                    + "      ,p.[name]\n"
                    + "      ,p.[image]\n"
                    + "      ,p.[price]\n"
                    + "      ,p.[title]\n"
                    + "      ,p.[description]\n"
                    + "      ,p.[cateID]\n"
                    + "      ,p.[sell_ID]\n"
                    + "  FROM  [product] p JOIN GroupFavorite g ON p.id = g.productId\n"
                    + "  WHERE g.userId = 6";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImageUrl(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTiltle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCategoryId(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

//    public int getTotalQuantity(int productId) {
//        List<Product> list = new ArrayList<>();
//        try {
//            String sql = "SELECT SUM(quantity) AS total_quantity FROM OrderDetail WHERE product_id = ?";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            
//            ResultSet rs = stm.executeQuery();
//            while (true) {
//                Product product = new Product();
//                product.setId(rs.getInt(1));
//                product.setName(rs.getString(2));
//                product.setImageUrl(rs.getString(3));
//                product.setPrice(rs.getDouble(4));
//                product.setTiltle(rs.getString(5));
//                product.setDescription(rs.getString(6));
//                product.setCategoryId(rs.getInt(7));
//                product.setSell_ID(rs.getInt(8));
//                list.add(product);
//            }
//
//        } catch (SQLException e) {
//            
//        }
//        return list;
//    }
    
    
    // count
    public int  getTotalQuantity() {
        int totalQuantity = 0;
        try {

            String sql = "SELECT COUNT(*) as total FROM product ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
             if (rs.next()) {
            totalQuantity = rs.getInt("total");
        }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalQuantity;
    }

    
    public List<Product> getProductsByCategoryId(int categoryId) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from Product where Product.cateID = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, categoryId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImageUrl(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTiltle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCategoryId(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Product> getProductsWithPagging(int page, int PAGE_SIZE) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select *  from Product order by id\n"
                    + "offset (?-1)*? row fetch next ? rows only";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, PAGE_SIZE);
            stm.setInt(3, PAGE_SIZE);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImageUrl(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTiltle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCategoryId(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // sort product 
    public List<Product> getProductsAndSort(int page, int pageSize) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Product ORDER BY price ASC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (page - 1) * pageSize);
            stm.setInt(2, pageSize);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImageUrl(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTiltle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCategoryId(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int getTotalProducts() {
        try {
            String sql = "select count(id)  from Product ";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

//    public int getTotalProductsSort() {
//        try {
//            String sql = "SELECT * FROM Product ORDER BY price DESC";
//
//            PreparedStatement stm = connection.prepareStatement(sql);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                return rs.getInt(1);
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return 0;
//    }
    // get price asc
//        public int getTotalProductsByPrice() {
//        try {
//        String sql = "SELECT COUNT(id) FROM Product ORDER BY price DESC";
//        
//            PreparedStatement stm = connection.prepareStatement(sql);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                return rs.getInt(1);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return 0;
//    }
    // search by name (kw)
    public List<Product> search(String keyword) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select *  from Product where name like ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImageUrl(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTiltle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCategoryId(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);

            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Product getProductById(int productId) {
        try {
            String sql = "select *  from Product where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImageUrl(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTiltle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCategoryId(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                return product;
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Product> getProductsByPage(int page, int PAGE_SIZE) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from Product "
                    + "order by id\n"
                    + "offset (?-1)*? row fetch next ? rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, PAGE_SIZE);
            stm.setInt(3, PAGE_SIZE);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImageUrl(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTiltle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCategoryId(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void inSertProduct(Product product) {
        try {
            String sql = "INSERT INTO [product]\n"
                    + "           ([name]\n"
                    + "           ,[image]\n"
                    + "           ,[price]\n"
                    + "           ,[title]\n"
                    + "           ,[description]\n"
                    + "           ,[cateID]\n"
                    + "           ,[sell_ID])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, product.getName());
            stm.setString(2, product.getImageUrl());
            stm.setDouble(3, product.getPrice());
            stm.setString(4, product.getTiltle());
            stm.setString(5, product.getDescription());
            stm.setInt(6, product.getCategoryId());
            stm.setInt(7, product.getSell_ID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteProduct(int id) {
        try {
            String sql = "DELETE FROM [Product]\n"
                    + "WHERE id = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateProduct(Product p) {
        try {
            String sql = "UPDATE [product]\n"
                    + "   SET [name] = ?\n"
                    + "      ,[image] = ?\n"
                    + "      ,[price] = ?\n"
                    + "      ,[title] = ?\n"
                    + "      ,[description] = ?\n"
                    + "      ,[cateID] = ?\n"
                    + " WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, p.getName());
            stm.setString(2, p.getImageUrl());
            stm.setDouble(3, p.getPrice());
            stm.setString(4, p.getTiltle());
            stm.setString(5, p.getDescription());
            stm.setInt(6, p.getCategoryId());
            stm.setInt(7, p.getId());
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Product> getAllProductsLast() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT TOP 4 * FROM product ORDER BY ID DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImageUrl(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTiltle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCategoryId(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

//    public static void main(String[] args) {
//        ProductDBContext a = new ProductDBContext();
//        List<Product> list = a.getAllProducts();
//        for (Product product : list) {
//            System.out.println(product.getName());
//        }
//    }
}
