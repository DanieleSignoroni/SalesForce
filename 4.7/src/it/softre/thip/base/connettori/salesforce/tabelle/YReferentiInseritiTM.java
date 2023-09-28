package it.softre.thip.base.connettori.salesforce.tabelle;

import com.thera.thermfw.persist.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class YReferentiInseritiTM extends TableManager {

	public static final String R_ANAGRAFICO = "R_ANAGRAFICO";

	public static final String R_SEQUENZA_RUB = "R_SEQUENZA_RUB";

	public static final String ID_SALES_FORCE = "ID_SALES_FORCE";

	public static final String STATO = "STATO";

	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "YREFERENTI_INSERITI";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.softre.thip.base.connettori.salesforce.tabelle.YReferentiInseriti.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager)Factory.createObject(YReferentiInseritiTM.class);
		}
		return cInstance;
	}

	public YReferentiInseritiTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("IdSalesForce", ID_SALES_FORCE);
		addAttribute("RAnagrafico", R_ANAGRAFICO);
		addAttribute("RSequenzaRub", R_SEQUENZA_RUB);

		addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
		setKeys(R_ANAGRAFICO + "," + R_SEQUENZA_RUB);

		setTimestampColumn("TIMESTAMP_AGG");
		((it.thera.thip.cs.DatiComuniEstesiTTM)getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure(ID_SALES_FORCE + ", " + R_ANAGRAFICO + ", " + R_SEQUENZA_RUB + ", " + STATO
				+ ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG + ", " + TIMESTAMP_AGG
				);
	}

}

