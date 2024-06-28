package dal;

import model.Product;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ProductDBContextTest {

    private static Connection connection;
    private ProductDBContext dbContext;

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

    @Before
    public void setUp() {
        // Khởi tạo ProductDBContext với kết nối đã thiết lập
        dbContext = new ProductDBContext();
        dbContext.setConnection(connection);
    }

    @After
    public void tearDown() throws SQLException {
        // Rollback transaction sau mỗi test case để đảm bảo không ảnh hưởng đến dữ liệu thực
        connection.rollback();
        connection.commit(); // Commit để áp dụng rollback vào cơ sở dữ liệu
    }

    @Test
    public void testInsertValidProduct() throws SQLException {
        System.out.println("insertProduct");

        // Tạo đối tượng sản phẩm để insert
        Product product = new Product();
        product.setName("Product A");
        product.setImageUrl("imageA.png");
        product.setPrice(100000);
        product.setTiltle("Test Insert Product");
        product.setDescription("Test Insert Product Description");
        product.setCategoryId(8); // Thay thế bằng ID thực tế của danh mục sản phẩm
        product.setSell_ID(8); // Thay thế bằng ID thực tế của người bán

        // Thực thi phương thức insertProduct để insert sản phẩm vào cơ sở dữ liệu
        dbContext.inSertProduct(product);

        // Kiểm tra xem sản phẩm đã được thêm vào cơ sở dữ liệu chưa
        assertNotNull("Product ID should not be null after insertion", product.getId());

        // Truy vấn lại sản phẩm từ cơ sở dữ liệu để kiểm tra
        Product insertedProduct = dbContext.getProductById(product.getId());

        // Kiểm tra sản phẩm đã được lưu trong cơ sở dữ liệu và có đúng thông tin
        assertNotNull("Inserted product should not be null", insertedProduct);
        assertEquals("Product name should match", product.getName(), insertedProduct.getName());
        assertEquals("Product image URL should match", product.getImageUrl(), insertedProduct.getImageUrl());
        assertEquals("Product price should match", product.getPrice(), insertedProduct.getPrice(), 0.0);
        assertEquals("Product title should match", product.getTiltle(), insertedProduct.getTiltle());
        assertEquals("Product description should match", product.getDescription(), insertedProduct.getDescription());
        assertEquals("Product category ID should match", product.getCategoryId(), insertedProduct.getCategoryId());
        assertEquals("Product seller ID should match", product.getSell_ID(), insertedProduct.getSell_ID());
    }

    @Test(expected = Exception.class)
    public void testInsertProductNull() throws SQLException {
        System.out.println("insertProductNull");

        // Thực thi phương thức insertProduct với null để insert sản phẩm vào cơ sở dữ liệu
        dbContext.inSertProduct(null);
    }

    @Test(expected = Exception.class)
    public void testInsertProductEmpty() throws SQLException {
        System.out.println("insertProductNull");
        Product product = new Product();
        // Thực thi phương thức insertProduct với null để insert sản phẩm vào cơ sở dữ liệu
        dbContext.inSertProduct(null);
    }

    @Test(expected = SQLException.class)
    public void testInsertProductWithNonExistentCategoryId() throws SQLException {
        System.out.println("insertProductWithNonExistentCategoryId");

        // Tạo đối tượng sản phẩm để insert với categoryId không tồn tại
        Product product = new Product();
        product.setName("Product A");
        product.setImageUrl("imageA.png");
        product.setPrice(100000);
        product.setTiltle("Test Insert Product");
        product.setDescription("Test Insert Product");
        product.setCategoryId(9999); // Thay thế bằng ID thực tế của danh mục sản phẩm
        product.setSell_ID(8); // Thay thế bằng ID thực tế của người bán

        // Thực thi phương thức insertProduct để insert sản phẩm vào cơ sở dữ liệu
        // Kỳ vọng một ngoại lệ SQLException sẽ được ném ra do vi phạm ràng buộc khóa ngoại
        dbContext.inSertProduct(product);
    }

    @Test(expected = SQLException.class)
    public void testInsertDuplicateProduct() throws SQLException {
        System.out.println("insertDuplicateProduct");
        // Tạo đối tượng sản phẩm để insert
        Product product = new Product();
        product.setName("Product A");
        product.setImageUrl("imageA.png");
        product.setPrice(100000);
        product.setTiltle("Test Insert Product");
        product.setDescription("Test Insert Product");
        product.setCategoryId(8); // Thay thế bằng ID thực tế của danh mục sản phẩm
        product.setSell_ID(8); // Thay thế bằng ID thực tế của người bán
        // Thêm sản phẩm vào cơ sở dữ liệu
        dbContext.inSertProduct(product);
        // Thêm sản phẩm với cùng thông tin vào cơ sở dữ liệu lại
        dbContext.inSertProduct(product);
    }

    @Test(expected = SQLException.class)
    public void testInsertProductWithNegativePrice() throws SQLException {
        System.out.println("insertProductWithNegativePrice");

        // Tạo đối tượng sản phẩm để insert
        Product product = new Product();
        product.setName("Product A");
        product.setImageUrl("imageA.png");
        product.setPrice(-1);
        product.setTiltle("Test Insert Product");
        product.setDescription("Test Insert Product");
        product.setCategoryId(8); // Thay thế bằng ID thực tế của danh mục sản phẩm
        product.setSell_ID(8); // Thay thế bằng ID thực tế của người bán
        // Thực thi phương thức insertProduct để insert sản phẩm vào cơ sở dữ liệu
        dbContext.inSertProduct(product);
        // Nếu phương thức không ném ra SQLException, test case sẽ fail
    }

    @Test
    public void testInsertProductWithDecimalPrice() throws SQLException {
        System.out.println("insertProductWithDecimalPrice");
        // Tạo đối tượng sản phẩm để insert
        Product product = new Product();
        product.setName("Product A");
        product.setImageUrl("imageA.png");
        product.setPrice(123.1234);
        product.setTiltle("Test Insert Product");
        product.setDescription("Test Insert Product Description");
        product.setCategoryId(8); // Thay thế bằng ID thực tế của danh mục sản phẩm
        product.setSell_ID(8); // Thay thế bằng ID thực tế của người bán
        // Thực thi phương thức insertProduct để insert sản phẩm vào cơ sở dữ liệu
        dbContext.inSertProduct(product);
        // Kiểm tra xem sản phẩm đã được thêm vào cơ sở dữ liệu chưa
        assertNotNull("Product ID should not be null after insertion", product.getId());
        // Truy vấn lại sản phẩm từ cơ sở dữ liệu để kiểm tra
        Product insertedProduct = dbContext.getProductById(product.getId());
        // Kiểm tra sản phẩm đã được lưu trong cơ sở dữ liệu và có đúng thông tin
        assertNotNull("Inserted product should not be null", insertedProduct);
        assertEquals("Product price should match", product.getPrice(), insertedProduct.getPrice(), 0.0);
    }

    @Test
    public void testInsertProductWithMaxPrice() throws SQLException {
        System.out.println("insertProductWithDecimalPrice");
        // Tạo đối tượng sản phẩm để insert
        Product product = new Product();
        product.setName("Product A");
        product.setImageUrl("imageA.png");
        product.setPrice(922337203685477.5806);
        product.setTiltle("Test Insert Product");
        product.setDescription("Test Insert Product");
        product.setCategoryId(8); // Thay thế bằng ID thực tế của danh mục sản phẩm
        product.setSell_ID(8); // Thay thế bằng ID thực tế của người bán
        // Thực thi phương thức insertProduct để insert sản phẩm vào cơ sở dữ liệu
        dbContext.inSertProduct(product);
        // Kiểm tra xem sản phẩm đã được thêm vào cơ sở dữ liệu chưa
        assertNotNull("Product ID should not be null after insertion", product.getId());
    }

    @Test(expected = SQLException.class)
    public void testInsertProductWithExceedingMaxMoneyValue() throws SQLException {
        Product testProduct = new Product();
        testProduct.setName("Test Product");
        testProduct.setImageUrl("test_image.jpg");
        // Set a price exceeding the maximum allowed for money datatype in SQL Server
        testProduct.setPrice(922337203685477.5808); // Actual maximum is 922337203685477.5807
        testProduct.setTiltle("Test Title");
        testProduct.setDescription("Test Description");
        testProduct.setCategoryId(1); // Replace with your actual category ID
        testProduct.setSell_ID(1); // Replace with your actual seller ID
        ProductDBContext dbContext = new ProductDBContext(); // Replace with your actual DB context
        // Insert the product and expect an SQLException to be thrown
        dbContext.inSertProduct(testProduct);
    }

    @Test
    public void testInsertProductWithZeroPrice() throws SQLException {
        System.out.println("insertProductWithZeroPrice");

        // Create a new product with a price of zero
        Product product = new Product();
        product.setName("Product A");
        product.setImageUrl("imageA.png");
        product.setPrice(0); // Price is set to zero
        product.setTiltle("Test Insert Product");
        product.setDescription("Test Insert Product");
        product.setCategoryId(8); // Replace with actual category ID
        product.setSell_ID(8); // Replace with actual seller ID

        // Insert the product into the database
        dbContext.inSertProduct(product);
        // Assert that the product ID is not null after insertion
        assertNotNull("Product ID should not be null after insertion", product.getId());
        // Optional: Verify the inserted product details from the database
        Product insertedProduct = dbContext.getProductById(product.getId());
        assertNotNull("Inserted product should not be null", insertedProduct);
        assertEquals("Product price should match", product.getPrice(), insertedProduct.getPrice(), 0.0);
        // Add more assertions as needed for other properties
    }

    @Test
    public void testInsertProductWithMoreThanFourDecimalPlaces() throws SQLException {
        // Tạo đối tượng sản phẩm để insert
        Product testProduct = new Product();
        testProduct.setName("Product Decimal Price");
        testProduct.setImageUrl("imageDecimalPrice.png");
        testProduct.setPrice(12345.67891); // Set price to a value with more than four decimal places
        testProduct.setTiltle("Test Insert Product with Decimal Price");
        testProduct.setDescription("Test Insert Product with Decimal Price Description");
        testProduct.setCategoryId(8); // Replace with your actual category ID
        testProduct.setSell_ID(1); // Replace with your actual seller ID
        ProductDBContext dbContext = new ProductDBContext(); // Replace with your actual DB context
        // Thực thi phương thức insertProduct để insert sản phẩm vào cơ sở dữ liệu
        dbContext.inSertProduct(testProduct);
        // Kiểm tra xem sản phẩm đã được thêm vào cơ sở dữ liệu chưa
        // Cách đơn giản nhất là kiểm tra ID của sản phẩm có được gán sau khi insert hay không
        assertNotNull("Product ID should not be null after insertion", testProduct.getId());
        // Kiểm tra lại dữ liệu trong cơ sở dữ liệu để xác nhận giá trị đã được lưu đúng cách
        Product insertedProduct = dbContext.getProductById(testProduct.getId());
        // Giá trị được lưu trữ sẽ được làm tròn thành 12345.6789
        assertEquals("Inserted product price should be rounded to four decimal places",
                12345.6789, insertedProduct.getPrice(), 0.0001);
    }

    @Test
    public void testDeleteValidProduct() throws SQLException {
        System.out.println("deleteProduct");
//         Tạo đối tượng sản phẩm để insert
        Product product = new Product();
        product.setName("Product to Delete");
        product.setImageUrl("image_to_delete.png");
        product.setPrice(100);
        product.setTiltle("Test Delete Product");
        product.setDescription("Test Delete Product");
        product.setCategoryId(8); // Thay thế bằng ID thực tế của danh mục sản phẩm
        product.setSell_ID(8); // Thay thế bằng ID thực tế của người bán
        // Thêm sản phẩm vào cơ sở dữ liệu
        dbContext.inSertProduct(product);
        // Lấy ID của sản phẩm vừa thêm vào
        int productId = product.getId();
        // Xóa sản phẩm từ cơ sở dữ liệu
        dbContext.deleteProduct(productId);
        // Kiểm tra xem sản phẩm đã bị xóa thành công
        Product deletedProduct = dbContext.getProductById(productId);
        assertNull("Deleted product should be null", deletedProduct);
    }

    @Test(expected = SQLException.class)
    public void testDeleteProductNotExist() throws SQLException {
        System.out.println("deleteProductNull");
        int NotExistid = 5000;
        dbContext.deleteProduct(NotExistid);
    }

    @Test(expected = SQLException.class)
    public void testDeleteProductWithNegativeId() throws SQLException {
        System.out.println("deleteProductWithNegativeId");
        // Attempt to delete a product with a negative ID
        int negativeProductId = -1; // Negative ID
        dbContext.deleteProduct(negativeProductId);
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteProductWithNullId() throws SQLException {
        System.out.println("deleteProductWithNullId");
        // Attempt to delete a product with a null ID
        Integer nullProductId = null;
        dbContext.deleteProduct(nullProductId);
    }

    @Test
    public void testGetProductByValidCategoryID() throws SQLException {
        // Arrange: Prepare test data or setup
        int categoryId = 8; // Replace with a valid category ID
        // Example: Insert test data if needed before testing retrieval

        // Act: Call the method under test
        List<Product> products = dbContext.getProductsByCategoryId(categoryId);

        // Assert: Verify the result
        assertNotNull("Products should not be null", products);
        assertFalse("Products list should not be empty", products.isEmpty());

        // Check each product's category ID
        for (Product product : products) {
            assertNotNull("Product should not be null", product);
            assertEquals("Product category ID should match", categoryId, product.getCategoryId());
            // Add more assertions as needed
        }
    }

    @Test
    public void testGetProductByCategoryIDNotExist() throws SQLException {
         System.out.println("GetProductByCategoryIDNotExist");
        int id = 5000;
        // Act: Call the method under test
        List<Product> products = dbContext.getProductsByCategoryId(id);
        // Assert: Verify the result
        assertNotNull("Products should not be null",products);
        assertTrue("Products list should be empty for non-existent category ID",products.isEmpty() );
    }

    @Test(expected = NullPointerException.class)
    public void testGetProductByCategoryIDNull() throws SQLException {
        System.out.println("GetProductByCategoryIDNull");
        Integer nullCategoryId = null;

        List<Product> products = dbContext.getProductsByCategoryId(nullCategoryId);

        assertNotNull("Products list should not be null",products);
        assertTrue("Products list should be empty for null category ID",products.isEmpty());
    }
    @Test
    public void testGetProductWithNegativeCategoryId() throws SQLException {
        System.out.println("GetProductWithNegativeCategoryId");
        Integer nullCategoryId = -1;
        List<Product> products = dbContext.getProductsByCategoryId(nullCategoryId);
        assertNotNull("Products list should not be null",products);
        assertTrue("Products list should be empty for null category ID",products.isEmpty());
    }
    @Test
    public void testUpdateValidProduct() throws SQLException{
        System.out.println("UpdateValidProduct");
        Product productToUpdate = new Product();
        productToUpdate.setId(1); // Replace with an existing product ID
        productToUpdate.setName("Updated Name");
        productToUpdate.setImageUrl("updated_image.jpg");
        productToUpdate.setPrice(99.99);
        productToUpdate.setTiltle("Updated Title");
        productToUpdate.setDescription("Updated Description");
        productToUpdate.setCategoryId(2); // Replace with an existing category ID
        dbContext.updateProduct(productToUpdate);
    }
    
    @Test(expected = NullPointerException.class)
    public void testUpdateProductNull() throws SQLException{
        System.out.println("UpdateProductNull");
        dbContext.updateProduct(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateProductEmpty() throws SQLException{
        System.out.println("UpdateProductEmpty");
        Product product=new Product();
        dbContext.updateProduct(product);
    }
}
