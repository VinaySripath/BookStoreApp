package com.sprint.bspro.util;

import com.sprint.bspro.dto.AuthorRequestDTO;
import com.sprint.bspro.dto.AuthorResponseDTO;
import com.sprint.bspro.entity.Author;
import com.sprint.bspro.entity.ContactInfo;

public class AuthorDTOMapper {
	public Author getAuthorFromAuthorDTO(AuthorRequestDTO reqDTO) {
		Author ac = new Author();
		ContactInfo contact = new ContactInfo();
		contact.setCity(reqDTO.getCity());
		contact.setCountry(reqDTO.getCountry());
		contact.setEmail(reqDTO.getEmail());
		contact.setHouseAddress(reqDTO.getHouseAddress());
		contact.setPhone(reqDTO.getPhone());
		ac.setUserCode(reqDTO.getUserCode());
		ac.setName(reqDTO.getName());
		ac.setContactInfo(contact);
		ac.setPassword(reqDTO.getPassword());
		ac.setUsername(reqDTO.getUsername());
		ac.setUserrole(reqDTO.getUserrole());
		ac.setNativeLanguage(reqDTO.getNativeLanguage());
		ac.setRegion(reqDTO.getRegion());
		ac.setStatus(reqDTO.getStatus());
		return ac;
	}
	public AuthorResponseDTO getAuthorDTOFromAuthor(Author author) {
		AuthorResponseDTO adto = new AuthorResponseDTO();
		ContactInfo c = author.getContactInfo();
		adto.setCity(c.getCity());
		adto.setCountry(c.getCountry());
		adto.setEmail(c.getEmail());
		adto.setHouseAddress(c.getHouseAddress());
		adto.setPhone(c.getPhone());
		adto.setName(author.getName());
		adto.setUserCode(author.getUserCode());
		adto.setUsername(author.getUsername());
		adto.setUserrole(author.getUserrole());
		adto.setPassword(author.getPassword());
		adto.setNativeLanguage(author.getNativeLanguage());
		adto.setRegion(author.getRegion());
		adto.setStatus(author.getStatus());
		adto.setFeedbacks(author.getFeedbacks());
		return adto;
	}
}
