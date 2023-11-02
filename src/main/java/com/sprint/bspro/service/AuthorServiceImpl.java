package com.sprint.bspro.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint.bspro.entity.Author;
import com.sprint.bspro.entity.ContactInfo;
import com.sprint.bspro.repository.IAuthorRepository;
@Service
public class AuthorServiceImpl implements IAuthorService{

	@Autowired
	IAuthorRepository authorRepository;
	@Override
	public Author createAppAuthor(Author author) {
		if(author != null) {
			authorRepository.save(author);
			author.setUserCode(authorRepository.getAuthorByUsername(author.getUsername()).getUserCode());
			return author;
		}
		return null;
	}

	@Override
	@Transactional
	public Author updateAuthor(Author author) {
		if(author != null) {
			int id = author.getUserCode();
			System.out.println("customer id : "+id);
			Author savedAuthor = authorRepository.findById(id).get();
			if(savedAuthor != null) {
				if(author.getContactInfo() != null) {
					ContactInfo cinfo = savedAuthor.getContactInfo();
					System.out.println("customer country : "+cinfo.getCountry());
					
					ContactInfo newInfo = author.getContactInfo();
					System.out.println("new Country : "+newInfo.getCountry());
					if(newInfo.getCity()!= null) {
						cinfo.setCity(newInfo.getCity());
					}
					if(newInfo.getCountry()!= null) {
						cinfo.setCountry(newInfo.getCountry());
					}
					if(newInfo.getEmail()!= null) {
						cinfo.setEmail(newInfo.getEmail());
					}
					if(newInfo.getHouseAddress()!= null) {
						cinfo.setHouseAddress(newInfo.getHouseAddress());
					}
					if(newInfo.getPhone()!= 0) {
						cinfo.setPhone(newInfo.getPhone());
					}
					savedAuthor.setContactInfo(cinfo);
				}
				if(author.getName()!= null) {
					savedAuthor.setName(author.getName());
				}
				if(author.getRegion()!= null) {
					savedAuthor.setRegion(author.getRegion());
				}
				if(author.getNativeLanguage()!= null) {
					savedAuthor.setNativeLanguage(author.getNativeLanguage());
				}
				return savedAuthor;
			}
		}
		return null;
	}
	
	@Override
	@Transactional
	public Author updateAuthorByName(Author author, String username) {
		if(author != null) {
			Author savedAuthor = authorRepository.getAuthorByUsername(username);
			if(savedAuthor != null) {
				if(author.getContactInfo() != null) {
					ContactInfo cinfo = savedAuthor.getContactInfo();
					System.out.println("customer country : "+cinfo.getCountry());
					
					ContactInfo newInfo = author.getContactInfo();
					System.out.println("new Country : "+newInfo.getCountry());
					if(newInfo.getCity()!= null) {
						cinfo.setCity(newInfo.getCity());
					}
					if(newInfo.getCountry()!= null) {
						cinfo.setCountry(newInfo.getCountry());
					}
					if(newInfo.getEmail()!= null) {
						cinfo.setEmail(newInfo.getEmail());
					}
					if(newInfo.getHouseAddress()!= null) {
						cinfo.setHouseAddress(newInfo.getHouseAddress());
					}
					if(newInfo.getPhone()!= 0) {
						cinfo.setPhone(newInfo.getPhone());
					}
					savedAuthor.setContactInfo(cinfo);
				}
				if(author.getName()!= null) {
					savedAuthor.setName(author.getName());
				}
				if(author.getRegion()!= null) {
					savedAuthor.setRegion(author.getRegion());
				}
				if(author.getNativeLanguage()!= null) {
					savedAuthor.setNativeLanguage(author.getNativeLanguage());
				}
				return savedAuthor;
			}
		}
		return null;
	}

	@Override
	@Transactional
	public Author updateAuthorStatus(int usercode, String status) {
		if(status != null && usercode != 0) {
			Author savedAuthor = authorRepository.findById(usercode).get();
			if(savedAuthor != null) {
				savedAuthor.setStatus(status);
				return savedAuthor;
			}
		}
		return null;
	}
	
	@Override
	public Author viewAuthor(int usercode) {
		return authorRepository.findById(usercode).get();
	}
	
	@Override
	public Author viewAuthorByName(String username) {
		return authorRepository.getAuthorByUsername(username);
	}

}
