package com.cs.common.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpClientUtils {

	private final static Logger log =LogManager.getLogger(HttpClientUtils.class);
	private final static String HTTP="http";
	private final static String HTTPS="https";
	private static CloseableHttpClient httpClient;
	private static RequestConfig config;
	static{
		try {
		    SSLContextBuilder builder=new SSLContextBuilder();		
			builder.loadTrustMaterial(null, new TrustStrategy(){
				@Override
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {					
					return true;
				}				
			});			
			SSLConnectionSocketFactory sslsf =new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register(HTTP, new PlainConnectionSocketFactory())
                    .register(HTTPS, sslsf)
                    .build();
			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		    cm.setMaxTotal(200);
		    config=RequestConfig.custom().setConnectionRequestTimeout(2000)
		    		.setConnectTimeout(2000).setSocketTimeout(3000).build();
		    httpClient=HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(config)
		    		   .setConnectionManager(cm).setConnectionManagerShared(true).build();
		    
		} catch (Exception e) {			
			log.error("httpClient初始化出错,error:{}",e);
		}
	}
	public static String getRequest(String url){
		log.info("url:{}",url);
		HttpGet httpGet =new HttpGet(url);		
		String result=null;
		try(CloseableHttpResponse response=httpClient.execute(httpGet)){			
			HttpEntity entity=response.getEntity();
			if(entity!=null){
				result=EntityUtils.toString(entity);
			}		    
		} catch (IOException e){	
			log.error("url:{},get请求失败,error:{}",url,e);
		}	
		
		return result;
	}
	public static String getRequest(String url,Map<String,String> mapParam){
		String result=null;
		try {
			URIBuilder builder=new URIBuilder(url);
			mapParam.entrySet().forEach(t->{
				builder.setParameter(t.getKey(),t.getValue());
			});						
			result=getRequest(builder.build().toString());
		} catch (URISyntaxException e) {
			log.error("url:{}is error",url);
		}		
		return result;
	}
	public static String postRequest(String url){
		String result=null;
		HttpPost httpPost=new HttpPost(url);
		try(CloseableHttpResponse response=httpClient.execute(httpPost)){
			HttpEntity entity=response.getEntity();
			if(entity!=null){
				result=EntityUtils.toString(entity);
			}
		} catch (IOException e) {
			log.error("url:{},post请求失败,error:{}",url,e);
		}
		return result;
	}
}
