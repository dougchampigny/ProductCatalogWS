package com.pegaxchange.services;

import java.io.EOFException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Service;

//import com.pegaxchange.services.Exception_Exception;
import com.pegaxchange.services.Product;
import com.pegaxchange.services.ProductCatalogInterface;


public class ProductCatalogMain {

	public static void main(String[] args) throws EOFException, Throwable 
	{
		//Experimental to get SOAP Headers
		/*SOAPMessage msg = MessageFactory.newInstance().createMessage();
		
		SOAPEnvelope env = msg.getSOAPPart().getEnvelope(); 
        SOAPHeader header = env.getHeader();
        
        QName qname = new QName("http://services.pegaxchange.com/",
				"ProductCatalogServiceImplService");
        SOAPHeaderElement headerElement = header.addHeaderElement(qname);
        headerElement.addTextNode("34");
        
     // Create body of SOAP Message
        SOAPBody body = msg.getSOAPPart().getEnvelope().getBody();
        QName personQName = new QName("http://services.pegaxchange.com/", "ProductCatalogServiceImplService");
        
        SOAPBodyElement bodyElem =  body.addBodyElement(personQName);
        bodyElem.addChildElement("firstname").addTextNode("Ben");
        bodyElem.addChildElement("lastname").addTextNode("Kenobi");
        
     // Extract the header
        SOAPHeader header1 = msg.getSOAPHeader();
        Iterator<SOAPHeaderElement> headersIt = header1.examineAllHeaderElements();
        
        SOAPHeaderElement headerElement1 = headersIt.next();
        // Print the header's text content and other properties
        System.out.println("\nHeader Text Content is " + headerElement1.getTextContent());       
        System.out.println("Header value is " + headerElement1.getValue());
        System.out.println("Header local name is " + headerElement1.getLocalName());
        System.out.println("Header prefix is " + headerElement1.getPrefix());
        System.out.println("Header node name is " + headerElement1.getNodeName());
        System.out.println("Header namespace uri is " + headerElement1.getNamespaceURI()); */
        
		//FIRST WAY OF CREATING A WEB SERVICE CLIENT
		URL url = new URL("http://localhost:8080/JaxWsSecurityProductCatalogWS/services/hello?wsdl");
		
		//first parameter is the TargetName from WSDL, and the second param is the Server Name from WSDL
		QName qname = new QName("http://services.pegaxchange.com/",
				"ProductCatalogServiceImplService");
		
		Service service = Service.create(url, qname);
		
		//port is the webservice class where all the methods are provided by the service.
		ProductCatalogInterface server = service.getPort(ProductCatalogInterface.class);
		
		//getAllProducts returns a list of products, the server class is created as an instance of
		//the service class interface.
		Product[] productList = server.getAllProducts();
		
		/*for(int x=0;x<productList.length;x++)
		{
			System.out.println(productList[x].getName());
		}*/
		
		//creating an object of Product and calling the method provided by the server class.
		Product product = server.searchById(5);
		System.out.println("SEARCH BY ID: " + product.getName());
		System.out.println();
		
		//inserting new product
		product.setId(10);
		product.setCategory("Tools");
		product.setName("Drill#3");
		product.setUnitPrice(59.99);
		
		server.insertProduct(product);
		
		Product[] productList2 = server.getAllProducts();
		for(int x=0;x<productList2.length;x++)
		{
			System.out.println(productList2[x].getName());
		}
		
		//SECOND WAY: Using the wsimport feature to gain access to generated classes from the webservice
		/*ProductCatalogInterface productService = new ProductCatalogServiceImplService().getProductCatalogServiceImplPort();
		List<Product> productList = productService.getAllProducts();
		
		Product product = productService.searchById(5);
		System.out.println("SEARCH BY ID: " + product.getName());
		
		product.setId(10);
		product.setCategory("Tools");
		product.setName("Drill#3");
		product.setUnitPrice(59.99);
		
		productService.insertProduct(product);
		productList = productService.getAllProducts();
		
		for(int x=0;x<productList.size();x++)
		{
			System.out.println(productList.get(x).getName());
		}*/
	}

}
