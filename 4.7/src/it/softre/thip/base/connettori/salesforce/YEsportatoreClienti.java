package it.softre.thip.base.connettori.salesforce;

import com.thera.thermfw.batch.BatchRunnable;

public class YEsportatoreClienti extends BatchRunnable{

	@Override
	protected boolean run() {
		return false;
	}

	@Override
	protected String getClassAdCollectionName() {
		return "YEsportatoreClienti";
	}

}
