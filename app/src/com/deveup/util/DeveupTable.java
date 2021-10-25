package com.deveup.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.FilterMeta;

@ManagedBean(name = "table")
public class DeveupTable<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<T> entity;
	private List<T> select;
	private List<T> filter;
	private List<FilterMeta> filterBy;
	
	public DeveupTable() {
		this(new ArrayList<T>());
	}
	
	public DeveupTable(List<T> entity) {
		this.entity = entity;
		this.filter = new ArrayList<T>();
	}

	public List<T> getEntity() {
		return entity;
	}

	public void setEntity(List<T> entity) {
		this.entity = entity;
	}

	public List<T> getSelect() {
		return select;
	}

	public void setSelect(List<T> select) {
		this.select = select;
	}

	public List<T> getFilter() {
		return filter;
	}

	public void setFilter(List<T> filter) {
		this.filter = filter;
	}

	public List<FilterMeta> getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(List<FilterMeta> filterBy) {
		this.filterBy = filterBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
