package com.PRJ321x_namtqFX20225.asm3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "doctor_id")
	private int doctorId;
	@Column(name = "user_id")
	private int userId;
	@Column(name = "date")
	private String date;
	@Column(name = "time")
	private String time;
	@Column(name = "price")
	private double price;
	@Column(name = "name")
	private String name;
	@Column(name = "gender")
	private String gender;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "dob")
	private String dob;
	@Column(name = "address")
	private String address;
	@Column(name = "reason")
	private String reason;
	@Column(name = "is_accept")
	private Boolean isAccept;
	@Column(name = "reject_reason")
	private String rejectReason;

	public Booking() {
	}

	public Booking(int doctorId, int userId, String date, String time, double price, String name, String gender,
			String phoneNumber, String dob, String address, String reason, Boolean isAccept, String rejectReason) {
		this.doctorId = doctorId;
		this.userId = userId;
		this.date = date;
		this.time = time;
		this.price = price;
		this.name = name;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.address = address;
		this.reason = reason;
		this.isAccept = isAccept;
		this.rejectReason = rejectReason;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(Boolean isAccept) {
		this.isAccept = isAccept;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", doctorId=" + doctorId + ", date=" + date + ", time=" + time + ", price=" + price
				+ ", name=" + name + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", dob=" + dob
				+ ", address=" + address + ", reason=" + reason + "]";
	}
}
