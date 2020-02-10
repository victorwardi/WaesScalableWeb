package com.waes.scalableweb.service;

import com.waes.scalableweb.dto.DifferenceRequestDTO;
import com.waes.scalableweb.dto.DifferenceResponseDTO;
import com.waes.scalableweb.enuns.DifferenceResult;
import com.waes.scalableweb.enuns.Side;
import com.waes.scalableweb.model.Difference;
import com.waes.scalableweb.repository.DifferenceRepository;
import com.waes.scalableweb.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Created by Victor Wardi - @victorwardi on 2/8/2020
 */
@Service
@Slf4j
public class DifferenceServiceImpl implements DifferenceService {

    private final DifferenceRepository differenceRepository;

    public DifferenceServiceImpl(DifferenceRepository differenceRepository) {
        this.differenceRepository = differenceRepository;
    }

    @Override
    public DifferenceResponseDTO getDifference(String id) {

        DifferenceResponseDTO differenceResponseDTO = DifferenceResponseDTO.builder()
            .id("1")
            .differenceResult(DifferenceResult.SAME_SIZE_DIFFERENT_CONTENTS)
            .build();

        return differenceResponseDTO;
    }


    @Override
    public void addDifferenceSideData(String id, String encodedString, Side side)  {


            this.isEncodedStringValid(encodedString);

            Difference difference = getDifferenceByIdOrCreate(id);

            this.setDifferenceSideData(encodedString, side, difference);

            Difference differenceSaved = this.differenceRepository.save(difference);

            log.info("Added content to ID " + id + " on " + side.name() + " side.");
            log.info("Saved data: " + differenceSaved.toString());


    }

    private Difference getDifferenceByIdOrCreate(String id) {
        return this.differenceRepository.findById(id).orElse(Difference.builder().id(id).build());
    }

    private void setDifferenceSideData(String encodedString, Side side, Difference difference) {

        if (side.equals(Side.LEFT)) {
            if(difference.getLeftData() != null){
                difference.setLeftData(encodedString);
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "LEFT side for id " + difference.getId() + " already has data.");
            }
        } else {
            if(difference.getRightData() != null){
                difference.setRightData(encodedString);
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RIGHT side for id " + difference.getId() + " already has data.");
            }
        }


    }

    private void isEncodedStringValid(String encodedString)  {

        this.isStringEncodedOnBase64(encodedString);

        try {
            String decodedString = StringUtils.getStringEncodedOnBase64(encodedString);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        // this.isStringValidJson(decodedString);

    }

    private void isStringEncodedOnBase64(String encodedString)  {
        if (!StringUtils.isStringEncodedOnBase64(encodedString)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "String is not on base 64");
        }
    }

    private void isStringValidJson(String decodedString) {
        if (!StringUtils.isStringValidJson(decodedString)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "String is an invalid JSON");
        }
    }

    @Override
    public boolean addDifference(DifferenceRequestDTO differenceRequestDTO) {
        return false;
    }


}
