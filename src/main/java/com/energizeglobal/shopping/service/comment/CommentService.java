package com.energizeglobal.shopping.service.comment;

import com.energizeglobal.shopping.model.entity.CommentEntity;

/**
 * A <i>CommentService</i>. This interface has responsibility to create
 * comment for specific product .<p>
 * The <tt>CommentService</tt> interface provides one methods for creating
 * the  Comment service implemented by <tt>CommentServiceImpl</tt> class.<p>
 *
 * @author Omid Rostami
 */

public interface CommentService {


    /**
     * Returns Long
     *
     * @param entity the Player of the game who created it for the first time
     * @return the id of comment created.
     */

    Long createComment(CommentEntity entity);


}
