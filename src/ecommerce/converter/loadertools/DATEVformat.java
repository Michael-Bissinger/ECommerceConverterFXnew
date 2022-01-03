package ecommerce.converter.loadertools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class DATEVformat {

    private static void createDATEVFormat(String platform, File filepath_origin, String[][] daten_final) {

        List<Integer> months = LoaderDate.collectMonths(daten_final);

        int aktueller_monat = 0;

        System.out.println("months.size_: " + months.size());
        while (aktueller_monat < months.size()) {


            //https://stackoverflow.com/questions/30073980/java-writing-strings-to-a-csv-file

            try (PrintWriter writer = new PrintWriter(filepath_origin.getParent() + "\\" +
                    "DATEV_Format_" + months.get(aktueller_monat) + ".csv")) {

                //fw = new FileWriter(filepath_origin.getParent() + "\\" +
                //        "DATEV_Format_" + months.get(aktueller_monat) + ".csv");

                StringBuilder sb = new StringBuilder();
                // ******* Kopfzeile/Header *******
                //"EXTF";700;21;"Buchungsstapel";12;20210130140440439;;"RK";"";"";29098;55003;20210101;4;20210101;20210831;"Buchungsstapel";"WD";1;0;0;"EUR";;"";;;"03";;;"";""

                sb.append("\"EXTF\"");          //"EXTF"
                sb.append(';');
                sb.append("700");               //700
                sb.append(';');
                sb.append("21");                //21
                sb.append(';');
                sb.append("\"Buchungsstapel\"");//"Buchungsstapel"
                sb.append(';');
                sb.append("12");                //12
                sb.append(';');
                sb.append("20210130140440439"); //20210130140440439
                sb.append(';');
                sb.append(';');
                sb.append("\"RK\"");            //"RK"
                sb.append(';');
                sb.append("\"\"");              //""
                sb.append(';');
                sb.append("\"\"");              //""
                sb.append(';');
                sb.append("29098");             //29098
                sb.append(';');
                sb.append("55003");             //55003
                sb.append(';');
                sb.append("20210101");          //20210101
                sb.append(';');
                sb.append("4");                 //4
                sb.append(';');
                sb.append("20210101");          //20210101
                sb.append(';');
                sb.append("20210831");          //20210831
                sb.append(';');
                sb.append("\"Buchungsstapel\"");//"Buchungsstapel"
                sb.append(';');
                sb.append("\"WD\"");            //"WD"
                sb.append(';');
                sb.append("1");                 //1
                sb.append(';');
                sb.append("0");                 //0
                sb.append(';');
                sb.append("0");                 //0
                sb.append(';');
                sb.append("\"EUR\"");           //"EUR"
                sb.append(';');
                sb.append(';');
                sb.append("\"\"");              //""
                sb.append(';');
                sb.append(';');
                sb.append(';');
                sb.append("\"03\"");            //"03"
                sb.append(';');
                sb.append(';');
                sb.append(';');
                sb.append("\"\"");              //""
                sb.append(';');
                sb.append("\"\"");              //""

                sb.append('\n');


                // ******* Überschriften für Nutzdaten *******
                //Umsatz (ohne Soll/Haben-Kz);Soll/Haben-Kennzeichen;WKZ Umsatz;Kurs;Basis-Umsatz;WKZ Basis-Umsatz;Konto;Gegenkonto (ohne BU-Schl�ssel);BU-Schl�ssel;Belegdatum;Belegfeld 1;Belegfeld 2;Skonto;Buchungstext;Postensperre;Diverse Adressnummer;Gesch�ftspartnerbank;Sachverhalt;Zinssperre;Beleglink;Beleginfo - Art 1;Beleginfo - Inhalt 1;Beleginfo - Art 2;Beleginfo - Inhalt 2;Beleginfo - Art 3;Beleginfo - Inhalt 3;Beleginfo - Art 4;Beleginfo - Inhalt 4;Beleginfo - Art 5;Beleginfo - Inhalt 5;Beleginfo - Art 6;Beleginfo - Inhalt 6;Beleginfo - Art 7;Beleginfo - Inhalt 7;Beleginfo - Art 8;Beleginfo - Inhalt 8;KOST1 - Kostenstelle;KOST2 - Kostenstelle;Kost-Menge;EU-Land u. UStID (Bestimmung);EU-Steuersatz (Bestimmung);Abw. Versteuerungsart;Sachverhalt L+L;Funktionserg�nzung L+L;BU 49 Hauptfunktionstyp;BU 49 Hauptfunktionsnummer;BU 49 Funktionserg�nzung;Zusatzinformation - Art 1;Zusatzinformation- Inhalt 1;Zusatzinformation - Art 2;Zusatzinformation- Inhalt 2;Zusatzinformation - Art 3;Zusatzinformation- Inhalt 3;Zusatzinformation - Art 4;Zusatzinformation- Inhalt 4;Zusatzinformation - Art 5;Zusatzinformation- Inhalt 5;Zusatzinformation - Art 6;Zusatzinformation- Inhalt 6;Zusatzinformation - Art 7;Zusatzinformation- Inhalt 7;Zusatzinformation - Art 8;Zusatzinformation- Inhalt 8;Zusatzinformation - Art 9;Zusatzinformation- Inhalt 9;Zusatzinformation - Art 10;Zusatzinformation- Inhalt 10;Zusatzinformation - Art 11;Zusatzinformation- Inhalt 11;Zusatzinformation - Art 12;Zusatzinformation- Inhalt 12;Zusatzinformation - Art 13;Zusatzinformation- Inhalt 13;Zusatzinformation - Art 14;Zusatzinformation- Inhalt 14;Zusatzinformation - Art 15;Zusatzinformation- Inhalt 15;Zusatzinformation - Art 16;Zusatzinformation- Inhalt 16;Zusatzinformation - Art 17;Zusatzinformation- Inhalt 17;Zusatzinformation - Art 18;Zusatzinformation- Inhalt 18;Zusatzinformation - Art 19;Zusatzinformation- Inhalt 19;Zusatzinformation - Art 20;Zusatzinformation- Inhalt 20;St�ck;Gewicht;Zahlweise;Forderungsart;Veranlagungsjahr;Zugeordnete F�lligkeit;Skontotyp;Auftragsnummer;Buchungstyp;USt-Schl�ssel (Anzahlungen);EU-Land (Anzahlungen);Sachverhalt L+L (Anzahlungen);EU-Steuersatz (Anzahlungen);Erl�skonto (Anzahlungen);Herkunft-Kz;Buchungs GUID;KOST-Datum;SEPA-Mandatsreferenz;Skontosperre;Gesellschaftername;Beteiligtennummer;Identifikationsnummer;Zeichnernummer;Postensperre bis;Bezeichnung SoBil-Sachverhalt;Kennzeichen SoBil-Buchung;Festschreibung;Leistungsdatum;Datum Zuord. Steuerperiode;F�lligkeit;Generalumkehr (GU);Steuersatz;Land;Abrechnungsreferenz;BVV-Position;EU-Land u. UStID (Ursprung);EU-Steuersatz (Ursprung)
                //sb.append("Umsatz (ohne Soll/Haben-Kz)";;;;;;;;;;;;;Buchungstext;Postensperre;Diverse Adressnummer;Gesch�ftspartnerbank;Sachverhalt;Zinssperre;Beleglink;Beleginfo - Art 1;Beleginfo - Inhalt 1;Beleginfo - Art 2;Beleginfo - Inhalt 2;Beleginfo - Art 3;Beleginfo - Inhalt 3;Beleginfo - Art 4;Beleginfo - Inhalt 4;Beleginfo - Art 5;Beleginfo - Inhalt 5;Beleginfo - Art 6;Beleginfo - Inhalt 6;Beleginfo - Art 7;Beleginfo - Inhalt 7;Beleginfo - Art 8;Beleginfo - Inhalt 8;KOST1 - Kostenstelle;KOST2 - Kostenstelle;Kost-Menge;EU-Land u. UStID (Bestimmung);EU-Steuersatz (Bestimmung);Abw. Versteuerungsart;Sachverhalt L+L;Funktionserg�nzung L+L;BU 49 Hauptfunktionstyp;BU 49 Hauptfunktionsnummer;BU 49 Funktionserg�nzung;Zusatzinformation - Art 1;Zusatzinformation- Inhalt 1;Zusatzinformation - Art 2;Zusatzinformation- Inhalt 2;Zusatzinformation - Art 3;Zusatzinformation- Inhalt 3;Zusatzinformation - Art 4;Zusatzinformation- Inhalt 4;Zusatzinformation - Art 5;Zusatzinformation- Inhalt 5;Zusatzinformation - Art 6;Zusatzinformation- Inhalt 6;Zusatzinformation - Art 7;Zusatzinformation- Inhalt 7;Zusatzinformation - Art 8;Zusatzinformation- Inhalt 8;Zusatzinformation - Art 9;Zusatzinformation- Inhalt 9;Zusatzinformation - Art 10;Zusatzinformation- Inhalt 10;Zusatzinformation - Art 11;Zusatzinformation- Inhalt 11;Zusatzinformation - Art 12;Zusatzinformation- Inhalt 12;Zusatzinformation - Art 13;Zusatzinformation- Inhalt 13;Zusatzinformation - Art 14;Zusatzinformation- Inhalt 14;Zusatzinformation - Art 15;Zusatzinformation- Inhalt 15;Zusatzinformation - Art 16;Zusatzinformation- Inhalt 16;Zusatzinformation - Art 17;Zusatzinformation- Inhalt 17;Zusatzinformation - Art 18;Zusatzinformation- Inhalt 18;Zusatzinformation - Art 19;Zusatzinformation- Inhalt 19;Zusatzinformation - Art 20;Zusatzinformation- Inhalt 20;St�ck;Gewicht;Zahlweise;Forderungsart;Veranlagungsjahr;Zugeordnete F�lligkeit;Skontotyp;Auftragsnummer;Buchungstyp;USt-Schl�ssel (Anzahlungen);EU-Land (Anzahlungen);Sachverhalt L+L (Anzahlungen);EU-Steuersatz (Anzahlungen);Erl�skonto (Anzahlungen);Herkunft-Kz;Buchungs GUID;KOST-Datum;SEPA-Mandatsreferenz;Skontosperre;Gesellschaftername;Beteiligtennummer;Identifikationsnummer;Zeichnernummer;Postensperre bis;Bezeichnung SoBil-Sachverhalt;Kennzeichen SoBil-Buchung;Festschreibung;Leistungsdatum;Datum Zuord. Steuerperiode;F�lligkeit;Generalumkehr (GU);Steuersatz;Land;Abrechnungsreferenz;BVV-Position;EU-Land u. UStID (Ursprung);EU-Steuersatz (Ursprung));
                sb.append("Umsatz (ohne Soll/Haben-Kz)");
                sb.append(';');
                sb.append("Soll/Haben-Kennzeichen");
                sb.append(';');
                sb.append("WKZ Umsatz");
                sb.append(';');
                sb.append("Kurs");
                sb.append(';');
                sb.append("Basis-Umsatz");
                sb.append(';');
                sb.append("WKZ Basis-Umsatz");
                sb.append(';');
                sb.append("Konto");
                sb.append(';');
                sb.append("Gegenkonto (ohne BU-Schl�ssel)");
                sb.append(';');
                sb.append("BU-Schl�ssel");
                sb.append(';');
                sb.append("Belegdatum");
                sb.append(';');
                sb.append("Belegfeld 1");
                sb.append(';');
                sb.append("Belegfeld 2");
                sb.append(';');
                sb.append("Skonto");
                sb.append(';');
                sb.append("Buchungstext");
                sb.append(';');
                sb.append("Postensperre");
                sb.append(';');
                sb.append("Diverse Adressnummer");
                sb.append(';');
                sb.append("Gesch�ftspartnerbank");
                sb.append(';');
                sb.append("Sachverhalt");
                sb.append(';');
                sb.append("Zinssperre");
                sb.append(';');
                sb.append("Beleglink");
                sb.append(';');
                for(int i = 1; i < 9; ++i) { // Schreibe 8mal
                    sb.append("Beleginfo - Art " + i);
                    sb.append(';');
                    sb.append("Beleginfo - Inhalt " + i);
                    sb.append(';');
                }
                sb.append("KOST1 - Kostenstelle");
                sb.append(';');
                sb.append("KOST2 - Kostenstelle");
                sb.append(';');
                sb.append("Kost-Menge");
                sb.append(';');
                sb.append("EU-Land u. UStID (Bestimmung)");
                sb.append(';');
                sb.append("EU-Steuersatz (Bestimmung)");
                sb.append(';');
                sb.append("Abw. Versteuerungsart");
                sb.append(';');
                sb.append("Sachverhalt L+L");
                sb.append(';');
                sb.append("Funktionserg�nzung L+L");
                sb.append(';');
                sb.append("BU 49 Hauptfunktionstyp");
                sb.append(';');
                sb.append("BU 49 Hauptfunktionsnummer");
                sb.append(';');
                sb.append("BU 49 Funktionserg�nzung");
                sb.append(';');
                for(int i = 1; i < 21; ++i) { // Schreibe 20mal
                    sb.append("Zusatzinformation - Art " + i);
                    sb.append(';');
                    sb.append("Zusatzinformation- Inhalt " + i);
                    sb.append(';');
                }
                sb.append("St�ck");
                sb.append(';');
                sb.append("Gewicht");
                sb.append(';');
                sb.append("Zahlweise");
                sb.append(';');
                sb.append("Forderungsart");
                sb.append(';');
                sb.append("Veranlagungsjahr");
                sb.append(';');
                sb.append("Zugeordnete F�lligkeit");
                sb.append(';');
                sb.append("Skontotyp");
                sb.append(';');
                sb.append("Auftragsnummer");
                sb.append(';');
                sb.append("Buchungstyp");
                sb.append(';');
                sb.append("USt-Schl�ssel (Anzahlungen)");
                sb.append(';');
                sb.append("EU-Land (Anzahlungen)");
                sb.append(';');
                sb.append("Sachverhalt L+L (Anzahlungen)");
                sb.append(';');
                sb.append("EU-Steuersatz (Anzahlungen)");
                sb.append(';');
                sb.append("Erl�skonto (Anzahlungen)");
                sb.append(';');
                sb.append("Herkunft-Kz");
                sb.append(';');
                sb.append("Buchungs GUID");
                sb.append(';');
                sb.append("KOST-Datum");
                sb.append(';');
                sb.append("SEPA-Mandatsreferenz");
                sb.append(';');
                sb.append("Skontosperre");
                sb.append(';');
                sb.append("Gesellschaftername");
                sb.append(';');
                sb.append("Beteiligtennummer");
                sb.append(';');
                sb.append("Identifikationsnummer");
                sb.append(';');
                sb.append("Zeichnernummer");
                sb.append(';');
                sb.append("Postensperre bis");
                sb.append(';');
                sb.append("Bezeichnung SoBil-Sachverhalt");
                sb.append(';');
                sb.append("Kennzeichen SoBil-Buchung");
                sb.append(';');
                sb.append("Festschreibung");
                sb.append(';');
                sb.append("Leistungsdatum");
                sb.append(';');
                sb.append("Datum Zuord. Steuerperiode");
                sb.append(';');
                sb.append("F�lligkeit");
                sb.append(';');
                sb.append("Generalumkehr (GU)");
                sb.append(';');
                sb.append("Steuersatz");
                sb.append(';');
                sb.append("Land");
                sb.append(';');
                sb.append("Abrechnungsreferenz");
                sb.append(';');
                sb.append("BVV-Position");
                sb.append(';');
                sb.append("EU-Land u. UStID (Ursprung)");
                sb.append(';');
                sb.append("EU-Steuersatz (Ursprung)");
                sb.append('\n');


                // ******* Nutzdaten *******
                sb.append("id");
                sb.append(';');
                sb.append("Name");
                sb.append('\n');

                sb.append("1");
                sb.append(';');
                sb.append("Prashant Ghimire");
                sb.append('\n');

                writer.write(sb.toString());

                System.out.println("done!");

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }



            aktueller_monat++;
        }

    }

}
