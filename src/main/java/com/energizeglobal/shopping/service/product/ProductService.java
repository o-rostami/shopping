package com.energizeglobal.shopping.service.product;

import com.energizeglobal.shopping.model.dto.PagingRequest;
import com.energizeglobal.shopping.model.dto.PagingResponse;
import com.energizeglobal.shopping.model.entity.ProductEntity;

import java.util.List;

/**
 * A <i>ProductService</i>. This interface has responsibility to create and
 * control the game and movements.<p>
 * The <tt>GameService</tt> interface provides two methods for creating and fetching the player
 * the player service implemented by <tt>PlayerServiceImp</tt> class.<p>
 *
 * @author Omid Rostami
 */

public interface ProductService {

    /**
     * Returns the created player's id.
     *
     * @param productEntity contains detail of player whom to be created
     * @return the player's id just created.
     */

    Long createProduct(ProductEntity productEntity);

    /**
     * Returns the created player's id.
     *
     * @param productEntity contains detail of player whom to be created
     * @return the player's id just created.
     */

    ProductEntity updateProduct(ProductEntity productEntity);


    /**
     * Returns the details of player fetched
     *
     * @param productId the Player of the game who created it for the first time
     * @return the  Product entity fetched.
     */


    ProductEntity getProductById(Long productId);

    /**
     * Returns the details of player fetched
     *
     * @return the  Product entity fetched.
     */
    List<ProductEntity> getAllProducts();

    /**
     * Returns the details of player fetched
     *
     * @param productId the Player of the game who created it for the first time
     */

    void deleteProductById(Long productId);

    /**
     * Returns the details of player fetched
     *
     * @param pagingRequest the Player of the game who created it for the first time
     * @return the PagingResponse fetched.
     */


    PagingResponse<ProductEntity> searchProduct(PagingRequest pagingRequest);

    ProductEntity rateProduct(ProductEntity dtoToEntity);
}
