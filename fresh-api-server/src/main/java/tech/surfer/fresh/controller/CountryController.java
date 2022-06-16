package tech.surfer.fresh.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.surfer.fresh.codegen.CountryApi;
import tech.surfer.fresh.codegen.model.Country;
import tech.surfer.fresh.mapper.CountryMapper;
import tech.surfer.fresh.repository.CountryRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CountryController implements CountryApi {

    @NonNull
    private CountryRepository countryRepository;

    @NonNull
    private CountryMapper countryMapper;

    @Override
    @ResponseBody
    public ResponseEntity<Long> getCountryCount() {
        return new ResponseEntity<>(countryRepository.count(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Country>> getCountries(Long offset, Long pageSize) {
        // TODO: implement Pagination
        return new ResponseEntity<>(countryMapper.entitiesToModels(countryRepository.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Country> createCountry(Country country) {
        tech.surfer.fresh.entity.Country countryEntity = countryMapper.modelToEntity(country);
        // ensure id is empty
        countryEntity.setId(null);

        countryEntity = countryRepository.save(countryEntity);

        return new ResponseEntity<>(countryMapper.entityToModel(countryEntity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Country> getCountryById(Long id) {
        Optional<tech.surfer.fresh.entity.Country> countryEntity = countryRepository.findById(id);

        if(countryEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(countryMapper.entityToModel(countryEntity.get()));
    }

    @Override
    public ResponseEntity<Void> deleteCountryById(Long id) {
        countryRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Country> updateCountry(Country country) {
        tech.surfer.fresh.entity.Country countryEntity = countryMapper.modelToEntity(country);
        countryEntity = countryRepository.save(countryEntity);

        return new ResponseEntity<>(countryMapper.entityToModel(countryEntity), HttpStatus.OK);
    }
}
