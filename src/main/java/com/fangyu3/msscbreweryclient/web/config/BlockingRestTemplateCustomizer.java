package com.fangyu3.msscbreweryclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.Closeable;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    @Value("${sfg.httpclient.maxTotal}")
    private int maxTotal;
    @Value("${sfg.httpclient.maxPerRoute}")
    private int maxPerRoute;
    @Value("${sfg.httpclient.connectionRequestTimeout}")
    private int connectionRequestTimeout;
    @Value("${sfg.httpclient.socketTimeout}")
    private int socketTimeout;

    public ClientHttpRequestFactory clientHttpRequestFactory() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(maxPerRoute);

        RequestConfig requestConfig = RequestConfig.custom()
                                .setConnectionRequestTimeout(connectionRequestTimeout)
                                .setSocketTimeout(socketTimeout)
                                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                                    .setConnectionManager(connectionManager)
                                    .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                                    .setDefaultRequestConfig(requestConfig)
                                    .build();

        return new HttpComponentsClientHttpRequestFactory();
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
