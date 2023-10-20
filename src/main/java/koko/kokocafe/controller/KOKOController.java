package koko.kokocafe.controller;

import koko.kokocafe.domain.Cafe;
import koko.kokocafe.domain.Coordinate;
import koko.kokocafe.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class KOKOController {

    private CafeRepository cafeRepository;

    @Autowired
    public KOKOController(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    @GetMapping("cafes")
    @ResponseBody
    public List<Cafe> findAllApi() {
        List<Cafe> cafes = cafeRepository.findAll();

        return cafes;
    }

    @GetMapping("cafes/byRadius")
    @ResponseBody
    public List<Cafe> findByRadiusApi(
            @RequestParam("lat") Double userLatitude,
            @RequestParam("lng") Double userLongitude,
            @RequestParam("rad") Double radius
    ) {
        List<Cafe> cafes = cafeRepository.findByRadius(userLatitude, userLongitude, radius);
        System.out.println("userLatitude: " + userLatitude);
        System.out.println("userLongitude: " + userLongitude);
        System.out.println("radius: " + radius);
        System.out.println(cafes);
        return cafes;
    }

    @GetMapping("coordinate")
    @ResponseBody
    public Coordinate coordinateApi(@RequestParam("name") String name) {
        Coordinate coordinate = cafeRepository.getCoordinateByName(name);

        return coordinate;
    }

    @GetMapping("positions")
    @ResponseBody
    public List<Long> positionsApi(@RequestParam("name") String name) {
        List<Long> positions = cafeRepository.getPositionsByName(name);

        return positions;
    }

    @GetMapping("benefits")
    @ResponseBody
    public Map<String, Long> benefitsApi(@RequestParam("name") String name) {
        Map<String, Long> benefits = cafeRepository.getBenefitsByName(name);

        return benefits;
    }

    @PutMapping("positions/update")
    @ResponseBody
    public void updatePositionsApi(@RequestParam("name") String name, @RequestBody List<Long> newPositions) {
        cafeRepository.updatePositionsByName(name, newPositions);
    }
}