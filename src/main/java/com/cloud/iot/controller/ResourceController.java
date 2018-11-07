package com.cloud.iot.controller;

import static com.cloud.iot.constants.Constants.EQUIPMENT;
import static com.cloud.iot.constants.Constants.HEALTH;
import static com.cloud.iot.constants.Constants.SEARCH;
import static com.cloud.iot.constants.Constants.SEARCH_BY_UNIQUE_ID;
import static com.cloud.iot.constants.Constants.SUCCESS;
import static com.cloud.iot.constants.Constants.VERSION;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.iot.exception.ApplicationException;
import com.cloud.iot.models.EquipmentVO;
import com.cloud.iot.service.EquipmentService;
import com.cloudant.client.api.CloudantClient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller class
 * 
 * @author Vivek
 * 
 */
@RestController
@RequestMapping(VERSION)
@Api(value = "Manage Equipments", description = "API's listed for KONE equipment details")
public class ResourceController {

	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private CloudantClient client;
	
	
	@GetMapping("/cloudant")
	String test()
	{
		List<String> dbs = client.getAllDbs(); 
		return "Success" + dbs.toString();
	}

	/**
	 * Health check API
	 * 
	 * @return
	 */

	@GetMapping(HEALTH)
	@ApiOperation(value = "Health check API", response = String.class)
	public String healthCheck() {
		logger.info("Health check invoked");
		return SUCCESS;
	}

	/**
	 * Controller method to post single equipment into IoT
	 * 
	 * @param EquipmentVO
	 * @return
	 * @throws ParseException 
	 * @throws ApplicationException 
	 */

	@PostMapping(EQUIPMENT)
	@ApiOperation(value = "Post single equipment", response = ResponseEntity.class)
	public ResponseEntity<?> createEquipment(@RequestBody EquipmentVO equipment) throws ParseException, ApplicationException {
		logger.info("Post single equipment invoked from controller");
		return equipmentService.createEquipment(equipment);
	}

	/**
	 * Controller method to get equipment details based on given equipmentNumber
	 * 
	 * @param String limit
	 * @return ResponseEntity
	 * @throws ApplicationException
	 */

	@GetMapping(SEARCH_BY_UNIQUE_ID)
	@ApiOperation(value = "Get single equipment details by searching equipment number", response = ResponseEntity.class)
	public ResponseEntity<?> searchEquipments(@PathVariable(value = "id") int id) {
		logger.debug("Search equipments based on unique reference number API invoked from controller");
		return equipmentService.searchByUniqueId(id);
	}

	/**
	 * Controller method to get equipments list based on limit
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 */

	@GetMapping(SEARCH)
	@ApiOperation(value = "Search equipments based on limit", response = ResponseEntity.class)
	public ResponseEntity<?> searchEquipmentsByLimits(@RequestParam(value = "limit", required = true) int limit) throws IOException {
		logger.debug("Search equipments based on limit API invoked from controller");
		List<EquipmentVO> equipmentDetails = equipmentService.getEquipments(limit);
		return new ResponseEntity<>(equipmentDetails, HttpStatus.OK);
	}

}