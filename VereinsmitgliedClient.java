import Verein.*;
import Verein.VereinsMitgliedPackage.verein;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

import java.util.Properties;

public class VereinsmitgliedClient {

    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialPort", "1050");
            ORB orb = ORB.init(args, props);
            NamingContext ctx = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

            // Vereines eines Mitglieds
            System.out.println("~~ Vereine eines Mitglieds ~~");
            System.out.println("Vereine von Detlefsen:");
            printVereine(ctx, "Hamburg", "Hansasport", "Detlefsen");
            System.out.println("Vereine von Petersen:");
            printVereine(ctx, "Hamburg", "Hansakultur", "Petersen");
            System.out.println("____________________\n");

            // Beitragserhöhung
            System.out.println("~~ Beitragserhöhung ~~");
            printVereine(ctx, "Bremen", "Werdertheater", "Volkersen");
            erhoeheBeitrag(ctx, (short) 100, "Bremen", "Werdertheater", "Volkersen");
            System.out.println("Nach Beitragserhöhung:");
            printVereine(ctx, "Bremen", "Werdertheater", "Volkersen");
            System.out.println("____________________\n");

            // Alle Mitglieder eines Vereins
            System.out.println("~~ Alle Mitglieder eines Vereins ~~");
            printVereinsmitglieder(ctx, orb, "Hamburg", "Hansakultur");


        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            e.printStackTrace();
        }
    }

    private static verein[] printVereine(NamingContext context, String stadt, String verein, String mname) throws CannotProceed, InvalidName, NotFound {

        NameComponent[] name = {new NameComponent(stadt, "stadt"),
                                new NameComponent(verein, "verein"),
                                new NameComponent(mname, "mitglied")};
        org.omg.CORBA.Object obj = context.resolve(name);
        VereinsMitglied vmRef = VereinsMitgliedHelper.narrow(obj);
        verein[] vereine = vmRef.mvereine();
        for (verein v: vereine) {
            System.out.println(v.vname + " @ " + v.vbeitrag);
        }
        return vmRef.mvereine();
    }

    private static short erhoeheBeitrag(NamingContext context, short erhoehung, String stadt, String verein, String mname) throws CannotProceed, InvalidName, NotFound {

        NameComponent[] name = {new NameComponent(stadt, "stadt"),
                                new NameComponent(verein, "verein"),
                                new NameComponent(mname, "mitglied")};
        org.omg.CORBA.Object obj = context.resolve(name);
        VereinsMitglied vmRef = VereinsMitgliedHelper.narrow(obj);
        return vmRef.erhoeheBeitrag(verein, erhoehung);
    }

    private static void printVereinsmitglieder(NamingContext context, ORB orb, String stadt, String verein) throws CannotProceed, InvalidName, NotFound {

        NameComponent[] name = {new NameComponent(stadt, "stadt"),
                                new NameComponent(verein, "verein")};
        org.omg.CORBA.Object obj = context.resolve(name);
        NamingContext nc = NamingContextHelper.narrow(obj);


        BindingListHolder bl = new BindingListHolder();
        BindingIteratorHolder blIt= new BindingIteratorHolder();
        nc.list(100, bl, blIt);


        Binding[] bindings = bl.value;
        if (bindings.length == 0) { return; }

        for (int i=0; i < bindings.length; i++) {

            // get the object reference for each binding
            obj = nc.resolve(bindings[i].binding_name);
            String objStr = orb.object_to_string(obj);
            int lastIx = bindings[i].binding_name.length-1;

            // check to see if this is a naming context
            if (bindings[i].binding_type == BindingType.ncontext) {
                System.out.println( "Context: " +
                        bindings[i].binding_name[lastIx].id);
            } else {
                System.out.println("Object: " +
                        bindings[i].binding_name[lastIx].id);
            }
        }
    }

}
