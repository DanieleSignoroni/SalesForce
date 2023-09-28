package it.softre.thip.base.connettori.salesforce.tabelle;

import com.thera.thermfw.persist.*;

import java.sql.*;
import java.util.*;

import it.thera.thip.base.cliente.ClienteVendita;
import it.thera.thip.cs.*;

import com.thera.thermfw.common.*;

import it.thera.thip.base.azienda.Azienda;

import com.thera.thermfw.security.*;

public abstract class YClientiInseritiPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

	protected String iIdSalesForce;

	private static YClientiInseriti cInstance;

	protected Proxy iCliente = new Proxy(it.thera.thip.base.cliente.ClienteVendita.class);

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YClientiInseriti)Factory.createObject(YClientiInseriti.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YClientiInseriti elementWithKey(String key, int lockType) throws SQLException {
		return (YClientiInseriti)PersistentObject.elementWithKey(YClientiInseriti.class, key, lockType);
	}

	public YClientiInseritiPO() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setCliente(ClienteVendita cliente) {
		String idAzienda = getIdAzienda();
		if (cliente != null) {
			idAzienda = KeyHelper.getTokenObjectKey(cliente.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iCliente.setObject(cliente);
		setDirty();
		setOnDB(false);
	}

	public ClienteVendita getCliente() {
		return (ClienteVendita)iCliente.getObject();
	}

	public void setClienteKey(String key) {
		iCliente.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		setOnDB(false);
	}

	public String getClienteKey() {
		return iCliente.getKey();
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

	public void setRCliente(String rCliente) {
		String key = iCliente.getKey();
		iCliente.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rCliente));
		setDirty();
		setOnDB(false);
	}

	public String getRCliente() {
		String key = iCliente.getKey();
		String objRCliente = KeyHelper.getTokenObjectKey(key,2);
		return objRCliente;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YClientiInseritiPO yClientiInseritiPO = (YClientiInseritiPO)obj;
		iCliente.setEqual(yClientiInseritiPO.iCliente);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setRCliente(KeyHelper.getTokenObjectKey(key, 2));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		String rCliente = getRCliente();
		Object[] keyParts = {idAzienda, rCliente};
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YClientiInseritiTM.getInstance();
	}

	protected void setIdAziendaInternal(String idAzienda) {
		iAzienda.setKey(idAzienda);
		String key2 = iCliente.getKey();
		iCliente.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
	}

}

