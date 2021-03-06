package com.waes.victor.api.repository;

import java.util.Optional;

import com.waes.victor.api.enuns.DifferenceResult;
import com.waes.victor.api.model.Difference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Victor Wardi - @victorwardi on 2/11/2020
 */
@DataJpaTest
@AutoConfigureTestDatabase
class DifferenceRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DifferenceRepository differenceRepository;

    @Test
    public void shouldReturnDifferenceWhenFindById() {

        //given
        Difference difference = Difference.builder()
            .id("1")
            .leftData("MTIzNDU72NwVX==")
            .rightData("MTIzNDU72NwVX==")
            .differenceResult(DifferenceResult.EQUALS)
            .build();


        entityManager.persist(difference);
        entityManager.flush();

        //when
        Optional<Difference> differenceTest = differenceRepository.findById(difference.getId());

        //then
        assertThat(differenceTest.get().getDifferenceResult()).isEqualTo(difference.getDifferenceResult());
    }

    @Test
    public void shouldReturnOptionalEmptyWhenFindById(){
        assertThat(differenceRepository.findById("1")).isEqualTo(Optional.empty());
    }
}
