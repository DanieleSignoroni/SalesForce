package it.softre.thip.base.connettori.salesforce.tabelle;

import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YProdottiInseritiPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static YProdottiInseriti cInstance;

	protected Proxy iArticolo = new Proxy(it.thera.thip.base.articolo.Articolo.class);

	protected String iIdSalesForce;

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YProdottiInseriti)Factory.createObject(YProdottiInseriti.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YProdottiInseriti elementWithKey(String key, int lockType) throws SQLException {
		return (YProdottiInseriti)PersistentObject.elementWithKey(YProdottiInseriti.class, key, lockType);
	}

	public YProdottiInseritiPO() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

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

	public Articolo getArticolo() {
		return (Articolo)iArticolo.getObject();
	}

	public void setArticoloKey(String key) {
		iArticolo.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		setOnDB(false);
	}

	public String getArticoloKey() {
		return iArticolo.getKey();
	}

	public String getIdSalesForce() {
		return iIdSalesForce;
	}

	public void setIdSalesForce(String iIdSalesForce) {
		this.iIdSalesForce = iIdSalesForce;
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

	public void setRArticolo(String rArticolo) {
		String key = iArticolo.getKey();
		iArticolo.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rArticolo));
		setDirty();
		setOnDB(false);
	}

	public String getRArticolo() {
		String key = iArticolo.getKey();
		String objRArticolo = KeyHelper.getTokenObjectKey(key,2);
		return objRArticolo;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YProdottiInseritiPO yProdottiInseritiPO = (YProdottiInseritiPO)obj;
		iArticolo.setEqual(yProdottiInseritiPO.iArticolo);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setRArticolo(KeyHelper.getTokenObjectKey(key, 2));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		String rArticolo = getRArticolo();
		Object[] keyParts = {idAzienda, rArticolo};
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YProdottiInseritiTM.getInstance();
	}

	protected void setIdAziendaInternal(String idAzienda) {
		iAzienda.setKey(idAzienda);
		String key2 = iArticolo.getKey();
		iArticolo.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
	}

}

