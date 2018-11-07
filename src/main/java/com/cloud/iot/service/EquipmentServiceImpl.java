/**
 * 
 */
package com.cloud.iot.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cloud.iot.entities.Equipment;
import com.cloud.iot.exception.ApplicationException;
import com.cloud.iot.exception.ErrorCodesEnum;
import com.cloud.iot.exception.ErrorVO;
import com.cloud.iot.models.EquipmentVO;
import com.cloudant.client.api.Database;

/**
 * @author Vivek
 *
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {

	private static final Logger logger = LoggerFactory.getLogger(EquipmentServiceImpl.class);

	@Autowired
	private Database dataBase;

	/**
	 * Method to query equipments based on limits
	 * 
	 * @param limit return
	 * @throws IOException
	 */
	@Override
	public List<EquipmentVO> getEquipments(int limit) throws IOException {
		logger.info("getEquipments service method invoked");
		List<Equipment> equipmentList = dataBase.getAllDocsRequestBuilder().includeDocs(true).limit(limit).build()
				.getResponse().getDocsAs(Equipment.class);
		List<EquipmentVO> equipmentVOList = new ArrayList<>();
		for (Equipment equipment : equipmentList) {
			equipmentVOList.add(convertToVO(equipment));
		}

		return equipmentVOList;
	}

	/**
	 * Method yo search equipments based on unique id
	 * 
	 * @param uniqueReferenceNumber
	 * 
	 */
	@Override
	public ResponseEntity<EquipmentVO> searchByUniqueId(int uniqueReferenceNumber) {
		logger.info("searchByUniqueId service method invoked");
		Equipment equipment = dataBase.find(Equipment.class, String.valueOf(uniqueReferenceNumber));
		EquipmentVO equipmentVO = convertToVO(equipment);
		if (equipmentVO != null)
			return new ResponseEntity<>(equipmentVO, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to create new equipment
	 * 
	 * @param equipmentVO
	 * @throws ApplicationException
	 */
	@Override
	public ResponseEntity<?> createEquipment(EquipmentVO equipmentVO) throws ParseException, ApplicationException {
		logger.info("createEquipment service method invoked");
		// Check whether equipment is exist with unique ID or not
		if (dataBase.contains(String.valueOf(equipmentVO.getEquipmentNumber()))) {
			ErrorVO errorVO = new ErrorVO(ErrorCodesEnum.DUPLICATE_EQUIPMENT.getStatus(),
					ErrorCodesEnum.DUPLICATE_EQUIPMENT.getDescription(), ErrorCodesEnum.DUPLICATE_EQUIPMENT.getCode());
			throw new ApplicationException(errorVO);
		} else {
			dataBase.post(convertToEntity(equipmentVO));
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}

}
