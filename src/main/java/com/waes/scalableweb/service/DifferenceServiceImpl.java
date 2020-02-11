package com.waes.scalableweb.service;

import java.util.ArrayList;
import java.util.List;

import com.waes.scalableweb.dto.DifferenceRequestDTO;
import com.waes.scalableweb.dto.DifferenceResponseDTO;
import com.waes.scalableweb.enuns.DifferenceResult;
import com.waes.scalableweb.enuns.Side;
import com.waes.scalableweb.model.Difference;
import com.waes.scalableweb.model.DifferenceDetail;
import com.waes.scalableweb.repository.DifferenceRepository;
import com.waes.scalableweb.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
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

    private final Environment environment;

    public DifferenceServiceImpl(DifferenceRepository differenceRepository, Environment environment) {
        this.differenceRepository = differenceRepository;
        this.environment = environment;
    }

    @Override
    public DifferenceResponseDTO getDifference(String id) {



        Difference difference = differenceRepository.findById(id)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry! Nothing was found with ID: " + id));


        DifferenceResult differenceResult = getDifferenceResult(difference);

        List<DifferenceDetail> differenceDetails = getDifferenceDetails(difference, differenceResult);

        DifferenceResponseDTO differenceResponseDTO = DifferenceResponseDTO.builder()
            .id(id)
            .differenceResult(differenceResult)
            .differenceDetails(differenceDetails)
            .build();

        return differenceResponseDTO;
    }

    private List<DifferenceDetail> getDifferenceDetails(Difference difference, DifferenceResult differenceResult) {

        List<DifferenceDetail> differenceDetails = new ArrayList<>();

        if(differenceResult == DifferenceResult.SAME_SIZE_DIFFERENT_CONTENTS){

            String leftData = difference.getLeftData();
            String rightData = difference.getRightData();

            int offset = 0;
            int length = 0;
            StringBuilder diffLeft = new StringBuilder();
            StringBuilder diffRight = new StringBuilder();

            for(int i = 0 ; i < difference.getLeftData().length(); i++) {

                while ( i < difference.getLeftData().length() && (leftData.charAt(i) !=  rightData.charAt(i)) ){

                    if(length == 0){
                        offset = i;
                    }

                    diffLeft.append(leftData.charAt(i));
                    diffRight.append(rightData.charAt(i));

                    length++;

                    i++;
                }

                if(length > 0){
                    differenceDetails.add(DifferenceDetail.builder()
                        .offset(offset)
                        .length(length)
                        .left(diffLeft.toString())
                        .right(diffRight.toString())
                        .build());

                    //reset length
                    length = 0;
                    diffLeft = new StringBuilder();
                    diffRight = new StringBuilder();
                }

            }
        }

        return differenceDetails;
    }



    private DifferenceResult getDifferenceResult(Difference difference) {

        boolean isEqualContent = difference.getLeftData().contentEquals(difference.getRightData());
        boolean isSameSize = difference.getLeftData().length() == difference.getRightData().length();

        if(isEqualContent){
            return DifferenceResult.EQUALS;
        }else if(!isSameSize){
            return DifferenceResult.DIFFERENT_SIZES;
        }else {
            return DifferenceResult.SAME_SIZE_DIFFERENT_CONTENTS;
        }
    }


    @Override
    public void addDifferenceSideData(String id,  Side side, DifferenceRequestDTO differenceRequestDTO)  {


         //   this.isEncodedStringValid(encodedString);

            Difference difference = findOrBuildDifferenceById(id);

            this.setDifferenceSideData(difference, side, differenceRequestDTO.getEncodedData());

            Difference differenceSaved = this.differenceRepository.save(difference);

            log.info("Added content to ID " + id + " on " + side.name() + " side.");
            log.info("Saved data: " + differenceSaved.toString());


    }

    private Difference findOrBuildDifferenceById(String id) {
        return this.differenceRepository.findById(id).orElse(Difference.builder().id(id).build());
    }

    private void setDifferenceSideData(Difference difference, Side side, String encodedString) {

        if (side.equals(Side.LEFT)) {
            if(difference.getLeftData() == null){
                difference.setLeftData(encodedString);
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "LEFT side for id " + difference.getId() + " already has data.");
            }
        } else {
            if(difference.getRightData() == null){
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



}
