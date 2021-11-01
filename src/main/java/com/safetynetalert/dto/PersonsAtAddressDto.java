package com.safetynetalert.dto;

import java.util.List;

public class PersonsAtAddressDto {
	List<ChildDto> listChildren;
	List<OtherPersonDto> listOtherPersons;
	
	public PersonsAtAddressDto(List<ChildDto> listChildren, List<OtherPersonDto> listOtherPersons) {
		super();
		this.listChildren = listChildren;
		this.listOtherPersons = listOtherPersons;
	}

	public List<ChildDto> getListChildren() {
		return listChildren;
	}

	public void setListChildren(List<ChildDto> listChildren) {
		this.listChildren = listChildren;
	}

	public List<OtherPersonDto> getListOtherPersons() {
		return listOtherPersons;
	}

	public void setListOtherPersons(List<OtherPersonDto> listOtherPersons) {
		this.listOtherPersons = listOtherPersons;
	}
}
