package dal;

import context.DBContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.Product;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductDBContextTest {

    private static Connection connection;

    @BeforeClass
    public static void setUpClass() throws ClassNotFoundException, SQLException {
        // Thiết lập kết nối đến cơ sở dữ liệu (trước khi các test case chạy)
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=AzanDB";
        String user = "sa";
        String password = "123";
        connection = DriverManager.getConnection(url, user, password);

        // Bắt đầu transaction để đảm bảo các thay đổi không ảnh hưởng đến dữ liệu thực
        connection.setAutoCommit(false);
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        // Rollback transaction sau khi các test case đã chạy xong để không lưu thay đổi vào cơ sở dữ liệu thực
        if (connection != null) {
            connection.rollback();
            connection.close();
        }
    }

    @Test
    public void testInsertProduct() throws SQLException {
        System.out.println("insertProduct");

        // Tạo đối tượng sản phẩm để insert
        Product product = new Product();
        product.setName("Test Product");
        product.setImageUrl("test_image.png");
        product.setPrice(99.99);
        product.setTiltle("Test Product Title");
        product.setDescription("This is a test product.");
        product.setCategoryId(1); // Thay thế bằng ID thực tế của danh mục sản phẩm
        product.setSell_ID(8); // Thay thế bằng ID thực tế của người bán

        // Khởi tạo ProductDBContext với kết nối đã thiết lập
        ProductDBContext instance = new ProductDBContext();

        // Thực thi phương thức inSertProduct để insert sản phẩm vào cơ sở dữ liệu
        instance.inSertProduct(product);

        // Kiểm tra xem sản phẩm đã được thêm vào cơ sở dữ liệu chưa
        assertNotNull("Product ID should not be null after insertion", product.getId());
    }

    @Test
    public void testGetAllProducts() {
        System.out.println("getAllProducts");

        // Khởi tạo ProductDBContext với kết nối đã thiết lập
        ProductDBContext instance = new ProductDBContext();

        // Lấy danh sách tất cả sản phẩm từ cơ sở dữ liệu
        List<Product> result = instance.getAllProducts();

        // Kiểm tra xem danh sách không null và có phần tử không
        assertNotNull("List should not be null", result);
        assertFalse("List should not be empty", result.isEmpty());
    }

    @Test
    public void testUpdateProduct() throws SQLException {
        System.out.println("updateProduct");

        // Khởi tạo ProductDBContext với kết nối đã thiết lập
        ProductDBContext instance = new ProductDBContext();

        // Lấy một sản phẩm từ cơ sở dữ liệu để cập nhật (giả sử sản phẩm đã tồn tại)
        Product existingProduct = instance.getAllProducts().get(0);
        assertNotNull("Existing product should not be null", existingProduct);

        // Cập nhật thông tin sản phẩm
        existingProduct.setName("Updated Product Name");
        existingProduct.setPrice(149.99);

        // Thực thi cập nhật thông tin sản phẩm
        instance.updateProduct(existingProduct);

        // Kiểm tra xem sản phẩm đã được cập nhật thành công
        Product updatedProduct = instance.getProductById(existingProduct.getId());
        assertEquals("Updated Product Name", updatedProduct.getName());
        assertEquals(149.99, updatedProduct.getPrice(), 0.01); // Floating point comparison with delta
    }

    @Test
    public void testDeleteProduct() throws SQLException {
        System.out.println("deleteProduct");

        // Khởi tạo ProductDBContext với kết nối đã thiết lập
        ProductDBContext instance = new ProductDBContext();

        // Tạo một sản phẩm mới để insert và sau đó delete
        Product product = new Product();
        product.setName("Product to delete");
        product.setImageUrl("delete_image.png");
        product.setPrice(49.99);
        product.setTiltle("Delete Product Title");
        product.setDescription("This product will be deleted.");
        product.setCategoryId(2); // Thay thế bằng ID thực tế của danh mục sản phẩm
        product.setSell_ID(10); // Thay thế bằng ID thực tế của người bán

        // Insert sản phẩm để có dữ liệu để delete
        instance.inSertProduct(product);

        // Lấy ID của sản phẩm vừa insert để delete
        int productId = product.getId();

        // Thực thi phương thức deleteProduct
        instance.deleteProduct(productId);

        // Kiểm tra xem sản phẩm có còn tồn tại trong cơ sở dữ liệu sau khi delete không
        Product deletedProduct = instance.getProductById(productId);
        assertNull("Product should be null after deletion", deletedProduct);
    }
}
