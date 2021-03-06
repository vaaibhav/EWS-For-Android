/**************************************************************************
 * copyright file="PropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.innovaturelabs.xml.stream.XMLStreamException;

/***
 * Represents the definition of a folder or item property.
 * 
 * 
 */
public abstract class PropertyDefinition extends
ServiceObjectPropertyDefinition {

	/** The xml element name. */
	private String xmlElementName;

	/** The flags. */
	private EnumSet<PropertyDefinitionFlags> flags;

	/** The name. */
	private String name;

	/** The version. */
	private ExchangeVersion version;

	/***
	 * Initializes a new instance.
	 * 
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @param uri
	 *            The URI.
	 * @param version
	 *            The version.
	 */
	protected PropertyDefinition(String xmlElementName, String uri,
			ExchangeVersion version) {
		super(uri);
		this.xmlElementName = xmlElementName;
		this.flags = EnumSet.of(PropertyDefinitionFlags.None);
		this.version = version;
	}

	/***
	 * Initializes a new instance.
	 * 
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @param flags
	 *            The flags.
	 * @param version
	 *            The version.
	 */
	protected PropertyDefinition(String xmlElementName,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
		super();
		this.xmlElementName = xmlElementName;
		this.flags = flags;
		this.version = version;
	}

	/**
	 * * Initializes a new instance.
	 * 
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @param uri
	 *            The URI.
	 * @param flags
	 *            The flags.
	 * @param version
	 *            The version.
	 */
	protected PropertyDefinition(String xmlElementName, String uri,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
		this(xmlElementName, uri, version);
		this.flags = flags;
	}

	/***
	 * Determines whether the specified flag is set.
	 * 
	 * @param flag
	 *            The flag.
	 * @return true if the specified flag is set; otherwise, false.
	 */
	protected boolean hasFlag(PropertyDefinitionFlags flag) {
        return this.hasFlag(flag, null);
    }
	
	/***
	 * Determines whether the specified flag is set.
	 * 
	 * @param flag
	 *            The flag.
	 * @return true if the specified flag is set; otherwise, false.
	 */
	protected boolean hasFlag(PropertyDefinitionFlags flag,
			ExchangeVersion version) {
		return this.flags.contains(flag);
	}

	/***
	 * Registers associated internal properties.
	 * 
	 * @param properties
	 *            The list in which to add the associated properties.
	 */
	protected void registerAssociatedInternalProperties(
			List<PropertyDefinition> properties) {
	}

	/***
	 * Gets a list of associated internal properties.
	 * 
	 * @return A list of PropertyDefinition objects. This is a hack. It is here
	 *         (currently) solely to help the API register the MeetingTimeZone
	 *         property definition that is internal.
	 */
	protected List<PropertyDefinition> getAssociatedInternalProperties() {
		List<PropertyDefinition> properties = new 
		ArrayList<PropertyDefinition>();
		this.registerAssociatedInternalProperties(properties);
		return properties;
	}

	/***
	 * Gets the minimum Exchange version that supports this property.
	 * 
	 * @return The version.
	 */
	public ExchangeVersion getVersion() {
		return version;
	}

	/**
	 * * Gets a value indicating whether this property definition is for a
	 * nullable type.
	 * 
	 * @return always true
	 */
	protected boolean isNullable() {
		return true;
	}

	/**
	 * * Loads from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @param propertyBag
	 *            The property bag.
	 * @throws ServiceXmlDeserializationException
	 *             the service xml deserialization exception
	 * @throws XMLStreamException
	 *             the xML stream exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws ServiceObjectPropertyException
	 *             the service object property exception
	 * @throws ServiceVersionException
	 *             the service version exception
	 * @throws Exception
	 *             the exception
	 */
	protected abstract void loadPropertyValueFromXml(
			EwsServiceXmlReader reader, PropertyBag propertyBag)
			throws ServiceXmlDeserializationException, XMLStreamException,
			InstantiationException, IllegalAccessException,
			ServiceObjectPropertyException, ServiceVersionException, Exception;

	/**
	 * * Writes the property value to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @param propertyBag
	 *            The property bag.
	 * @param isUpdateOperation
	 *            Indicates whether the context is an update operation.
	 * @throws XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws ServiceValidationException
	 *             the service validation exception
	 * @throws Exception
	 *             the exception
	 */
	protected abstract void writePropertyValueToXml(EwsServiceXmlWriter writer,
			PropertyBag propertyBag, boolean isUpdateOperation)
			throws XMLStreamException, ServiceXmlSerializationException,
			ServiceLocalException, InstantiationException,
			IllegalAccessException, ServiceValidationException, Exception;

	/***
	 * Gets the name of the XML element.
	 * 
	 * @return The name of the XML element.
	 */
	protected String getXmlElement() {
		return this.xmlElementName;
	}

	/***
	 * Gets the name of the property.
	 * 
	 * @return Name of the property.
	 */
	public String getName() {

		if (null == this.name || this.name.isEmpty()) {
			ServiceObjectSchema.initializeSchemaPropertyNames();
		}
		return name;
	}

	/***
	 * Sets the name of the property.
	 * 
	 * @param name
	 *            name of the property
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/***
	 * Gets the property definition's printable name.
	 * 
	 * @return The property definition's printable name.
	 */
	@Override
	protected String getPrintableName() {
		return this.getName();
	}
}
