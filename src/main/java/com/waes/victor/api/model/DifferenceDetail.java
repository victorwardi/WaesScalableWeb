package com.waes.victor.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the details of the differences
 *
 * @author Victor Wardi - @victorwardi on 2/10/2020
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description="Represents the details of the differences")
public class DifferenceDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @ApiModelProperty(value="Id")
    private Long id;

    @Column(name = "offset_detail")
    @ApiModelProperty(value="Offset of the difference")
    private Integer offset;

    @Column(name = "length_detail")
    @ApiModelProperty(value="Length of the difference")
    private Integer length;

    @Column(name = "left_detail")
    @ApiModelProperty(value="Difference of the left side.")
    private String left;

    @Column(name = "right_detail")
    @ApiModelProperty(value="Difference of the right side.")
    private String right;
}
