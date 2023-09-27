/*
 * @(#)YPsnDatiSalesForceTM.java
 */

/**
 * YPsnDatiSalesForceTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 27/09/2023 at 14:28:46
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 27/09/2023    Wizard     Codice generato da Wizard
 *
 */
package it.softre.thip.base.connettori.salesforce.generale;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class YPsnDatiSalesForceTM extends TableManager {

  
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
   * Attributo INSTANCE_URL
   */
  public static final String INSTANCE_URL = "INSTANCE_URL";

  /**
   * Attributo TOKEN
   */
  public static final String TOKEN = "TOKEN";

  /**
   * Attributo TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "YPSN_DATI_SALES_FORCE";

  /**
   *
   */
  private static TableManager cInstance;

  /**
   * Attributo CLASS_NAME
   */
  private static final String CLASS_NAME = it.softre.thip.base.connettori.salesforce.generale.YPsnDatiSalesForce.class.getName();

  
  /**
   * getInstance
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    CodeGen     Codice generato da CodeGenerator
   *
   */
  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(YPsnDatiSalesForceTM.class);
    }
    return cInstance;
  }

  /**
   * YPsnDatiSalesForceTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    CodeGen     Codice generato da CodeGenerator
   *
   */
  public YPsnDatiSalesForceTM() throws SQLException {
    super();
  }

  /**
   * initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    CodeGen     Codice generato da CodeGenerator
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
   * 27/09/2023    Wizard     Codice generato da Wizard
   *
   */
  protected void initializeRelation() throws SQLException {
    super.initializeRelation();
    addAttribute("InstanceUrl", INSTANCE_URL);
    addAttribute("Token", TOKEN);
    addAttribute("IdAzienda", ID_AZIENDA);
    
    addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
    setKeys(ID_AZIENDA);

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
   * 27/09/2023    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure(INSTANCE_URL + ", " + TOKEN + ", " + ID_AZIENDA + ", " + STATO
         + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG + ", " + TIMESTAMP_AGG
        );
  }

}

