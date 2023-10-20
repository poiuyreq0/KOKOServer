package koko.kokocafe.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import koko.kokocafe.domain.Cafe;
import koko.kokocafe.domain.Coordinate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
public class CafeRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Cafe cafe) {
        em.persist(cafe);

        return cafe.getId();
    }

    public Long update(Cafe cafe) {
        em.merge(cafe);

        return cafe.getId();
    }

    public Optional<Cafe> findById(Long id) {
        Cafe cafe = em.find(Cafe.class, id);

        return Optional.ofNullable(cafe);
    }

    public Optional<Cafe> findByName(String name) {
        List<Cafe> result = em.createQuery("SELECT c FROM Cafe c WHERE c.name = :name", Cafe.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    public List<Cafe> findAll() {
        return em.createQuery("SELECT c FROM Cafe c", Cafe.class)
                .getResultList();
    }

    public List<Cafe> findByRadius(Double userLatitude, Double userLongitude, Double radius) {
        String h2Query = "SELECT c FROM Cafe c " +
                "WHERE " +
                "(6371 * ACOS(SIN(RADIANS(:userLatitude)) * SIN(RADIANS(c.coordinate.latitude)) + " +
                "COS(RADIANS(:userLatitude)) * COS(RADIANS(c.coordinate.latitude)) * " +
                "COS(RADIANS(c.coordinate.longitude) - RADIANS(:userLongitude)))) <= :radius";

        Query query = em.createQuery(h2Query, Cafe.class);
        query.setParameter("userLatitude", userLatitude);
        query.setParameter("userLongitude", userLongitude);
        query.setParameter("radius", radius);

        return query.getResultList();
    }

    public Coordinate getCoordinateByName(String name) {
        return findByName(name).map(Cafe::getCoordinate).orElse(null);
    }

    public List<Long> getPositionsByName(String name) {
        return findByName(name).map(Cafe::getPositions).orElse(Collections.emptyList());
    }

    public Map<String, Long> getBenefitsByName(String name) {
        return findByName(name).map(Cafe::getBenefits).orElse(Collections.emptyMap());
    }

    public void updatePositionsByName(String name, List<Long> newPositions) {
        findByName(name).ifPresent(cafe -> {
            cafe.setPositions(newPositions);
            update(cafe);
        });
    }
}
