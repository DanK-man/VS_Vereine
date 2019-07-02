import Verein.*;
import Verein.VereinsMitgliedPackage.verein;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;


public class VereinsmitgliedServer {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContext ncRef = NamingContextHelper.narrow(objRef);

            NamingContext initRef = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

            // STAEDTE
            NameComponent hamburg = new NameComponent("Hamburg", "stadt");
            NameComponent bremen = new NameComponent("Bremen", "stadt");
            NamingContext hamburgRef = initRef.bind_new_context(new NameComponent[]{hamburg});
            NamingContext bremenRef = initRef.bind_new_context(new NameComponent[]{bremen});

            // VEREINE
            NameComponent hansasport = new NameComponent("Hansasport", "verein");
            NameComponent hansakultur = new NameComponent("Hansakultur", "verein");
            NameComponent hansahilfe = new NameComponent("Hansehilfe", "verein");
            NameComponent werdersport = new NameComponent("Werdersport", "verein");
            NameComponent werdertheater = new NameComponent("Werdertheater", "verein");
            NamingContext hansasportRef = hamburgRef.bind_new_context(new NameComponent[]{hansasport});
            NamingContext hansakulturRef = hamburgRef.bind_new_context(new NameComponent[]{hansakultur});
            NamingContext hansahilfeRef = hamburgRef.bind_new_context(new NameComponent[]{hansahilfe});
            NamingContext werdersportRef = bremenRef.bind_new_context(new NameComponent[]{werdersport});
            NamingContext werdertheaterRef = bremenRef.bind_new_context(new NameComponent[]{werdertheater});

            // PERSONEN
                // Arnoldsen
            NameComponent mitgliedsname = new NameComponent("Arnoldsen", "mitglied");
            VereinsmitgliedServant vmref = new VereinsmitgliedServant("Arnoldsen",
                    new verein[]{new verein("Hansasport", (short) 100)});
            orb.connect(vmref);
            hansasportRef.rebind(new NameComponent[]{mitgliedsname}, vmref);

                // Detlefsen
            mitgliedsname = new NameComponent("Detlefsen", "mitglied");
            vmref = new VereinsmitgliedServant("Detlefsen",
                    new verein[]{new verein("Hansasport", (short) 200),
                                 new verein("Hansakultur", (short) 500)});
            orb.connect(vmref);
            hansasportRef.rebind(new NameComponent[]{mitgliedsname}, vmref);
            hansakulturRef.rebind(new NameComponent[]{mitgliedsname}, vmref);

                // Nilssen
            mitgliedsname = new NameComponent("Nilssen", "mitglied");
            vmref = new VereinsmitgliedServant("Nilssen",
                    new verein[]{new verein("Hansakultur", (short) 350)});
            orb.connect(vmref);
            hansakulturRef.rebind(new NameComponent[]{mitgliedsname}, vmref);

                // Petersen
            mitgliedsname = new NameComponent("Petersen", "mitglied");
            vmref = new VereinsmitgliedServant("Petersen",
                    new verein[]{new verein("Hansakultur", (short) 150),
                                 new verein("Hansahilfe", (short) 500),
                                 new verein("Werdersport", (short) 600)});
            orb.connect(vmref);
            hansakulturRef.rebind(new NameComponent[]{mitgliedsname}, vmref);
            hansahilfeRef.rebind(new NameComponent[]{mitgliedsname}, vmref);
            werdersportRef.rebind(new NameComponent[]{mitgliedsname}, vmref);

                // Svenssen
            mitgliedsname = new NameComponent("Svenssen", "mitglied");
            vmref = new VereinsmitgliedServant("Svenssen",
                    new verein[]{new verein("Werdersport", (short) 300),
                                 new verein("Werdertheater", (short) 700)});
            orb.connect(vmref);
            werdersportRef.rebind(new NameComponent[]{mitgliedsname}, vmref);
            werdertheaterRef.rebind(new NameComponent[]{mitgliedsname}, vmref);

            // Volkersen
            mitgliedsname = new NameComponent("Volkersen", "mitglied");
            vmref = new VereinsmitgliedServant("Volkersen",
                    new verein[]{new verein("Werdertheater", (short) 120)});
            orb.connect(vmref);
            werdertheaterRef.rebind(new NameComponent[]{mitgliedsname}, vmref);


            java.lang.Object sync = new java.lang.Object ();
            synchronized (sync) {
                sync.wait();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
}
