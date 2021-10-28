package com.deveup.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


@ManagedBean(name = "security")
@RequestScoped
public class SecurityBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private FacesContext context;
	private ExternalContext external;
	private String url;
	
	@ManagedProperty("#{user}")
	private UserBean user;
	
	public SecurityBean() {
	}
	
	public void init() {
		this.context = FacesContext.getCurrentInstance();
		this.external = context.getExternalContext();
		this.url = this.external.getRequestContextPath();
	}
	
	public void direct(String path) {
		try {
			this.external.redirect(this.url +path);
		} catch (IOException e) {
		}
	}
	
	public void session() {
		init();
		if (!validate()) {
			try {
				external.redirect(this.url + "/login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void check() {
		this.session();
	}
	
	public void noSession() {
		init();
		if (this.user != null && user.getUserLogin() != null) {
			if (this.user.getUserLogin() != null) {
				direct("/home.xhtml");
			}
		}
	}
	
	public boolean validate() {
		return user != null && user.getUserLogin() != null;
	}

	public FacesContext getContext() {
		return context;
	}

	public void setContext(FacesContext context) {
		this.context = context;
	}

	public ExternalContext getExternal() {
		return external;
	}

	public void setExternal(ExternalContext external) {
		this.external = external;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
