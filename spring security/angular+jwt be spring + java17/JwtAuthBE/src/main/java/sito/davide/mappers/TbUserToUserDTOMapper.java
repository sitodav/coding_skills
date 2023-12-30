package sito.davide.mappers;

import org.mapstruct.Mapper;

import sito.davide.dto.UserDTO;
import sito.davide.entity.TbUser;

@Mapper(componentModel = "spring")
public interface TbUserToUserDTOMapper
{
	public TbUser dtoToTb(UserDTO userDTO);
	public UserDTO tbToDTO(TbUser TbUser);
}
