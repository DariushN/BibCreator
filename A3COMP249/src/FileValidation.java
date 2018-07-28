//----------------------------------
//Assignment 3
//Question 1 of 1 A3
//Written by Dariush Noorzada ID 40064242
//Due date March 19th
//----------------------------------
import java.io.*;
import java.util.Scanner;
//This is the main core of the program. We read through every line of the latex files, and then we take the relevant
//informations and output it in the correct format to the corresponding output file. If necessary, when there is a
//problem with a latex file, we delete it using the File class.

/**
 * Processes files and outputs bibliography in correct form
 * @author Dariush Noorzada
 * @version 1.1
 */
public class FileValidation {
    /**
     * Counts the number of invalid files
     */
    public static int count=0;

    /**
     * Processes the files for validation and then outputs result in created files
     * @param latexFileNumber Number of the Latex file
     */
    public static void processFilesForValidation(int latexFileNumber) {

        String problem = null;
        String authorIEEE=null;
        String authorACM=null;
        String authorNJ=null;
        String journal=null;
        String title=null;
        String year=null;
        String volume=null;
        String number=null;
        String pages=null;
        String keywords=null;
        String doi=null;
        String ISSN=null;
        String month=null;
        String line;
        PrintWriter pwIEEE=null;
        PrintWriter pwACM=null;
        PrintWriter pwNJ=null;
        int countACM=0;

        try {
            BufferedReader inFile = new BufferedReader(new FileReader("Latex" + latexFileNumber + ".bib"));
            File ACMFile = new File("ACM" + latexFileNumber + ".json");
            File IEEEFile = new File("IEEE" + latexFileNumber + ".json");
            File NJFile = new File("NJ" + latexFileNumber + ".json");
            pwIEEE = new PrintWriter(new FileOutputStream("IEEE" + latexFileNumber + ".json",true));
            pwACM = new PrintWriter(new FileOutputStream("ACM" + latexFileNumber + ".json",true));
            pwNJ = new PrintWriter(new FileOutputStream("NJ" + latexFileNumber + ".json",true));
            while ((line = inFile.readLine()) != null) {
                boolean lineError=true;
                if (line.equals("")) {
                    //
                } else if (line.contains("author={},")) {
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "author";
                    lineError=false;
                    throw new FileInvalidException();
                } else if (line.contains("journal={},")) {
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    lineError=false;
                    problem = "journal";
                    throw new FileInvalidException();
                } else if (line.contains("title={},")) {
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "title";
                    lineError=false;
                    throw new FileInvalidException();
                } else if (line.contains("year={},")) {
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "year";
                    throw new FileInvalidException();
                } else if (line.contains("volume={},")) {
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "volume";
                    lineError=false;
                    throw new FileInvalidException();
                } else if (line.contains("number={},")) {
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "number";
                    lineError=false;
                    throw new FileInvalidException();
                } else if (line.contains("pages={},")) {
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "pages";
                    lineError=false;
                    throw new FileInvalidException();
                } else if (line.contains("keywords={},")) {
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "keywords";
                    lineError=false;
                    throw new FileInvalidException();
                } else if (line.contains("doi={},")) {
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "doi";
                    lineError=false;
                    throw new FileInvalidException();
                } else if (line.contains("ISSN={},")) {
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "ISSN";
                    lineError=false;
                    throw new FileInvalidException();
                } else if (line.contains("month={},")) {
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "month";
                    lineError=false;
                    throw new FileInvalidException();
                } else if (line.contains("author={")&&lineError) {
                    authorIEEE=line.substring(8,line.indexOf("}"));
                    authorACM=line.substring(8,line.indexOf("}"));
                    authorNJ=line.substring(8,line.indexOf("}"));
                    authorIEEE=authorIEEE.replaceAll(" and",",");
                    authorNJ=authorNJ.replaceAll("and","&");
                    if (line.contains("and"))
                        authorACM=authorACM.substring(0,authorACM.indexOf("and")-1)+" et al";
                    else
                        authorACM=authorACM+ "et al";
                    countACM++;
                } else if (line.contains("journal={")&&lineError) {
                    journal=line.substring(9,line.indexOf("}"));
                } else if (line.contains("title={")&&lineError) {
                    title=line.substring(7,line.indexOf("}"));
                } else if (line.contains("year={")&&lineError) {
                    year=line.substring(6,line.indexOf("}"));
                } else if (line.contains("volume={")&&lineError) {
                    volume=line.substring(8,line.indexOf("}"));
                } else if (line.contains("number={")&&lineError) {
                    number=line.substring(8,line.indexOf("}"));
                } else if (line.contains("pages={")&&lineError) {
                    pages=line.substring(7,line.indexOf("}"));
                } else if (line.contains("keywords={")&&lineError) {
                    keywords=line.substring(10,line.indexOf("}"));
                } else if (line.contains("doi={")&&lineError) {
                    doi=line.substring(5,line.indexOf("}"));
                } else if (line.contains("ISSN={")&&lineError) {
                    ISSN=line.substring(6,line.indexOf("}"));
                } else if (line.contains("month={")&&lineError) {
                    month=line.substring(7,line.indexOf("}"));
                    pwIEEE.println(authorIEEE+". "+"\""+title+"\""+", "+journal+", vol. "+volume+", no. "+number+", p. "+pages+", "+month+" "+year+".\n");
                    pwACM.println("["+countACM+"] "+authorACM+". "+title+". "+year+". "+journal+". "+volume+", "+number+" ("+year+")"+", "+pages+". DOI:https://doi.org/"+doi+".\n");
                    pwNJ.println(authorNJ+". "+title+". "+journal+". "+volume+", "+pages+"("+year+")"+".\n");
                }
            }
            inFile.close();
            } catch (FileNotFoundException e) {
            System.out.println("File Latex" + latexFileNumber + ".bib not found! Program shall terminate now.");
            System.exit(0);
        } catch (FileInvalidException e) {
            count++;
            System.out.println("Error: Detected Empty Filed!");
            System.out.println("============================");
            System.out.println("\nProblem detected with file Latex." + latexFileNumber + "bib");
            System.out.println(e.getMessage());
            System.out.println("File is Invalid: Field \"" + problem + "\" is Empty. Processing has stopped at this point. Other empty fields may be present as well!\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }finally {
            pwIEEE.close();
            pwACM.close();
            pwNJ.close();
        }

    }
    //This is the function to read and print the wanted file using BufferedReader.

    /**
     * Scans and prints the desired file for review
     * @param br Buffered Reader
     * @param line String representing line
     * @param kb Keyboard as Scanner
     * @throws FileNotFoundException File was not found
     * @throws IOException IO error
     */
    public static void reviewFile(BufferedReader br, String line, Scanner kb) throws FileNotFoundException,IOException{
        System.out.println("\nPlease enter the name of a file you would like to review");
        String FileReview=kb.next();
        br=new BufferedReader(new FileReader(FileReview));
        while((line=br.readLine())!=null) {
            System.out.println(line);
        }
        System.out.println("Thank you for using BibCreator!");
        System.exit(0);
    }
}