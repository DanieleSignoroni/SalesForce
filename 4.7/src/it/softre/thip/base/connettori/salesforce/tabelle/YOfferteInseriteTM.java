package it.softre.thip.base.connettori.salesforce.tabelle;

import com.thera.thermfw.persist.*;

import java.sql.*;

import com.thera.thermfw.base.*;

import it.thera.thip.cs.*;

public class YOfferteInseriteTM extends TableManager {

  public static final String ID_AZIENDA = "ID_AZIENDA";

  public static final String STATO = "STATO";

  public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

  public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

  public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

  public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

  public static final String R_ANNO_OFF = "R_ANNO_OFF";

  public static final String R_NUMERO_OFF = "R_NUMERO_OFF";
  
  public static final String ID_SALES_FORCE = "ID_SALES_FORCE";
  
  public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "YOFFERTE_INSERITE";

  private static TableManager cInstance;

  private static final String CLASS_NAME = it.softre.thip.base.connettori.salesforce.tabelle.YOfferteInserite.class.getName();

  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(YOfferteInseriteTM.class);
    }
    return cInstance;
  }

  public YOfferteInseriteTM() throws SQLException {
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
    addAttribute("IdAzienda", ID_AZIENDA);
    addAttribute("RAnnoOff", R_ANNO_OFF);
    addAttribute("RNumeroOff", R_NUMERO_OFF);
    
    addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
    setKeys(ID_AZIENDA + "," + R_ANNO_OFF + "," + R_NUMERO_OFF);

    setTimestampColumn("TIMESTAMP_AGG");
    ((it.thera.thip.cs.DatiComuniEstesiTTM)getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
  }

  private void init() throws SQLException {
    configure(ID_SALES_FORCE + ", " + ID_AZIENDA + ", " + R_ANNO_OFF + ", " + R_NUMERO_OFF
         + ", " + STATO + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG
         + ", " + TIMESTAMP_AGG);
  }

}

