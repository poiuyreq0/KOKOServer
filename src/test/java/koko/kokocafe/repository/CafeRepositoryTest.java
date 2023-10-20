package koko.kokocafe.repository;

import koko.kokocafe.domain.Coordinate;
import koko.kokocafe.domain.Cafe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Commit
@SpringBootTest
@Transactional
class CafeRepositoryTest {

    @Autowired CafeRepository cafeRepository;

    @Test
    public void test_save() throws Exception {
        // given

        // when

        // then
    }
}