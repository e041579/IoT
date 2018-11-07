/**
 * 
 */
package com.cloud.iot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Vivek
 *
 */

public class EquipmentVO {
	
	@JsonIgnore
	private int id;
	@ApiModelProperty(notes = "Unique equipement identifier number")
	private int equipmentNumber;
	@ApiModelProperty(notes = "Address")
	private String address;
	@ApiModelProperty(notes = "Contract start date, Sample date format: yyyy-MM-dd'T'HH:mm:ss")
	private String contractStartDate;
	@ApiModelProperty(notes = "Contract end date, Sample date format: yyyy-MM-dd'T'HH:mm:ss")
	private String contractEndDate;
	@ApiModelProperty(notes = "Equipment status, either Running or stopped")
	private String status;
	
	
	/**
	 * @return the equipmentNumber
	 */
	public int getEquipmentNumber() {
		return equipmentNumber;
	}
	/**
	 * @param equipmentNumber the equipmentNumber to set
	 */
	public void setEquipmentNumber(int equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the contractStartDate
	 */
	public String getContractStartDate() {
		return contractStartDate;
	}
	/**
	 * @param contractStartDate the contractStartDate to set
	 */
	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	/**
	 * @return the contractEndDate
	 */
	public String getContractEndDate() {
		return contractEndDate;
	}
	/**
	 * @param contractEndDate the contractEndDate to set
	 */
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
}
