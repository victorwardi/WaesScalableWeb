package com.waes.victor.api.service;

import com.waes.victor.api.dto.DifferenceRequestDTO;
import com.waes.victor.api.dto.DifferenceResponseDTO;
import com.waes.victor.api.enuns.Side;

/**
 * Interface to provide methods that process data from controller and apply business rules
 *
 * @author Victor Wardi - @victorwardi on 2/8/2020
 */
public interface DifferenceService {

    /**
     * Gets difference.
     *
     * @param id the id
     * @return the difference
     */
    DifferenceResponseDTO getDifference(String id);

    /**
     * Add difference side data.
     *
     * @param id                   the id
     * @param side                 the side
     * @param differenceRequestDTO the difference request dto
     */
    void addDifferenceSideData(String id, Side side, DifferenceRequestDTO differenceRequestDTO);
}
