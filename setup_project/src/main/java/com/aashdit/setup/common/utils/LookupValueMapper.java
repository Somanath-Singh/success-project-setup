package com.aashdit.setup.common.utils;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.aashdit.setup.common.dto.LookupValueDTO;
import com.aashdit.setup.common.model.CommonLookupValue;

@Mapper(componentModel = "spring")
public interface LookupValueMapper {

	LookupValueMapper INSTANCE = Mappers.getMapper(LookupValueMapper.class);

	@Mapping(source = "lookupValueId", target = "valueId")
	@Mapping(source = "lookupValueCode", target = "valueCode")
	@Mapping(source = "lookupCode", target = "code")
	@Mapping(source = "lookupValueEn", target = "valueEn")
	@Mapping(source = "lookupValueHi", target = "valueHi")
	@Mapping(source = "isActive", target = "isActive")
	LookupValueDTO toDTO(CommonLookupValue entity);

	List<LookupValueDTO> toDTOList(List<CommonLookupValue> entities);

	@InheritInverseConfiguration
	CommonLookupValue toEntity(LookupValueDTO dto);

	List<CommonLookupValue> toEntityList(List<LookupValueDTO> dtos);
	
}
