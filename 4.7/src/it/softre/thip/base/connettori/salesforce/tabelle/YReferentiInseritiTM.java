/*
 * @(#)YReferentiInseritiTM.java
 */

/**
 * YReferentiInseritiTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 26/09/2023 at 12:33:35
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

public class YReferentiInseritiTM extends TableManager {

  
  /**
   * Attributo R_ANAGRAFICO
   */
  public static final String R_ANAGRAFICO = "R_ANAGRAFICO";

  /**
   * Attributo R_SEQUENZA_RUB
   */
  public static final String R_SEQUENZA_RUB = "R_SEQUENZA_RUB";

  /**
   * Attributo ID_SALES_FORCE
   */
  public static final String ID_SALES_FORCE = "ID_SALES_FORCE";

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
   * Attributo TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "YREFERENTI_INSERITI";

  /**
   *
   */
  private static TableManager cInstance;

  /**
   * Attributo CLASS_NAME
   */
  private static final String CLASS_NAME = it.softre.thip.base.connettori.salesforce.tabelle.YReferentiInseriti.class.getName();

  
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
      cInstance = (TableManager)Factory.createObject(YReferentiInseritiTM.class);
    }
    return cInstance;
  }

  /**
   * YReferentiInseritiTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    CodeGen     Codice generato da CodeGenerator
   *
   */
  public YReferentiInseritiTM() throws SQLException {
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
    addAttribute("IdSalesForce", ID_SALES_FORCE);
    addAttribute("RAnagrafico", R_ANAGRAFICO);
    addAttribute("RSequenzaRub", R_SEQUENZA_RUB);
    
    addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
    setKeys(R_ANAGRAFICO + "," + R_SEQUENZA_RUB);

    setTimestampColumn("TIMESTAMP_AGG");
    ((it.thera.thip.cs.DatiComuniEstesiTTM)getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
  }

  /**
   * init
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure(ID_SALES_FORCE + ", " + R_ANAGRAFICO + ", " + R_SEQUENZA_RUB + ", " + STATO
         + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG + ", " + TIMESTAMP_AGG
        );
  }

}

