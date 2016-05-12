package com.codependent.storyteller;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import rx.Observable;
import rx.schedulers.Schedulers;

@Service
public class StoryService {

	private static String[] stories = {
		"Once upon a time there lived...",
		"In an Italian habour, at the foot of the mountain, lives our friend",
		"A long time ago in a galaxy far, far away...",
		"It was a dark and stormy night, and our friend..."
	};

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Autowired
	//private EurekaClient discoveryClient;
	
	/*
	//private RestTemplate restTemplate = new RestTemplate();
	
	@HystrixCommand(fallbackMethod="imageServiceNotAvailable")
	public Map<String, String> getRandomImage(){
		InstanceInfo ii = discoveryClient.getNextServerFromEureka("IMAGES-MICROSERVICE", false);
		String homePageUrl = ii.getHomePageUrl();
		Map<String, String> imageInfo = restTemplate.exchange(homePageUrl+"/images?random=true&fields=url", HttpMethod.GET, null, new ParameterizedTypeReference<Map<String,String>>() {}, new Object[]{}).getBody();
		return imageInfo;
	}
	*/
	
	public Observable<String> getRandomStory(){
		return Observable.just(loadRandomStory())
				  .delay(500, TimeUnit.MILLISECONDS)
				  .subscribeOn(Schedulers.io());
	}
	
	private String loadRandomStory(){
		long random = Math.round(Math.random()*(stories.length-1));
		return stories[(int)random];
	}
	
	/*
	protected Map<String, String> imageServiceNotAvailable(){
		logger.warn("imageServiceNotAvailable()");
		Map<String, String> i = new HashMap<String, String>();
		i.put("imageUrl", "https://camo.githubusercontent.com/e871b5d002a9699e7a2d9fa0178af5c72f0743e0/68747470733a2f2f6e6574666c69782e6769746875622e636f6d2f487973747269782f696d616765732f687973747269782d6c6f676f2d7461676c696e652d3835302e706e67");
		return i;
	}*/
}
