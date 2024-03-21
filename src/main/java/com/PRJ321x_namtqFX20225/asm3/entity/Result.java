package com.PRJ321x_namtqFX20225.asm3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "result")
public class Result {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "doctor_id")
	private int doctorId;
	@Column(name = "user_id")
	private int userId;
	@Column(name = "name")
	private String name;
	@Column(name = "gender")
	private String gender;
	@Column(name = "address")
	private String address;
	@Column(name = "pathological")
	private String pathological;
	@Column(name = "detail")
	private String detail;

	public Result() {
	}

	public Result(int doctorId, int userId, String name, String gender, String address, String pathological,
			String detail) {
		this.doctorId = doctorId;
		this.userId = userId;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.pathological = pathological;
		this.detail = detail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPathological() {
		return pathological;
	}

	public void setPathological(String pathological) {
		this.pathological = pathological;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Result [Name=" + name + ", gender=" + gender + ", address=" + address + ", pathological=" + pathological
				+ ", detail=" + detail + "]";
	}

}
