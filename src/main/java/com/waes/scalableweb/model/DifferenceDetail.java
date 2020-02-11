package com.waes.scalableweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Victor Wardi - @victorwardi on 2/10/2020
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DifferenceDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "offset_detail")
    private Integer offset;

    @Column(name = "length_detail")
    private Integer length;

    @Column(name = "left_detail")
    private String left;

    @Column(name = "right_detail")
    private String right;
}
