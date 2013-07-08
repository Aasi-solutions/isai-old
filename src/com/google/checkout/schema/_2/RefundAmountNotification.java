//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.07.10 at 05:31:30 PM PDT 
//


package com.google.checkout.schema._2;


/**
 * Java content class for RefundAmountNotification complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/home/colinc/projects/apache-tomcat-5.5.12/apiv2.xsd line 435)
 * <p>
 * <pre>
 * &lt;complexType name="RefundAmountNotification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="google-order-number" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="latest-refund-amount" type="{http://checkout.google.com/schema/2}Money"/>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="total-refund-amount" type="{http://checkout.google.com/schema/2}Money"/>
 *       &lt;/all>
 *       &lt;attribute name="serial-number" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface RefundAmountNotification {


    /**
     * Gets the value of the serialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getSerialNumber();

    /**
     * Sets the value of the serialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setSerialNumber(java.lang.String value);

    /**
     * Gets the value of the latestRefundAmount property.
     * 
     * @return
     *     possible object is
     *     {@link com.google.checkout.schema._2.Money}
     */
    com.google.checkout.schema._2.Money getLatestRefundAmount();

    /**
     * Sets the value of the latestRefundAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.google.checkout.schema._2.Money}
     */
    void setLatestRefundAmount(com.google.checkout.schema._2.Money value);

    /**
     * Gets the value of the totalRefundAmount property.
     * 
     * @return
     *     possible object is
     *     {@link com.google.checkout.schema._2.Money}
     */
    com.google.checkout.schema._2.Money getTotalRefundAmount();

    /**
     * Sets the value of the totalRefundAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.google.checkout.schema._2.Money}
     */
    void setTotalRefundAmount(com.google.checkout.schema._2.Money value);

    /**
     * Gets the value of the googleOrderNumber property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getGoogleOrderNumber();

    /**
     * Sets the value of the googleOrderNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setGoogleOrderNumber(java.lang.String value);

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link java.util.Calendar}
     */
    java.util.Calendar getTimestamp();

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.util.Calendar}
     */
    void setTimestamp(java.util.Calendar value);

}
