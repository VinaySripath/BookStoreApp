package com.sprint.bspro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.bspro.entity.Admin;
import com.sprint.bspro.entity.ContactInfo;
import com.sprint.bspro.exceptions.InvalidUserNameException;
import com.sprint.bspro.repository.IAdminRepository;
@Service
public class AdminServiceImpl implements IAdminService {
	@Autowired
	IAdminRepository adminRepository;
	@Override
	public Admin createAppAdmin(Admin admin) {
		if(admin != null) {
			adminRepository.save(admin);
			admin.setUserCode(adminRepository.getAdminByUsername(admin.getUsername()).getUserCode());
			return admin;
		}
		return null;
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		if(admin != null) {
			int id = admin.getUserCode();
			System.out.println("customer id : "+id);
			Admin savedAdmin = adminRepository.findById(id).get();
			if(savedAdmin != null) {
				if(admin.getContactInfo() != null) {
					ContactInfo cinfo = savedAdmin.getContactInfo();
					
					ContactInfo newInfo = admin.getContactInfo();
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
					savedAdmin.setContactInfo(cinfo);
				}
				if(admin.getFullName()!= null) {
					savedAdmin.setFullName(admin.getFullName());
				}
				adminRepository.save(savedAdmin);
				return savedAdmin;
			}
		}
		return null;
	}
	
	@Override
	public Admin updateAdminByName(Admin admin, String username) throws InvalidUserNameException {
		if(admin != null) {
			Admin savedAdmin = adminRepository.getAdminByUsername(username);
			if(savedAdmin != null) {
				if(admin.getContactInfo() != null) {
					ContactInfo cinfo = savedAdmin.getContactInfo();
					System.out.println("customer country : "+cinfo.getCountry());
					
					ContactInfo newInfo = admin.getContactInfo();
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
					savedAdmin.setContactInfo(cinfo);
				}
				if(admin.getFullName()!= null) {
					savedAdmin.setFullName(admin.getFullName());
				}
				adminRepository.save(savedAdmin);
				return savedAdmin;
			}
			else {
				throw new InvalidUserNameException("username not found", "update admin");
			}
		}
		return null;
	}

	@Override
	public Admin viewAdmin(int userCode) {
		if(userCode != 0) {
			return adminRepository.findById(userCode).get();
		}
		return null;
	}

	@Override
	public Admin viewAdminByUserName(String username) throws InvalidUserNameException{
		if(username != null) {
			Admin admin = adminRepository.getAdminByUsername(username);
			if(admin != null) {
				return admin;
			}
			else {
				throw new InvalidUserNameException("Invalid Username: please check username","view admin");
			}
		}
		return null;
	}

}
