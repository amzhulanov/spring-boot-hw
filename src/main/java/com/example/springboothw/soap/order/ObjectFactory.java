//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.02.02 at 06:44:30 PM NOVT 
//


package com.example.springboothw.soap.order;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.springboothw.soap.order package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.springboothw.soap.order
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetOrdersListResponse }
     * 
     */
    public GetOrdersListResponse createGetOrdersListResponse() {
        return new GetOrdersListResponse();
    }

    /**
     * Create an instance of {@link OrdersList }
     * 
     */
    public OrdersList createOrdersList() {
        return new OrdersList();
    }

    /**
     * Create an instance of {@link GetOrdersListRequest }
     * 
     */
    public GetOrdersListRequest createGetOrdersListRequest() {
        return new GetOrdersListRequest();
    }

    /**
     * Create an instance of {@link OrderDto }
     * 
     */
    public OrderDto createOrderDto() {
        return new OrderDto();
    }

}
