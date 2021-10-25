package com.deveup.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.deveup.dao.UserDao;
import com.deveup.entity.User;
import com.deveup.util.DeveupTable;

@ManagedBean(name = "user")
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;
	private User userLogin;
	
	private DeveupTable<User> table;

	public UserBean() {
	}

	@PostConstruct
	public void init() {
		user = new User();
		this.userLogin = null;
		this.table = new DeveupTable<User>();
	}

	public String login() {
		FacesMessage messageFaces = null;
		if (userLogin == null) {
			if (user != null) {
				String message = user.validateLogin();
				if (message == null) {
					UserDao dao = new UserDao();
					User aux = dao.findByField("mail", user.getMail());
					if (aux != null && aux.getPassword().equals(user.getPassword())) {
						this.userLogin = user;
						user = new User();
						messageFaces = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCES", "Te has logeado.");
						users();
						return "home";
					} else {
						messageFaces = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Datos incorrectos.");
					}
				} else {
					messageFaces = new FacesMessage(FacesMessage.SEVERITY_WARN, "WARN", message);
				}
			} else {
				messageFaces = new FacesMessage(FacesMessage.SEVERITY_FATAL, "FATAL",
						"No se ha recibido la informacion del usuario.");
			}
		} else {
			messageFaces = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Ya se ha logeado un usuario.");
		}
		FacesContext.getCurrentInstance().addMessage(null, messageFaces);
		return "login";
	}
	
	public void users() {
		this.table = new DeveupTable<User>();
		UserDao dao = new UserDao();
		table.setEntity(dao.list());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DeveupTable<User> getTable() {
		return table;
	}

	public void setTable(DeveupTable<User> table) {
		this.table = table;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}