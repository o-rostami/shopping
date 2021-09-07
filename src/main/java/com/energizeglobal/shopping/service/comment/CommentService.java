package com.energizeglobal.shopping.service.comment;

import com.energizeglobal.shopping.model.entity.CommentEntity;

/**
 * A <i>ProductService</i>. This interface has responsibility to create and
 * control the game and movements.<p>
 * The <tt>GameService</tt> interface provides two methods for creating and fetching the player
 * the player service implemented by <tt>PlayerServiceImp</tt> class.<p>
 *
 * @author Omid Rostami
 */

public interface CommentService {

    Long createComment(CommentEntity entity);


}
