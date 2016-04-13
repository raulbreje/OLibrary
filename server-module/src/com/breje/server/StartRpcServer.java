package com.breje.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.breje.network.utils.AbstractServer;
import com.breje.network.utils.LibraryRpcConcurrentServer;
import com.breje.network.utils.ServerException;
import com.breje.services.ILibraryServer;
import com.breje.services.impl.LibraryServerImpl;

public class StartRpcServer {
	public static void main(String[] args) {
		try {
			Properties serverProps = new Properties(System.getProperties());

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream inputStream = new FileInputStream("./resources/config.xml");
			Document document = builder.parse(inputStream);
			document.getDocumentElement().normalize();
			NodeList nodeList = document.getElementsByTagName("config");
			Node node = nodeList.item(0);
			Element element = (Element) node;
			NodeList childNodes = element.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element childElement = (Element) childNodes.item(i);
					String tagName = childElement.getTagName();
					String tagValue = childElement.getChildNodes().item(0).getTextContent();
					serverProps.setProperty(tagName, tagValue);
				}
			}

			System.setProperties(serverProps);
		} catch (ParserConfigurationException | IOException | SAXException e) {
			e.printStackTrace();
		}

		ILibraryServer libraryServer = new LibraryServerImpl();
		AbstractServer server = new LibraryRpcConcurrentServer(55555, libraryServer);
		try {
			server.start();
		} catch (ServerException e) {
			System.out.println(e.getMessage());
		}
	}
}
