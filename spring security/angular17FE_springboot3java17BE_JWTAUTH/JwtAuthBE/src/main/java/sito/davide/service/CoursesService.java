package sito.davide.service;

import java.util.List;

import sito.davide.dto.CourseDTO;

public interface CoursesService
{
	public abstract List<CourseDTO> getAllCourses() throws Exception;
}
