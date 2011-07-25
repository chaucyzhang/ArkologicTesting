import java.io.*;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Hashtable;

public class ReadWithScanner {


  public static void main(String args[]) throws FileNotFoundException {
	//  fileUrl="C:\\Users\\Administrator\\Desktop\\test.txt";
	try{
		System.out.println("input file url:");
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	fileUrl=br.readLine();
	}
	catch(Exception e){
                e.printStackTrace();
            }
    ReadWithScanner parser = new ReadWithScanner(fileUrl);
	parser.trimAllContect();
   Hashtable resultMap=new Hashtable(); 
	 resultMap=parser.processLineByLine(fileUrl+"_copy");
	 System.out.println(resultMap.toString());
  }

  private final File fFile;
  private static String fileUrl;
  
  /**
   Constructor.
   @param aFileName full name of an existing, readable file.
  */
  public ReadWithScanner(String aFileName){
    fFile = new File(aFileName);  
	
  }
  
  /** Template method that calls {@link #processLine(String)}.  */
  public final Hashtable processLineByLine(String url) throws FileNotFoundException {
    //Note that FileReader is used, not File, since File is not Closeable
    Scanner scanner = new Scanner(new FileReader(url));
	String keys[]=new String[10];
	String values[]=new String[10];
	int iter=0;
	Hashtable<String,Hashtable<String,String>> ht = new Hashtable<String,Hashtable<String,String>>();
    try {
		scanner.nextLine();
      //first use a Scanner to get each line
      if ( scanner.hasNext() ){
		    String line=scanner.nextLine();
		  for(int i=0;i<processKeys(line).length;i++)
		  {  
			  keys[i]=processKeys(line)[i];
      }
	  }
	  while ( scanner.hasNextLine()){
Hashtable<String,String> map = new Hashtable<String,String>();
		  String secline=scanner.nextLine();
		  for (int i=0;i<processLines(secline).length ;i++ )
		  {
			  
			  values[i]= processLines(secline)[i];
		
			   int col=i%10;
			   switch (col)
			   {
			   case 0:map.put(keys[0],values[i]);
			   case 1:map.put(keys[1],values[i]);
			   case 2:map.put(keys[2],values[i]);
			   case 3:map.put(keys[3],values[i]);
			   case 4:map.put(keys[4],values[i]);
			   case 5:map.put(keys[5],values[i]);
			   case 6:map.put(keys[6],values[i]);
			   case 7:map.put(keys[7],values[i]);
			   case 8:map.put(keys[8],values[i]);
			   case 9:map.put(keys[9],values[i]);

			   }
			
		  }
		  ht.put("device "+iter,map);
    iter++;
      }

	return ht;
    }
    finally {

      scanner.close();
    }
  }
  

  protected String[] processKeys(String aLine){
    //use a second Scanner to parse the content of each line 
    Scanner scanner = new Scanner(aLine);
    scanner.useDelimiter(" ");
	String keys[]=new String[10];
	int i=0;
    while ( scanner.hasNext()&&i<10 ){
           String key=scanner.next();
		   keys[i]=key.trim();

	  i++;
    }
	return keys;

     // log("Empty or invalid line. Unable to process.");

    //no need to call scanner.close(), since the source is a String
  }

    protected String[] processLines(String aLine){
    //use a second Scanner to parse the content of each line 
    Scanner scanner = new Scanner(aLine);
    scanner.useDelimiter(" ");
	String values[]=new String[10];
	int i=0;
    while ( scanner.hasNext()&&i<10 ){
           String value=scanner.next();
		    values[i]=value.trim();

	  i++;
	 
    }
 return values;

  }
  protected void trimAllContect() throws FileNotFoundException
  {
    Scanner scn=new Scanner(new FileReader(fFile));
	try{
		 FileWriter fw = new FileWriter(fileUrl+"_copy");
   PrintWriter fout = new PrintWriter(fw);
  
	while (scn.hasNextLine())
	{
		String s=scn.nextLine();
        String lineStr=trimLine(s);

		 fout.println(lineStr);
		
		
 
	}
	 fout.close();
   fw.close();
	 
	}
	catch (IOException e) {
   e.printStackTrace();
}
	finally{
	scn.close();
	}
  }
   protected String trimLine(String aLine){
    Scanner scanner = new Scanner(aLine);
	StringBuffer sbf = new StringBuffer();
	sbf.append(scanner.next());
    while ( scanner.hasNext() ){
             sbf.append(" "+scanner.next());
      
    }

	return sbf.toString();

  }
  

  
  private static void log(Object aObject){
    System.out.println(String.valueOf(aObject));
  }
  
  private String quote(String aText){
    String QUOTE = "'";
    return QUOTE + aText + QUOTE;
  }
} 