package com.PRJ321x_namtqFX20225.asm3.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "hospitals")
public class Hospital {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "address")
	private String address;
	@Column(name = "workingTime")
	private String workingTime;
	@Column(name = "description")
	private String description;
	@Column(name = "min_price")
	private double minPrice;
	@Column(name = "max_price")
	private double maxPrice;
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "hospital_specialization", joinColumns = @JoinColumn(name = "hospital_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "specialization_id", referencedColumnName = "id"))
	private Set<Specialization> specializations;

	public Hospital() {
	}

	public Hospital(String name, String address, String workingTime, String description, double minPrice,
			double maxPrice, Set<Specialization> specializations) {
		this.name = name;
		this.address = address;
		this.workingTime = workingTime;
		this.description = description;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.specializations = specializations;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(String workingTime) {
		this.workingTime = workingTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Set<Specialization> getSpecializations() {
		return specializations;
	}

	public void setSpecializations(Set<Specialization> specializations) {
		this.specializations = specializations;
	}

	@Override
	public String toString() {
		return "Hospital [Name=" + name + ", address=" + address + ", workingTime=" + workingTime + ", description="
				+ description + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", specializations="
				+ specializations + "]";
	}

}
