package com.vk.recoengine.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vk.recoengine.service.BaseService;

public class BaseServiceImpl implements BaseService{
	
	@PersistenceContext
	public EntityManager entityManager;
	
	@Autowired
	public EntityManagerFactory entityManagerFactory;
}
