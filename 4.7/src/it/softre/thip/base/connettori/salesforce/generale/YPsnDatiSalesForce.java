package it.softre.thip.base.connettori.salesforce.generale;

import java.sql.SQLException;

import com.thera.thermfw.common.*;

public class YPsnDatiSalesForce extends YPsnDatiSalesForcePO {

	public ErrorMessage checkDelete() {
		return null;
	}
	
	public static YPsnDatiSalesForce getCurrentPersDatiSalesForce(String idAzienda) {
		try {
			return (YPsnDatiSalesForce) YPsnDatiSalesForce.elementWithKey(YPsnDatiSalesForce.class, idAzienda, NO_LOCK);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

