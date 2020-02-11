package com.waes.scalableweb.service;

import com.waes.scalableweb.dto.DifferenceRequestDTO;
import com.waes.scalableweb.dto.DifferenceResponseDTO;
import com.waes.scalableweb.enuns.Side;

/**
 * Created by Victor Wardi - @victorwardi on 2/8/2020
 */
public interface DifferenceService {

    /**
     * Gets difference.
     *
     * @param id the id
     * @return the difference
     */
    DifferenceResponseDTO getDifference(String id);

    void addDifferenceSideData(String id,  Side side, DifferenceRequestDTO differenceRequestDTO);
}
