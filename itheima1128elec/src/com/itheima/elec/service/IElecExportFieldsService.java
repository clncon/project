package com.itheima.elec.service;

import com.itheima.elec.domain.ElecExportFields;



public interface IElecExportFieldsService {
	public static final String SERVICE_NAME = "com.itheima.elec.service.impl.ElecExportFieldsServiceImpl";

	ElecExportFields findExportFieldsByID(String belongTo);

	void saveSetExportExcel(ElecExportFields elecExportFields);


}
