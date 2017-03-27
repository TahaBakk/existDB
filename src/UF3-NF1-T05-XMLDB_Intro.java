import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

import java.io.File;

import static org.exist.security.utils.Utils.getOrCreateCollection;

class XMLDBIntro {
    private static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static String driver = "org.exist.xmldb.DatabaseImpl";

    //http://harvardforest.fas.harvard.edu:8080/exist/apps/doc/devguide_xmldb.xml

    public static void main(String args[]) throws XMLDBException,
            ClassNotFoundException, IllegalAccessException, InstantiationException{

        afegirFitxer("Plantes.xml");

    }

    private static void afegirFitxer(String fl) throws XMLDBException, ClassNotFoundException, IllegalAccessException, InstantiationException{

        // inicializando los drivers de la database
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        // crear el manegador
        DatabaseManager.registerDatabase(database);

        // adquirir la col·lecció que volem tractar
        Collection col = DatabaseManager.getCollection(URI+"/db","admin","admin");

        //creem la colleció
        CollectionManagementService mgt = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
        col = mgt.createCollection("tahabakk");

        //afegir el recurs que farem servir
        Resource res = col.createResource("Plantes.xml","XMLResource");
        res.setContent(fl);
        col.storeResource(res);

    }
}
