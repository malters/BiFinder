package combi.bifinder;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class BiFinderOperadoresActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bi_finder_operadores);
        
        List<String> listprovincias = new ArrayList<String>();
        List<String> listcertificacion = new ArrayList<String>();
        List<String> listsectores = new ArrayList<String>();
        List<String> listoperadores = new ArrayList<String>();
        
        Spinner provincias = (Spinner) findViewById(R.id.spprovincia);
        Spinner sectores = (Spinner) findViewById(R.id.spsector);
        Spinner certificaciones = (Spinner) findViewById(R.id.spinner1);
        Spinner operadores = (Spinner) findViewById(R.id.spinner2);
        
        Document xmlProvincias=getXmlFromUrl("http://dl.dropbox.com/u/40992884/comobs.xml");
         
        // Obtenemos el elemento raiz del documento
        Element raiz = xmlProvincias.getDocumentElement();
 
        NodeList items = raiz.getElementsByTagName("provincia");
        // Recorremos todos los elementos obtenidos
        for( int i = 0; i < items.getLength(); i++ ) {
        	 String nodo = items.item(i).getTextContent();
        	 listprovincias.add(nodo);
        }
        
        NodeList items2 = raiz.getElementsByTagName("certificacion");
        // Recorremos todos los elementos obtenidos
        for( int i = 0; i < items2.getLength(); i++ ) {
        	 String nodo = items2.item(i).getTextContent();
        	 listcertificacion.add(nodo);
        }
        
        NodeList items3 = raiz.getElementsByTagName("sector");
        // Recorremos todos los elementos obtenidos
        for( int i = 0; i < items3.getLength(); i++ ) {
        	 String nodo = items3.item(i).getTextContent();
        	 listsectores.add(nodo);
        }
        
        NodeList items4 = raiz.getElementsByTagName("operador");
        // Recorremos todos los elementos obtenidos
        for( int i = 0; i < items4.getLength(); i++ ) {
        	 String nodo = items4.item(i).getTextContent();
        	 listoperadores.add(nodo);
        }
        
      //Creamos el adaptador de provincias
       ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listprovincias);
       dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       provincias.setAdapter(dataAdapter);
       
       //Creamos el adaptador de sectores
       ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listsectores);
       dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       sectores.setAdapter(dataAdapter2);
       
       //Creamos el adaptador de certificaciones
       ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listcertificacion);
       dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       certificaciones.setAdapter(dataAdapter3);
       
       //Creamos el adaptador de provincias
       ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listoperadores);
       dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       operadores.setAdapter(dataAdapter4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_bi_finder_main, menu);
        return true;
    }
    
    public Document getXmlFromUrl(String url) {
    	Document doc =null;
    	
    	try {
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder = factory.newDocumentBuilder();
    	
    	// Obtenemos el fichero
        HttpGet httpGet = new HttpGet(url);
        HttpClient httpCliente = new DefaultHttpClient();
        HttpResponse response = httpCliente.execute(httpGet);
        
        InputStream is = response.getEntity().getContent();
        
        doc = builder.parse(is);
        
    	}catch (Exception e) {
        	// TODO: handle exception
        	  System.out.println("error: "+e.getMessage());
        }

        
        return doc;
    }
    
    public Document getDomElement(String xml){
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
 
            DocumentBuilder db = dbf.newDocumentBuilder();
 
            InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(xml));
                doc = db.parse(is); 
 
            } catch (ParserConfigurationException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            } catch (SAXException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            } catch (IOException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            }
                // return DOM
            return doc;
    }
}
