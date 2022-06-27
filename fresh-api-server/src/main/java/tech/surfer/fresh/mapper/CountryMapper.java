package tech.surfer.fresh.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.surfer.fresh.codegen.model.Country;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    @Mapping(target = "tech.surfer.fresh.codegen.model.Country")
    Country entityToModel(tech.surfer.fresh.entity.Country country);

    @InheritInverseConfiguration
    tech.surfer.fresh.entity.Country modelToEntity(Country country);

    List<Country> entitiesToModels(List<tech.surfer.fresh.entity.Country> country);
}
