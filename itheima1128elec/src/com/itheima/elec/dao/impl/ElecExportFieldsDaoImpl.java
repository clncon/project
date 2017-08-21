package com.itheima.elec.dao.impl;

import org.springframework.stereotype.Repository;

import com.itheima.elec.dao.IElecExportFieldsDao;
import com.itheima.elec.domain.ElecExportFields;


@Repository(IElecExportFieldsDao.SERVICE_NAME)
public class ElecExportFieldsDaoImpl  extends CommonDaoImpl<ElecExportFields> implements IElecExportFieldsDao {

}
