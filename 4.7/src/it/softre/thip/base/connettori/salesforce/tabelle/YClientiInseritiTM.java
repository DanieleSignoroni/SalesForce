/*
 * @(#)YClientiInseritiTM.java
 */

/**
 * YClientiInseritiTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 25/09/2023 at 17:04:47
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 25/09/2023    Wizard     Codice generato da Wizard
 *
 */
package it.softre.thip.base.connettori.salesforce.tabelle;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class YClientiInseritiTM extends TableManager {

  
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
   * Attributo R_CLIENTE
   */
  public static final String R_CLIENTE = "R_CLIENTE";
  
  public static final String ID_SALES_FORCE = "ID_SALES_FORCE";

  /**
   * Attributo TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "YCLIENTI_INSERITI";

  /**
   *
   */
  private static TableManager cInstance;

  /**
   * Attributo CLASS_NAME
   */
  private static final String CLASS_NAME = it.softre.thip.base.connettori.salesforce.tabelle.YClientiInseriti.class.getName();

  
  /**
   * getInstance
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    CodeGen     Codice generato da CodeGenerator
   *
   */
  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(YClientiInseritiTM.class);
    }
    return cInstance;
  }

  /**
   * YClientiInseritiTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    CodeGen     Codice generato da CodeGenerator
   *
   */
  public YClientiInseritiTM() throws SQLException {
    super();
  }

  /**
   * initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    CodeGen     Codice generato da CodeGenerator
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
   * 25/09/2023    Wizard     Codice generato da Wizard
   *
   */
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

  /**
   * init
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure();
  }

}

