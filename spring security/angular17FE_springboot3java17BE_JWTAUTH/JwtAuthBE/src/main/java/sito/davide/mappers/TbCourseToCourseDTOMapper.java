package sito.davide.mappers;

import org.mapstruct.Mapper;

import sito.davide.dto.CourseDTO;
import sito.davide.entity.TbCourse;

@Mapper(componentModel = "spring")
public interface TbCourseToCourseDTOMapper
{
	public TbCourse dtoToTb(CourseDTO courseDTO);
	public CourseDTO tbToDTO(TbCourse tbCourse);
}
