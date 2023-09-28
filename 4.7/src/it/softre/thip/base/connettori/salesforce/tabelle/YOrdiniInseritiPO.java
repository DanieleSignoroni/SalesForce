package it.softre.thip.base.connettori.salesforce.tabelle;

import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.vendite.ordineVE.OrdineVendita;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YOrdiniInseritiPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static YOrdiniInseriti cInstance;

	protected String iIdSalesForce;

	protected boolean iProcessato;

	public boolean isProcessato() {
		return iProcessato;
	}

	public void setProcessato(boolean iProcessato) {
		this.iProcessato = iProcessato;
	}

	protected Proxy iOrdinevendita = new Proxy(it.thera.thip.vendite.ordineVE.OrdineVendita.class);

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YOrdiniInseriti)Factory.createObject(YOrdiniInseriti.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public void setIdSalesForce(String idSalesForce) {
		this.iIdSalesForce = idSalesForce;
		setDirty();
	}

	public String getIdSalesForce() {
		return iIdSalesForce;
	}

	public static YOrdiniInseriti elementWithKey(String key, int lockType) throws SQLException {
		return (YOrdiniInseriti)PersistentObject.elementWithKey(YOrdiniInseriti.class, key, lockType);
	}

	public YOrdiniInseritiPO() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

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

	public OrdineVendita getOrdinevendita() {
		return (OrdineVendita)iOrdinevendita.getObject();
	}

	public void setOrdinevenditaKey(String key) {
		iOrdinevendita.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		setOnDB(false);
	}

	public String getOrdinevenditaKey() {
		return iOrdinevendita.getKey();
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

	public void setRAnnoOrd(String rAnnoOrd) {
		String key = iOrdinevendita.getKey();
		iOrdinevendita.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rAnnoOrd));
		setDirty();
		setOnDB(false);
	}

	public String getRAnnoOrd() {
		String key = iOrdinevendita.getKey();
		String objRAnnoOrd = KeyHelper.getTokenObjectKey(key,2);
		return objRAnnoOrd;

	}

	public void setRNumeroOrd(String rNumeroOrd) {
		String key = iOrdinevendita.getKey();
		iOrdinevendita.setKey(KeyHelper.replaceTokenObjectKey(key , 3, rNumeroOrd));
		setDirty();
		setOnDB(false);
	}

	public String getRNumeroOrd() {
		String key = iOrdinevendita.getKey();
		String objRNumeroOrd = KeyHelper.getTokenObjectKey(key,3);
		return objRNumeroOrd;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YOrdiniInseritiPO yOrdiniInseritiPO = (YOrdiniInseritiPO)obj;
		iOrdinevendita.setEqual(yOrdiniInseritiPO.iOrdinevendita);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setRAnnoOrd(KeyHelper.getTokenObjectKey(key, 2));
		setRNumeroOrd(KeyHelper.getTokenObjectKey(key, 3));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		String rAnnoOrd = getRAnnoOrd();
		String rNumeroOrd = getRNumeroOrd();
		Object[] keyParts = {idAzienda, rAnnoOrd, rNumeroOrd};
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YOrdiniInseritiTM.getInstance();
	}

	protected void setIdAziendaInternal(String idAzienda) {
		iAzienda.setKey(idAzienda);
		String key2 = iOrdinevendita.getKey();
		iOrdinevendita.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
	}

}

