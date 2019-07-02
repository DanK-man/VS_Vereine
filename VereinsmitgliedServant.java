import Verein.VereinsMitgliedPackage.verein;
import Verein._VereinsMitgliedImplBase;

import java.util.Arrays;

public class VereinsmitgliedServant extends _VereinsMitgliedImplBase{

    private final String mname;
    private verein[] vereine;

    VereinsmitgliedServant() {
        this("Arnoldsen", new verein[]{new verein("Hansasport", (short) 100)});
    }

     VereinsmitgliedServant(String mname, verein[] vereine) {
        super();
        this.mname = mname;
        this.vereine = Arrays.copyOf(vereine, vereine.length);
        System.out.println("Vereinsmitglied erstellt.");
    }

    @Override
    public String mname() {
        return this.mname;
    }

    @Override
    public verein[] mvereine() {
        return Arrays.copyOf(vereine, vereine.length);
    }

    @Override
    public short erhoeheBeitrag(String vereinname, short erhoehung) {
        for (verein v: this.vereine) {
            if (v.vname.equals(vereinname)) {
                v.vbeitrag += erhoehung;
                return v.vbeitrag;
            }
        }
        return -1;
    }

}
