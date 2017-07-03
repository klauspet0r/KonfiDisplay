package com.kp.core;

import microsoft.exchange.webservices.data.autodiscover.IAutodiscoverRedirectionUrl;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;

public class KonfiDisplay {

	public static void main(String[] args) 
	{
		ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
		ExchangeCredentials credentials = new WebCredentials("email@d.dy", "password");
		service.setCredentials(credentials);
		
		try 
		{
			service.autodiscoverUrl("email@d.dy");
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
		}
		
		try
		{
			service.autodiscoverUrl("email@d.dy", new RedirectionUrlCallback());
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
		}
		
		
		
		service.close();

	}
	
	static class RedirectionUrlCallback implements IAutodiscoverRedirectionUrl {
        public boolean autodiscoverRedirectionUrlValidationCallback(
                String redirectionUrl) {
            return redirectionUrl.toLowerCase().startsWith("https://");
        }
    }

}
