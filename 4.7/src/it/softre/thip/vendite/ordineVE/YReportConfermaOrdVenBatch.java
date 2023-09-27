package it.softre.thip.vendite.ordineVE;

import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.PersistentObject;

import it.softre.thip.base.connettori.salesforce.tabelle.YOrdiniInseriti;
import it.thera.thip.vendite.ordineVE.OrdineVendita;
import it.thera.thip.vendite.ordineVE.ReportConfermaOrdVenBatch;

public class YReportConfermaOrdVenBatch extends ReportConfermaOrdVenBatch{

	@Override
	protected void azioniPersOrdine(OrdineVendita ordine) {
		super.azioniPersOrdine(ordine);
		opportunitaSalesForce(ordine);
	}

	protected void opportunitaSalesForce(OrdineVendita ordine) {
		try {
			YOrdiniInseriti ordSalesForce = (YOrdiniInseriti) 
					YOrdiniInseriti.elementWithKey(
							YOrdiniInseriti.class,
							ordine.getKey(),
							PersistentObject.NO_LOCK
							);
			if(ordSalesForce != null) {
				ordSalesForce.save();
			}else {
				ordSalesForce = (YOrdiniInseriti) Factory.createObject(YOrdiniInseriti.class);
				ordSalesForce.setKey(ordine.getKey());
				ordSalesForce.setProcessato(false);
				ordSalesForce.setIdSalesForce("non def");
				ordSalesForce.save();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
