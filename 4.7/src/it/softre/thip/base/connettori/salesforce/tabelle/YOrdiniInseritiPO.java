/*
 * @(#)YOrdiniInseritiPO.java
 */

/**
 * null
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
import java.sql.*;
import java.util.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import it.thera.thip.vendite.ordineVE.OrdineVendita;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YOrdiniInseritiPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {


	/**
	 * Attributo cInstance
	 */
	private static YOrdiniInseriti cInstance;
	protected String iIdSalesForce;
	protected boolean iProcessato;


	public boolean isProcessato() {
		return iProcessato;
	}

	public void setProcessato(boolean iProcessato) {
		this.iProcessato = iProcessato;
	}

	/**
	 * Attributo iOrdinevendita
	 */
	protected Proxy iOrdinevendita = new Proxy(it.thera.thip.vendite.ordineVE.OrdineVendita.class);


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
			cInstance = (YOrdiniInseriti)Factory.createObject(YOrdiniInseriti.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

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
	 * elementWithKey
	 * @param key
	 * @param lockType
	 * @return YOrdiniInseriti
	 * @throws SQLException
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 26/09/2023    CodeGen     Codice generato da CodeGenerator
	 *
	 */
	public static YOrdiniInseriti elementWithKey(String key, int lockType) throws SQLException {
		return (YOrdiniInseriti)PersistentObject.elementWithKey(YOrdiniInseriti.class, key, lockType);
	}

	/**
	 * YOrdiniInseritiPO
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 26/09/2023    Wizard     Codice generato da Wizard
	 *
	 */
	public YOrdiniInseritiPO() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	/**
	 * Valorizza l'attributo. 
	 * @param ordinevendita
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 26/09/2023    Wizard     Codice generato da Wizard
	 *
	 */
	public void setOrdinevendita(OrdineVendita ordinevendita) {
		String idAzienda = getIdAzienda();
		if (ordinevendita != null) {
			idAzienda = KeyHelper.getTokenObjectKey(ordinevendita.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iOrdinevendita.setObject(ordinevendita);
		setDirty();
		setOnDB(false);
	}

	/**
	 * Restituisce l'attributo. 
	 * @return OrdineVendita
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 26/09/2023    Wizard     Codice generato da Wizard
	 *
	 */
	public OrdineVendita getOrdinevendita() {
		return (OrdineVendita)iOrdinevendita.getObject();
	}

	/**
	 * setOrdinevenditaKey
	 * @param key
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 26/09/2023    Wizard     Codice generato da Wizard
	 *
	 */
	public void setOrdinevenditaKey(String key) {
		iOrdinevendita.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		setOnDB(false);
	}

	/**
	 * getOrdinevenditaKey
	 * @return String
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 26/09/2023    Wizard     Codice generato da Wizard
	 *
	 */
	public String getOrdinevenditaKey() {
		return iOrdinevendita.getKey();
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
	 * @param rAnnoOrd
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 26/09/2023    Wizard     Codice generato da Wizard
	 *
	 */
	public void setRAnnoOrd(String rAnnoOrd) {
		String key = iOrdinevendita.getKey();
		iOrdinevendita.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rAnnoOrd));
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
	public String getRAnnoOrd() {
		String key = iOrdinevendita.getKey();
		String objRAnnoOrd = KeyHelper.getTokenObjectKey(key,2);
		return objRAnnoOrd;

	}

	/**
	 * Valorizza l'attributo. 
	 * @param rNumeroOrd
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 26/09/2023    Wizard     Codice generato da Wizard
	 *
	 */
	public void setRNumeroOrd(String rNumeroOrd) {
		String key = iOrdinevendita.getKey();
		iOrdinevendita.setKey(KeyHelper.replaceTokenObjectKey(key , 3, rNumeroOrd));
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
	public String getRNumeroOrd() {
		String key = iOrdinevendita.getKey();
		String objRNumeroOrd = KeyHelper.getTokenObjectKey(key,3);
		return objRNumeroOrd;
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
		YOrdiniInseritiPO yOrdiniInseritiPO = (YOrdiniInseritiPO)obj;
		iOrdinevendita.setEqual(yOrdiniInseritiPO.iOrdinevendita);
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
		setRAnnoOrd(KeyHelper.getTokenObjectKey(key, 2));
		setRNumeroOrd(KeyHelper.getTokenObjectKey(key, 3));
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
		String rAnnoOrd = getRAnnoOrd();
		String rNumeroOrd = getRNumeroOrd();
		Object[] keyParts = {idAzienda, rAnnoOrd, rNumeroOrd};
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
		return YOrdiniInseritiTM.getInstance();
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
		String key2 = iOrdinevendita.getKey();
		iOrdinevendita.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
	}

}

