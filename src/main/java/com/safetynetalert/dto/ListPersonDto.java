package com.safetynetalert.dto;

import java.util.ArrayList;
import java.util.List;

public class ListPersonDto {
	private List<PersonDto> personsDto;
	private int nbAdults;
	private int nbChildren;
	
	public ListPersonDto() {
		personsDto = new ArrayList<>();
	}
	/**
	 * @return the listPersonDto
	 */
	public List<PersonDto> getListPersonDto() {
		return personsDto;
	}
	/**
	 * @param listPersonDto the listPersonDto to set
	 */
	public void setListPersonDto(List<PersonDto> listPersonDto) {
		this.personsDto = listPersonDto;
	}
	/**
	 * @return the nbAdults
	 */
	public int getNbAdults() {
		return nbAdults;
	}
	/**
	 * @param nbAdults the nbAdults to set
	 */
	public void setNbAdults(int nbAdults) {
		this.nbAdults = nbAdults;
	}
	/**
	 * @return the nbChildren
	 */
	public int getNbChildren() {
		return nbChildren;
	}
	/**
	 * @param nbChildren the nbChildren to set
	 */
	public void setNbChildren(int nbChildren) {
		this.nbChildren = nbChildren;
	}
}
