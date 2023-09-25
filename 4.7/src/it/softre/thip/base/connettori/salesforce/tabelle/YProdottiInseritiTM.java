/*
 * @(#)YProdottiInseritiTM.java
 */

/**
 * YProdottiInseritiTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 25/09/2023 at 16:43:51
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

public class YProdottiInseritiTM extends TableManager {

  
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
   * Attributo R_ARTICOLO
   */
  public static final String R_ARTICOLO = "R_ARTICOLO";

  /**
   * Attributo TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "YPRODOTTI_INSERITI";

  /**
   *
   */
  private static TableManager cInstance;

  /**
   * Attributo CLASS_NAME
   */
  private static final String CLASS_NAME = it.softre.thip.base.connettori.salesforce.tabelle.YProdottiInseriti.class.getName();

  
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
      cInstance = (TableManager)Factory.createObject(YProdottiInseritiTM.class);
    }
    return cInstance;
  }

  /**
   * YProdottiInseritiTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    CodeGen     Codice generato da CodeGenerator
   *
   */
  public YProdottiInseritiTM() throws SQLException {
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
    addAttribute("RArticolo", R_ARTICOLO);
    
    addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
    setKeys(ID_AZIENDA + "," + R_ARTICOLO);

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
    configure(ID_AZIENDA + ", " + R_ARTICOLO + ", " + STATO + ", " + R_UTENTE_CRZ
         + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG + ", " + TIMESTAMP_AGG);
  }

}

