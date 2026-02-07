package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class ProductServiceTests {

    private ProductService buildService(ProductRepository repository) {
        ProductService service = new ProductService();
        try {
            Field field = ProductService.class.getDeclaredField("productRepository");
            field.setAccessible(true);
            field.set(service, repository);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            throw new IllegalStateException("Failed to set repository", ex);
        }
        return service;
    }

    @Test
    void getAllProductsReturnsRepositoryResults() {
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = buildService(productRepository);
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(2, result.size());
        verify(productRepository).findAll();
    }

    @Test
    void getProductByIdReturnsOptional() {
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = buildService(productRepository);
        Product product = new Product();
        product.setId(5L);
        when(productRepository.findById(5L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(5L);

        assertEquals(5L, result.orElseThrow().getId());
        verify(productRepository).findById(5L);
    }

    @Test
    void createProductPersistsEntity() {
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = buildService(productRepository);
        Product product = new Product();
        product.setProductName("Keyboard");
        when(productRepository.save(product)).thenReturn(product);

        Product created = productService.createProduct(product);

        assertEquals("Keyboard", created.getProductName());
        verify(productRepository).save(product);
    }

    @Test
    void updateProductUpdatesFields() {
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = buildService(productRepository);
        Product existing = new Product();
        existing.setId(1L);
        existing.setProductName("Old");
        existing.setCostPrice(10.0);
        existing.setSellingPrice(15.0);
        existing.setCategory("A");
        existing.setDescription("Old description");

        Product updates = new Product();
        updates.setProductName("New");
        updates.setCostPrice(12.0);
        updates.setSellingPrice(20.0);
        updates.setCategory("B");
        updates.setDescription("New description");

        when(productRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product updated = productService.updateProduct(1L, updates);

        assertEquals("New", updated.getProductName());
        assertEquals(12.0, updated.getCostPrice());
        assertEquals(20.0, updated.getSellingPrice());
        assertEquals("B", updated.getCategory());
        assertEquals("New description", updated.getDescription());
        verify(productRepository).findById(1L);
        verify(productRepository).save(existing);
    }

    @Test
    void updateProductThrowsWhenMissing() {
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = buildService(productRepository);
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> productService.updateProduct(99L, new Product()));

        assertNotNull(ex.getMessage());
        verify(productRepository).findById(99L);
    }

    @Test
    void deleteProductRemovesEntity() {
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = buildService(productRepository);
        Product product = new Product();
        product.setId(7L);
        when(productRepository.findById(7L)).thenReturn(Optional.of(product));

        productService.deleteProduct(7L);

        verify(productRepository).findById(7L);
        verify(productRepository).delete(product);
    }

    @Test
    void deleteProductThrowsWhenMissing() {
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = buildService(productRepository);
        when(productRepository.findById(101L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> productService.deleteProduct(101L));

        assertNotNull(ex.getMessage());
        verify(productRepository).findById(101L);
    }

    @Test
    void searchProductsDelegatesToRepository() {
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = buildService(productRepository);
        List<Product> results = Arrays.asList(new Product());
        when(productRepository.findByProductNameContainingIgnoreCase("lap")).thenReturn(results);

        List<Product> found = productService.searchProducts("lap");

        assertEquals(1, found.size());
        verify(productRepository).findByProductNameContainingIgnoreCase("lap");
    }

    @Test
    void getProductsByCategoryDelegatesToRepository() {
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = buildService(productRepository);
        List<Product> results = Arrays.asList(new Product(), new Product());
        when(productRepository.findByCategory("Electronics")).thenReturn(results);

        List<Product> found = productService.getProductsByCategory("Electronics");

        assertEquals(2, found.size());
        verify(productRepository).findByCategory(eq("Electronics"));
    }
}
