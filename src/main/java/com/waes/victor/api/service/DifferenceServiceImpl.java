package com.waes.victor.api.service;

import com.waes.victor.api.contants.WaesApiMessage;
import com.waes.victor.api.dto.DifferenceRequestDTO;
import com.waes.victor.api.dto.DifferenceResponseDTO;
import com.waes.victor.api.enuns.Side;
import com.waes.victor.api.exception.WaesApiRuntimeException;
import com.waes.victor.api.model.Difference;
import com.waes.victor.api.repository.DifferenceRepository;
import com.waes.victor.api.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * The implementation of a Difference service.
 *
 * @author Victor - @victorwardi
 * @see DifferenceService
 */
@Service
@Slf4j
public class DifferenceServiceImpl implements DifferenceService {

    private final DifferenceRepository differenceRepository;

    /**
     * Instantiates a new Difference service.
     *
     * @param differenceRepository the difference repository
     */
    public DifferenceServiceImpl(DifferenceRepository differenceRepository) {
        this.differenceRepository = differenceRepository;
    }

    /**
     * Get a Difference by ID
     *
     * @param id the difference id
     * @return differenceResponseDTO - the response
     */
    @Override
    public DifferenceResponseDTO getDifference(String id) {

        Difference difference = differenceRepository.findById(id)
            .orElseThrow(() -> new WaesApiRuntimeException(WaesApiMessage.DIFFERENCE_ID_NOT_FOUND, HttpStatus.NOT_FOUND, id));

        //Compare Sides
        try {
            difference.compareSides();
        } catch (WaesApiRuntimeException e) {
            new WaesApiRuntimeException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        DifferenceResponseDTO differenceResponseDTO = DifferenceResponseDTO.builder()
            .id(id)
            .differenceResult(difference.getDifferenceResult())
            .differenceDetails(difference.getDetails())
            .build();

        return differenceResponseDTO;
    }

    /**
     * @param id                   the difference id
     * @param side                 the side
     * @param differenceRequestDTO the request
     */
    @Override
    public void addDifferenceSideData(String id, Side side, DifferenceRequestDTO differenceRequestDTO) {

        //Check if data is Base64
        checkEncodedData(differenceRequestDTO.getEncodedData());

        //Find difference by Id or build a new object
        Difference difference = findOrBuildDifferenceById(id);

        //Set data to the side
        difference.setSideData(side, differenceRequestDTO.getEncodedData());

        //Save difference on database
        this.differenceRepository.save(difference);
    }

    /**
     * Check if String is encoded on Base64
     *
     * @param encodedData String is encoded on Base64
     */
    private void checkEncodedData(String encodedData) {

        if (!StringUtils.isStringEncodedOnBase64(encodedData)) {
            throw new WaesApiRuntimeException(WaesApiMessage.STRING_NOT_ENCODED_ON_BASE64, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find a Difference from database by ID or a new instance
     *
     * @param id Id of the operation
     * @return a Diffference
     */
    private Difference findOrBuildDifferenceById(String id) {
        return this.differenceRepository.findById(id).orElse(Difference.builder().id(id).build());
    }

}
