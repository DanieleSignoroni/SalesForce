/*
 * @(#)YProdottiInseritiPO.java
 */

/**
 * null
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
import java.sql.*;
import java.util.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YProdottiInseritiPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   * Attributo cInstance
   */
  private static YProdottiInseriti cInstance;

  /**
   * Attributo iArticolo
   */
  protected Proxy iArticolo = new Proxy(it.thera.thip.base.articolo.Articolo.class);

  
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
   * 25/09/2023    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (YProdottiInseriti)Factory.createObject(YProdottiInseriti.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   * elementWithKey
   * @param key
   * @param lockType
   * @return YProdottiInseriti
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static YProdottiInseriti elementWithKey(String key, int lockType) throws SQLException {
    return (YProdottiInseriti)PersistentObject.elementWithKey(YProdottiInseriti.class, key, lockType);
  }

  /**
   * YProdottiInseritiPO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public YProdottiInseritiPO() {
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param articolo
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setArticolo(Articolo articolo) {
    String idAzienda = getIdAzienda();
    if (articolo != null) {
      idAzienda = KeyHelper.getTokenObjectKey(articolo.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iArticolo.setObject(articolo);
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return Articolo
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public Articolo getArticolo() {
    return (Articolo)iArticolo.getObject();
  }

  /**
   * setArticoloKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setArticoloKey(String key) {
    iArticolo.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    setOnDB(false);
  }

  /**
   * getArticoloKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public String getArticoloKey() {
    return iArticolo.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    Wizard     Codice generato da Wizard
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
   * 25/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public String getIdAzienda() {
    String key = iAzienda.getKey();
    return key;
  }

  /**
   * Valorizza l'attributo. 
   * @param rArticolo
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setRArticolo(String rArticolo) {
    String key = iArticolo.getKey();
    iArticolo.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rArticolo));
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
   * 25/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public String getRArticolo() {
    String key = iArticolo.getKey();
    String objRArticolo = KeyHelper.getTokenObjectKey(key,2);
    return objRArticolo;
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    YProdottiInseritiPO yProdottiInseritiPO = (YProdottiInseritiPO)obj;
    iArticolo.setEqual(yProdottiInseritiPO.iArticolo);
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    Wizard     Codice generato da Wizard
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
   * 25/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public void setKey(String key) {
    setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
    setRArticolo(KeyHelper.getTokenObjectKey(key, 2));
  }

  /**
   * getKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    Wizard     Codice generato da Wizard
   *
   */
  public String getKey() {
    String idAzienda = getIdAzienda();
    String rArticolo = getRArticolo();
    Object[] keyParts = {idAzienda, rArticolo};
    return KeyHelper.buildObjectKey(keyParts);
  }

  /**
   * isDeletable
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    Wizard     Codice generato da Wizard
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
   * 25/09/2023    Wizard     Codice generato da Wizard
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
   * 25/09/2023    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return YProdottiInseritiTM.getInstance();
  }

  /**
   * setIdAziendaInternal
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 25/09/2023    Wizard     Codice generato da Wizard
   *
   */
  protected void setIdAziendaInternal(String idAzienda) {
    iAzienda.setKey(idAzienda);
        String key2 = iArticolo.getKey();
    iArticolo.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
  }

}

