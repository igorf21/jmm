package bsm.prototype.database.latlon;


import generated.Deliveries;
import generated.Delivery;
import generated.InventoryLevel;
import generated.Root;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataProcessor {
	
	private static final Logger LOG = LoggerFactory.getLogger(DataProcessor.class);

	public String setBodyToPayload(Exchange exchange) throws Exception {
		
		Map<String, Object> record = exchange.getIn().getBody(Map.class);
		
		exchange.setProperty("MsgType", record.get("Object"));
		exchange.setProperty("RecordID", record.get("ID"));
		//exchange.setProperty("RecordID", record.get("Payload"));
		String xmlPyload = (String)record.get("Payload");
		
		return xmlPyload;
	}
	
	

	public Map<String, Object> mapToDeliveriesTgt(@Body Root msgSource) throws Exception {	
		
		LOG.info("Check Unmarshalled Data Address: " + msgSource.getAddress());
		List<Delivery> deliveriesXML = msgSource.getDeliveries().getDelivery();
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		int siteId = msgSource.getSiteId();
		for (int i=0; i < deliveriesXML.size(); i++)
		{
			Delivery delivery = deliveriesXML.get(i);
			sqlParams.put("siteId",siteId);
			sqlParams.put("startDate", delivery.getStartDate());
			sqlParams.put("endDate", delivery.getEndDate());
			sqlParams.put("startHeight", delivery.getStartHeight());
			sqlParams.put("endHeight", delivery.getEndHeight());
			sqlParams.put("startTemp", delivery.getStartTemp());
			sqlParams.put("endTemp", delivery.getEndTemp());
			sqlParams.put("startWater", delivery.getStartWater());
			sqlParams.put("endWater", delivery.getEndWater());
			sqlParams.put("startVolume", delivery.getStartVolume());
			sqlParams.put("endVolume", delivery.getEndVolume());
			sqlParams.put("gallons", delivery.getGallons());
			sqlParams.put("inches", delivery.getInches());
			sqlParams.put("tankNumber", delivery.getTankNumber());
		}
		
		LOG.info("SiteID param: " + msgSource.getSiteId());

		
		return sqlParams;
		

	}
	
public Map<String, Object> mapToSiteIdParam(@Body Root msgSource) throws Exception {	
		
		LOG.info("Check Unmarshalled Data Address: " + msgSource.getAddress());
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		

		sqlParams.put("siteId", msgSource.getSiteId());
		


		
		LOG.info("SiteID param: " + msgSource.getSiteId());
		
		return sqlParams;
		

	}
}
