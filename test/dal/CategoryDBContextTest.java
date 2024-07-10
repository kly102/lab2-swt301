package dal;

import java.util.List;
import model.Category;
import org.junit.Test;
import static org.junit.Assert.*;

public class CategoryDBContextTest {

    public CategoryDBContextTest() {
    }

    @Test
    public void testGetAllCategories() {
        CategoryDBContext instance = new CategoryDBContext();
        List<Category> result = instance.getAllCategories();
        assertNotNull(result);
        assertTrue(result.size() > 0); // Assuming there are some categories in the database
    }

    @Test
    public void testGetAllCategoriesByPage() {
        CategoryDBContext instance = new CategoryDBContext();
        int page = 1;
        int PAGE_SIZE = 10;
        List<Category> result = instance.getAllCategoriesByPage(page, PAGE_SIZE);
        assertNotNull(result);
        assertEquals(PAGE_SIZE, result.size()); // Assuming there are enough categories to fill the page
    }

    @Test
    public void testInsertCategory() {
        CategoryDBContext instance = new CategoryDBContext();
        String name = "Test Category";
        instance.insertCategory(name);
        // Verify that the category has been inserted
        List<Category> categories = instance.getAllCategories();
        boolean found = false;
        for (Category c : categories) {
            if (c.getCname().equals(name)) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testGetCategoryById() {
        CategoryDBContext instance = new CategoryDBContext();
        int id = 1; // Assuming there is a category with ID 1
        Category result = instance.getCategoryById(id);
        assertNotNull(result);
        assertEquals(id, result.getCid());
    }

    @Test
    public void testUpdateCategory() {
        CategoryDBContext instance = new CategoryDBContext();
        Category category = instance.getCategoryById(1); // Assuming there is a category with ID 1
        assertNotNull(category);
        String newName = "Updated Category";
        category.setCname(newName);
        instance.updateCategory(category);
        Category updatedCategory = instance.getCategoryById(1);
        assertEquals(newName, updatedCategory.getCname());
    }

    @Test
    public void testDeleteCategory() {
        CategoryDBContext instance = new CategoryDBContext();
        String name = "Category to Delete";
        instance.insertCategory(name);
        List<Category> categories = instance.getAllCategories();
        int idToDelete = -1;
        for (Category c : categories) {
            if (c.getCname().equals(name)) {
                idToDelete = c.getCid();
                break;
            }
        }
        assertTrue(idToDelete != -1);
        instance.deleteCategory(idToDelete);
        Category result = instance.getCategoryById(idToDelete);
        assertNull(result);
    }
}
