package it.softre.thip.base.connettori.salesforce.tabelle;

import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.partner.RubricaPrimrose;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import com.thera.thermfw.security.*;

public abstract class YReferentiInseritiPO extends PersistentObject implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static YReferentiInseriti cInstance;

	protected String iIdSalesForce;

	protected Proxy iRubrica = new Proxy(it.thera.thip.base.partner.RubricaPrimrose.class);

	protected DatiComuniEstesi iDatiComuniEstesi;

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YReferentiInseriti)Factory.createObject(YReferentiInseriti.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YReferentiInseriti elementWithKey(String key, int lockType) throws SQLException {
		return (YReferentiInseriti)PersistentObject.elementWithKey(YReferentiInseriti.class, key, lockType);
	}

	public YReferentiInseritiPO() {
		iDatiComuniEstesi = (DatiComuniEstesi) Factory.createObject(DatiComuniEstesi.class);
		iDatiComuniEstesi.setOwner(this);

	}

	public void setIdSalesForce(String idSalesForce) {
		this.iIdSalesForce = idSalesForce;
		setDirty();
	}

	public String getIdSalesForce() {
		return iIdSalesForce;
	}

	public void setRubrica(RubricaPrimrose rubrica) {
		this.iRubrica.setObject(rubrica);
		setDirty();
		setOnDB(false);
	}

	public RubricaPrimrose getRubrica() {
		return (RubricaPrimrose)iRubrica.getObject();
	}

	public void setRubricaKey(String key) {
		iRubrica.setKey(key);
		setDirty();
		setOnDB(false);
	}

	public String getRubricaKey() {
		return iRubrica.getKey();
	}

	public void setRAnagrafico(java.math.BigDecimal rAnagrafico) {
		String key = iRubrica.getKey();
		iRubrica.setKey(KeyHelper.replaceTokenObjectKey(key , 1, rAnagrafico));
		setDirty();
		setOnDB(false);
	}

	public java.math.BigDecimal getRAnagrafico() {
		String key = iRubrica.getKey();
		String objRAnagrafico = KeyHelper.getTokenObjectKey(key,1);
		return KeyHelper.stringToBigDecimal(objRAnagrafico);

	}

	public void setRSequenzaRub(java.math.BigDecimal rSequenzaRub) {
		String key = iRubrica.getKey();
		iRubrica.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rSequenzaRub));
		setDirty();
		setOnDB(false);
	}

	public java.math.BigDecimal getRSequenzaRub() {
		String key = iRubrica.getKey();
		String objRSequenzaRub = KeyHelper.getTokenObjectKey(key,2);
		return KeyHelper.stringToBigDecimal(objRSequenzaRub);
	}

	public DatiComuniEstesi getDatiComuniEstesi() {
		return iDatiComuniEstesi;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YReferentiInseritiPO yReferentiInseritiPO = (YReferentiInseritiPO)obj;
		iRubrica.setEqual(yReferentiInseritiPO.iRubrica);
		iDatiComuniEstesi.setEqual(yReferentiInseritiPO.iDatiComuniEstesi);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setRAnagrafico(KeyHelper.stringToBigDecimal(KeyHelper.getTokenObjectKey(key, 1)));
		setRSequenzaRub(KeyHelper.stringToBigDecimal(KeyHelper.getTokenObjectKey(key, 2)));
	}

	public String getKey() {
		java.math.BigDecimal rAnagrafico = getRAnagrafico();
		java.math.BigDecimal rSequenzaRub = getRSequenzaRub();
		Object[] keyParts = {rAnagrafico, rSequenzaRub};
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YReferentiInseritiTM.getInstance();
	}

}

