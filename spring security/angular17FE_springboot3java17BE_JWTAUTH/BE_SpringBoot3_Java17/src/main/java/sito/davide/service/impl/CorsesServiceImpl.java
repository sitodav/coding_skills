package sito.davide.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sito.davide.dao.TbCourseRepository;
import sito.davide.dto.CourseDTO;
import sito.davide.mappers.TbCourseToCourseDTOMapper;
import sito.davide.service.CoursesService;

@Service
public class CorsesServiceImpl implements CoursesService
{
	private static final Logger log = LoggerFactory.getLogger(CoursesService.class);

	@Autowired
	private TbCourseRepository coursesRepository;

	@Autowired
	public TbCourseToCourseDTOMapper courseMapper;

	@Transactional(rollbackFor = Exception.class) @Override
	public List<CourseDTO> getAllCourses() throws Exception
	{
		try
		{
			log.info("called getAllCourses service");
			return coursesRepository.findAll().stream().map(courseDB -> courseMapper.tbToDTO(courseDB)).collect(Collectors.toList());
		}
		catch (Exception e)
		{
			log.error("Error ", e);
			throw e;
		}
	}

}
