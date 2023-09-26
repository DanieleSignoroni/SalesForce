package it.softre.thip.base.connettori.salesforce.tabelle;

import java.sql.*;

import com.thera.thermfw.common.*;

public class YReferentiInseriti extends YReferentiInseritiPO {

	@Override
	public void setTimestamp(Timestamp t) throws SQLException {
		return;
	}

	public ErrorMessage checkDelete() {
		return null;
	}

}

