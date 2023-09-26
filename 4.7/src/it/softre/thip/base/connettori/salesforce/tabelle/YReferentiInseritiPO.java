/*
 * @(#)YReferentiInseritiPO.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 26/09/2023 at 12:33:36
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 26/09/2023    Wizard     Codice generato da Wizard
 *
 */
package it.softre.thip.base.connettori.salesforce.tabelle;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.partner.RubricaPrimrose;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import com.thera.thermfw.security.*;

public abstract class YReferentiInseritiPO extends PersistentObject implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   * Attributo cInstance
   */
  private static YReferentiInseriti cInstance;

  /**
   * Attributo iIdSalesForce
   */
  protected String iIdSalesForce;

  /**
   * Attributo iRubrica
   */
  protected Proxy iRubrica = new Proxy(it.thera.thip.base.partner.RubricaPrimrose.class);

  /**
   * Attributo iDatiComuniEstesi
   */
  protected DatiComuniEstesi iDatiComuniEstesi;

  
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
   * 26/09/2023    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (YReferentiInseriti)Factory.createObject(YReferentiInseriti.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   * elementWithKey
   * @param key
   * @param lockType
   * @return YReferentiInseriti
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static YReferentiInseriti elementWithKey(String key, int lockType) throws SQLException {
    return (YReferentiInseriti)PersistentObject.elementWithKey(YReferentiInseriti.class, key, lockType);
  }

  /**
   * YReferentiInseritiPO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public YReferentiInseritiPO() {
    iDatiComuniEstesi = (DatiComuniEstesi) Factory.createObject(DatiComuniEstesi.class);
    iDatiComuniEstesi.setOwner(this);
    
  }

  /**
   * Valorizza l'attributo. 
   * @param idSalesForce
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setIdSalesForce(String idSalesForce) {
    this.iIdSalesForce = idSalesForce;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public String getIdSalesForce() {
    return iIdSalesForce;
  }

  /**
   * Valorizza l'attributo. 
   * @param rubrica
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setRubrica(RubricaPrimrose rubrica) {
    this.iRubrica.setObject(rubrica);
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return RubricaPrimrose
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public RubricaPrimrose getRubrica() {
    return (RubricaPrimrose)iRubrica.getObject();
  }

  /**
   * setRubricaKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setRubricaKey(String key) {
    iRubrica.setKey(key);
    setDirty();
    setOnDB(false);
  }

  /**
   * getRubricaKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public String getRubricaKey() {
    return iRubrica.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param rAnagrafico
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setRAnagrafico(java.math.BigDecimal rAnagrafico) {
    String key = iRubrica.getKey();
    iRubrica.setKey(KeyHelper.replaceTokenObjectKey(key , 1, rAnagrafico));
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return java.math.BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public java.math.BigDecimal getRAnagrafico() {
    String key = iRubrica.getKey();
    String objRAnagrafico = KeyHelper.getTokenObjectKey(key,1);
    return KeyHelper.stringToBigDecimal(objRAnagrafico);
    
  }

  /**
   * Valorizza l'attributo. 
   * @param rSequenzaRub
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setRSequenzaRub(java.math.BigDecimal rSequenzaRub) {
    String key = iRubrica.getKey();
    iRubrica.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rSequenzaRub));
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return java.math.BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public java.math.BigDecimal getRSequenzaRub() {
    String key = iRubrica.getKey();
    String objRSequenzaRub = KeyHelper.getTokenObjectKey(key,2);
    return KeyHelper.stringToBigDecimal(objRSequenzaRub);
  }

  /**
   * Restituisce l'attributo. 
   * @return DatiComuniEstesi
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public DatiComuniEstesi getDatiComuniEstesi() {
    return iDatiComuniEstesi;
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    YReferentiInseritiPO yReferentiInseritiPO = (YReferentiInseritiPO)obj;
    iRubrica.setEqual(yReferentiInseritiPO.iRubrica);
    iDatiComuniEstesi.setEqual(yReferentiInseritiPO.iDatiComuniEstesi);
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
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
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setKey(String key) {
    setRAnagrafico(KeyHelper.stringToBigDecimal(KeyHelper.getTokenObjectKey(key, 1)));
    setRSequenzaRub(KeyHelper.stringToBigDecimal(KeyHelper.getTokenObjectKey(key, 2)));
  }

  /**
   * getKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public String getKey() {
    java.math.BigDecimal rAnagrafico = getRAnagrafico();
    java.math.BigDecimal rSequenzaRub = getRSequenzaRub();
    Object[] keyParts = {rAnagrafico, rSequenzaRub};
    return KeyHelper.buildObjectKey(keyParts);
  }

  /**
   * isDeletable
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
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
   * 26/09/2023    Wizard     Codice generato da Wizard
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
   * 26/09/2023    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return YReferentiInseritiTM.getInstance();
  }

}

