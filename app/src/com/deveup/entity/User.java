package com.deveup.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.deveup.util.DeveupValidate;

import java.util.Date;


@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_register")
	private Date dateRegister;

	private String mail;

	private String name;

	private String password;

	private String photo;

	public User() {
	}
	
	public String validateLogin() {
		String message = null;
		if(!DeveupValidate.isChain(mail)) {
			message = "El campo email es obligatorio.";
		}else {
			if(!DeveupValidate.isEmail(mail)) {
				message = "El campo email no cumple para ser un email.";
			}
		}
		if(!DeveupValidate.isChain(password)) {
			message = "El campo clave es obligatorio.";
		}else {
			if(password.length() < 3) {
				message = "El campo clave debe tener mas de 3 caracteres.";
		}
		}
		
		return message;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateRegister() {
		return this.dateRegister;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}