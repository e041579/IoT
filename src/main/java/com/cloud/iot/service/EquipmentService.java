/**
 * 
 */
package com.cloud.iot.service;

import static com.cloud.iot.constants.Constants.SIMPLE_DATE_FORMATTER;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cloud.iot.entities.Equipment;
import com.cloud.iot.exception.ApplicationException;
import com.cloud.iot.models.EquipmentVO;

/**
 * @author krishav
 *
 */
public interface EquipmentService {

	public List<EquipmentVO> getEquipments(int limit) throws IOException;
	public ResponseEntity<EquipmentVO> searchByUniqueId(int uniqueReferenceNumber);
	public ResponseEntity<?> createEquipment(EquipmentVO equipment) throws ParseException, ApplicationException;
	
	
	/** Default method to convert entity to DTO
	 * @param entity
	 * @return
	 */
	
	default EquipmentVO convertToVO(Equipment entity)
	{
		EquipmentVO equipmentVO = null;
		if (entity != null) {
			equipmentVO = new EquipmentVO();
			equipmentVO.setAddress(entity.getAddress());
			equipmentVO.setContractEndDate(SIMPLE_DATE_FORMATTER.format(entity.getContractEndDate()));
			equipmentVO.setContractStartDate(SIMPLE_DATE_FORMATTER.format(entity.getContractStartDate()));
			equipmentVO.setEquipmentNumber(entity.getEquipmentNumber());
			equipmentVO.setStatus(entity.getStatus());
		}
		return equipmentVO;
	}
	
	
	/** Default method to convert DTO to Entity
	 * @param entity
	 * @return
	 * @throws ParseException 
	 */
	
	default Equipment convertToEntity(EquipmentVO equipmentVO) throws ParseException
	{
		Equipment entity = null;
		if (equipmentVO != null) {
			entity = new Equipment();
			entity.set_id(String.valueOf(equipmentVO.getEquipmentNumber()));
			entity.setAddress(equipmentVO.getAddress());
			entity.setContractEndDate(SIMPLE_DATE_FORMATTER.parse(equipmentVO.getContractEndDate()));
			entity.setContractStartDate(SIMPLE_DATE_FORMATTER.parse(equipmentVO.getContractStartDate()));
			entity.setEquipmentNumber(equipmentVO.getEquipmentNumber());
			entity.setStatus(equipmentVO.getStatus());
		}
		return entity;
	}
	
}
