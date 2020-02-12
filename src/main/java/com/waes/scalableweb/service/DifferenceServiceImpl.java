package com.waes.scalableweb.service;

import com.waes.scalableweb.dto.DifferenceRequestDTO;
import com.waes.scalableweb.dto.DifferenceResponseDTO;
import com.waes.scalableweb.enuns.Side;
import com.waes.scalableweb.exception.WaesApiRuntimeException;
import com.waes.scalableweb.model.Difference;
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

    /**
     * Instantiates a new Difference service.
     *
     * @param differenceRepository the difference repository
     * @param environment          the environment
     */
    public DifferenceServiceImpl(DifferenceRepository differenceRepository, Environment environment) {
        this.differenceRepository = differenceRepository;
        this.environment = environment;
    }

    @Override
    public DifferenceResponseDTO getDifference(String id) {

        Difference difference = differenceRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry! Nothing was found with ID: " + id));

        DifferenceResponseDTO differenceResponseDTO = DifferenceResponseDTO.builder()
            .id(id)
            .differenceResult(difference.getDifferenceResult())
            .differenceDetails(difference.getDetails())
            .build();

        return differenceResponseDTO;
    }

    @Override
    public void addDifferenceSideData(String id, Side side, DifferenceRequestDTO differenceRequestDTO) {


        checkEncodedData(differenceRequestDTO.getEncodedData());

        Difference difference = findOrBuildDifferenceById(id);

        difference.setSideData(side, differenceRequestDTO.getEncodedData());

        Difference differenceSaved = this.differenceRepository.save(difference);

        log.info("Added content to ID " + id + " on " + side.name() + " side.");
        log.info("Saved data: " + differenceSaved.toString());


    }

    private void checkEncodedData(String encodedData) {

        if (!StringUtils.isStringEncodedOnBase64(encodedData)) {
            throw new WaesApiRuntimeException("String is not on base 64", HttpStatus.BAD_REQUEST);
        }

    }

    private Difference findOrBuildDifferenceById(String id) {
        return this.differenceRepository.findById(id).orElse(Difference.builder().id(id).build());
    }

}
