package com.kb.persistence;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;

public class DataSourceTests {
	
	@Setter(onMethod_ = {@Autowired})
	private DataSource dataSourcce;

}
