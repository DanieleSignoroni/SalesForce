/*
 * @(#)YOrdiniInseritiTM.java
 */

/**
 * YOrdiniInseritiTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 26/09/2023 at 14:43:35
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 26/09/2023    Wizard     Codice generato da Wizard
 *
 */
package it.softre.thip.base.connettori.salesforce.tabelle;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class YOrdiniInseritiTM extends TableManager {


	/**
	 * Attributo ID_AZIENDA
	 */
	public static final String ID_AZIENDA = "ID_AZIENDA";

	/**
	 * Attributo STATO
	 */
	public static final String STATO = "STATO";

	/**
	 * Attributo R_UTENTE_CRZ
	 */
	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	/**
	 * Attributo TIMESTAMP_CRZ
	 */
	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	/**
	 * Attributo R_UTENTE_AGG
	 */
	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	/**
	 * Attributo TIMESTAMP_AGG
	 */
	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	/**
	 * Attributo R_ANNO_ORD
	 */
	public static final String R_ANNO_ORD = "R_ANNO_ORD";

	/**
	 * Attributo R_NUMERO_ORD
	 */
	public static final String R_NUMERO_ORD = "R_NUMERO_ORD";

	public static final String ID_SALES_FORCE = "ID_SALES_FORCE";

	/**
	 * Attributo TABLE_NAME
	 */
	public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "YORDINI_INSERITI";

	/**
	 *
	 */
	private static TableManager cInstance;

	/**
	 * Attributo CLASS_NAME
	 */
	private static final String CLASS_NAME = it.softre.thip.base.connettori.salesforce.tabelle.YOrdiniInseriti.class.getName();

	public static final String PROCESSATO = "PROCESSATO";


	/**
	 * getInstance
	 * @return TableManager
	 * @throws SQLException
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 26/09/2023    CodeGen     Codice generato da CodeGenerator
	 *
	 */
	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager)Factory.createObject(YOrdiniInseritiTM.class);
		}
		return cInstance;
	}

	/**
	 * YOrdiniInseritiTM
	 * @throws SQLException
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 26/09/2023    CodeGen     Codice generato da CodeGenerator
	 *
	 */
	public YOrdiniInseritiTM() throws SQLException {
		super();
	}

	/**
	 * initialize
	 * @throws SQLException
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 26/09/2023    CodeGen     Codice generato da CodeGenerator
	 *
	 */
	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	/**
	 * initializeRelation
	 * @throws SQLException
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 26/09/2023    Wizard     Codice generato da Wizard
	 *
	 */
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

