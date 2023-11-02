package com.sprint.bspro.util;

import com.sprint.bspro.dto.AppCustomerRequestDTO;
import com.sprint.bspro.dto.AppCustomerResponseDTO;
import com.sprint.bspro.entity.AppCustomer;
import com.sprint.bspro.entity.ContactInfo;

public class AppCustomerDTOMapper {
	public AppCustomer getAppCustomerFromAppCustomerDTO(AppCustomerRequestDTO reqDTO) {
		AppCustomer ac = new AppCustomer();
		ContactInfo contact = new ContactInfo();
		contact.setCity(reqDTO.getCity());
		contact.setCountry(reqDTO.getCountry());
		contact.setEmail(reqDTO.getEmail());
		contact.setHouseAddress(reqDTO.getHouseAddress());
		contact.setPhone(reqDTO.getPhone());
		ac.setUserCode(reqDTO.getUserCode());
		ac.setFullName(reqDTO.getFullName());
		ac.setContactInfo(contact);
		ac.setPassword(reqDTO.getPassword());
		ac.setUsername(reqDTO.getUsername());
		ac.setUserrole(reqDTO.getUserrole());
		return ac;
	}
	public AppCustomerResponseDTO getAppCustomerDTOFromAppCustomer(AppCustomer customer) {
		AppCustomerResponseDTO adto = new AppCustomerResponseDTO();
		ContactInfo c = customer.getContactInfo();
		adto.setCity(c.getCity());
		adto.setCountry(c.getCountry());
		adto.setEmail(c.getEmail());
		adto.setHouseAddress(c.getHouseAddress());
		adto.setPhone(c.getPhone());
		adto.setFullName(customer.getFullName());
		adto.setUserCode(customer.getUserCode());
		adto.setUsername(customer.getUsername());
		adto.setUserrole(customer.getUserrole());
		adto.setPassword(customer.getPassword());
		return adto;
	}
}
