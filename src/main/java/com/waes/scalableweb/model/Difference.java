package com.waes.scalableweb.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.waes.scalableweb.contants.WaesApiMessage;
import com.waes.scalableweb.enuns.DifferenceResult;
import com.waes.scalableweb.enuns.Side;
import com.waes.scalableweb.exception.WaesApiRuntimeException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Difference model class
 *
 * Created by Victor Wardi - @victorwardi on 2/8/2020
 */

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Difference implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    private String id;

    @Lob
    private String leftData;

    @Lob
    private String rightData;

    private DifferenceResult differenceResult;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "difference_id")
    private List<DifferenceDetail> details;


    /**
     * Set data according to side
     * @param side
     * @param data
     */
    public void setSideData(Side side, String data) {
        if (side.equals(Side.LEFT)) {
            this.setLeftData(data);
        } else {
            this.setRightData(data);
        }
    }

    /**
     * Compare Left and Right sides, set result and details if so.
     */
    public Difference compareSides() {


        //Check if both sides are available
        checkBothSidesAvailability();

        //Check if both sides are the same size
        boolean isSameSize = this.getLeftData().length() == this.getRightData().length();

        //If different result = DIFFERENT_SIZES
        if(!isSameSize){

            this.differenceResult = DifferenceResult.DIFFERENT_SIZES;

        //Else both are same size
        }else{

            //Compare both contents
            boolean isEqualContent = this.getLeftData().contentEquals(this.getRightData());

            //If contents equals = EQUALS
            if(isEqualContent){
                this.differenceResult = DifferenceResult.EQUALS;

            //Else contents = SAME_SIZE_DIFFERENT_CONTENTS
            }else{

                this.differenceResult = DifferenceResult.SAME_SIZE_DIFFERENT_CONTENTS;

                //Set list with the details do the differences
                this.setDetails(getDifferencesDetails());

            }
        }

        return this;
    }

    /**
     * Check if both sides are available
     */
    private void checkBothSidesAvailability() {

        boolean isLeftSideNotFound = this.getLeftData() == null || this.getLeftData().isEmpty();

        boolean isRightSideNotFound = this.getRightData() == null || this.getRightData().isEmpty();

        //If both sides are not available throw exception
        if(isLeftSideNotFound && isRightSideNotFound){

            throw new WaesApiRuntimeException(WaesApiMessage.SIDES_NOT_FOUND);

        //Check each side and throw specific exception
        }else{

            if (isLeftSideNotFound) {
                throw new WaesApiRuntimeException(WaesApiMessage.LEFT_SIDE_NOT_FOUND);
            }else if (isRightSideNotFound) {
                throw new WaesApiRuntimeException(WaesApiMessage.RIGHT_SIDE_NOT_FOUND);
            }
        }



    }

    private List<DifferenceDetail> getDifferencesDetails() {

        List<DifferenceDetail> differenceDetails = new ArrayList<>();

        String leftData = this.getLeftData();
        String rightData = this.getRightData();

        int offset = 0;
        int length = 0;

        //StringBuilders to hold each differences
        StringBuilder diffLeft = new StringBuilder();
        StringBuilder diffRight = new StringBuilder();

        //Both data have the same size, loop over only one side is enough
        for (int i = 0; i < this.getLeftData().length(); i++) {

            //While has data and both chars at same index are different
            while (i < this.getLeftData().length() && (leftData.charAt(i) != rightData.charAt(i))) {

                //Add current index to offset
                if (length == 0) {
                    offset = i;
                }

                //Append to each side the different char
                diffLeft.append(leftData.charAt(i));
                diffRight.append(rightData.charAt(i));

                length++;

                i++;
            }

            //If length bigger than 0, add to detailsList and reset length and diffs
            if (length > 0) {
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

        return differenceDetails;
    }



}
