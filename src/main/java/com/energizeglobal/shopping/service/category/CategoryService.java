package com.energizeglobal.shopping.service.category;

import com.energizeglobal.shopping.model.entity.CategoryEntity;

/**
 * A <i>CategoryService</i>. This interface has responsibility to find category
 * based on id<p>
 * The <tt>CategoryService</tt> interface provides one methods for retrieving the
 * specified category.<p>
 * the  Category service implemented by <tt>CategoryServiceImpl</tt> class.<p>
 *
 * @author Omid Rostami
 */

public interface CategoryService {

    /**
     * Returns the CategoryEntity
     *
     * @param categoryId the Player of the game who created it for the first time
     * @return the Category entity fetched.
     */

    CategoryEntity getCategoryById(Long categoryId);


}
