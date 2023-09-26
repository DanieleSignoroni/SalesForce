/*
 * @(#)YOfferteInseritePO.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 26/09/2023 at 14:41:13
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
import it.thera.thip.base.azienda.AziendaEstesa;
import it.thera.thip.vendite.offerteCliente.OffertaCliente;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YOfferteInseritePO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   * Attributo cInstance
   */
  private static YOfferteInserite cInstance;

  /**
   * Attributo iIdSalesForce
   */
  protected String iIdSalesForce;

  /**
   * Attributo iOffertacliente
   */
  protected Proxy iOffertacliente = new Proxy(it.thera.thip.vendite.offerteCliente.OffertaCliente.class);

  
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
      cInstance = (YOfferteInserite)Factory.createObject(YOfferteInserite.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   * elementWithKey
   * @param key
   * @param lockType
   * @return YOfferteInserite
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static YOfferteInserite elementWithKey(String key, int lockType) throws SQLException {
    return (YOfferteInserite)PersistentObject.elementWithKey(YOfferteInserite.class, key, lockType);
  }

  /**
   * YOfferteInseritePO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public YOfferteInseritePO() {
    setIdAzienda(Azienda.getAziendaCorrente());
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
   * @param offertacliente
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setOffertacliente(OffertaCliente offertacliente) {
    String idAzienda = getIdAzienda();
    if (offertacliente != null) {
      idAzienda = KeyHelper.getTokenObjectKey(offertacliente.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iOffertacliente.setObject(offertacliente);
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return OffertaCliente
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public OffertaCliente getOffertacliente() {
    return (OffertaCliente)iOffertacliente.getObject();
  }

  /**
   * setOffertaclienteKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setOffertaclienteKey(String key) {
    iOffertacliente.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    setOnDB(false);
  }

  /**
   * getOffertaclienteKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public String getOffertaclienteKey() {
    return iOffertacliente.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setIdAzienda(String idAzienda) {
    setIdAziendaInternal(idAzienda);
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
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public String getIdAzienda() {
    String key = iAzienda.getKey();
    return key;
  }

  /**
   * Valorizza l'attributo. 
   * @param rAnnoOff
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setRAnnoOff(String rAnnoOff) {
    String key = iOffertacliente.getKey();
    iOffertacliente.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rAnnoOff));
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
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public String getRAnnoOff() {
    String key = iOffertacliente.getKey();
    String objRAnnoOff = KeyHelper.getTokenObjectKey(key,2);
    return objRAnnoOff;
    
  }

  /**
   * Valorizza l'attributo. 
   * @param rNumeroOff
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setRNumeroOff(String rNumeroOff) {
    String key = iOffertacliente.getKey();
    iOffertacliente.setKey(KeyHelper.replaceTokenObjectKey(key , 3, rNumeroOff));
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
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public String getRNumeroOff() {
    String key = iOffertacliente.getKey();
    String objRNumeroOff = KeyHelper.getTokenObjectKey(key,3);
    return objRNumeroOff;
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
    YOfferteInseritePO yOfferteInseritePO = (YOfferteInseritePO)obj;
    iOffertacliente.setEqual(yOfferteInseritePO.iOffertacliente);
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
    setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
    setRAnnoOff(KeyHelper.getTokenObjectKey(key, 2));
    setRNumeroOff(KeyHelper.getTokenObjectKey(key, 3));
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
    String idAzienda = getIdAzienda();
    String rAnnoOff = getRAnnoOff();
    String rNumeroOff = getRNumeroOff();
    Object[] keyParts = {idAzienda, rAnnoOff, rNumeroOff};
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
    return YOfferteInseriteTM.getInstance();
  }

  /**
   * setIdAziendaInternal
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/09/2023    Wizard     Codice generato da Wizard
   *
   */
  protected void setIdAziendaInternal(String idAzienda) {
    iAzienda.setKey(idAzienda);
        String key2 = iOffertacliente.getKey();
    iOffertacliente.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
  }

}

