package com.energizeglobal.shopping.service.category;

import com.energizeglobal.shopping.model.entity.CategoryEntity;

/**
 * A <i>CategoryService</i>. This interface has responsibility to create and
 * control the game and movements.<p>
 * The <tt>GameService</tt> interface provides two methods for creating and fetching the player
 * the player service implemented by <tt>PlayerServiceImp</tt> class.<p>
 *
 * @author Omid Rostami
 */

public interface CategoryService {


    /**
     * Returns the details of player fetched
     *
     * @param categoryId the Player of the game who created it for the first time
     * @return the  Product entity fetched.
     */

    CategoryEntity getCategoryById(Long categoryId);


}
