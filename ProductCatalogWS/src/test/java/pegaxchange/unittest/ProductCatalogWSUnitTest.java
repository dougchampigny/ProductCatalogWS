package pegaxchange.unittest;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.Test;

import com.pegaxchange.services.Product;
import com.pegaxchange.services.ProductCatalogInterface;

public class ProductCatalogWSUnitTest 
{
	@Test
	public void ProductCatalogAPITest() 
	{
		//FIRST WAY OF CREATING A WEB SERVICE CLIENT
		URL url = null;
		
		try {
			
			url = new URL("http://localhost:8080/JaxWsSecurityProductCatalogWS/services/hello?wsdl");
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	}
}
