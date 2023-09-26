package it.softre.thip.base.connettori.salesforce;

import com.thera.thermfw.batch.BatchRunnable;

public class YEsportatoreOpportunita extends BatchRunnable{
	
	public String endpoint = "https://softresrl-dev-ed.develop.my.salesforce.com/services/data/v58.0/sobjects/";

	public String id = "Product2";

	public String apiPath = "00D7R000004wxNy!ASAAQNiFGQuty4a.9KM6XuWg.96y23vqI9iD4kOd0Q41lMrLN4ShGeIlMQ1xyvM2dJ8GsUF88RIzNwWpHgdQxYgBhoXP87L0";

	@Override
	protected boolean run() {
		return false;
	}

	@Override
	protected String getClassAdCollectionName() {
		return "YOpportunitaSF";
	}

}
