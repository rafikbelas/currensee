package com.rafikbelas.currensee.mapper;

import com.rafikbelas.currensee.dto.TimeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalTime;

@Mapper
public interface TimeMapper {
    TimeMapper INSTANCE = Mappers.getMapper(TimeMapper.class);

    TimeDTO toTimeDTO(LocalTime now);
}
