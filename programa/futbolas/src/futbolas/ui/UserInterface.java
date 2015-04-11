/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package futbolas.ui;

/**
 *
 * @author Justas
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import futbolas.sql.WorkSQL;

public class UserInterface {
    public void runUI() {
        WorkSQL db = new WorkSQL();
        BufferedReader bufRead = new BufferedReader(new InputStreamReader(System.in)); 
        int choice = 1;
        
        printChoices();
        
        while (choice != 0) {
            try {
                System.out.print(">");
                choice = Integer.parseInt(bufRead.readLine());
                
                switch (choice) {
                    case  0: break;
                    case  1: surastiKluboZaidejus(bufRead, db);
                             break;
                    case  2: pridetiDarbuotojaIkluba(bufRead, db);
                             break;
                    case  3: pridetiDarbuotoja(bufRead, db);
                             break;
                    case  4: pakeistiDarbuotojoAtlyginima(bufRead, db);
                             break;
                    case  5: istrintiDarbuotoja(bufRead, db);
                             break;
                    case  6: switchWorkerTeams(bufRead, db);
                             break;
                    default: System.out.println("Blogas pasirinkimas");
                             break;
                }
            } catch (IOException e) {
               System.out.println("Klaida skaitant ivesti");
            } catch(NumberFormatException e) {
               System.out.println("Netinkamas ivesties formatas");
            }
        }
        
        db.closeConnection();
    }
    
    private void printChoices() {
        System.out.println("Meniu:");
        System.out.println("[0] - baigti darba");
        System.out.println("[1] - rasti klubo zaidejus");
        System.out.println("[2] - priskirti darbuotoja klubui");
        System.out.println("[3] - pasamdyti nauja darbuotoja");
        System.out.println("[4] - pakeisti darbuotojo atlyginima");
        System.out.println("[5] - panaikinti darbuotoja");
        System.out.println("[6] - sukeisti dvieju darbuotoju komandas");
    }
    
    private void surastiKluboZaidejus(BufferedReader bufRead, WorkSQL db) {
        List<List> result = new LinkedList<List>();
      
        try {
            result = db.queryDb("SELECT * FROM juru0847.Klubas");
            
            System.out.println("Klubas:");
            for (int i = 0; i < result.size(); i++) {
                System.out.println((String) result.get(i).get(0) + " " + 
                        result.get(i).get(1) + " " + result.get(i).get(2));
            }
            
            System.out.println("Iveskite klubo pavadinima");
            
            result = db.queryDb("SELECT Vardas, Pavarde, klubas.pavadinimas FROM juru0847.Klubas, "
                    + "juru0847.Zaidejas WHERE Klubas.ID_Klubo = Zaidejas.ID_Klubo " +
                    "AND Klubas.Pavadinimas LIKE '%" + 
                    bufRead.readLine() + "%'");
            
            if (result.isEmpty()) {
                System.out.println("Tokio klubo nera arba jis neturi zaideju");
            } else {
                System.out.println("Klubo zaidejai:");
                for (int i = 0; i < result.size(); i++) {
                    System.out.print(result.get(i).get(0)+ " ");
                    System.out.print(result.get(i).get(1)+ " ");
                    System.out.println(result.get(i).get(2));
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void pridetiDarbuotojaIkluba(BufferedReader bufRead, WorkSQL db) {
        List<List> result = new LinkedList<List>();
        
        try {
            result = db.queryDb("SELECT * FROM juru0847.Klubas ORDER BY ID_Klubo");
            
            System.out.println("Klubai:");
            for (int i = 0; i < result.size(); i++) {
                System.out.println((String) result.get(i).get(0) + " " + result.get(i).get(1));
            }
            
            result = db.queryDb("SELECT ID_P, Vardas, Pavarde FROM "
                    + "juru0847.Personalas EXCEPT SELECT Personalas.ID_P, Vardas, Pavarde FROM "
                    + "juru0847.Komandu_sudetys, juru0847.Personalas "
                    + "WHERE Personalas.ID_P = Komandu_sudetys.ID_P");
            
            System.out.println("Laisvi darbuotojai:");
            for (int i = 0; i < result.size(); i++) {
                System.out.println((String) result.get(i).get(0) + " " + 
                        result.get(i).get(1) + " " + result.get(i).get(2));
            }
            
            result = db.queryDb("SELECT * FROM juru0847.Komandu_sudetys");
            
            System.out.println("Komandu sudetys (ID_Klubo ID_P):");
            for (int i = 0; i < result.size(); i++) {
                System.out.println((String) result.get(i).get(0) + " " + result.get(i).get(1));
            }
            
            System.out.println("Atitinkamai iveskite klubo numeri ir darbuotojo ID:");
            
            result = db.queryDb("INSERT INTO juru0847.Komandu_sudetys VALUES "
                    + "(" + bufRead.readLine() + ",'" + bufRead.readLine() + "')");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void pridetiDarbuotoja(BufferedReader bufRead, WorkSQL db) {
        System.out.println("Iveskite naujo darbuotojo varda,"
                + " pavarde, gimimo data ir atlyginima");
        
        try {
            db.queryDb("INSERT INTO juru0847.Personalas VALUES "
                    + "(DEFAULT,'" + bufRead.readLine() + "','" + bufRead.readLine() 
                    + "','" + bufRead.readLine() + "','" + bufRead.readLine() + "')");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }        
    }
    
    private void pakeistiDarbuotojoAtlyginima(BufferedReader bufRead, WorkSQL db) {
        List<List> result = new LinkedList<List>();
        
        try {
            result = db.queryDb("SELECT * FROM juru0847.Personalas");
            
            System.out.println("Darbuotojai:");
            for (int i = 0; i < result.size(); i++) {
                System.out.println((String) result.get(i).get(0) + " " + 
                        result.get(i).get(1) + " " + result.get(i).get(2) + 
                        " " + result.get(i).get(3) + " " + result.get(i).get(4));
            }
            
            System.out.println("Iveskite darbuotojo nauja atlyginima ir ID:");
            
            result = db.queryDb("UPDATE juru0847.Personalas SET atlyginimas = " + 
                    bufRead.readLine() + " WHERE ID_P = '" + bufRead.readLine() + "'");
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void istrintiDarbuotoja(BufferedReader bufRead, WorkSQL db) {
        List<List> result = new LinkedList<List>();
        
        try {
            result = db.queryDb("SELECT * FROM juru0847.Personalas");
            
            System.out.println("Darbuotojai:");
            for (int i = 0; i < result.size(); i++) {
                System.out.println((String) result.get(i).get(0) + " " + 
                        result.get(i).get(1) + " " + result.get(i).get(2) + 
                        " " + result.get(i).get(3));
            }
            
            System.out.println("Iveskite darbuotojo ID:");
            
            result = db.queryDb("DELETE FROM juru0847.Personalas WHERE ID_P = '" + 
                    bufRead.readLine() +  "'");
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void switchWorkerTeams(BufferedReader bufRead, WorkSQL db) {
        List<List> result = new LinkedList<List>();
        
        try {
            db.queryDb("BEGIN");
            
            db.queryDb("ALTER TABLE " + db.getDbUsername() + 
                    ".Komandu_sudetys DISABLE TRIGGER darbuotojuSkMax");                       

            db.queryDb("ALTER TABLE " + db.getDbUsername() + 
                    ".Komandu_sudetys DISABLE TRIGGER BiudzetasMax");                       
            
                      
            result = db.queryDb("SELECT * FROM juru0847.Klubas ORDER BY ID_Klubo");
            
            System.out.println("Klubai:");
            for (int i = 0; i < result.size(); i++) {
                System.out.println((String) result.get(i).get(0) + " " + result.get(i).get(1));
            }
            System.out.println();
            
            result = db.queryDb("SELECT * FROM juru0847.Personalas");
            
            System.out.println("Darbuotojai:");
            for (int i = 0; i < result.size(); i++) {
                System.out.println((String) result.get(i).get(0) + " " + 
                        result.get(i).get(1) + " " + result.get(i).get(2) + 
                        " " + result.get(i).get(3));
            }
            System.out.println();
            
            result = db.queryDb("SELECT * FROM juru0847.Komandu_sudetys");
            
            System.out.println("Komandu sudetys (ID_Klubo ID_P):");
            for (int i = 0; i < result.size(); i++) {
                System.out.println((String) result.get(i).get(0) + " " + result.get(i).get(1));
            }
            
            
            System.out.println("Atitinkamai iveskite dvieju darbuotoju ID"
                    + " ir klubu ID (ID_P, ID_Klubo, ID_P, ID_Klubo):");
            
            int pirmasDarb = Integer.parseInt(bufRead.readLine());
            int pirmoKluboID = Integer.parseInt(bufRead.readLine());
            int antrasDarb = Integer.parseInt(bufRead.readLine());
            int antroKluboID = Integer.parseInt(bufRead.readLine());
            
            result = db.queryDb("Select * FROM juru0847.Komandu_sudetys where ID_Klubo = " + pirmoKluboID + " and ID_P = "+ pirmasDarb);
            if (result.isEmpty())
            {
                throw new Exception("tokio darbuotojo nera klube");
            }              
            db.queryDb("Select * FROM juru0847.Komandu_sudetys where ID_Klubo = " + antroKluboID + " and ID_P = "+ antrasDarb);
            if (result.isEmpty())
            {
                throw new Exception("tokio darbuotojo nera klube");
            }
            
            db.queryDb("UPDATE juru0847.Komandu_sudetys SET ID_Klubo = " + 
                    antroKluboID + " WHERE ID_P = '" + pirmasDarb + 
                    "' AND ID_Klubo = " + pirmoKluboID);
            
            db.queryDb("UPDATE juru0847.Komandu_sudetys SET ID_Klubo = " + 
                    pirmoKluboID + " WHERE ID_P = '" + antrasDarb + 
                    "' AND ID_Klubo = " + antroKluboID);
            
            db.queryDb("ALTER TABLE " + db.getDbUsername() + 
                    ".Komandu_sudetys ENABLE TRIGGER darbuotojuSkMax");
            
            db.queryDb("ALTER TABLE " + db.getDbUsername() + 
                    ".Komandu_sudetys ENABLE TRIGGER BiudzetasMax");
                        
            db.queryDb("COMMIT");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            try {
                db.queryDb("ROLLBACK");
            } catch (Exception ex) {
                System.out.println("Error:" + ex.getMessage());
            }
        }
    }
}