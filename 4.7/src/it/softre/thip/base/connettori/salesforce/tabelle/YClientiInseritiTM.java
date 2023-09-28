package it.softre.thip.base.connettori.salesforce.tabelle;

import com.thera.thermfw.persist.*;

import java.sql.*;

import com.thera.thermfw.base.*;

import it.thera.thip.cs.*;

public class YClientiInseritiTM extends TableManager {

	public static final String ID_AZIENDA = "ID_AZIENDA";

	public static final String STATO = "STATO";

	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	public static final String R_CLIENTE = "R_CLIENTE";

	public static final String ID_SALES_FORCE = "ID_SALES_FORCE";

	public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "YCLIENTI_INSERITI";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.softre.thip.base.connettori.salesforce.tabelle.YClientiInseriti.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager)Factory.createObject(YClientiInseritiTM.class);
		}
		return cInstance;
	}

	public YClientiInseritiTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("IdAzienda", ID_AZIENDA);
		addAttribute("RCliente", R_CLIENTE);
		addAttribute("IdSalesForce", ID_SALES_FORCE);

		addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
		setKeys(ID_AZIENDA + "," + R_CLIENTE);

		setTimestampColumn("TIMESTAMP_AGG");
		((it.thera.thip.cs.DatiComuniEstesiTTM)getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure();
	}

}

