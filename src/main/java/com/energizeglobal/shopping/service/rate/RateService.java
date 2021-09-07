package com.energizeglobal.shopping.service.rate;

import com.energizeglobal.shopping.model.entity.RateEntity;

/**
 * A <i>RateService</i>. This interface has responsibility to create
 * rate for specific product .<p>
 * The <tt>GameService</tt> interface provides two methods for creating
 * the rate service implemented by <tt>RateServiceImpl</tt> class.<p>
 *
 * @author Omid Rostami
 */

public interface RateService {

    /**
     * Returns Long
     *
     * @param entity the Player of the game who created it for the first time
     * @return the id of rate created.
     */


    Long createRate(RateEntity entity);


}
