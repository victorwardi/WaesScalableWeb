package com.waes.scalableweb.service;

import com.waes.scalableweb.model.Difference;

/**
 * Created by Victor Wardi - @victorwardi on 2/8/2020
 */
public interface DifferenceService {

    /**
     * @param id
     * @return
     */
    public Difference getDiference(Integer id);

    boolean save(Object any);
}
