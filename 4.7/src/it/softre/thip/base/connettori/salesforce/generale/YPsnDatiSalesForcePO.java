/*
 * @(#)YPsnDatiSalesForcePO.java
 */

/**
 * null
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
import java.sql.*;
import java.util.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YPsnDatiSalesForcePO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   * Attributo cInstance
   */
  private static YPsnDatiSalesForce cInstance;

  /**
   * Attributo iInstanceUrl
   */
  protected String iInstanceUrl;

  /**
   * Attributo iToken
   */
  protected String iToken;

  
  /**
   * retrieveList
   * @param where
   * @param orderBy
   * @param optimistic
   * @return Vector
   * @throws SQLException
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (YPsnDatiSalesForce)Factory.createObject(YPsnDatiSalesForce.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   * elementWithKey
   * @param key
   * @param lockType
   * @return YPsnDatiSalesForce
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static YPsnDatiSalesForce elementWithKey(String key, int lockType) throws SQLException {
    return (YPsnDatiSalesForce)PersistentObject.elementWithKey(YPsnDatiSalesForce.class, key, lockType);
  }

  /**
   * YPsnDatiSalesForcePO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public YPsnDatiSalesForcePO() {
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param instanceUrl
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setInstanceUrl(String instanceUrl) {
    this.iInstanceUrl = instanceUrl;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public String getInstanceUrl() {
    return iInstanceUrl;
  }

  /**
   * Valorizza l'attributo. 
   * @param token
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setToken(String token) {
    this.iToken = token;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public String getToken() {
    return iToken;
  }

  /**
   * Valorizza l'attributo. 
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setIdAzienda(String idAzienda) {
    iAzienda.setKey(idAzienda);
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public String getIdAzienda() {
    String key = iAzienda.getKey();
    return key;
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public Vector checkAll(BaseComponentsCollection components) {
    Vector errors = new Vector();
    components.runAllChecks(errors);
    return errors;
  }

  /**
   * setKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setKey(String key) {
    setIdAzienda(key);
  }

  /**
   * getKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public String getKey() {
    return getIdAzienda();
  }

  /**
   * isDeletable
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public boolean isDeletable() {
    return checkDelete() == null;
  }

  /**
   * toString
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public String toString() {
    return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
  }

  /**
   * getTableManager
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 27/09/2023    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return YPsnDatiSalesForceTM.getInstance();
  }

}

