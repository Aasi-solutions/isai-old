//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.07.10 at 05:31:30 PM PDT 
//


package com.google.checkout.schema._2.impl;

public class RiskInformationNotificationImpl implements com.google.checkout.schema._2.RiskInformationNotification, com.sun.xml.bind.JAXBObject, com.google.checkout.schema._2.impl.runtime.UnmarshallableObject, com.google.checkout.schema._2.impl.runtime.XMLSerializable, com.google.checkout.schema._2.impl.runtime.ValidatableObject
{

    protected java.lang.String _SerialNumber;
    protected com.google.checkout.schema._2.RiskInformation _RiskInformation;
    protected java.lang.String _GoogleOrderNumber;
    protected java.util.Calendar _Timestamp;
    public final static java.lang.Class version = (com.google.checkout.schema._2.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.google.checkout.schema._2.RiskInformationNotification.class);
    }

    public java.lang.String getSerialNumber() {
        return _SerialNumber;
    }

    public void setSerialNumber(java.lang.String value) {
        _SerialNumber = value;
    }

    public com.google.checkout.schema._2.RiskInformation getRiskInformation() {
        return _RiskInformation;
    }

    public void setRiskInformation(com.google.checkout.schema._2.RiskInformation value) {
        _RiskInformation = value;
    }

    public java.lang.String getGoogleOrderNumber() {
        return _GoogleOrderNumber;
    }

    public void setGoogleOrderNumber(java.lang.String value) {
        _GoogleOrderNumber = value;
    }

    public java.util.Calendar getTimestamp() {
        return _Timestamp;
    }

    public void setTimestamp(java.util.Calendar value) {
        _Timestamp = value;
    }

    public com.google.checkout.schema._2.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.google.checkout.schema._2.impl.runtime.UnmarshallingContext context) {
        return new com.google.checkout.schema._2.impl.RiskInformationNotificationImpl.Unmarshaller(context);
    }

    public void serializeBody(com.google.checkout.schema._2.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        context.startElement("http://checkout.google.com/schema/2", "google-order-number");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _GoogleOrderNumber), "GoogleOrderNumber");
        } catch (java.lang.Exception e) {
            com.google.checkout.schema._2.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        context.startElement("http://checkout.google.com/schema/2", "risk-information");
        context.childAsURIs(((com.sun.xml.bind.JAXBObject) _RiskInformation), "RiskInformation");
        context.endNamespaceDecls();
        context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _RiskInformation), "RiskInformation");
        context.endAttributes();
        context.childAsBody(((com.sun.xml.bind.JAXBObject) _RiskInformation), "RiskInformation");
        context.endElement();
        context.startElement("http://checkout.google.com/schema/2", "timestamp");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(com.sun.msv.datatype.xsd.DateTimeType.theInstance.serializeJavaObject(((java.util.Calendar) _Timestamp), null), "Timestamp");
        } catch (java.lang.Exception e) {
            com.google.checkout.schema._2.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
    }

    public void serializeAttributes(com.google.checkout.schema._2.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        context.startAttribute("", "serial-number");
        try {
            context.text(((java.lang.String) _SerialNumber), "SerialNumber");
        } catch (java.lang.Exception e) {
            com.google.checkout.schema._2.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endAttribute();
    }

    public void serializeURIs(com.google.checkout.schema._2.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.google.checkout.schema._2.RiskInformationNotification.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsr\u0000!com.sun.msv.grammar.InterleaveExp\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsq\u0000~\u0000\u0006ppsr\u0000\'com.sun.msv.grammar.trex.Element"
+"Pattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameCl"
+"ass;xr\u0000\u001ecom.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUn"
+"declaredAttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003pp\u0000sq\u0000~\u0000\u0000ppsr\u0000\u001bc"
+"om.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/da"
+"tatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/St"
+"ringPair;xq\u0000~\u0000\u0003ppsr\u0000\"com.sun.msv.datatype.xsd.TokenType\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\ri"
+"sAlwaysValidxr\u0000*com.sun.msv.datatype.xsd.BuiltinAtomicType\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000"
+"\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fna"
+"mespaceUrit\u0000\u0012Ljava/lang/String;L\u0000\btypeNameq\u0000~\u0000\u0017L\u0000\nwhiteSpace"
+"t\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000 http:/"
+"/www.w3.org/2001/XMLSchemat\u0000\u0005tokensr\u00005com.sun.msv.datatype.x"
+"sd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.da"
+"tatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0001sr\u00000com.sun.msv."
+"grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003ppsr\u0000\u001bc"
+"om.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0017L\u0000\fnam"
+"espaceURIq\u0000~\u0000\u0017xpq\u0000~\u0000\u001bq\u0000~\u0000\u001asr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\nxq\u0000~\u0000\u0003sr\u0000\u0011java.lang.Boolean"
+"\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psq\u0000~\u0000\u000eppsr\u0000\"com.sun.msv.datatype.xsd."
+"QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0014q\u0000~\u0000\u001at\u0000\u0005QNameq\u0000~\u0000\u001eq\u0000~\u0000 sq\u0000~\u0000!q\u0000~\u0000,"
+"q\u0000~\u0000\u001asr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlo"
+"calNameq\u0000~\u0000\u0017L\u0000\fnamespaceURIq\u0000~\u0000\u0017xr\u0000\u001dcom.sun.msv.grammar.Name"
+"Class\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://www.w3.org/2001/XMLSchema"
+"-instancesr\u00000com.sun.msv.grammar.Expression$EpsilonExpressio"
+"n\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000\'\u0001q\u0000~\u00004sq\u0000~\u0000.t\u0000\u0013google-order-numbert\u0000"
+"#http://checkout.google.com/schema/2sq\u0000~\u0000\tpp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\tp"
+"p\u0000sq\u0000~\u0000#ppsr\u0000 com.sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000"
+"\u001ccom.sun.msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003q\u0000~"
+"\u0000(psq\u0000~\u0000%q\u0000~\u0000(psr\u00002com.sun.msv.grammar.Expression$AnyStringE"
+"xpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003q\u0000~\u00005q\u0000~\u0000Bsr\u0000 com.sun.msv.grammar."
+"AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000/q\u0000~\u00004sq\u0000~\u0000.t\u0000-com.google.checko"
+"ut.schema._2.RiskInformationt\u0000+http://java.sun.com/jaxb/xjc/"
+"dummy-elementssq\u0000~\u0000#ppsq\u0000~\u0000%q\u0000~\u0000(pq\u0000~\u0000)q\u0000~\u00000q\u0000~\u00004sq\u0000~\u0000.t\u0000\u0010ri"
+"sk-informationq\u0000~\u00008sq\u0000~\u0000\tpp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u000eppsr\u0000%com.sun.msv."
+"datatype.xsd.DateTimeType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000)com.sun.msv.datatype"
+".xsd.DateTimeBaseType\u0014W\u001a@3\u00a5\u00b4\u00e5\u0002\u0000\u0000xq\u0000~\u0000\u0014q\u0000~\u0000\u001at\u0000\bdateTimeq\u0000~\u0000\u001eq"
+"\u0000~\u0000 sq\u0000~\u0000!q\u0000~\u0000Rq\u0000~\u0000\u001asq\u0000~\u0000#ppsq\u0000~\u0000%q\u0000~\u0000(pq\u0000~\u0000)q\u0000~\u00000q\u0000~\u00004sq\u0000~\u0000"
+".t\u0000\ttimestampq\u0000~\u00008sq\u0000~\u0000%ppsq\u0000~\u0000\u000eppsq\u0000~\u0000\u0013q\u0000~\u0000\u001at\u0000\u0006stringsr\u00005co"
+"m.sun.msv.datatype.xsd.WhiteSpaceProcessor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002"
+"\u0000\u0000xq\u0000~\u0000\u001d\u0001q\u0000~\u0000 sq\u0000~\u0000!q\u0000~\u0000[q\u0000~\u0000\u001asq\u0000~\u0000.t\u0000\rserial-numbert\u0000\u0000sr\u0000\"c"
+"om.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lc"
+"om/sun/msv/grammar/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.m"
+"sv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rst"
+"reamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/grammar/ExpressionPool;x"
+"p\u0000\u0000\u0000\u000b\u0001pq\u0000~\u0000:q\u0000~\u0000\u0005q\u0000~\u0000Mq\u0000~\u0000$q\u0000~\u0000Hq\u0000~\u0000Tq\u0000~\u0000\u0007q\u0000~\u0000?q\u0000~\u0000\rq\u0000~\u0000\bq\u0000~"
+"\u0000<x"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.google.checkout.schema._2.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.google.checkout.schema._2.impl.runtime.UnmarshallingContext context) {
            super(context, "----------");
        }

        protected Unmarshaller(com.google.checkout.schema._2.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.google.checkout.schema._2.impl.RiskInformationNotificationImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  3 :
                        if (("timestamp" == ___local)&&("http://checkout.google.com/schema/2" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 6;
                            return ;
                        }
                        if (("risk-information" == ___local)&&("http://checkout.google.com/schema/2" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 8;
                            return ;
                        }
                        if (("google-order-number" == ___local)&&("http://checkout.google.com/schema/2" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 4;
                            return ;
                        }
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  0 :
                        attIdx = context.getAttribute("", "serial-number");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText1(v);
                            continue outer;
                        }
                        break;
                    case  8 :
                        if (("partial-cc-number" == ___local)&&("http://checkout.google.com/schema/2" == ___uri)) {
                            _RiskInformation = ((com.google.checkout.schema._2.impl.RiskInformationImpl) spawnChildFromEnterElement((com.google.checkout.schema._2.impl.RiskInformationImpl.class), 9, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("ip-address" == ___local)&&("http://checkout.google.com/schema/2" == ___uri)) {
                            _RiskInformation = ((com.google.checkout.schema._2.impl.RiskInformationImpl) spawnChildFromEnterElement((com.google.checkout.schema._2.impl.RiskInformationImpl.class), 9, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("eligible-for-protection" == ___local)&&("http://checkout.google.com/schema/2" == ___uri)) {
                            _RiskInformation = ((com.google.checkout.schema._2.impl.RiskInformationImpl) spawnChildFromEnterElement((com.google.checkout.schema._2.impl.RiskInformationImpl.class), 9, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("cvn-response" == ___local)&&("http://checkout.google.com/schema/2" == ___uri)) {
                            _RiskInformation = ((com.google.checkout.schema._2.impl.RiskInformationImpl) spawnChildFromEnterElement((com.google.checkout.schema._2.impl.RiskInformationImpl.class), 9, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("buyer-account-age" == ___local)&&("http://checkout.google.com/schema/2" == ___uri)) {
                            _RiskInformation = ((com.google.checkout.schema._2.impl.RiskInformationImpl) spawnChildFromEnterElement((com.google.checkout.schema._2.impl.RiskInformationImpl.class), 9, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("billing-address" == ___local)&&("http://checkout.google.com/schema/2" == ___uri)) {
                            _RiskInformation = ((com.google.checkout.schema._2.impl.RiskInformationImpl) spawnChildFromEnterElement((com.google.checkout.schema._2.impl.RiskInformationImpl.class), 9, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("avs-response" == ___local)&&("http://checkout.google.com/schema/2" == ___uri)) {
                            _RiskInformation = ((com.google.checkout.schema._2.impl.RiskInformationImpl) spawnChildFromEnterElement((com.google.checkout.schema._2.impl.RiskInformationImpl.class), 9, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        _RiskInformation = ((com.google.checkout.schema._2.impl.RiskInformationImpl) spawnChildFromEnterElement((com.google.checkout.schema._2.impl.RiskInformationImpl.class), 9, ___uri, ___local, ___qname, __atts));
                        return ;
                }
                super.enterElement(___uri, ___local, ___qname, __atts);
                break;
            }
        }

        private void eatText1(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _SerialNumber = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  3 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  5 :
                        if (("google-order-number" == ___local)&&("http://checkout.google.com/schema/2" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  0 :
                        attIdx = context.getAttribute("", "serial-number");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText1(v);
                            continue outer;
                        }
                        break;
                    case  8 :
                        _RiskInformation = ((com.google.checkout.schema._2.impl.RiskInformationImpl) spawnChildFromLeaveElement((com.google.checkout.schema._2.impl.RiskInformationImpl.class), 9, ___uri, ___local, ___qname));
                        return ;
                    case  7 :
                        if (("timestamp" == ___local)&&("http://checkout.google.com/schema/2" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  9 :
                        if (("risk-information" == ___local)&&("http://checkout.google.com/schema/2" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                }
                super.leaveElement(___uri, ___local, ___qname);
                break;
            }
        }

        public void enterAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  3 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  0 :
                        if (("serial-number" == ___local)&&("" == ___uri)) {
                            state = 1;
                            return ;
                        }
                        break;
                    case  8 :
                        _RiskInformation = ((com.google.checkout.schema._2.impl.RiskInformationImpl) spawnChildFromEnterAttribute((com.google.checkout.schema._2.impl.RiskInformationImpl.class), 9, ___uri, ___local, ___qname));
                        return ;
                }
                super.enterAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  3 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  0 :
                        attIdx = context.getAttribute("", "serial-number");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText1(v);
                            continue outer;
                        }
                        break;
                    case  2 :
                        if (("serial-number" == ___local)&&("" == ___uri)) {
                            state = 3;
                            return ;
                        }
                        break;
                    case  8 :
                        _RiskInformation = ((com.google.checkout.schema._2.impl.RiskInformationImpl) spawnChildFromLeaveAttribute((com.google.checkout.schema._2.impl.RiskInformationImpl.class), 9, ___uri, ___local, ___qname));
                        return ;
                }
                super.leaveAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void handleText(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                try {
                    switch (state) {
                        case  3 :
                            revertToParentFromText(value);
                            return ;
                        case  6 :
                            state = 7;
                            eatText2(value);
                            return ;
                        case  0 :
                            attIdx = context.getAttribute("", "serial-number");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 3;
                                eatText1(v);
                                continue outer;
                            }
                            break;
                        case  8 :
                            _RiskInformation = ((com.google.checkout.schema._2.impl.RiskInformationImpl) spawnChildFromText((com.google.checkout.schema._2.impl.RiskInformationImpl.class), 9, value));
                            return ;
                        case  4 :
                            state = 5;
                            eatText3(value);
                            return ;
                        case  1 :
                            state = 2;
                            eatText1(value);
                            return ;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Timestamp = ((java.util.Calendar) com.sun.msv.datatype.xsd.DateTimeType.theInstance.createJavaObject(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value), null));
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText3(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _GoogleOrderNumber = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}
