package com.safetynetalert.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	@Override
	public int hashCode() {
		return Objects.hash(nbAdults, nbChildren, personsDto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListPersonDto other = (ListPersonDto) obj;
		return nbAdults == other.nbAdults && nbChildren == other.nbChildren
				&& Objects.equals(personsDto, other.personsDto);
	}
	
	
}
