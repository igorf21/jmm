package bsm.prototype.database.latlon;

import java.util.Collection;
import java.util.Map;

import org.apache.camel.Exchange;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MessageTypeCheck {
	
	private static final Logger LOG = LoggerFactory.getLogger(MessageTypeCheck.class);
	
	public void checkPayloadType(Exchange exchange) throws Exception {	
        
		Map<String, Object> record = exchange.getIn().getBody(Map.class);
		
        LOG.info("Record id is: " + record.get("ID"));
		
		exchange.setProperty("MsgType", record.get("Object"));
		exchange.setProperty("RecordID", record.get("ID"));
	}
}
