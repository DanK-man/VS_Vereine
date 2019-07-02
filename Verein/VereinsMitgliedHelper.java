package Verein;


/**
* Verein/VereinsMitgliedHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from vmitglied.idl
* Sonntag, 19. Mai 2019 18:59 Uhr MESZ
*/

abstract public class VereinsMitgliedHelper
{
  private static String  _id = "IDL:Verein/VereinsMitglied:1.0";

  public static void insert (org.omg.CORBA.Any a, Verein.VereinsMitglied that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static Verein.VereinsMitglied extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (Verein.VereinsMitgliedHelper.id (), "VereinsMitglied");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static Verein.VereinsMitglied read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_VereinsMitgliedStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, Verein.VereinsMitglied value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static Verein.VereinsMitglied narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Verein.VereinsMitglied)
      return (Verein.VereinsMitglied)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      Verein._VereinsMitgliedStub stub = new Verein._VereinsMitgliedStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static Verein.VereinsMitglied unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Verein.VereinsMitglied)
      return (Verein.VereinsMitglied)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      Verein._VereinsMitgliedStub stub = new Verein._VereinsMitgliedStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}