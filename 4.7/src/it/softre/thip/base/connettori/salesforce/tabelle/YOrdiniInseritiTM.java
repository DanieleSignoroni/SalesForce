package it.softre.thip.base.connettori.salesforce.tabelle;

import com.thera.thermfw.persist.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class YOrdiniInseritiTM extends TableManager {

	public static final String ID_AZIENDA = "ID_AZIENDA";

	public static final String STATO = "STATO";

	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	public static final String R_ANNO_ORD = "R_ANNO_ORD";

	public static final String R_NUMERO_ORD = "R_NUMERO_ORD";

	public static final String ID_SALES_FORCE = "ID_SALES_FORCE";

	public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "YORDINI_INSERITI";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.softre.thip.base.connettori.salesforce.tabelle.YOrdiniInseriti.class.getName();

	public static final String PROCESSATO = "PROCESSATO";

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager)Factory.createObject(YOrdiniInseritiTM.class);
		}
		return cInstance;
	}

	public YOrdiniInseritiTM() throws SQLException {
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
		addAttribute("RAnnoOrd", R_ANNO_ORD);
		addAttribute("RNumeroOrd", R_NUMERO_ORD);
		addAttribute("IdSalesForce", ID_SALES_FORCE);
		addAttribute("Processato", PROCESSATO);
		addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
		setKeys(ID_AZIENDA + "," + R_ANNO_ORD + "," + R_NUMERO_ORD);

		setTimestampColumn("TIMESTAMP_AGG");
		((it.thera.thip.cs.DatiComuniEstesiTTM)getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure();
	}

}

