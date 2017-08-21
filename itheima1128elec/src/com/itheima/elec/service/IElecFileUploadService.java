package com.itheima.elec.service;

import java.util.List;

import com.itheima.elec.domain.ElecFileUpload;



public interface IElecFileUploadService {
	public static final String SERVICE_NAME = "com.itheima.elec.service.impl.ElecFileUploadServiceImpl";

	void saveFileUpload(ElecFileUpload elecFileUpload);

	List<ElecFileUpload> findFileUploadListByCondition(
			ElecFileUpload elecFileUpload);

	ElecFileUpload findFileByID(Integer fileID);

	List<ElecFileUpload> findFileUploadListByLuceneCondition(
			ElecFileUpload elecFileUpload);

	void deleteFileUploadByID(Integer seqId);


}
