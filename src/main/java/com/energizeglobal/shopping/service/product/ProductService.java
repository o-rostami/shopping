package com.energizeglobal.shopping.service.product;

import com.energizeglobal.shopping.model.dto.PagingRequest;
import com.energizeglobal.shopping.model.dto.PagingResponse;
import com.energizeglobal.shopping.model.entity.ProductEntity;

import java.util.List;

/**
 * A <i>ProductService</i>. This interface have responsibilities to do CRUD operation
 * on the product and also provide some other methods <p>
 * The <tt>ProductService</tt> interface provides multiple methods for different operations
 * the Product service implemented by <tt>ProductServiceImpl</tt> class.<p>
 *
 * @author Omid Rostami
 */

public interface ProductService {

    /**
     * Returns the created Product's id.
     *
     * @param productEntity contains detail of Product whom to be created
     * @return the Product's id just created.
     */

    Long createProduct(ProductEntity productEntity);

    /**
     * Returns the updated Product.
     *
     * @param productEntity contains detail of product whom to be updated
     * @return the Product  just updated.
     */

    ProductEntity updateProduct(ProductEntity productEntity);


    /**
     * Returns the details of Product fetched
     *
     * @param productId the id of the product who created it for the first time
     * @return the Product entity fetched.
     */

    ProductEntity getProductById(Long productId);

    /**
     * Returns the all products
     *
     * @return the Product  list .
     */
    List<ProductEntity> getAllProducts();

    /**
     * Returns the details of product fetched
     *
     * @param pagingRequest contains the search criteria and filters
     * @return the PagingResponse fetched.
     */

    PagingResponse<ProductEntity> searchProduct(PagingRequest pagingRequest);


    /**
     * Returns the details of product fetched based on the categoryId and productId
     *
     * @param categoryId of the product
     * @param productId
     * @return the Product fetched.
     */

    ProductEntity findByIdAndCategoryId(Long categoryId, Long productId);


    /**
     * Detelet the  product based on the categoryId and productId
     *
     * @param categoryId of the product
     * @param productId
     */

    void deleteByIdCategoryId(Long categoryId, Long productId);

}
