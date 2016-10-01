package net.yesiltas.sample.client.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ClientAdapterFactory {
	private static final Logger logger = LoggerFactory.getLogger(ClientAdapterFactory.class);
	
	@Autowired
	private Environment env;

	
	private ClientAdapterFactory(){
		super();
	}
	
	public ClientAdapter getAdapterForModule(String moduleName) {
		try {		
			ClientAdapter adapter = (ClientAdapter)Class.forName(env.getProperty("modules."+moduleName+".adapter.class")).newInstance();
			adapter.setModuleName(moduleName, env);
			return adapter;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
		
	}

}
