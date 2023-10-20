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
        Map<String, Long> hm = new HashMap(){{
            put("red", 10L);
            put("green", 20L);
            put("orange", 30L);
        }};
        Cafe cafe = new Cafe("koko1", new Coordinate(36.3461714054393, 127.29480966136128), new ArrayList<>(Arrays.asList(1L, 2L, 3L)), hm);
        cafeRepository.save(cafe);
        // when

        // then
        List<Cafe> cafes;
        cafes = cafeRepository.findByRadius(36.3461714054393, 127.29480966136128, 10.0);
        System.out.println("cafes" + cafes);
    }
}