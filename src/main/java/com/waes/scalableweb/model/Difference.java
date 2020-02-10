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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
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

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "difference_id")
    private List<DifferenceDetail> details = new ArrayList<>();

}
