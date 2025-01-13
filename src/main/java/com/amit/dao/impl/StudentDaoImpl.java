package com.amit.dao.impl;

import static com.amit.util.constant.SqlQueryConstants.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.amit.dao.StudentDao;
import com.amit.entity.AddressEntity;
import com.amit.entity.CommunicationEntity;
import com.amit.entity.StudentEntity;
import com.amit.util.SqlUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private SqlUtils sqlUtils;

	@Override
	public List<StudentEntity> fetchStudentByRollNumber(List<String> rollNumberList) {
		
		var queryString = getSql(SELECT_STUDENT_BY_ROLL_NUMBERS_IN);
		var query = entityManager.createNativeQuery(queryString, Tuple.class);
		query.setParameter(FIELD_ROLL_NUMBER_LIST, rollNumberList);
		
		List<Tuple> tuples = query.getResultList();

		if (CollectionUtils.isEmpty(tuples)) {
			return Collections.emptyList();
		}

		return tuples.stream().map(StudentEntity::new).toList();
	}

	@Override
	public Map<Integer,List<AddressEntity>> fetchAddressListByStudentId(List<Integer> studentIds) {
		
		var queryString = getSql(SELECT_ADDRESS_BY_STUDENT_ID);
		var query = entityManager.createNativeQuery(queryString, Tuple.class);
		query.setParameter(FIELD_STUDENT_ID_LIST, studentIds);
		
		List<Tuple> tuples = query.getResultList();
		
		if (CollectionUtils.isEmpty(tuples)) {
			return Collections.emptyMap();
		}
		
		var result = tuples.stream().map(AddressEntity::new).collect(Collectors.groupingBy(AddressEntity::getStudentId));
		
		return result;
	}

	@Override
	public Map<Integer, List<CommunicationEntity>> fetchCommunicationListByStudentId(List<Integer> studentIds) {
		
		var queryString = getSql(SELECT_COMMUNICATION_BY_STUDENT_ID);
		var query = entityManager.createNativeQuery(queryString, Tuple.class);
		query.setParameter(FIELD_STUDENT_ID_LIST, studentIds);
		
		List<Tuple> tuples = query.getResultList();
		
		if (CollectionUtils.isEmpty(tuples)) {
			return Collections.emptyMap();
		}
		
		var result = tuples.stream().map(CommunicationEntity::new).collect(Collectors.groupingBy(CommunicationEntity::getStudentId));
		
		return result;
	}

	@Override
	public Integer getTotalRecordCount() {

		var queryString = getSql(SELECT_STUDENT_TOTAL_COUNT);
		var query = entityManager.createNativeQuery(queryString, Integer.class);
		var recordCount = (Integer)query.getSingleResult();
		return recordCount;
	}
	
	private String getSql(String queryConstant) {
		return sqlUtils.getSql(queryConstant);
	}

	@Override
	public List<StudentEntity> getPaginatedStudent(Integer pageNumber, Integer pageSize) {
		
		var queryString = getSql(SELECT_ALL_STUDENT_PAGINATED);
		var query = entityManager.createNativeQuery(queryString, Tuple.class);
		query.setParameter(PAGE_NUMBER, pageNumber-1);
		query.setParameter(PAGE_SIZE, pageSize);
		List<Tuple> tuples = query.getResultList();
		
		if (CollectionUtils.isEmpty(tuples)) {
			return Collections.emptyList();
		}
		
		return tuples.stream().map(StudentEntity::new).toList();
	}
}
