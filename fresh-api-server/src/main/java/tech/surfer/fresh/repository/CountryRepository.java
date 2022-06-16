package tech.surfer.fresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.surfer.fresh.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
