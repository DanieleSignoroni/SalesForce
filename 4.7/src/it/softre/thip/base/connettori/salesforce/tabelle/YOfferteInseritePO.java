package it.softre.thip.base.connettori.salesforce.tabelle;

import com.thera.thermfw.persist.*;

import java.sql.*;
import java.util.*;

import it.thera.thip.vendite.offerteCliente.OffertaCliente;
import it.thera.thip.cs.*;

import com.thera.thermfw.common.*;

import it.thera.thip.base.azienda.Azienda;

import com.thera.thermfw.security.*;

public abstract class YOfferteInseritePO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static YOfferteInserite cInstance;

	protected String iIdSalesForce;

	protected Proxy iOffertacliente = new Proxy(it.thera.thip.vendite.offerteCliente.OffertaCliente.class);

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YOfferteInserite)Factory.createObject(YOfferteInserite.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YOfferteInserite elementWithKey(String key, int lockType) throws SQLException {
		return (YOfferteInserite)PersistentObject.elementWithKey(YOfferteInserite.class, key, lockType);
	}

	public YOfferteInseritePO() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setIdSalesForce(String idSalesForce) {
		this.iIdSalesForce = idSalesForce;
		setDirty();
	}

	public String getIdSalesForce() {
		return iIdSalesForce;
	}

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

	public OffertaCliente getOffertacliente() {
		return (OffertaCliente)iOffertacliente.getObject();
	}

	public void setOffertaclienteKey(String key) {
		iOffertacliente.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		setOnDB(false);
	}

	public String getOffertaclienteKey() {
		return iOffertacliente.getKey();
	}

	public void setIdAzienda(String idAzienda) {
		setIdAziendaInternal(idAzienda);
		setDirty();
		setOnDB(false);
	}

	public String getIdAzienda() {
		String key = iAzienda.getKey();
		return key;
	}

	public void setRAnnoOff(String rAnnoOff) {
		String key = iOffertacliente.getKey();
		iOffertacliente.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rAnnoOff));
		setDirty();
		setOnDB(false);
	}

	public String getRAnnoOff() {
		String key = iOffertacliente.getKey();
		String objRAnnoOff = KeyHelper.getTokenObjectKey(key,2);
		return objRAnnoOff;

	}

	public void setRNumeroOff(String rNumeroOff) {
		String key = iOffertacliente.getKey();
		iOffertacliente.setKey(KeyHelper.replaceTokenObjectKey(key , 3, rNumeroOff));
		setDirty();
		setOnDB(false);
	}

	public String getRNumeroOff() {
		String key = iOffertacliente.getKey();
		String objRNumeroOff = KeyHelper.getTokenObjectKey(key,3);
		return objRNumeroOff;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YOfferteInseritePO yOfferteInseritePO = (YOfferteInseritePO)obj;
		iOffertacliente.setEqual(yOfferteInseritePO.iOffertacliente);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setRAnnoOff(KeyHelper.getTokenObjectKey(key, 2));
		setRNumeroOff(KeyHelper.getTokenObjectKey(key, 3));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		String rAnnoOff = getRAnnoOff();
		String rNumeroOff = getRNumeroOff();
		Object[] keyParts = {idAzienda, rAnnoOff, rNumeroOff};
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YOfferteInseriteTM.getInstance();
	}

	protected void setIdAziendaInternal(String idAzienda) {
		iAzienda.setKey(idAzienda);
		String key2 = iOffertacliente.getKey();
		iOffertacliente.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
	}

}

