package com.aem.umesh.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.umesh.service.PageNavigatorService;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;



@Component
@Service
public class PageNavigator implements PageNavigatorService
{
	
	private static Logger log = LoggerFactory.getLogger(PageNavigator.class);
	@Reference
	ResourceResolverFactory factory;
	
	ResourceResolver resolver = null;
	
	public List<String> readChildPag()
	{
		try {
		List<String > childPagePaths = new ArrayList<String>();
		resolver = factory.getAdministrativeResourceResolver(null);
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page rootPage = pageManager.getPage("/content/newagecoaching1/en");
		Iterator<Page> rootPageIterator = rootPage.listChildren();
		while(rootPageIterator.hasNext())
		{
			Page childPage = rootPageIterator.next();
			String pagePath = childPage.getPath();
			childPagePaths.add(pagePath);
		}
		log.info("page paths is "+childPagePaths);
		return childPagePaths;
		
		}catch(Exception e)
		{
			log.info("Some errors are there "+e.getMessage());
			return null;
		}
	}
	

}
